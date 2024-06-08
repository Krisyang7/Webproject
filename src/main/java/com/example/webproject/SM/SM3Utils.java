package com.example.webproject.SM;

// 引用Bouncy Castle库
import org.bouncycastle.crypto.digests.SM3Digest;

public class SM3Utils {
    public static String encrypt(String message) {
        byte[] bytes = message.getBytes();

        // 创建SM3Digest对象
        SM3Digest digest = new SM3Digest();

        // 更新输入数据
        digest.update(bytes, 0, bytes.length);

        // 计算哈希值
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);

        // 将结果转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
