package com.mirroring.beans;

/**
 * <package xmlns="http://www.idpf.org/2007/opf"
 *             xmlns:dc="http://purl.org/dc/elements/1.1/"
 *             unique-identifier="bookid" version="2.0">
 *   <metadata>
 *     <dc:title>Hello World: My First EPUB</dc:title>
 *     <dc:creator>My Name</dc:creator>
 *     <dc:identifier id="bookid">urn:uuid:12345</dc:identifier>
 *     <meta name="cover" content="cover-image" />
 *   </metadata>
 *   <manifest>
 *     <item id="ncx" href="toc.ncx" media-type="text/xml"/>
 *     <item id="cover" href="title.html" media-type="application/xhtml+xml"/>
 *     <item id="content" href="content.html" media-type="application/xhtml+xml"/>
 *     <item id="cover-image" href="images/cover.png" media-type="image/png"/>
 *     <item id="css" href="stylesheet.css" media-type="text/css"/>
 *   </manifest>
 *   <spine toc="ncx">
 *     <itemref idref="cover" linear="no"/>
 *     <itemref idref="content"/>
 *   </spine>
 *   <guide>
 *     <reference href="cover.html" type="cover" title="Cover"/>
 *   </guide>
 * </package>
 */
public class OPFReference {
    private String path;
    private Metadata metadata;
    private Manifest manifest;
    private Spine spine;
    private Guide guide;

    public OPFReference(Metadata metadata, Manifest manifest, Spine spine) {
        this(metadata, manifest, spine, null);
    }

    public OPFReference(Metadata metadata, Manifest manifest, Spine spine, Guide guide) {
        this.metadata = metadata;
        this.manifest = manifest;
        this.spine = spine;
        this.guide = guide;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Manifest getManifest() {
        return manifest;
    }

    public void setManifest(Manifest manifest) {
        this.manifest = manifest;
    }

    public Spine getSpine() {
        return spine;
    }

    public void setSpine(Spine spine) {
        this.spine = spine;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }
}
