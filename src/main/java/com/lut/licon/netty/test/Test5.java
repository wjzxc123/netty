package com.lut.licon.netty.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.util.resources.LocaleData;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/5/6 9:27
 */
public class Test5 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("");


		JSONArray objects = JSONArray.parseArray(sb.toString());
		List<String> list = new ArrayList<>();
		for (Object object : objects) {
			JSONObject jo = (JSONObject )object;
			list.add(jo.getString("sku")+"@"+jo.getString("PROVIDER_GOODS_SKU")+"@"+jo.getString("COUNT")+"@"+jo.getString("TIME"));
		}

		list.forEach(x-> System.out.println(x.trim()));
	}
}
