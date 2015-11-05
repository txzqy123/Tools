package com.function.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作共通类
 * @author zqy
 *
 */
public class FileUtil {  
    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);  
    public static Set<String> sets = new HashSet<String>();  
  
  
    /** 
     * 过滤MP3文件 
     *  
     * @param strPath 
     */  
    public static void refreshFileList(String strPath) {  
        File dir = new File(strPath);  
        File[] files = dir.listFiles();  
        if (files == null) {  
            return;  
        }  
        for (int i = 0; i < files.length; i++) {  
            if (files[i].isDirectory()) {  
                refreshFileList(files[i].getAbsolutePath());  
            } else {  
                String strFilePath = files[i].getAbsolutePath().toLowerCase();  
                String strName = files[i].getName();  
                if (strName.endsWith(".mp3")) {  
                    boolean bFlag = sets.add(strName);  
                    if (bFlag == Boolean.FALSE) {  
                        // 删除重复文件  
                        removeFile(strFilePath);  
                    }  
                }  
                // System.out.println("FILE_PATH:" + strFilePath + "|strName:" +  
                // strName);  
            }  
        }  
    }  
  
    /** 
     * 创建文件夹 
     *  
     * @param strFilePath 
     *            文件夹路径 
     */  
    public static boolean mkdirFolder(String strFilePath) {  
        boolean bFlag = false;  
        try {  
            File file = new File(strFilePath.toString());  
            if (!file.exists()) {  
                bFlag = file.mkdir();  
            }  
        } catch (Exception e) {  
            logger.error("新建目录操作出错" + e.getLocalizedMessage());  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    public static boolean createFile(String strFilePath, String strFileContent) {  
        boolean bFlag = false;  
        try {  
            File file = new File(strFilePath.toString());  
            if (!file.exists()) {  
                bFlag = file.createNewFile();  
            }  
            if (bFlag == Boolean.TRUE) {  
                FileWriter fw = new FileWriter(file);  
                PrintWriter pw = new PrintWriter(fw);  
                pw.println(strFileContent.toString());  
                pw.close();
                fw.close();
                logger.error("创建文件" + strFilePath);
            }  
        } catch (Exception e) {  
            logger.error("路径："+ strFilePath +"新建文件操作出错" + e.getLocalizedMessage());  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 删除文件 
     *  
     * @param strFilePath 
     * @return 
     */  
    public static boolean removeFile(String strFilePath) {  
        boolean result = false;  
        if (strFilePath == null || "".equals(strFilePath)) {  
            return result;  
        }  
        File file = new File(strFilePath);  
        if (file.isFile() && file.exists()) {  
            result = file.delete();  
            if (result == Boolean.TRUE) {  
                logger.debug("[REMOE_FILE:" + strFilePath + "删除成功!]");  
            } else {  
                logger.debug("[REMOE_FILE:" + strFilePath + "删除失败]");  
            }  
        }  
        return result;  
    }  
  
    /** 
     * 删除文件夹(包括文件夹中的文件内容，文件夹) 
     *  
     * @param strFolderPath 
     * @return 
     */  
    public static boolean removeFolder(String strFolderPath) {  
        boolean bFlag = false;  
        try {  
            if (strFolderPath == null || "".equals(strFolderPath)) {  
                return bFlag;  
            }  
            File file = new File(strFolderPath.toString());  
            bFlag = file.delete();  
            if (bFlag == Boolean.TRUE) {  
                logger.debug("[REMOE_FOLDER:" + file.getPath() + "删除成功!]");  
            } else {  
                logger.debug("[REMOE_FOLDER:" + file.getPath() + "删除失败]");  
            }  
        } catch (Exception e) {  
            logger.error("FLOADER_PATH:" + strFolderPath + "删除文件夹失败!");  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 移除所有文件 
     *  
     * @param strPath 
     */  
    public static void removeAllFile(String strPath) {  
        File file = new File(strPath);  
        if (!file.exists()) {  
            return;  
        }  
        if (!file.isDirectory()) {  
            return;  
        }  
        String[] fileList = file.list();  
        File tempFile = null;  
        for (int i = 0; i < fileList.length; i++) {  
            if (strPath.endsWith(File.separator)) {  
                tempFile = new File(strPath + fileList[i]);  
            } else {  
                tempFile = new File(strPath + File.separator + fileList[i]);  
            }  
            if (tempFile.isFile()) {  
                tempFile.delete();  
            }  
            if (tempFile.isDirectory()) {  
                removeAllFile(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件  
                removeFolder(strPath + "/" + fileList[i]);// 删除文件夹  
            }  
        }  
    }  
  
    public static void copyFile(String oldPath, String newPath) {  
        try {  
            int bytesum = 0;  
            int byteread = 0;  
            File oldfile = new File(oldPath);  
            if (oldfile.exists()) { // 文件存在时  
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件  
                FileOutputStream fs = new FileOutputStream(newPath);  
                byte[] buffer = new byte[1444];  
                while ((byteread = inStream.read(buffer)) != -1) {  
                    bytesum += byteread; // 字节数 文件大小  
                    System.out.println(bytesum);  
                    fs.write(buffer, 0, byteread);  
                }  
                inStream.close();  
                logger.debug("[COPY_FILE:" + oldfile.getPath() + "复制文件成功!]");  
            }  
        } catch (Exception e) {  
            System.out.println("复制单个文件操作出错 ");  
            e.printStackTrace();  
        }  
    }  
  
    public static void copyFolder(String oldPath, String newPath) {  
        try {  
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹  
            File a = new File(oldPath);  
            String[] file = a.list();  
            File temp = null;  
            for (int i = 0; i < file.length; i++) {  
                if (oldPath.endsWith(File.separator)) {  
                    temp = new File(oldPath + file[i]);  
                } else {  
                    temp = new File(oldPath + File.separator + file[i]);  
                }  
                if (temp.isFile()) {  
                    FileInputStream input = new FileInputStream(temp);  
                    FileOutputStream output = new FileOutputStream(newPath  
                            + "/ " + (temp.getName()).toString());  
                    byte[] b = new byte[1024 * 5];  
                    int len;  
                    while ((len = input.read(b)) != -1) {  
                        output.write(b, 0, len);  
                    }  
                    output.flush();  
                    output.close();  
                    input.close();  
                    logger.debug("[COPY_FILE:" + temp.getPath() + "复制文件成功!]");  
                }  
                if (temp.isDirectory()) {// 如果是子文件夹  
                    copyFolder(oldPath + "/ " + file[i], newPath + "/ "  
                            + file[i]);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("复制整个文件夹内容操作出错 ");  
            e.printStackTrace();  
        }  
    }  
  
    public static void moveFile(String oldPath, String newPath) {  
        copyFile(oldPath, newPath);  
        //removeFile(oldPath);  
    }  
  
    public static void moveFolder(String oldPath, String newPath) {  
        copyFolder(oldPath, newPath);  
        //removeFolder(oldPath);  
    }  
    
    
    
    
    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
//        try {
//            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
//            // 一次读多个字节
//            byte[] tempbytes = new byte[100];
//            int byteread = 0;
//            in = new FileInputStream(fileName);
//            ReadFromFile.showAvailableBytes(in);
//            // 读入多个字节到字节数组中，byteread为一次读入的字节数
//            while ((byteread = in.read(tempbytes)) != -1) {
//                System.out.write(tempbytes, 0, byteread);
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        List<String> rtnValue = new ArrayList<String>();
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            String rowContent = "";
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
                rowContent = rowContent + String.valueOf((char) tempchar);
                if (((char) tempchar) != '\n') {
                	rtnValue.add(rowContent);
                	rowContent = "";
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {  
            return new ArrayList<String>();  
        } 
        BufferedReader reader = null;
        List<String> rtnValue = new ArrayList<String>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	rtnValue.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return rtnValue;
    }

    /**
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethod(String fileName, String content) {
        try {
        	File file = new File(fileName.toString());  
            if (!file.exists()) {  
                file.createNewFile();  
            } 
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取目录下所有文件  单层目录
     * @param path
     * @return
     */
    public static List<String> getAllFileName(String path){
    	File file=new File(path);
    	File[] fileList = file.listFiles();
    	List<String> strFileList = new ArrayList<String>();
    	for(File item:fileList){
    		
    		if(item.isFile()){
    			
    			strFileList.add(item.getAbsolutePath());
    		}
    	}
    	return strFileList;
    }
}