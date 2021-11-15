package com.lut.licon.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/30 14:03
 */
public class MappedBuffer {
	public static void main(String[] args) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("e.txt", "rw")) {
			FileChannel channel = randomAccessFile.getChannel();
			byte[] bytes = new String("hello world").getBytes();
			MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
			map.put(bytes,0,bytes.length);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
