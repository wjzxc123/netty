package com.lut.licon.netty.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/7/21 13:19
 */
public class DistributedAtomicLongExample {
	private static final int QTY = 5;
	private static final String PATH = "/sanguo";

	public static void main(String[] args) {
		final Random rand = new Random();
		ExecutorService service = Executors.newFixedThreadPool(QTY);
		try(TestingServer server = new TestingServer()) {
			CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
			client.start();

			List<DistributedAtomicLong> examples = Lists.newArrayList();

			for (int i = 0; i < QTY; i++) {
				final  DistributedAtomicLong count = new DistributedAtomicLong(client, PATH, new RetryNTimes(10, 10));
				examples.add(count);

				Callable<Void> task = () -> {
					try {
						Thread.sleep(rand.nextInt(1000));
						AtomicValue<Long> value = count.increment();
						//AtomicValue<Long> value = count.decrement();
						//AtomicValue<Long> value = count.add((long)rand.nextInt(20));
						System.out.println("succeed: " + value.succeeded());
						if (value.succeeded())
							System.out.println("Increment: from " + value.preValue() + " to " + value.postValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				};
				service.submit(task);
			}
			examples.forEach(x-> {
				try {
					AtomicValue<Long> longAtomicValue = x.get();
					System.out.println("prevalue:"+longAtomicValue.preValue());
					System.out.println("postvalue:"+longAtomicValue.postValue());
				}catch (Exception e) {
					e.printStackTrace();
				}
			});
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			service.shutdown();
			try {
				service.awaitTermination(10, TimeUnit.MINUTES);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
