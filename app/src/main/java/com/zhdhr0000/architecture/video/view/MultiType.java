package com.zhdhr0000.architecture.video.view;

/**
 * Created by zhangyinghao on 2017/8/25.
 */

public class MultiType {
    public static final int VIDEO = 0;
    public static final int NOT_VIDEO = 1;
    private int type;
    private String content;

    public MultiType() {
    }

    public MultiType(int type, String content) {
        setType(type);
        setContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiType)) return false;

        MultiType multiType = (MultiType) o;

        if (getType() != multiType.getType()) return false;
        return getContent() != null ? getContent().equals(multiType.getContent()) : multiType.getContent() == null;
    }

    @Override
    public int hashCode() {
        int result = getType();
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MultiType{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
