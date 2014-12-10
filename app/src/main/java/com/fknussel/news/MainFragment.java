package com.fknussel.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Create some dummy data for the ListView.
        Post[] data = {
                new Post(1, "Sonaron las campanas adhiriendo a la movida nacional \"Bailemos Todos\""),
                new Post(2, "El Intendente inauguró nuevas luminarias de la zona céntrica"),
                new Post(3, "El Ipem Nº 326 informó los ganadores de su Bono Contribución"),
                new Post(4, "Retoño de la higuera de la Casa de Sarmiento en nuestra localidad"),
                new Post(5, "Deportes y Recreación: Se inician las actividades de verano."),
                new Post(6, "El Ipem Nº 326 informó los ganadores de su Bono Contribución"),
                new Post(7, "El miércoles 10 será la 15º Edición de los Premios Sport 6"),
                new Post(8, "Ciclo lectivo 2015: las clases en Córdoba comenzarán el 2 de marzo y finalizarán el 18 de diciembre"),
                new Post(9, "2° Clínica de Rugby en Córdoba Capital"),
                new Post(10, "Se presentó el plan de Ordenamiento Urbano")
        };

        List<Post> news = new ArrayList<Post>(Arrays.asList(data));

        // The ArrayAdapter will take data from a source (RESTful API)
        // and use it to populate the ListView it's attached to.
        final ArrayAdapter<Post> newsAdapter =
                new ArrayAdapter<Post>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_post, // The name of the layout ID
                        R.id.list_item_post_textview, // The ID of the textview to populate
                        news);

        ListView listview = (ListView) rootView.findViewById(R.id.listview_news);
        listview.setAdapter(newsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra("id", newsAdapter.getItem(position).getId());
                startActivity(i);
            }
        });

        return rootView;
    }
}