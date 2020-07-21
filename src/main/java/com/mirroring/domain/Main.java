package com.mirroring.domain;

import com.mirroring.beans.Book;
import com.mirroring.beans.Chapter;
import com.mirroring.beans.ResourceHtml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 章节类型：纯文本，纯图片，html,三者在BookProcessor和BookWriter类中会做不同处理
 */
public class Main {
    public static void main(String[] args) throws Exception {
        /*
        //书名
        Book book = new Book("第一本书");
        book.setAuthor("无镜像");
        //封面
        book.setCover(new File("F:\\1.jpg"));
        //添加图片章节
        List<File> picList = new ArrayList<File>();
        File[] files = new File("F:\\t").listFiles();
        Collections.addAll(picList, files);

        Chapter chapter = new Chapter("chapter0",picList);
        book.addChapter(chapter);

        //添加文字章节
        Chapter chapter1 = new Chapter("第二章", "章节内容");
        book.addChapter(chapter1);

        List<File> picList = new ArrayList<File>();
        File[] files = new File("F:\\t").listFiles();
        Collections.addAll(picList, files);
        Chapter chapter_html = new Chapter("html章",new ResourceHtml(new File("F:\\1.html")).withImages(picList));
        book.addChapter(chapter_html);

        BookWriter bookWriter = new BookWriter(book.build(), new File("F:\\tt\\ttt\\"));
        bookWriter.writeBook();
        */
    }
}
