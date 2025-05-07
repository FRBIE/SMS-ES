const express = require('express');
const fs = require('fs');
const path = require('path');
const cors = require('cors');
const morgan = require('morgan');
const { v4: uuidv4 } = require('uuid');
const config = require('./config');

// 创建Express应用
const app = express();

// 中间件
app.use(cors()); // 启用CORS
app.use(morgan('dev')); // 日志记录
app.use(express.json()); // 解析JSON请求体

// 验证API密钥的中间件
const verifyApiKey = (req, res, next) => {
  const authHeader = req.headers.authorization;
  
  if (!authHeader) {
    return res.status(401).json({ error: '缺少API密钥' });
  }
  
  // 处理Bearer认证格式
  let apiKey = authHeader;
  if (authHeader.startsWith('Bearer ')) {
    apiKey = authHeader.substring(7); // 移除"Bearer "前缀
  }
  
  // 检查API密钥是否有效
  if (!config.API_KEYS.includes(apiKey)) {
    return res.status(403).json({ error: 'API密钥无效' });
  }
  
  // 将API密钥存储在请求对象中，以便后续使用
  req.apiKey = apiKey;
  next();
};

// 确保数据文件存在
const ensureDataFileExists = () => {
  const dataDir = path.dirname(config.DATA_FILE);
  
  if (!fs.existsSync(dataDir)) {
    fs.mkdirSync(dataDir, { recursive: true });
  }
  
  if (!fs.existsSync(config.DATA_FILE)) {
    fs.writeFileSync(config.DATA_FILE, '[]', 'utf8');
  }
};

// 读取消息数据
const readMessages = () => {
  ensureDataFileExists();
  const data = fs.readFileSync(config.DATA_FILE, 'utf8');
  try {
    return JSON.parse(data);
  } catch (error) {
    console.error('解析数据文件时出错:', error);
    return [];
  }
};

// 保存消息数据
const saveMessages = (messages) => {
  ensureDataFileExists();
  fs.writeFileSync(config.DATA_FILE, JSON.stringify(messages, null, 2), 'utf8');
};

// API端点

// 根路径 - 用于测试服务器是否运行
app.get('/', (req, res) => {
  res.json({ message: 'SMS Encrypt Sync Server is running' });
});

// POST /sms/upload - 上传加密短信
app.post('/sms/upload', verifyApiKey, (req, res) => {
  // 验证请求体
  const { sender, timestamp, encryptedContent, iv, deviceId } = req.body;
  
  if (!sender || !timestamp || !encryptedContent || !iv || !deviceId) {
    return res.status(400).json({ error: '缺少必要字段' });
  }
  
  // 创建新消息对象
  const newMessage = {
    id: uuidv4(), // 生成唯一ID
    sender,
    timestamp,
    encryptedContent,
    iv,
    deviceId, // 存储设备ID
    apiKey: req.apiKey, // 存储API密钥以便区分用户
    createdAt: Date.now() // 服务器接收时间
  };
  
  try {
    // 读取现有消息
    const messages = readMessages();
    
    // 添加新消息
    messages.push(newMessage);
    
    // 保存更新后的消息
    saveMessages(messages);
    
    // 响应成功
    res.status(201).json({
      success: true,
      id: newMessage.id // 使用id作为响应键名以匹配客户端预期
    });
  } catch (error) {
    console.error('保存消息时出错:', error);
    res.status(500).json({ error: '服务器错误' });
  }
});

// GET /sms/download - 下载加密短信
app.get('/sms/download', verifyApiKey, (req, res) => {
  try {
    // 读取所有消息
    const messages = readMessages();
    
    // 筛选当前用户的消息
    let userMessages = messages.filter(msg => msg.apiKey === req.apiKey);
    
    // 检查是否有since_timestamp查询参数
    const sinceTimestamp = req.query.since_timestamp ? parseInt(req.query.since_timestamp) : null;
    
    // 检查是否有device_id查询参数 (仅记录，不用于筛选)
    const deviceId = req.query.device_id;
    
    // 如果提供了since_timestamp参数，仅返回更新的消息
    const filteredMessages = sinceTimestamp
      ? userMessages.filter(msg => msg.timestamp > sinceTimestamp)
      : userMessages;
    
    // 返回筛选后的消息，移除apiKey字段
    const responseMessages = filteredMessages.map(({ apiKey, ...msg }) => msg);
    
    res.json(responseMessages);
    console.log(`返回 ${responseMessages.length} 条消息`);
  } catch (error) {
    console.error('获取消息时出错:', error);
    res.status(500).json({ error: '服务器错误' });
  }
});

// 启动服务器
const port = config.PORT;
app.listen(port, () => {
  console.log(`SMS Encrypt Sync Server 运行在端口 ${port}`);
});