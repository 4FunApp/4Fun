package com.mollychin.utils;

import static com.mollychin.utils.ConstantsUtil.COMPUTER_IP_ADDRESS;
import static com.mollychin.utils.ConstantsUtil.LOCAL_MUSIC_PATH;
import static com.mollychin.utils.ConstantsUtil.LOCAL_PICTURE_PATH;
import static com.mollychin.utils.ConstantsUtil.LOCAL_RECITATION_PATH;
import static com.mollychin.utils.ConstantsUtil.MUSIC_PROJECT_PATH;
import static com.mollychin.utils.ConstantsUtil.PICTURE_MOVIE;
import static com.mollychin.utils.ConstantsUtil.PICTURE_MUSIC;
import static com.mollychin.utils.ConstantsUtil.PICTURE_ONE;
import static com.mollychin.utils.ConstantsUtil.PICTURE_PROJECT_PATH;
import static com.mollychin.utils.ConstantsUtil.PICTURE_ZHIHU;
import static com.mollychin.utils.ConstantsUtil.RECITATION_PROJECT_PATH;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sun.misc.BASE64Encoder;

public class DownloadUtil {
	/**
	 * 下载一个的图片
	 * 
	 * @param urlString
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String downloadPicture(String urlString, String fileName,
			String picType) throws IOException {
		File file = new File(LOCAL_PICTURE_PATH);
		String localPicturePath = "";
		String projectPicturePath = "";

		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		if (picType.equals(PICTURE_ONE)) {
			localPicturePath = LOCAL_PICTURE_PATH + PICTURE_ONE;
			projectPicturePath = PICTURE_PROJECT_PATH + PICTURE_ONE;

		} else if (picType.equals(PICTURE_MOVIE)) {
			localPicturePath = LOCAL_PICTURE_PATH + PICTURE_MOVIE;
			projectPicturePath = PICTURE_PROJECT_PATH + PICTURE_MOVIE;

		} else if (picType.equals(PICTURE_ZHIHU)) {
			localPicturePath = LOCAL_PICTURE_PATH + PICTURE_ZHIHU;
			projectPicturePath = PICTURE_PROJECT_PATH + PICTURE_ZHIHU;
		} else if (picType.equals(PICTURE_MUSIC)) {
			localPicturePath = LOCAL_PICTURE_PATH + PICTURE_MUSIC;
			projectPicturePath = PICTURE_PROJECT_PATH + PICTURE_MUSIC;
		}
		if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png"))) {
			fileName = changeName(fileName) + ".jpg";
		} else {
			String prex = fileName.substring(0, fileName.length() - 4);
			fileName.replace(prex, changeName(prex));
		}

		download(urlString, fileName, localPicturePath, projectPicturePath);
		if (picType.equals(PICTURE_ONE)) {
			return COMPUTER_IP_ADDRESS + "/4Fun/PictureFile/" + PICTURE_ONE
					+ fileName;
		} else if (picType.equals(PICTURE_MOVIE)) {
			return COMPUTER_IP_ADDRESS + "/4Fun/PictureFile/" + PICTURE_MOVIE
					+ fileName;
		} else if (picType.equals(PICTURE_ZHIHU)) {
			return COMPUTER_IP_ADDRESS + "/4Fun/PictureFile/" + PICTURE_ZHIHU
					+ fileName;
		} else if (picType.equals(PICTURE_MUSIC)) {
			return COMPUTER_IP_ADDRESS + "/4Fun/PictureFile/" + PICTURE_MUSIC
					+ fileName;
		}

		return projectPicturePath;
	}

	/**
	 * 下载音乐
	 * 
	 * @param urlString
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String downloadMusic(String urlString, String fileName)
			throws IOException {
		String localFile = LOCAL_MUSIC_PATH;
		String projectFile = MUSIC_PROJECT_PATH;
		File file = new File(localFile);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		if (!fileName.endsWith(".mp3")) {
			fileName = fileName + ".mp3";
		}
		download(urlString, fileName, localFile, projectFile);

		return COMPUTER_IP_ADDRESS + "/4Fun/MusicFile/" + fileName;
	}

	/**
	 * 下载诗歌朗诵
	 * 
	 * @param urlString
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String downloadRecitation(String urlString, String filename)
			throws IOException {
		String localFile = LOCAL_RECITATION_PATH;
		String projectFile = RECITATION_PROJECT_PATH;
		File file = new File(localFile);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		if (!filename.endsWith(".mp3")) {
			filename = filename + ".mp3";
		}
		download(urlString, filename, localFile, projectFile);

		return COMPUTER_IP_ADDRESS + "/4Fun/RecitationFile/" + filename;
	}

	/**
	 * 修改文件名
	 * 
	 * @param fileName
	 * @return
	 */
	private static String changeName(String fileName) {
		String file = new BASE64Encoder().encode(fileName.getBytes());

		return file.replace("\n", "").replace("\r", "").replace("\r\n", "")
				.replace(" ", "").replace("|", "").replace("?", "")
				.replace("\\", "").replace("\\\\", "").replace("//", "")
				.replace("=", "");
	}

	private static void download(String urlString, String filename,
			String localFile, String projectFile) throws MalformedURLException,
			IOException, FileNotFoundException {
		URL url = new URL(urlString);
		// open connection
		URLConnection conn = url.openConnection();
		// get InputStream(向程序读进来)
		InputStream is = conn.getInputStream();
		// 1K dateBuffer
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 程序向外写（输出的文件流）
		OutputStream os = new FileOutputStream(localFile + filename);
		OutputStream osPro = new FileOutputStream(projectFile + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
			osPro.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		osPro.close();
		is.close();
	}
}
