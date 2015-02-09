package com.fknussel.news;

import java.util.ArrayList;

import hirondelle.date4j.DateTime;

public class Post {

    public static final String TAG = Post.class.getSimpleName();
    
    private int id;
    private String title;
    private String excerpt;
    private String body;
    private String category;
    private String date;
    private ArrayList<Media> media;

    public Post(int id, String title, String excerpt, String body, String category, String date, ArrayList<Media> media) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.body = body;
        this.category = category;
        this.date = date;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getBody() {
        return body;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        ArrayList<String> months = new ArrayList<>();
        months.add("Enero");
        months.add("Febrero");
        months.add("Marzo");
        months.add("Abril");
        months.add("Mayo");
        months.add("Junio");
        months.add("Julio");
        months.add("Agosto");
        months.add("Septiembre");
        months.add("Octubre");
        months.add("Noviembre");
        months.add("Diciembre");

        ArrayList<String> days = new ArrayList<>();
        days.add("Domingo");
        days.add("Lunes");
        days.add("Martes");
        days.add("Miercoles");
        days.add("Jueves");
        days.add("Viernes");
        days.add("Sábado");
        
        DateTime dt = new DateTime(date);
        
        String weekDay = days.get(dt.getWeekDay() - 1);
        String day = dt.getDay().toString();
        String month = months.get(dt.getMonth() - 1);
        
        return weekDay + " " + day + " de " + month;
    }
    
    public ArrayList<Media> getMedia() {
        return media;
    }
    
    public String getImage() {
        String image;
        if (this.media.get(0).getType() == "imagen") {
            image = this.media.get(0).getUrl();
        } else {
            image = null;
        }
        return image;
    }
}
