package com.lut.licon.netty.zookeeper;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/7/21 10:09
 */
public class ShareCounterExample implements SharedCountListener {
	private static final int QTY = 5;
	private static final String PATH = "/sanguo";

	public static void main(String[] args) {
		final Random rand = new Random();
		ShareCounterExample example = new ShareCounterExample();
		ExecutorService service = Executors.newFixedThreadPool(QTY);
		try(TestingServer server = new TestingServer()){
			CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
			client.start();

			SharedCount baseCount = new SharedCount(client, PATH, 0);
			baseCount.addListener(example);
			baseCount.start();

			List<SharedCount> examples = Lists.newArrayList();

			for (int i = 0; i < QTY; i++) {
				final SharedCount count = new SharedCount(client, PATH, 0);
				examples.add(count);
				Callable<Void> task = () -> {
					count.start();
					Thread.sleep(rand.nextInt(10000));
					System.out.println("current count:"+count.getCount());
					System.out.println("Increment:" + count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
					return null;
				};
				service.submit(task);
			}

			service.shutdown();
			service.awaitTermination(10, TimeUnit.MINUTES);

			for (int i = 0; i < QTY; ++i) {
				examples.get(i).close();
			}
			baseCount.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			service.shutdown();
		}
	}
	@Override
	public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
		System.out.println("Counter's value is changed to " + newCount);
	}

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		System.out.println("State changed: " + newState.toString());
	}
}
