package com.mirroring.domain;

import com.mirroring.beans.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

public class BookProcessor {
    private Book book;
    private ContainerReference container;
    private OPFReference opf;
    private NCXReference ncxReference;
    private List<Chapter> chapterList;

    public BookProcessor(Book book) {
        this.book = book;
        this.chapterList = book.getChapterList();
    }

    /**
     * 开始解析
     *
     * @return BookProcessor
     */
    public BookProcessor process() {
        processContainer();
        processOPF();
        processNCX();
        return this;
    }

    /**
     * 解析container.xml文件
     */
    private void processContainer() {
        //使用默认配置
        container = new ContainerReference();
    }

    /**
     * 解析mirroring.opf文件
     */
    private void processOPF() {
        Metadata metadata = new Metadata(book.getBookName());

        //遍历添加manifest的item
        Manifest manifest = new Manifest();
        //先添加一条ncx
        manifest.addItem(manifest.new Item("ncx", "mirroring.ncx", MediaType.MANIFEST_NCX_MEDIA_TYPE));

        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            //无论哪种类型章节，都对应添加一个html
            manifest.addItem(manifest.new Item("chapter" + i, "chapter" + i + ".html", MediaType.MANIFEST_HTML));
            //根据章节的不同类型，做不同处理
            switch (chapter.getMediaType()) {
                //图片章节
                case MediaType.PIC_LIST:
                    List<File> picList = chapter.getPicFileList();
                    for (int j = 0; j < picList.size(); j++) {
                        File pic = picList.get(j);
                        //图片添加时放在images/
                        manifest.addItem(manifest.new Item("image_"+FilenameUtils.getBaseName(pic.getName()), "images/" + pic.getName(), MediaType.MANIFEST_JPEG));

                    }
                    break;
                //文字章节
                case MediaType.TEXT:
                    break;
            }
        }

        Spine spine = new Spine();
        for (int i = 0; i < chapterList.size(); i++) {
            spine.addItemRef(spine.new ItemRef("chapter"+i,true));
        }

        opf = new OPFReference(metadata,manifest,spine);
    }

    /**
     * 解析mirroring.ncx文件
     */
    private void processNCX() {
        ncxReference = new NCXReference();
        ncxReference.addHeadMeta(new HeadMeta("dtb:uid",book.getBookID()));
        ncxReference.addHeadMeta(new HeadMeta("dtb:depth","1"));
        ncxReference.addHeadMeta(new HeadMeta("dtb:totalPageCount","0"));
        ncxReference.addHeadMeta(new HeadMeta("dtb:maxPageNumber","0"));
        ncxReference.addHeadMeta(new HeadMeta("dtb:maxPageNumber","0"));
        ncxReference.addHeadMeta(new HeadMeta("right","由no-mirroring工具制作，仅供个人交流与学习使用。在未获得掌上书苑的商业授权前，不得用于任何商业用途。"));

        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            ncxReference.addNavPoint(new NavPoint("chapter"+i,i+"",chapter.getChapterName(),"chapter"+i+".html"));
        }

    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ContainerReference getContainer() {
        return container;
    }

    public void setContainer(ContainerReference container) {
        this.container = container;
    }

    public OPFReference getOpf() {
        return opf;
    }

    public void setOpf(OPFReference opf) {
        this.opf = opf;
    }

    public NCXReference getNcxReference() {
        return ncxReference;
    }

    public void setNcxReference(NCXReference ncxReference) {
        this.ncxReference = ncxReference;
    }
}
