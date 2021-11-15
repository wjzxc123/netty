package com.lut.licon.netty.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/7/12 15:23
 */
public class ZkCliTest {
	private final static String connectString = "localhost:2181";
	private static int sessionTimeout = 2000;
	private static ZooKeeper zkClient = null;
	public static void init() throws Exception{
		zkClient = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
			// 收到事件通知后的回调函数（用户的业务逻辑）
			System.out.println( watchedEvent.getType() + "----" + watchedEvent.getPath());
			//再次启动监听
			try{
				List<String> children = zkClient.getChildren("/",true);
				for (String child : children) {
					System.out.println(child);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		});
	}

	public static void main(String[] args) {
		try {
			init();
			List<String> children = zkClient.getChildren("/", true);
			System.out.println("输出");
			children.forEach(x-> System.out.println(x));
			// 延时阻塞
			Thread.sleep(Long.MAX_VALUE);
			String nodeCreated = zkClient.create("/atguigu ","shanghai".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
