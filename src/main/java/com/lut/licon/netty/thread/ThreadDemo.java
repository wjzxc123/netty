package com.lut.licon.netty.thread;

import java.util.concurrent.TimeUnit;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/25 16:01
 */
public class ThreadDemo {
	public static void main(String[] args) {
		Thread childThread = new Thread(() -> {
			while (true){
				System.out.println("childThread");
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		//守护线程守护用户线程，当用户线程结束后，守护线程也会结束
		childThread.setDaemon(true);
		childThread.start();
		System.out.println("end");
	}
}
