import Categories.Category;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    private Lexicon lexicon;
    private ArrayList<ArrayList<Set<Category>>> chart;
    private ArrayList<Set<Category>> sentenceCategories = new ArrayList<>();

    public Parser(Lexicon lexicon, ArrayList<ArrayList<Set<Category>>> chart) {
        this.lexicon = lexicon;
        this.chart = chart;
    }

    public Parser(Lexicon lexicon) {
        this.lexicon = lexicon;
        this.chart = new ArrayList<>();
    }

    public void parse(String sentence){
        //TODO: Currently does not work with words like New York (or any entry that has space between it)
        String[] sentenceArray = sentence.split(" ");
        this.sentenceCategories = getCategoriesFromLexicon(sentenceArray);
        this.buildChartCells();
        //start building chart

        // for each span
        for (int span = 2 ; span<this.sentenceCategories.size() ; span ++){
            //for each start
            for(int start = 0 ; start <= this.sentenceCategories.size() - span ; start++){
                // for each break
                for(int spanBreak = 1 ; spanBreak < span ; spanBreak++){
                    //consider if [start - spanBreak] and [spanBreak+1 - span) combines
                }
            }
        }
    }

    private ArrayList<Set<Category>> getCategoriesFromLexicon(String[] sentenceArray){
        ArrayList<Set<Category>> categories = new ArrayList<>();
        for(int i = 0 ; i < sentenceArray.length ; i++){
            String word = sentenceArray[i];
            categories.add(this.lexicon.get(word));
        }
        return categories;
    }

    private void buildChartCells(){
        for(int i = 0; i < this.sentenceCategories.size()-1 ; i++){
            ArrayList<Set<Category>> chartRow = new ArrayList<>();
            for (int j = 0 ; j < i+1 ; j++){
                chartRow.add(new HashSet<Category>());
            }
            this.chart.add(chartRow);
        }
        this.chart.add(this.sentenceCategories);
    }

    public ArrayList<ArrayList<Set<Category>>> getChart(){
        return this.chart;
    }
}
