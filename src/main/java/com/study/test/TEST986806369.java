package com.study.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class TEST986806369 {
    private static Charset charset = Charset.forName("UTF8");// 创建GBK字符集
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("www.aliyun.com", 80));
        channel.configureBlocking(false);
        String str = "GET / HTTP/1.1 \r\n";
        str+="Host: www.aliyun.com\r\n";
        str+="Sec-Fetch-Mode: navigate\r\n";
//		str+="Sec-Fetch-User: ?1\r\n";
//		str+="Upgrade-Insecure-Requests: 1\r\n";
        str+="SUser-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36\r\n";
        str+="\r\n";
        channel.write(charset.encode(str));
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建1024字节的缓冲
        int size = channel.read(buffer);
        while (size != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println(charset.decode(buffer));
            }
            buffer.clear();
            size = channel.read(buffer);
        }
    }

}
