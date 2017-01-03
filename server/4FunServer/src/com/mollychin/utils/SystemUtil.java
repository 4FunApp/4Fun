package com.mollychin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class SystemUtil {
	/**
	 * 获取当日时间的字符串
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public static String getCurrentYear() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);
	}

	public static <T> T jsonParser(String urlString, Class<T> aClass)
			throws MalformedURLException, IOException {
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			// 设置请求头，反爬虫，模拟browser
			urlcon.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");
			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(is));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			// bs是json
			String json = bs.toString();
			T fromJson = new Gson().fromJson(json, aClass);
			return fromJson;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
