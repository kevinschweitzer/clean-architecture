package com.globant.equattrocchio.domain.model;

public class Image {

    private Integer id;
    private String url;
    private String largeUrl;
    private Object sourceId;

    public Image(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public Object getSourceId() {
        return sourceId;
    }

    public void setSourceId(Object sourceId) {
        this.sourceId = sourceId;
    }
}
