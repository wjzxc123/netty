package com.lut.licon.netty.nio;


import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/30 16:30
 */
public class SelectorSocketServerDemo {
	public static void main(String[] args) throws Exception {
		// 声明一个端口号
		InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

		// 开启一个socket连接通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		// 在服务端绑定6666端口监听
		serverSocketChannel.socket().bind(inetSocketAddress);

		// 设置为非阻塞
		serverSocketChannel.configureBlocking(false);

		// 得到Selector对象
		Selector selector = Selector.open();

		// 把 socket通道 注册到 selector，并绑定激活该通道的事件为 OP_ACCEPT
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		// 循环等待客户端连接
		while (true) {
			// 等待一秒，如果返回0则代表没有事件发生
			if (selector.select(1000) == 0) {
				continue;
			}

			// 获取Selector中注册的所有事件
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();

				// OP_ACCEPT事件处理
				if (selectionKey.isAcceptable()) {
					// 给客户端生成一个 SocketChannel
					SocketChannel socketChannel = serverSocketChannel.accept();
					// 设置为非阻塞
					socketChannel.configureBlocking(false);
					// 把客户端通道注册到Selector，并绑定读取事件，关联一个buffer
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					socketChannel.register(selector, SelectionKey.OP_READ, byteBuffer);
				}
				// OP_READ事件处理器
				if (selectionKey.isReadable()){
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					int read = channel.read(byteBuffer);
					System.out.println("from 客户端 " + new String(byteBuffer.array(), StandardCharsets.UTF_8));
					byteBuffer.clear();
				}

				// 手动从集合中移除selectionKey，防止重复操作
				iterator.remove();
			}
		}
	}
}
