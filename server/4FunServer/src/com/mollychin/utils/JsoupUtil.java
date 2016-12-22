package com.mollychin.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {
	public static Document connect(String url) throws IOException {
		// 十秒超时连接
		return Jsoup.connect(url).timeout(10000).get();
	}
}