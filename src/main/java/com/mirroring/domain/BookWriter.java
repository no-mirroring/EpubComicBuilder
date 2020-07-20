package com.mirroring.domain;

import com.mirroring.beans.*;
import com.mirroring.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.util.List;

public class BookWriter {
    private BookProcessor bookProcessor;
    private File rootPath;
    private String fileName;

    /**
     *
     * @param bookProcessor BookProcessor
     * @param rootPath 文件目录
     * @param fileName 文件名
     */
    public BookWriter(BookProcessor bookProcessor, File rootPath, String fileName) {
        this.bookProcessor = bookProcessor;
        this.rootPath = rootPath;
        this.fileName = fileName;
    }

    /**
     *
     * @param bookProcessor
     * @param rootPath 文件目录
     */
    public BookWriter(BookProcessor bookProcessor, File rootPath) {
        this(bookProcessor, rootPath, bookProcessor.getBook().getBookName());
    }

    public void writeBook() throws Exception {
        //创建mimetype文件
        IOUtils.writeText(new File(rootPath,"mimetype"),"application/epub+zip");
        //创建META-INF/container.xml,OPS/mirroring.opf,OPS/mirroring.ncx
        IOUtils.writeXML(parseContainer(),new File(rootPath+"\\META-INF\\","container.xml"));
        IOUtils.writeXML(parseOPF(),new File(rootPath+"\\OPS\\","mirroring.opf"));
        IOUtils.writeXML(parseNCX(),new File(rootPath+"\\OPS\\","mirroring.ncx"));
        //移动图片文件
        moveImages();
        packEPUB();

    }

    private Document parseContainer() {
        /*META-INF/container.xml
        <container version="1.0" xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
           <rootfiles>
              <rootfile full-path="OPS/fb.opf" media-type="application/oebps-package+xml"/>
           </rootfiles>
        </container>*/
        ContainerReference container = bookProcessor.getContainer();
        //构建基本结构
        Document document = DocumentHelper.createDocument();
        Element root= document.addElement("container");
        Element root_files = root.addElement("rootfiles");
        Element root_file = root_files.addElement("rootfile");
        //填充属性
        root.addAttribute("version",container.getVersion());
        root.addAttribute("xmlns",container.getXmlns());
        root_file.addAttribute("full-path", container.getFull_path());
        root_file.addAttribute("media-type", container.getMedia_type());

        return document;
    }

    private Document parseOPF() {
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

        OPFReference opf = bookProcessor.getOpf();
        Document document = DocumentHelper.createDocument();
        //构建基本结构
        Element root=document.addElement("package","http://www.idpf.org/2007/opf");//先添加命名空间，否则xmlns不会出现
        Element metadata = root.addElement("metadata");
        Element manifest = root.addElement("manifest");
        manifest.addAttribute("toc", "ncx");
        Element spine = root.addElement("spine").addAttribute("toc", "ncx");//添加该属性来关联ncx目录，否则目录加载不正常
        Element guide = root.addElement("guide");

        //填充属性root
        root.addAttribute("xmlns:dc", "http://purl.org/dc/elements/1.1/");
        root.addAttribute("unique-identifier", bookProcessor.getBook().getBookID());
        root.addAttribute("version", "2.0");
        //填充属性metadata
        metadata.addNamespace("dc", "");//设置命名空间，否则标签不能包含冒号
        metadata.addElement("dc:title").addText(opf.getMetadata().getTitle());
        metadata.addElement("dc:identifier").addText(opf.getMetadata().getIdentifier());
        metadata.addElement("dc:language").addText(opf.getMetadata().getLanguage());
        metadata.addElement("dc:creator").addText(opf.getMetadata().getCreator());
        //填充属性manifest
        List<Manifest.Item> itemList = opf.getManifest().getItemList();
        for (int i = 0; i < itemList.size(); i++) {
            Manifest.Item item = itemList.get(i);
            manifest.addElement("item")
                    .addAttribute("id", item.getId())
                    .addAttribute("href", item.getHref())
                    .addAttribute("media-type", item.getMetaType());
        }

        //这里单独处理封面
        File cover = bookProcessor.getBook().getCover();
        if (cover!= null) {
            //在metadata里添加封面标签
            metadata.addElement("meta")
                    .addAttribute("name", "cover")
                    .addAttribute("content", "cover-image");//这个属性对应manifest里的id
            //把封面加入manifest清单
            manifest.addElement("item")
                    .addAttribute("id", "cover-image")
                    .addAttribute("href", "images/cover_image." + FilenameUtils.getExtension(cover.getName()))
                    .addAttribute("media-type", MediaType.MANIFEST_JPEG);
        }

        //填充属性spine
        List<Spine.ItemRef> itemRefList = opf.getSpine().getItemRefList();
        for (int i = 0; i < itemRefList.size(); i++) {
            Spine.ItemRef itemRef = itemRefList.get(i);
            spine.addElement("itemref").addAttribute("idref", itemRef.getIdref()).addAttribute("linear", itemRef.getLinear());
        }

        //填充属性guide
        Guide g = opf.getGuide();
        if (g!=null) {
            guide.addElement("reference").addAttribute("href", g.getHref()).addAttribute("type", g.getType()).addAttribute("title", g.getTitle());
        }

        return document;
    }

