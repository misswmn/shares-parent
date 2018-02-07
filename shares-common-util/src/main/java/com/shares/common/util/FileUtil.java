package com.shares.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 文件操作工具类
 * 
 */
public class FileUtil {

	/** 路径分隔符Linux */
	public static final String PATH_SEPERIATOR_LINUX = "/";

	/** 路径分隔符Linux */
	public static final String PATH_SEPERIATOR_WINDOWS = "\\";

	/** 文件后缀名分隔符 */
	public static final String SUFFIX_SPLITOR = ".";

	/**
	 * 将路径名称统一为Linux格式
	 * 
	 * @param path
	 * @return
	 */
	public static String toLinuxPath(String path) {
		return StringUtils.replace(path, PATH_SEPERIATOR_WINDOWS, PATH_SEPERIATOR_LINUX);
	}

	/**
	 * 取得文件后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameSuffix(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return StringUtils.EMPTY;
		}
		int index = StringUtils.lastIndexOf(fileName, SUFFIX_SPLITOR);
		if (index < 0) {
			return StringUtils.EMPTY;
		}
		return StringUtils.substring(fileName, index + 1).toLowerCase();
	}
	
	public static boolean checkAndMakeDir(String pathName){
		File temp = new File(pathName);
		if(temp.exists()){
			return true;
		}else{
			return temp.mkdirs();
		}
	}

	/**
	 * 从流中读取数据
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String getStringFromStream(InputStream inputStream,String contextType) throws IOException {
	    // 读取请求内容
	    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,contextType));
	    String line = null;
	    StringBuilder sb = new StringBuilder();
	    while((line = br.readLine())!=null){
	        sb.append(line);
	    }
		return sb.toString();
	}
	
	/**
	 * 将图片转换为字符串
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public static String convertImgToString (String filePath) throws IOException {
		return Base64.encodeBase64String(IOUtils.toByteArray(new FileInputStream(new File(filePath))));
	}
}
