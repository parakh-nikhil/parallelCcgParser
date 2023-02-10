import Categories.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- CCG Parser ---\n");

        //TODO: should keys be case-sensitive or not
        Lexicon lexicon = new Lexicon();
        lexicon.initializeEntries();

        List<String> sentences = new ArrayList<>(Arrays.asList("1 is less than 5","Tom likes apples", "I booked a flight to KTM", "I want apples", "I want to fly a kite in the sky"));

        ArrayList<ArrayList<Set<Category>>> parsedChart = new ArrayList<>();
        Parser parser = new Parser(lexicon);
        for(String sentence : sentences){
            sentence = sentence.strip();
            System.out.println("Sentence: " + sentence);
            System.out.println("Parsing...");
            try{
                parsedChart = parser.parse(sentence);
            }catch (Exception e){
                System.out.println(e);
            }
            if(parsedChart == null){
                System.out.println("\tGiven sentence is not grammatically correct!");
            }
            else{
                System.out.println("\tSentence successfully parsed.");
                printChart(parsedChart);
                parsedChart.clear();
            }
            parser.clearChart();
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------");

        }




    }

    public static void printChart(ArrayList<ArrayList<Set<Category>>> chart){
        for (ArrayList<Set<Category>> row : chart){
            for(Set<Category> cell : row){
                System.out.print(String.format("%-30s", cell));
            }
            System.out.println();
        }
    }
}
