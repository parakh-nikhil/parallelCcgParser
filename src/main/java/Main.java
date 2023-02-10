import Categories.*;

import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello CCG Parser");

        //TODO: should keys be case-sensitive or not
        Lexicon lexicon = new Lexicon();
        lexicon.initializeEntries();

        String sentence = "Tom likes apples";
        String sentence2 = "I booked a flight to KTM";

        Parser parser = new Parser(lexicon);
        try{
            parser.parse(sentence2);
        }catch (Exception e){
            System.out.println(e);
        }


        ArrayList<ArrayList<Set<Category>>> chart = parser.getChart();
        for (ArrayList<Set<Category>> row : chart){
            for(Set<Category> cell : row){
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
}
