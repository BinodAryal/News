package com.fknussel.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Create some dummy data for the ListView.
        String[] data = {
                "Sonaron las campanas adhiriendo a la movida nacional \"Bailemos Todos\"",
                "El Intendente inauguró nuevas luminarias de la zona céntrica",
                "El Ipem Nº 326 informó los ganadores de su Bono Contribución",
                "Retoño de la higuera de la Casa de Sarmiento en nuestra localidad",
                "Deportes y Recreación: Se inician las actividades de verano.",
                "El Ipem Nº 326 informó los ganadores de su Bono Contribución",
                "El miércoles 10 será la 15º Edición de los Premios Sport 6",
                "Ciclo lectivo 2015: las clases en Córdoba comenzarán el 2 de marzo y finalizarán el 18 de diciembre",
                "2° Clínica de Rugby en Córdoba Capital",
                "Se presentó el plan de Ordenamiento Urbano"
        };

        List<String> news = new ArrayList<String>(Arrays.asList(data));

        // The ArrayAdapter will take data from a source (RESTful API)
        // and use it to populate the ListView it's attached to.
        ArrayAdapter<String> newsAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_post, // The name of the layout ID
                        R.id.list_item_post_textview, // The ID of the textview to populate
                        news);

        ListView listview = (ListView) rootView.findViewById(R.id.listview_news);
        listview.setAdapter(newsAdapter);


        return rootView;
    }
}