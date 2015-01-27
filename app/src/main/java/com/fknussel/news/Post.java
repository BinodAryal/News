package com.fknussel.news;

import java.util.ArrayList;

public class Post {

    private int id;
    private String title;
    private String image;
//    private String excerpt;
//    private String body;
//    private Date date;

    public Post(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
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
    
    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<Post> getDummyPosts() {
        ArrayList<Post> items = new ArrayList<Post>();

        items.add(new Post(1, "Stunning photos of Scottish skies", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2g/q7/p02gq7ww.jpg"));
        items.add(new Post(2, "Living in 2015’s hottest cities", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2h/b4/p02hb4wh.jpg"));
        items.add(new Post(3, "One of WWI's bloodiest frontlines", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2g/t3/p02gt3v6.jpg"));
        items.add(new Post(4, "The quest to see China’s extraordinary frozen fog", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2g/vw/p02gvwkz.jpg"));
        items.add(new Post(5, "The quietest place in the US", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2g/ng/p02gngy4.jpg"));
        items.add(new Post(6, "The trip that changed national parks forever", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2g/ly/p02gly8k.jpg"));
        items.add(new Post(7, "The first rule in tracking lions: No running", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2h/7z/p02h7z3t.jpg"));
        items.add(new Post(8, "Four days in Australia’s open ocean", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2g/ql/p02gqlb5.jpg"));
        items.add(new Post(9, "How I quit my job to travel: The blogger", "http://ichef.bbci.co.uk/wwtravel/608_342/images/live/p0/2f/w9/p02fw9cx.jpg"));

        return items;
    }
}
