package com.lut.licon.netty.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/10/20 13:54
 */
public class Test8 {
	public static void main(String[] args) {
		File baseFile  = new File("C:\\Users\\lvshaowei\\Desktop\\20211020");
		File[] files = baseFile .listFiles();
		Map<String,Integer> map  = new HashMap<>();
		for (File file : files) {
			String name = file.getName();
			String[] s = name.split("_");
			String invoice = s[5].substring(1);
			if (map.containsKey(invoice)){
				map.put(invoice,map.get(invoice)+1);
			}else {
				map.put(invoice,1);
			}
		}
		map.forEach((k,v)->{
			if (v>1){
				System.out.println(k+","+v);
			}
		});

		for (File file : files) {
			if (file.getName().contains("_P14660274") || file.getName().contains("_P26828853")){
				System.out.println(file.getName());
			}
		}
	}
}
