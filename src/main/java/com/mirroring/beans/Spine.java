package com.mirroring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <spine toc="ncx">
 *   <itemref idref="cover" linear="no"/>
 *   <itemref idref="content"/>
 * </spine>
 */
public class Spine {
    private List<ItemRef> itemRefList;

    public Spine() {
        itemRefList = new ArrayList<ItemRef>();
    }

    public void addItemRef(ItemRef itemRef) {
        itemRefList.add(itemRef);
    }

    public List<ItemRef> getItemRefList() {
        return itemRefList;
    }

    public void setItemRefList(List<ItemRef> itemRefList) {
        this.itemRefList = itemRefList;
    }

    public class ItemRef {
        private String idref;
        private String linear;

        public ItemRef(String idref, boolean linear) {
            this.idref = idref;
            if (linear) {
                this.linear = "yes";
            } else {
                this.linear = "no";
            }
        }

        public String getIdref() {
            return idref;
        }

        public void setIdref(String idref) {
            this.idref = idref;
        }

        public String getLinear() {
            return linear;
        }
    }
}
