package com.mollychin.utils;

import static com.mollychin.utils.ConstantsUtil.BEGIN_TIME;
import static com.mollychin.utils.ConstantsUtil.END_TIME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import com.mollychin.spider.ArDailySpider;
import com.mollychin.spider.ArOneSpider;
import com.mollychin.spider.MovieSpider;
import com.mollychin.spider.MusicSpider;
import com.mollychin.spider.PicWufaZhuceSpider;
import com.mollychin.spider.PicZhihuSpider;
import com.mollychin.spider.PoemSpider;

public class TimerAction extends TimerTask {
	public final SimpleDateFormat Df = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void run() {
		// 设置日期格式
		String currentDate = Df.format(new Date());
		int touchFlag = compareDate(BEGIN_TIME, currentDate, END_TIME);
		if (touchFlag == 1) {
			ArDailySpider arDailySpider = new ArDailySpider();
			arDailySpider.arDailySpider();
			ArOneSpider arOneSpider = new ArOneSpider();
			arOneSpider.arOneSpider();
			MovieSpider movieSpider = new MovieSpider();
			movieSpider.movieSpider();
			PicZhihuSpider picZhihuSpider = new PicZhihuSpider();
			picZhihuSpider.picZhihuSpider();
			PicWufaZhuceSpider spider = new PicWufaZhuceSpider();
			spider.picWufaZhuceSpider();
			PoemSpider poemSpider = new PoemSpider();
			poemSpider.poemSpider();
			MusicSpider musicSpider = new MusicSpider();
			musicSpider.musicSpider();
			System.out.println("爬取结束");
		}
	}

	public int compareDate(String beginTime, String currentTime, String endTime) {
		int flag = 0;
		Date beginPoint;
		try {
			beginPoint = Df.parse(beginTime);
			Date currentPoint = Df.parse(currentTime);
			Date endPoint = Df.parse(endTime);
			if (endPoint.getTime() > beginPoint.getTime()) {
				if (endPoint.getTime() > currentPoint.getTime()) {
					if (currentPoint.getTime() > beginPoint.getTime()) {
						flag = 1;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
}