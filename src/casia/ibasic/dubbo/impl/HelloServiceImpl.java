package casia.ibasic.dubbo.impl;

import java.util.Date;

import casia.ibasic.dubbo.service.HelloService;

import com.alibaba.fastjson.JSONObject;

/**
 * 关键词提取组件实现类
 * @author Shixian.Lei
 * @version 1.0 2015年9月15日
 * @since jdk1.7
 */
public class HelloServiceImpl implements HelloService {
	
	@Override
	public JSONObject hello(JSONObject data) {
		if (null != data) {
			data.put("currentDate", new Date());
		}
		return data;
	}

}
