package com.mollychin.spider;

import static com.mollychin.utils.ConstantsUtil.PIC_ZHIHU_URL;

import java.io.IOException;

import com.mollychin.bean.PicZhihu;
import com.mollychin.utils.DownloadUtil;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.SystemUtil;
import com.mysql.jdbc.Connection;

public class PicZhihuSpider {
	public void picZhihuSpider() {
		final String PICTURE4ZHIHU = "Picture4Zhihu/";
		final String fileName = "pic4Zhihu";
		try {
			PicZhihuSpider picZhihu = new PicZhihuSpider();
			PicZhihu zhihu = SystemUtil.jsonParser(PIC_ZHIHU_URL,
					PicZhihu.class);
			String date = SystemUtil.getDate();
			zhihu.setTime(date);

			// 数据库操作
			Connection conn = (Connection) JDBCUtil.getConnection();
			JDBCUtil.insertData(conn, picZhihu.insertSql(zhihu));
			DownloadUtil
					.downloadPicture(PIC_ZHIHU_URL, fileName, PICTURE4ZHIHU);
			JDBCUtil.closeConnection(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String insertSql(PicZhihu zhihu) {
		return "insert into picturezhihu (text, time, img) values('"
				+ zhihu.getText().replace("'", "\\\'") + "','"
				+ zhihu.getTime().replace("'", "\\\'") + "','"
				+ zhihu.getImg().replace("'", "\\\'") + "');";
	}
}
