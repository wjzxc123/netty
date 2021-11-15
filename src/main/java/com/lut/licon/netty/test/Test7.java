package com.lut.licon.netty.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/15 22:36
 */
public class Test7 {
	public static void main(String[] args) {
		StringBuilder sql = new StringBuilder("select SENDORDER_NO,STATE from eshop_sendorder where SENDORDER_NO in (");
		try(FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\lvshaowei\\Desktop\\tst.txt"));
			BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(fileInputStream))){
			String line = null;
			while ((line = bufferedReader.readLine()) != null){
				sql.append("'");
				sql.append(line);
				sql.append("',");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(sql);
	}
}
