package com.lut.licon.netty.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/24 16:07
 */
public class Dome {
	public static void main(String[] args) {
		String content ="具体原因：您好，如下商品无货:100002318478,100011665739,100008584294,100008567636,100011338879,100008494708,100006116894,100004002009";
		String skuStr = content.substring(content.indexOf("无货") + 3, content.length());
		skuStr = skuStr.replaceAll("\n", "");
		skuStr = skuStr.replaceAll("商品", "");
		skuStr = skuStr.replaceAll(",", "，");
		Map<String, Object> so = Maps.newHashMap();
		String[] skuArray = skuStr.split("，");
		if (skuArray.length != 0 && skuArray[0].length() > 10) {
			skuArray = skuStr.split(",");
		}
		StringBuffer contentString = new StringBuffer();
		contentString.append("下单失败，具体原因：您好，如下商品无货：");
		for (int j = 0; j < skuArray.length; j++) {
			String sku = skuArray[j];
			//so = orderService.queryNameBySku(sku);
			contentString.append("商品").append("<u  style=").append("color:#0066FF")
					.append(" title='").append(so.get("NAME").toString()).append("' >").append(sku)
					.append("</u>;");

		}
	}

	public void testExtends(List<?> list){

	}
}
