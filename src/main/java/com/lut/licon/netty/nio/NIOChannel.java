package com.lut.licon.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/25 9:06
 */
public class NIOChannel {
	public static void main(String[] args) {
		//write();
		//read();
		//copy();
		copyChannel();
	}

	public static void write(){

		try(FileOutputStream fileOutputStream = new FileOutputStream("a.txt")){

			FileChannel writeChannel = fileOutputStream.getChannel();

			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
			byteBuffer.put(new String("hello zhangsan").getBytes());

			byteBuffer.flip();
			writeChannel.write(byteBuffer);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void read(){
		try(FileInputStream fileInputStream = new FileInputStream("a.txt")){
			FileChannel readChannel = fileInputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
			readChannel.read(byteBuffer);
			System.out.println(new String(byteBuffer.array()));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void copy(){
		try(FileInputStream fileInputStream = new FileInputStream("a.txt");
			FileOutputStream fileOutputStream = new FileOutputStream("b.txt")){
			FileChannel readChannel = fileInputStream.getChannel();
			FileChannel writeChannel = fileOutputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);
			while (true){
				byteBuffer.clear();
				int read = readChannel.read(byteBuffer);
				if (read == -1){
					break;
				}
				byteBuffer.flip();
				writeChannel.write(byteBuffer);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void copyChannel(){
		try (FileInputStream fileInputStream = new FileInputStream("a.txt");
			FileOutputStream fileOutputStream = new FileOutputStream("d.txt")){
			FileChannel readChannel = fileInputStream.getChannel();
			FileChannel writeChannel = fileOutputStream.getChannel();
			//readChannel.transferTo(0,100,writeChannel); c.txt
			writeChannel.transferFrom(readChannel,0,readChannel.size());  //d.txt
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
