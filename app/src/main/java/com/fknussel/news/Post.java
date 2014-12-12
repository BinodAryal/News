package com.fknussel.news;

import java.util.ArrayList;

public class Post {

    private int id;
    private String title;
//    private String excerpt;
//    private String body;
//    private Date date;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
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

    public static ArrayList<Post> getDummyPosts() {
        ArrayList<Post> items = new ArrayList<Post>();

        items.add(new Post(1, "Sonaron las campanas adhiriendo a la movida nacional \"Bailemos Todos\""));
        items.add(new Post(2, "El Intendente inauguró nuevas luminarias de la zona céntrica"));
        items.add(new Post(3, "El Ipem Nº 326 informó los ganadores de su Bono Contribución"));
        items.add(new Post(4, "Retoño de la higuera de la Casa de Sarmiento en nuestra localidad"));
        items.add(new Post(5, "Deportes y Recreación: Se inician las actividades de verano."));
        items.add(new Post(6, "El Ipem Nº 326 informó los ganadores de su Bono Contribución"));
        items.add(new Post(7, "El miércoles 10 será la 15º Edición de los Premios Sport 6"));
        items.add(new Post(8, "Ciclo lectivo 2015: las clases en Córdoba comenzarán el 2 de marzo y finalizarán el 18 de diciembre"));
        items.add(new Post(9, "2° Clínica de Rugby en Córdoba Capital"));
        items.add(new Post(10, "Se presentó el plan de Ordenamiento Urbano"));

        return items;
    }
}
