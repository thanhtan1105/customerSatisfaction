package com.hackathon.modelMCS;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class RectangleImage {

    private Integer top;
    private Integer left;
    private Integer width;
    private Integer height;

    public RectangleImage() {
    }

    public RectangleImage(Integer top, Integer left, Integer width, Integer height) {
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "RectangleImage{" +
                "top=" + top +
                ", left=" + left +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public String toFaceRectangle(){
        return String.format("%s,%s,%s,%s", left, top, width, height);
    }

    public Long area(){
        return ((long) width) * ((long)height);
    }
}
