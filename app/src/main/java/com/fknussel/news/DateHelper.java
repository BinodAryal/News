package com.fknussel.news;

import java.util.ArrayList;

import hirondelle.date4j.DateTime;

public class DateHelper {
    
    public static String getHumanFriendlyDate(String input) {
        
        // months
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

        // weekdays
        ArrayList<String> days = new ArrayList<>();
        days.add("Domingo");
        days.add("Lunes");
        days.add("Martes");
        days.add("Miercoles");
        days.add("Jueves");
        days.add("Viernes");
        days.add("Sábado");

        DateTime dt = new DateTime(input);

        String weekDay = days.get(dt.getWeekDay() - 1);
        String day = dt.getDay().toString();
        String month = months.get(dt.getMonth() - 1);

        return weekDay + " " + day + " de " + month;
    }
}
