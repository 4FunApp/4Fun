package com.mollychin.utils;

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
	public static final String COMPUTER_IP_ADDRESS = "http://192.168.97.2";

	/**
	 * 下载一个的图片
	 * 
	 * @param urlString
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String downloadPicture(String urlString, String fileName) throws IOException {
		String localFile = "f://4FunResource/picture/";
		String projectFile = System.getProperty("user.dir").replace("\\", "//") + "/WebContent/PictureFile/";
		File file = new File(localFile);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png"))) {
			fileName = changeName(fileName) + ".jpg";
		} else {
			String prex = fileName.substring(0, fileName.length() - 4);
			fileName.replace(prex, changeName(prex));
		}
		download(urlString, fileName, localFile, projectFile);

		return COMPUTER_IP_ADDRESS + ":8080/4Fun/PictureFile/" + fileName;
	}

	/**
	 * 下载音乐
	 * 
	 * @param urlString
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String downloadMusic(String urlString, String fileName) throws IOException {
		String localFile = "f://4FunResource/music/";
		String projectFile = System.getProperty("user.dir").replace("\\", "//") + "/WebContent/MusicFile/";
		File file = new File(localFile);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		if (!fileName.endsWith(".mp3")) {
			fileName = changeName(fileName) + ".mp3";
		} else {
			String prex = fileName.substring(0, fileName.length() - 4);
			fileName.replace(prex, changeName(prex));
		}
		download(urlString, fileName, localFile, projectFile);

		return COMPUTER_IP_ADDRESS + ":8080/4Fun/MusicFile/" + fileName;
	}

	/**
	 * 下载诗歌朗诵
	 * 
	 * @param urlString
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String downloadRecitation(String urlString, String filename) throws IOException {
		String localFile = "f://4FunResource/recitation/";
		String projectFile = System.getProperty("user.dir").replace("\\", "//") + "/WebContent/RecitationFile/";
		File file = new File(localFile);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		if (!filename.endsWith(".mp3")) {
			filename = filename + ".mp3";
		}
		download(urlString, filename, localFile, projectFile);

		return COMPUTER_IP_ADDRESS + ":8080/4Fun/RecitationFile/" + filename;
	}

	private static String changeName(String fileName) {
		String file = new BASE64Encoder().encode(fileName.getBytes());

		if (file.length() >= 12) {
			return file.substring(file.length() - 12, file.length() - 1);
		}
		return file;
	}

	private static void download(String urlString, String filename, String localFile, String projectFile)
			throws MalformedURLException, IOException, FileNotFoundException {
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
