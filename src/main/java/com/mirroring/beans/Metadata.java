package com.mirroring.beans;

/**
 * <metadata>
 *   <dc:title>Hello World: My First EPUB</dc:title>
 *   <dc:creator>My Name</dc:creator>
 *   <dc:identifier id="bookid">urn:uuid:12345</dc:identifier>
 *   <meta name="cover" content="cover-image" />
 * </metadata>
 */
public class Metadata{
    private String title;
    private String identifier = "id:mirroring";
    private String creator = "mirroring";
    private String language = "zh-CN";


    public Metadata(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
