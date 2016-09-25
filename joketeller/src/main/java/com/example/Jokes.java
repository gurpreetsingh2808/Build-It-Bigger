package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jokes {

    //private String jokes;
    private static List<String> jokes = new ArrayList<>();
    private static Random random = new Random();

    static {
        jokes.add("Texan: \"Where are you from?\" \n\n" +
                "Harvard Grad: \"I come from a place where we do not end our sentences with prepositions.\" \n\n" +
                "Texan: \"OK - where are you from, jackass?\" ");
        jokes.add("Knock, Knock. \nWho's there? \nPolice. \nPolice who? \nPolice stop telling these awful knock, knock jokes! \n" );
        jokes.add("Q: What do you get when you mix sulfur, tungsten, and silver? \n A: SWAG \n");
        jokes.add("What do you call it when Batman skips Church?  \n ... \n\n\n Christian Bale.");
        jokes.add("Q: Why do chemists call helium, curium, and barium the healing elements? \n" +
                "A: Because if you can't helium or curium, you barium!");
        jokes.add("Knock Knock \nWho's There? \nAhmed \nAhmed who? \nAhmed the payphone trying to call home." +
                " All of my change I spent on you. \n" );
        jokes.add("Q: What's dangerous and swings from trees? \n A: A monkey with a chainsaw");
        jokes.add("How many tickles does it take to tickle an octopus? \nTEN-TICKLES!");
        jokes.add("What do you call a boomerang that doesn’t come back?? \n" +
                "A stick!");
        jokes.add("Q: \"What's the difference between a guitar and a fish?\"\n A: \"You can't tuna fish.\" \n");

    }

    public Jokes(String data) {
        jokes.add(data);
    }

    /**
     * for fetching a single joke in asynctask
     *
     * @return
     */
    public static String tellJoke(){

        int index = random.nextInt(jokes.size());
        return jokes.get(index);
        //return jokes;
    }

    public static List<String> getJokes() {
        return jokes;
    }

}