    private Document parseNCX() {
        /**
         * <!DOCTYPE ncx PUBLIC "-//NISO//DTD ncx 2005-1//EN"
         *                  "http://www.daisy.org/z3986/2005/ncx-2005-1.dtd">
         * <ncx xmlns="http://www.daisy.org/z3986/2005/ncx/" version="2005-1">
         *   <head>
         *     <meta name="dtb:uid" content="urn:uuid:12345"/>
         *     <meta name="dtb:depth" content="1"/>
         *     <meta name="dtb:totalPageCount" content="0"/>
         *     <meta name="dtb:maxPageNumber" content="0"/>
         *   </head>
         *   <docTitle>
         *     <text>Hello World: My First EPUB</text>
         *   </docTitle>
         *   <navMap>
         *     <navPoint id="navpoint-1" playOrder="1">
         *       <navLabel>
         *         <text>Book cover</text>
         *       </navLabel>
         *       <content src="title.html"/>
         *     </navPoint>
         *     <navPoint id="navpoint-2" playOrder="2">
         *       <navLabel>
         *         <text>Contents</text>
         *       </navLabel>
         *       <content src="content.html"/>
         *     </navPoint>
         *   </navMap>
         * </ncx>
         */
        NCXReference ncxReference = bookProcessor.getNcxReference();
        Document document = DocumentHelper.createDocument();
        //构建基本结构
        Element root = document.addElement("ncx").addAttribute("xmlns", "http://www.daisy.org/z3986/2005/ncx/\" version=\"2005-1");
        Element head = root.addElement("head");
        Element docTitle = root.addElement("docTitle");
        Element navMap = root.addElement("navMap");

        //填充属性head
        List<HeadMeta> headMetaList = ncxReference.getHeadMetaList();
        for (int i = 0; i < headMetaList.size(); i++) {
            HeadMeta headMeta = headMetaList.get(i);
            head.addElement("meta").addAttribute("name", headMeta.getName()).addAttribute("content", headMeta.getContent());
        }
        //填充属性docTitle
        docTitle.addElement("text").addText(bookProcessor.getBook().getBookName());

        //填充navMap
        List<NavPoint> navPointList = ncxReference.getNavPointList();
        for (int i = 0; i < navPointList.size(); i++) {
            NavPoint navPoint = navPointList.get(i);
            Element navPointE = navMap.addElement("navPoint")
                    .addAttribute("id",navPoint.getId())
                    .addAttribute("playOrder",navPoint.getPlayOrder());
            navPointE.addElement("navLabel").addElement("text").addText(navPoint.getName());
            navPointE.addElement("content").addAttribute("src", navPoint.getSrc());
        }
        return document;
    }

    private void moveImages() throws IOException {
        List<Chapter> chapterList = bookProcessor.getBook().getChapterList();
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            IOUtils.writeXML(createChapterHtml(chapter), new File(rootPath + "\\OPS\\" + "chapter" + i+".html"));
            //如果是图片章节，把图片复制到images/
            if (chapter.getMediaType() == MediaType.PIC_LIST) {
                List<File> picList = chapter.getPicFileList();
                for (File file : picList) {
                    FileUtils.copyFileToDirectory(file, new File(rootPath + "\\OPS\\images\\"), true);
                }
            }
        }
        //单独复制封面图片
        File cover = bookProcessor.getBook().getCover();
        if (cover != null) {
            //重命名成cover_image
            FileUtils.copyFile(cover, new File(rootPath + "\\OPS\\images\\cover_image." + FilenameUtils.getExtension(cover.getName())));
        }
    }

    /**
     * 把一个章节解析成html
     * @param chapter
     * @return
     */
    private Document createChapterHtml(Chapter chapter) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("html");
        root.addElement("head");
        Element body = root.addElement("body");

        //如果是图片章节，把图片标签写入html
        if (chapter.getMediaType() == MediaType.PIC_LIST) {
            List<File> picList = chapter.getPicFileList();
            for (int i = 0; i < picList.size(); i++) {
                File pic = picList.get(i);
                //加入style使图片适应屏幕
                body.addElement("p").addAttribute("style","text-align:center;text-indent:0em")
                        .addElement("img").addAttribute("src", "images\\" + pic.getName());
            }
        } else if (chapter.getMediaType() == MediaType.TEXT) {
            //如果是文字章节,直接把文字写入html
            body.addText(chapter.getContent());
        }
        return document;
    }

    /**
     * 压缩成EPUB
     */
    private void packEPUB() throws Exception {
        File mimetype = new File(rootPath, "mimetype");
        File meta_inf = new File(rootPath + "\\META-INF");
        File ops = new File(rootPath + "\\OPS");
        //新文件夹，等待压缩
        File newDir = new File(rootPath + "\\" + fileName+".epub");
        //把三个文件夹和文件移动到新文件夹
        if (newDir.exists()) throw new Exception("文件已经存在，请先删除:" + newDir.getName());
        FileUtils.moveFileToDirectory(mimetype,newDir,true);
        FileUtils.moveDirectoryToDirectory(meta_inf,newDir,true);
        FileUtils.moveDirectoryToDirectory(ops,newDir,true);
        //压缩新文件夹
        ZipUtil.unexplode(newDir);
        //新文件夹变成了一个文件
        File newZip = new File(rootPath + "\\" + fileName);
    }
}
