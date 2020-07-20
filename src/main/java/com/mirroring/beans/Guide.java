package com.mirroring.beans;

/**
 * <guide>
 *   <reference href="cover.html" type="cover" title="Cover"/>
 * </guide>
 */
public class Guide {
    private String href;
    private String title ="封面";
    private String type="cover";

    public Guide(String coverHref) {
        this.href = coverHref;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
