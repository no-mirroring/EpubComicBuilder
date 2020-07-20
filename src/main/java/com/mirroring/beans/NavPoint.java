package com.mirroring.beans;

public class NavPoint{
    private String id;
    private String playOrder;
    private String name;
    private String src;

    public NavPoint(String id, String playOrder, String name, String src) {
        this.id = id;
        this.playOrder = playOrder;
        this.name = name;
        this.src = src;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayOrder() {
        return playOrder;
    }

    public void setPlayOrder(String playOrder) {
        this.playOrder = playOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
