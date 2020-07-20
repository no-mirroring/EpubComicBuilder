package com.mirroring.utils;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class IOUtils {


    /**
     * 写一个文件
     *
     * @param targetFile 文件目录
     * @param content    内容
     * @throws IOException IO
     */
    public static void writeText(File targetFile, String content) throws IOException {
        if (!isExist(targetFile.getParentFile())) targetFile.getParentFile().mkdirs();
        if (!isExist(targetFile)) targetFile.createNewFile();
        FileWriter fileWriter = new FileWriter(targetFile);
        fileWriter.write(content);
        fileWriter.close();
    }


    public static void writeXML(Document document, File file) throws IOException {
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) file.createNewFile();

        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        /*outputFormat.setSuppressDeclaration(true); //是否生产xml头
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setIndent("    "); //以四个空格方式实现缩进
        outputFormat.setNewlines(true); //设置是否换行*/

        XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(file)),outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * 文件或文件夹是否存在
     *
     * @param file file
     * @return 存在返回true
     */
    public static boolean isExist(File file) {
        return file.exists();
    }
}
