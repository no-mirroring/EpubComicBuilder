package com.mirroring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <manifest>
 * <item id="ncx" href="toc.ncx" media-type="text/xml"/>
 * <item id="cover" href="title.html" media-type="application/xhtml+xml"/>
 * <item id="content" href="content.html" media-type="application/xhtml+xml"/>
 * <item id="cover-image" href="images/cover.png" media-type="image/png"/>
 * <item id="css" href="stylesheet.css" media-type="text/css"/>
 * </manifest>
 */
public class Manifest {
    private List<Item> itemList;

    public Manifest(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Manifest() {
        this(new ArrayList<Item>());
    }

    public Manifest addItem(Item item) {
        itemList.add(item);
        return this;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public class Item {
        private String id;
        private String href;
        private String metaType;

        public Item(String id, String href, String metaType) {
            this.id = id;
            this.href = href;
            this.metaType = metaType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getMetaType() {
            return metaType;
        }

        public void setMetaType(String metaType) {
            this.metaType = metaType;
        }
    }
}
