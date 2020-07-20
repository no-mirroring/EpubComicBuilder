package com.mirroring.beans;


import com.mirroring.beans.Chapter;
import com.mirroring.domain.BookProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String bookName;//书名
    private String bookID;//ID
    private String author;//作者
    private List<Chapter> chapterList;//章节
    private File cover;//封面

    public Book(String bookName) {
        this(bookName, "id:mirroring");
    }

    public Book(String bookName, String bookID) {
        this.bookName = bookName;
        this.bookID = bookID;
        chapterList = new ArrayList<Chapter>();
    }

    public void addChapter(Chapter chapter) {
        chapterList.add(chapter);
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookID() {
        return bookID;
    }

    public String getAuthor() {
        return author;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public File getCover() {
        return cover;
    }

    public void setCover(File cover) {
        this.cover = cover;
    }

    public BookProcessor build() {
        if (chapterList.size() != 0) {
            return new BookProcessor(this).process();
        }
        return null;
    }
}
