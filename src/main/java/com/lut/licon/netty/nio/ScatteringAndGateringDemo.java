package com.lut.licon.netty.nio;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/29 16:08
 */
public class ScatteringAndGateringDemo {
	public static void main(String[] args) {
		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			InetSocketAddress inetSocketAddress = new InetSocketAddress(6379);
			serverSocketChannel.socket().bind(inetSocketAddress);
			//设置成非阻塞
			serverSocketChannel.configureBlocking(false);

			//创建一个选择器
			Selector selector = Selector.open();
			//需要将ServerSocketChannel也注册的到选择器中，且注册事件为 SelectionKey.OP_ACCEPT
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			//轮训注册的通道，看哪个通道有事件
			while (true){
				//阻塞1s，如果没有连接，则继续轮序
				if (selector.select(1000) == 0){
					continue;
				}
				//select>0表示有连接进入
				//获取所有注册进来的通道，进行遍历，然后对响应事件进行处理
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
				while (keyIterator.hasNext()){
					SelectionKey key = keyIterator.next();
					//判断事件，如果是接收事件
					if (key.isAcceptable()){
						SocketChannel channel = serverSocketChannel.accept();
						channel.configureBlocking(false);
						//读取到新的连接通道，注册到Selector中
						ByteBuffer[] byteBuffers = new ByteBuffer[2];
						byteBuffers[0] = ByteBuffer.allocate(2);
						byteBuffers[1] = ByteBuffer.allocate(4);
						channel.register(selector,SelectionKey.OP_READ,byteBuffers);
					}else if (key.isReadable()){
						//处理可读事件
						if (SocketChannel.class.isAssignableFrom(key.channel().getClass())){
							SocketChannel channel = (SocketChannel) key.channel();
							//获取注册时添加的buffer
							ByteBuffer[] byteBuffers = (ByteBuffer[])key.attachment();
							//将数据写入buffer
							while (true){
								long read = channel.read(byteBuffers);
								if (read == -1){
									break;
								}
								//Arrays.asList(byteBuffers).forEach(Buffer::flip);
								Arrays.stream(byteBuffers).map(x->new String(x.array()).substring(0,x.position()))
										.forEach(System.out::print);
								Arrays.stream(byteBuffers).forEach(Buffer::clear);
							}
						}
					}
					//处理完成后移除当前key防止重复处理
					keyIterator.remove();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
