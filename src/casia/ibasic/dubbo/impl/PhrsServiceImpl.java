package casia.ibasic.dubbo.impl;

import casia.ibasic.dubbo.service.PhrsService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 短语滤组件实现类
 * @author yuqi wang
 * @version 1.0 2019年10月09日
 * @since jdk1.8
 */
public class PhrsServiceImpl implements PhrsService {
	private static final Logger logger = Logger.getLogger(PhrsServiceImpl.class.getName());


	public JSONObject phrs(JSONObject data) {
		JSONObject o = new JSONObject();
		try {
			if (null == data) {
				o.put("Error", "ERROR! Empty Json Object");
				return o;
			} else {
				try {
					String url = "http://192.168.10.121:7000//phraser_news_update";

					HttpPost post = new HttpPost(url);

					List<NameValuePair> urlParameters = new LinkedList<NameValuePair>();
					urlParameters.add(new BasicNameValuePair("title", data.getString("title")));
					urlParameters.add(new BasicNameValuePair("content", data.getString("content")));
					urlParameters.add(new BasicNameValuePair("source", data.getString("source")));

					UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(urlParameters, "utf-8");
					post.setEntity(formEntity);

					HttpClient client = HttpClients.createDefault();
					HttpResponse response = client.execute(post);

					HttpEntity httpEntity = response.getEntity();
					String Result = EntityUtils.toString(httpEntity, "utf-8");
					o.put("phrase",Result);
//					o = JSONObject.parseObject(EntityUtils.toString(httpEntity, "utf-8"));
//					o.put("phrase",EntityUtils.toString(httpEntity, "utf-8"));

				} catch (Exception var1) {
					logger.error("Exception", var1);
					o.put("Error", "ERROR! " + var1.getMessage());
				}

			}
		} catch (Exception var2) {
			logger.error("Exception", var2);
		}
		return o;
	}

//	public static void main(String[] args) {
//		JSONObject obj = new JSONObject();
//		PhrsService service = new PhrsServiceImpl();
////		long start = System.currentTimeMillis();
//		try {
//			long start = System.currentTimeMillis();
//			String title = "快讯｜新力地产荣登2019中国房地产开发企业品牌价值50强" ;
//			String content =" //@废橙一个:恭喜王一博！一直低调努力，博得大众喜爱！《陪你到世界之巅》正在芒果TV热播，《陈情令》正在腾讯视频热播，《天天向上》相约不变，新作《有翡》敬请期待！@UNIQ-王一博";
//			String source="网站";
//			obj.put("title", title);
//			obj.put("content", content);
//			obj.put("source", source);
//			JSONObject result =service.phrs(obj);
//			System.out.println(result);
//			long end = System.currentTimeMillis();
//			System.out.println("Time elapse: " + (end - start) + "ms.");
//		} catch (Exception var10) {
//			var10.printStackTrace();
//		}
//
//		}

	}


