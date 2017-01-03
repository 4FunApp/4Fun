package com.mollychin.utils;

public class ConstantsUtil {
	// 注册登录状态码
	public static final int LOGIN_PASSWORD_ERROR_CODE = 1025;
	public static final int LOGIN_USER_ERROR_CODE = 1026;
	public static final int REGISTER_USER_REPEAT_ERROR_CODE = 1027;

	public static final int REGISTER_SUCCESS_CODE = 1050;
	public static final int LOGIN_SUCCESS_CODE = 1051;

	// 注册登录状态信息
	public static final String LOGIN_PASSWORD_ERROR_MESSAGE = "密码错误，请重试";
	public static final String LOGIN_USER_ERROR_MESSAGE = "用户名不存在";
	public static final String REGISTER_USER_REPEAT_ERROR_MESSAGE = "用户已注册";

	public static final String REGISTER_SUCCESS_MESSAGE = "注册成功";
	public static final String LOGIN_SUCCESS_MESSAGE = "登录成功";

	// 网址相关
	public static final String AR_DAILY_URL = "http://meiriyiwen.com/random";
	public static final String AR_ONE_ROOT_URL = "http://www.wufafuwu.com/";
	public static final String AR_ONE_PRE_URL = "http://www.wufafuwu.com/a/ONE_wenzhang/list_1_";
	public static final String AR_ONE_SUFF_URL = ".html";
	public static final String MOVIE_URL = "http://movie.mtime.com/classic/";
	public static final String MUSIC_PARENT_URL = "http://fm.baidu.com/dev/api/?tn=playlist&id=public_fengge_qingyinyue&hashcode=091575870fd397f79a49347bec1b6529&_=1481594082582";
	public static final String MUSIC_LINK_URL = "http://play.baidu.com/data/music/songlink?songIds=";
	public static final String MUSIC_INFO_URL = "http://play.baidu.com/data/music/songinfo?songIds=";
	public static final String PIC_WUFAZHUCE_URL = "http://wufazhuce.com/";
	public static final String POEM_BASE_URL = "http://www.zgshige.com";
	public static final String PIC_ZHIHU_URL = "http://news-at.zhihu.com/api/4/start-image/1080*1776";

	// 数据库相关
	public final static String USER = "root";
	public final static String DATABASE_NAME = "4fun_v1";
	public final static String ROOT_URL = "jdbc:mysql://localhost:3306/"
			+ DATABASE_NAME;
	// 密码注意修改
	// ---------------------------------------------------------------------
	public final static String PASSWORD = "123456";

	// 计时器相关
	public final static String BEGIN_TIME = "07:30:00";
	public final static String END_TIME = "07:31:00";
	public final static int CYCLES_TIME = 120 * 1000;

	// 下载相关
	public static final String COMPUTER_IP_ADDRESS = "http://www.icodes.vip";
	public static final String PICTURE_ONE = "Picture4One/";
	public static final String PICTURE_MOVIE = "Picture4Movie/";
	public static final String PICTURE_ZHIHU = "Picture4Zhihu/";
	public static final String PICTURE_MUSIC = "Picture4Music/";
	// 服务器上是 c 盘
	// --------------------------------------------------------------------------------------
	public static final String LOCAL_PATH = "f://4FunResource";
	public static final String LOCAL_PICTURE_PATH = LOCAL_PATH
			+ "/PictureFile/";
	public static final String LOCAL_MUSIC_PATH = LOCAL_PATH + "/musicFile/";
	public static final String LOCAL_RECITATION_PATH = LOCAL_PATH
			+ "/recitationFile/";
	public static final String FILE_PATH = SysContextListener.PROJECT_PATH;
	public static final String PICTURE_PROJECT_PATH = FILE_PATH
			+ "/PictureFile/";
	public static final String MUSIC_PROJECT_PATH = FILE_PATH + "/MusicFile/";
	public static final String RECITATION_PROJECT_PATH = FILE_PATH
			+ "/RecitationFile/";

}
