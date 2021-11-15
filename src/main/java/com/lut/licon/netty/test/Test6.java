package com.lut.licon.netty.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/7/7 17:06
 */
public class Test6 {
	public static void main(String[] args) {
		File baseFile  = new File("C:\\Users\\lvshaowei\\Desktop\\20210706");
		File[] files = baseFile .listFiles();

		for (File file : files) {
			try (FileInputStream in =  new FileInputStream(file)) {
				byte[] buf = new byte[1024];
				int length = 0;
				while((length = in.read(buf)) != -1){
					String str = new String(buf, 0, length);
					if (str.contains("DN20210618164450306")){
						System.out.println(file.getName());
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
