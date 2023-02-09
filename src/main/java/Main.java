import Categories.Category;
import Categories.NP;
import Categories.S;
import Categories.TV;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello CCG Parser");

        Lexicon lexicon = new Lexicon();
        lexicon.addEntry("Tom", new NP());
        lexicon.addEntry("likes", new TV());
        lexicon.addEntry("apples", new NP());

        lexicon.addEntry("apples", new S());

        for(Map.Entry<String, Set<Category>> entry: lexicon.entrySet()){
            System.out.println(String.format("%s\t|\t%s", entry.getKey(), entry.getValue()));
        }

    }
}
