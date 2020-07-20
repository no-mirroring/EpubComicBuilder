package com.mirroring.domain;

import com.mirroring.beans.Book;
import com.mirroring.beans.Chapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    /*public static void main(String[] args) throws IOException {
        Book book = new Book("test");

        List<File> picList = new ArrayList<File>();
        File[] files = new File("F:\\t").listFiles();
        Collections.addAll(picList, files);

        Chapter chapter = new Chapter("chapter0",picList);
        book.addChapter(chapter);

        BookWriter bookWriter = new BookWriter(book.build(), new File("F:\\tt\\ttt\\"));
        bookWriter.writeBook(book);
    }*/
}
