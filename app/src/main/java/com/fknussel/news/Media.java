package com.fknussel.news;

/*
 * Media items can be either images or YouTube hosted videos 
 */
public class Media {
    
    private int id;
    private int postId;
    private String type;
    private String url;
    
    private static final String IMAGE_BASE_URL = "http://www.freyre.com.ar/files/";
    private static final String VIDEO_BASE_URL = "";

    public Media(int id, int postId, String type, String url) {
        this.id = id;
        this.postId = postId;
        this.type = type;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
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
