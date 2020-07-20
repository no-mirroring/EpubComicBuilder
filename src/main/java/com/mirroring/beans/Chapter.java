package com.mirroring.beans;

import com.mirroring.beans.MediaType;

import java.io.File;
import java.util.List;

public class Chapter {
    private String chapterName;
    private int mediaType;
    private List<File> picFileList;//图片
    private String content;

    /**
     * 纯图片章节
     * @param chapterName 章节名
     * @param picFileList 图片List
     */
    public Chapter(String chapterName, List<File> picFileList) {
        if (picFileList.size() == 0) {
            try {
                throw new Exception("picFileList是空的");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.chapterName = chapterName;
        this.picFileList = picFileList;
        this.mediaType = MediaType.PIC_LIST;
    }


    /**
     * 纯文本章节
     * @param chapterName 章节名
     * @param content 文本
     */
    public Chapter(String chapterName, String content) {
        this.chapterName = chapterName;
        this.content = content;
        this.mediaType = MediaType.TEXT;
    }

    public String getChapterName() {
        return chapterName;
    }

    public int getMediaType() {
        return mediaType;
    }

    public List<File> getPicFileList() {
        return picFileList;
    }

    public String getContent() {
        return content;
    }
}
