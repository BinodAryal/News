package com.fknussel.news;

/*
 * Media items can be either images or YouTube hosted videos 
 */
public class Media {
    
    private String type;
    private String url;
    
    private static final String IMAGE_BASE_URL = "http://www.freyre.com.ar/files/";
    private static final String VIDEO_BASE_URL = "";

    public Media(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getUrl() {
        if (type.equals("imagen")) {
            return IMAGE_BASE_URL + url;
        } else {
            return VIDEO_BASE_URL + url;
        }
    }

    public String getType() {
        return type;
    }
}
