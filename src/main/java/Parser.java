import Categories.Category;

import java.util.ArrayList;
import java.util.Set;

public class Parser {
    private Lexicon lexicon;
    private ArrayList<ArrayList<Set<Category>>> chart;

    public Parser(Lexicon lexicon, ArrayList<ArrayList<Set<Category>>> chart) {
        this.lexicon = lexicon;
        this.chart = chart;
    }

    public Parser(Lexicon lexicon) {
        this.lexicon = lexicon;
        this.chart = new ArrayList<>();
    }

    public void parse(String sentence){
        String[] sentenceArray = sentence.split(" ");
        ArrayList<Set<Category>> categories = new ArrayList<>();
        for(int i = 0 ; i < sentenceArray.length ; i++){
            String word = sentenceArray[i];
            categories.add(this.lexicon.get(word));
        }
        this.chart.add(categories);
    }

    public ArrayList<ArrayList<Set<Category>>> getChart(){
        return this.chart;
    }
}
