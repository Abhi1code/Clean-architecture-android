package com.mindorks.framework.mvvm.custom.remote.volley.model;

public class ModelByPart {
    private String fileName;
    private byte[] content;
    private String mimeType;

    /**
     * Constructor with data mimeType.
     *
     * @param fileName Label of data
     * @param data     Byte data
     * @param mimeType Mime data like "image/*"
     * @author Jatin Sahgal (Jamun 01-12-2018)  */
    public ModelByPart(String fileName, byte[] data, String mimeType) {
        this.fileName = fileName;
        this.content = data;
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getType() {
        return mimeType;
    }

    public void setType(String mimeType) {
        this.mimeType = mimeType;
    }
}
