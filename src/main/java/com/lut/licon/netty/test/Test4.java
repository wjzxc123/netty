package com.lut.licon.netty.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/5/6 9:27
 */
public class Test4 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("[{\"ID\":149,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210423/7f8962b0-52b4-4b2b-8de0-9e7fa253e7d5.jpg\"},{\"ID\":152,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210426/0516786c-9b3a-417e-afa9-4824e620277d.png\"},{\"ID\":153,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210426/3daa93e5-f74a-4631-a65b-c066d4306932.jpg\"},{\"ID\":155,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210426/dbad32b5-1274-4d86-aaa1-5e6012b76889.png\"},{\"ID\":157,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210426/c96c089f-d822-4e25-a213-f30c26746fd4.jpg\"},{\"ID\":159,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210426/8683769c-332a-4cca-a383-b6c14db9a14b.jpg\"},{\"ID\":161,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210428/ea1869c1-310a-4b61-b0e5-84ffc2512389.jpg\"},{\"ID\":163,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210429/709fb9c9-47c4-4fd4-a770-3a03b6622a1f.png\"},{\"ID\":165,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210429/e6835a88-68e4-4e7a-8a16-47f9f0259832.png\"},{\"ID\":167,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210429/57ecad0c-cbfb-4939-a985-fadf7a596fe9.png\"},{\"ID\":169,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210429/b08016c9-90fb-4b04-b0e9-7cca0fe49ba1.png\"},{\"ID\":171,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210429/e6febb83-2a58-417f-8574-f8818de85ec4.png\"},{\"ID\":173,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210429/bbb0415b-9672-49df-97a9-bd2a3fab42b8.png\"},{\"ID\":175,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210430/73cd8aa7-884d-4938-a0ca-5fab07d68235.png\"},{\"ID\":177,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210506/8eca5089-c15e-4f6b-9e72-62791993ea59.png\"},{\"ID\":181,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210506/20a4bfa5-fba7-4cac-8950-82807fb3a42b.png\"},{\"ID\":182,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210506/8eebb5ba-35b9-474f-a7e7-0b3def3d9d5a.jpg\"},{\"ID\":184,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/caf02b87-cb05-4a79-8915-4e9bda7eb21c.jpg\"},{\"ID\":185,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/de56ea53-eed9-4e12-84bc-5ebbc5a65031.jpg\"},{\"ID\":186,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/5c384104-d0b0-466e-a112-8aaf895965a4.png\"},{\"ID\":188,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/6d3fa1a8-efc5-4da4-ac8f-ba3b0377ebaa.jpg\"},{\"ID\":189,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/053d5996-57bb-4bb4-a5d6-2aeb4b876c0a.jpg\"},{\"ID\":190,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/65e91e24-62e8-47e5-a4b8-7c0d6dbcaa20.jpg\"},{\"ID\":192,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/4e322408-a2a4-4caf-8826-ef0b35b23a83.jpg\"},{\"ID\":194,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/29e79e37-0693-4ded-a2fd-692b95947890.jpg\"},{\"ID\":195,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/95fe95e3-dea1-4f4a-a4ab-4f7cba621426.jpg\"},{\"ID\":198,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210507/c498a287-eedd-4004-8056-e809a3deb196.jpg\"},{\"ID\":200,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210508/7734db0a-0ae2-4244-87d3-fa19f1bf8ea5.jpg\"},{\"ID\":202,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210508/b29b4f13-322a-4b11-955a-6aca110f3af4.jpg\"},{\"ID\":203,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210508/106dfa60-56a0-46aa-9a5b-c958f48577da.jpg\"},{\"ID\":205,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210508/8c3a0d39-81a8-4efd-903d-5c730ebaa1b7.jpg\"},{\"ID\":207,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210508/4c99a8de-1c96-476e-b695-96c14e7d7c5f.jpg\"},{\"ID\":209,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210510/01b8607a-f889-42b3-b96f-32a3ea2a04e7.jpg\"},{\"ID\":211,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210510/95504384-66cd-4194-9b13-f3161b0cea5c.PNG\"},{\"ID\":214,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210510/477f96ad-b8bb-48af-826f-422dc1837ad8.png\"},{\"ID\":216,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210511/459f4671-2c67-44a4-af08-aee444b671f2.jpg\"},{\"ID\":218,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210512/c6bfe6eb-76dc-4ff9-a209-9576240badb6.png\"},{\"ID\":219,\"attachmentPath\":\"http://p003-gp17ipm.group.cpic.com:82///txd/20210512/c6183943-d989-4e09-ab16-28ec4fb5de52.png\"}]");

		JSONArray objects = JSONArray.parseArray(sb.toString());
		List<String> list = new ArrayList<>();
		for (Object object : objects) {
			JSONObject jo = (JSONObject )object;
			list.add("update goods_require_attachement set attachmentPath ='"+jo.getString("attachmentPath").replace("http://p003-gp17ipm.group.cpic.com:82/","")+"'"+" where id = '"+jo.getString("ID")+"';");
		}

		list.forEach(x-> System.out.println(x.trim()));
	}
}
