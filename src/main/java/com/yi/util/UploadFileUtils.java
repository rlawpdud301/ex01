package com.yi.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	public static String uploadFile(String uploadPath,String originalname,byte[] fileData) throws IOException {
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalname;
		File target = new File(uploadPath + clacPath(uploadPath) +"/" + savedName);
		FileCopyUtils.copy(fileData, target);
		
		
		
		//썸네일이미지
		String thumPath = makeThumbnail(uploadPath, clacPath(uploadPath), savedName);
		
		
		
		
		
		return thumPath;
	}
	
	private static String makeThumbnail(String uploadPath, //c:zzz/upload
										String path, //2019/02/13
										String fileName) throws IOException {
		
		
		/*원본이미지의 데이터를가지고와서 가상이미지를만듬*/
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path,fileName));
		
		/*세로 100크기에 고정하여 가로넓이는 자동 조절하도록하여 thumbnail데이타를 만듬*/
		BufferedImage desImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);

		/*thumbnail 파일명을 만듬*/
		String thumbnailName = uploadPath + path + "/" + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		/*만들어진 빈 thumbnail 파일에 destImg 데이터를 넣음 */
		ImageIO.write(desImg, formatName.toUpperCase(), newFile);
		return path + "/" + "s_" + fileName;
	}
	
	private static String clacPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = "/" + cal.get(Calendar.YEAR);
		String monthPath = String.format("%s/%02d", yearPath,cal.get(Calendar.MONTH)+1);
		String datePath = String.format("%s/%02d", monthPath, cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath,monthPath,datePath);
		
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String... paths) {
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (dirPath.exists()==false) {
				dirPath.mkdirs();
			}
		}
	}
}
