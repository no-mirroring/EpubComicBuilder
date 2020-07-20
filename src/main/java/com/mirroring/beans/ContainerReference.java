package com.mirroring.beans;

/**
 * META-INF/container.xml
 * <container version="1.0" xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
 *    <rootfiles>
 *       <rootfile full-path="OPS/fb.opf" media-type="application/oebps-package+xml"/>
 *    </rootfiles>
 * </container>
 */
public class ContainerReference {
    private String version = "1.0";
    private String xmlns = "urn:oasis:names:tc:opendocument:xmlns:container";
    private String full_path = "OPS/mirroring.opf";
    private String media_type = MediaType.CONTAINER_MEDIA_TYPE;
    private String path;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getFull_path() {
        return full_path;
    }

    public void setFull_path(String full_path) {
        this.full_path = full_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
