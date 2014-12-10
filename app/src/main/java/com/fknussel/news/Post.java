package com.fknussel.news;

import java.util.Date;

public class Post {

    private int id;
    private String title;
    private String excerpt;
    private String body;
    private Date date;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
