package com.lut.licon.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/29 16:50
 */
public class NIODemo {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(12345);

		// 绑定端口到socket并启动
		serverSocketChannel.socket().bind(inetSocketAddress);

		// 创建buffer数组
		ByteBuffer[] byteBuffers = new ByteBuffer[2];
		byteBuffers[0] = ByteBuffer.allocate(3);
		byteBuffers[1] = ByteBuffer.allocate(5);

		SocketChannel socketChannel = serverSocketChannel.accept();
		int messageLength = 8;
		while (true)
		{
			int byteRead = 0;

			while (byteRead < messageLength)
			{
				long read = socketChannel.read(byteBuffers);
				byteRead += read;
				System.out.println("byteRead=" + byteRead);
				Stream<ByteBuffer> stream = Arrays.stream(byteBuffers);
				Stream<String> stringStream = stream.map(byteBuffer ->
						String.format("position=%s, limit=%s", byteBuffer.position(), byteBuffer.limit()));
				stringStream.forEach(System.out::println);

				// 所有buffer切换读写模式
				Arrays.stream(byteBuffers).forEach(ByteBuffer::flip);

				// 将数据读出现实到客户端
				long byteWrite = 0;
				while (byteWrite < messageLength)
				{
					long write = socketChannel.write(byteBuffers);
					byteWrite += write;
				}

				// 重置标志位
				Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);

				String str = String.format("byteRead=%s, byteWrite=%s, messageLight=%s",
						byteRead, byteWrite, messageLength);
				System.out.println(str);
			}
		}
	}
}
