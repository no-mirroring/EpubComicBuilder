package com.mirroring.beans;

import java.io.File;
import java.util.List;

public class Chapter {
    private String chapterName;
    private int mediaType;
    private List<File> picFileList;//图片
    private String content;
    private ResourceHtml resourceHtml;

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
        this.mediaType = MediaType.CHAPTER_PIC_LIST;
    }

    /**
     * 纯文本章节
     * @param chapterName 章节名
     * @param content 文本,可以包含html标签
     */
    public Chapter(String chapterName, String content) {
        this.chapterName = chapterName;
        this.content = content;
        this.mediaType = MediaType.CHAPTER_TEXT;
    }

    /**
     * html章节
     * @param resourceHtml
     */
    public Chapter(String chapterName,ResourceHtml resourceHtml) {
        this.chapterName = chapterName;
        this.resourceHtml = resourceHtml;
        this.mediaType = MediaType.CHAPTER_HTML;
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

    public ResourceHtml getResourceHtml() {
        return resourceHtml;
    }

}
