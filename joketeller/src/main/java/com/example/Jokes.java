package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jokes {

    //private String jokes;
    private static List<String> jokes = new ArrayList<>();
    private static Random random = new Random();

    public Jokes(String data) {
        jokes.add(data);
        /*
        jokes.add("What do you call it when Batman skips Church?  \n ... \n Christian Bale.");
        jokes.add("Texan: \"Where are you from?\" \n" +
                "Harvard Grad: \"I come from a place where we do not end our sentences with prepositions.\" \n" +
                "Texan: \"OK - where are you from, jackass?\" ");
        */
    }

    public static String tellJoke(){

        int index = random.nextInt(jokes.size());
        return jokes.get(index);
        //return jokes;
    }

    public static List<String> getJokes() {
        return jokes;
    }

}
