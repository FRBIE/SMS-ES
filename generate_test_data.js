const crypto = require('crypto');
const fs = require('fs');
const path = require('path');
const { v4: uuidv4 } = require('uuid');

// 与Android应用相同的加密参数
const ALGORITHM = 'aes-256-gcm';
const ITERATION_COUNT = 10000;
const KEY_LENGTH = 32; // 256位 = 32字节
const GCM_TAG_LENGTH = 16; // 128位 = 16字节
const IV_LENGTH = 12; // GCM模式IV通常为12字节

// 用户密码和盐
const password = '123';
// 使用固定的盐，确保Android应用使用相同的盐也能解密
const salt = Buffer.from('0123456789abcdef0123456789abcdef');

// 从密码派生密钥
function deriveKey(password, salt) {
  return crypto.pbkdf2Sync(password, salt, ITERATION_COUNT, KEY_LENGTH, 'sha256');
}

// 加密函数
function encrypt(text, key) {
  const iv = crypto.randomBytes(IV_LENGTH);
  const cipher = crypto.createCipheriv(ALGORITHM, key, iv, { authTagLength: GCM_TAG_LENGTH });
  
  let encrypted = cipher.update(text, 'utf8');
  encrypted = Buffer.concat([encrypted, cipher.final()]);
  
  const authTag = cipher.getAuthTag();
  // 在GCM模式下，我们需要将authTag附加到密文上，与Android端一致
  const encryptedWithTag = Buffer.concat([encrypted, authTag]);
  
  return {
    encryptedContent: encryptedWithTag.toString('base64'),
    iv: iv.toString('base64')
  };
}

// 生成测试数据
function generateTestMessages() {
  const key = deriveKey(password, salt);
  
  // 创建两条测试短信
  const messages = [
    {
      id: uuidv4(),
      sender: "+8613800138000",
      timestamp: Date.now() - 86400000, // 1天前
      deviceId: "test-device-id-123", 
      apiKey: "test_api_key",
      createdAt: Date.now() - 86400000
    },
    {
      id: uuidv4(),
      sender: "+8615900001234",
      timestamp: Date.now() - 3600000, // 1小时前
      deviceId: "test-device-id-456",
      apiKey: "test_api_key", 
      createdAt: Date.now() - 3600000
    }
  ];
  
  // 加密短信内容
  messages[0] = {
    ...messages[0],
    ...encrypt("这是一条测试短信，您的验证码是123456，请勿告诉他人。", key)
  };
  
  messages[1] = { 
    ...messages[1],
    ...encrypt("【银行通知】您的账户余额变动-88.00元，当前余额12345.67元。", key)
  };
  
  return messages;
}

// 将生成的测试数据写入文件
const messages = generateTestMessages();
fs.writeFileSync(path.join(__dirname, 'server', 'data', 'messages.json'), 
                 JSON.stringify(messages, null, 2));

console.log('已生成与密码"123"匹配的测试数据，保存到 server/data/messages.json');
console.log(`生成了 ${messages.length} 条加密短信。`);