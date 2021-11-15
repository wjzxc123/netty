package com.lut.licon.netty.zookeeper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/7/14 10:17
 */
public class CuratorDemo {
	public static void main(String[] args) {
		start1();
	}

	public static void start1(){
		RetryPolicy retryPolicy  = new ExponentialBackoffRetry(10000, 3);
		try(CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy)){
			client.start();
			System.out.println("client info==============================");
			System.out.println(client.getState());
			byte[] bytes = client.getData().inBackground().forPath("/test");
			System.out.println(new String(bytes));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void start2(){
		RetryPolicy retryPolicy  = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
									.connectString("127.0.0.1:2181")
									.sessionTimeoutMs(3000)
									.connectionTimeoutMs(10000)
									.retryPolicy(retryPolicy)
									.build();
		client.start();
		try {
			client.blockUntilConnected();
			System.out.println("client info==============================");
			System.out.println(client.getState());
			byte[] bytes = client.getData().inBackground().forPath("/admin");
			System.out.println(new String(bytes));
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
