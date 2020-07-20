package com.mirroring.beans;

import java.util.ArrayList;
import java.util.List;

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
public class NCXReference {

    private List<HeadMeta> headMetaList;
    private List<NavPoint> navPointList;

    public NCXReference() {
        headMetaList = new ArrayList<HeadMeta>();
        navPointList = new ArrayList<NavPoint>();
    }

    public void addHeadMeta(HeadMeta headMeta) {
        headMetaList.add(headMeta);
    }

    public void addNavPoint(NavPoint navPoint) {
        navPointList.add(navPoint);
    }


    public List<HeadMeta> getHeadMetaList() {
        return headMetaList;
    }

    public void setHeadMetaList(List<HeadMeta> headMetaList) {
        this.headMetaList = headMetaList;
    }

    public List<NavPoint> getNavPointList() {
        return navPointList;
    }

    public void setNavPointList(List<NavPoint> navPointList) {
        this.navPointList = navPointList;
    }


}
