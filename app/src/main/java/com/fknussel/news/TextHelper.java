package com.fknussel.news;

import android.text.Html;

public class TextHelper {

    public static CharSequence trim(CharSequence text) {
        while (text.charAt(text.length() - 1) == '\n') {
            text = text.subSequence(0, text.length() - 1);
        }
        return text;
    }
    
    public static String removeEmptyParagraphs(String text) {
        return text.replaceAll("<p> </p>", " ");
    }
    
    public static CharSequence processHtml(String text) {
        return trim(Html.fromHtml(removeEmptyParagraphs(text)));
    }
}