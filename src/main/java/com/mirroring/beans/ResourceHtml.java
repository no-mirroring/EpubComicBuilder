package com.mirroring.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ResourceHtml {
    private File htmlFile;
    private List<File> picFileList;

    public ResourceHtml(File htmlFile) {
        this.htmlFile = htmlFile;
        picFileList = new ArrayList<File>();
    }

    /**
     * 传入html包含的图片
     * @param images
     * @return
     */
    public ResourceHtml withImages(List<File> images) {
        this.picFileList = images;
        return this;
    }

    public File getHtmlFile() {
        return htmlFile;
    }

    public void setHtmlFile(File htmlFile) {
        this.htmlFile = htmlFile;
    }

    public List<File> getPicFileList() {
        return picFileList;
    }

    public void setPicFileList(List<File> picFileList) {
        this.picFileList = picFileList;
    }
}
