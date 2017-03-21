package com.zhdhr0000.architecture.main.model;

/**
 * Created by win7 on 2017/3/21.
 */

public class PageType {
    int type;
    String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PageType pageType = (PageType) o;

        if (type != pageType.type) {
            return false;
        }
        return name != null ? name.equals(pageType.name) : pageType.name == null;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageType{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
