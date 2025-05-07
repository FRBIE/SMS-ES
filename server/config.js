// 服务器配置
module.exports = {
  PORT: 8000,                         // 服务器端口
  DATA_FILE: './data/messages.json',  // 数据文件路径
  API_KEYS: [                         // 有效的API密钥列表
    'test_api_key',                   // 与数据库中消息匹配的API密钥
    '123456',                         // 测试API密钥
    'your_api_key_here'               // 替换为实际API密钥
  ]
};