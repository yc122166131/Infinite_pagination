package com.atguigu.springmvc.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.springmvc.entity.User;
import com.ibm.core.ConnectionUtil;

@Controller
public class Helloworld {
	
	@ResponseBody
	@RequestMapping("/hello1")
	public List<Map<String,Object>> hello(@RequestBody String content){
		System.out.println(content);
		String[] split = content.split("&");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		Map<String,Object> map3 = new HashMap<String,Object>();
		User user1 = new User(1,"zhangsan");
		User user2 = new User(2,"lisi");
		User user3 = new User(Integer.parseInt((split[0].split("="))[1]),split[1].split("=")[1]);
//		List<User> li = new ArrayList<User>();
//		li.add(user1);
//		li.add(user2);
		map1.put("key", new User(1,"zhangsan"));
		
		map2.put("key", new User(2,"lisi"));
		map3.put("key",new User(Integer.parseInt((split[0].split("="))[1]),split[1].split("=")[1]));
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		return list;
	}
	
	
	/**
	 * ¼òµ¥µÄ·ÖÒ³
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageContent")
	public Map<String,Object> getPageContent(@RequestBody String content){
		System.out.println(content);
		//pageNo=0&pageSize=11
		String[] split = content.split("&");
		List<HashMap<String,Object>> list =  ConnectionUtil.getContents(Integer.parseInt((split[0].split("="))[1]),Integer.parseInt((split[1].split("="))[1]));
		int count = ConnectionUtil.getcount();
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("count", count);
		maps.put("datas", list);
		return maps;
	}
}
