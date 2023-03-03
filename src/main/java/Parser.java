import Categories.*;

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

    public ArrayList<ArrayList<Set<Category>>> parse(String sentence) throws Exception {
        //TODO: for multiple sentences this.chart builds upon the previous chart. Considering making a chart class.
        //TODO: Currently does not work with words like New York (or any entry that has space between it)
        String[] sentenceArray = sentence.split(" ");
        this.sentenceCategories = getCategoriesFromLexicon(sentenceArray);
        if(sentenceCategories == null){
            return null;
        }
        this.buildChartCells();
        //start building chart

        // for each span
        for (int span = 2 ; span<this.sentenceCategories.size() + 1 ; span ++){
            //for each start
            for(int start = 0 ; start <= this.sentenceCategories.size() - span ; start++){
                // for each break
                Set<Category> result = new HashSet<>();
                for(int spanBreak = start+1 ; spanBreak < start+span ; spanBreak++){
                    //TODO: consider if [start - spanBreak] and [spanBreak+1 - span) combines
                    Set<Category> cell1 = this.chart.get(spanBreak-start-1).get(start);
                    Set<Category> cell2 = this.chart.get((start + span ) - spanBreak - 1).get(spanBreak);

                    //if yes for any, update the chart[span-1][start] cell
                    for(Category c1 : cell1){
                        for(Category c2 : cell2){
                            Category resultCell = Rules.combine(c1,c2, this.lexicon);
                            if(resultCell != null){
                                result.add(resultCell);
                            }
                        }
                    }
                }
                if(result.isEmpty()){
                    result.add(null);
                }
                this.chart.get(span-1).set(start, result);
            }
        }
        Set<Category> root = this.chart.get(this.sentenceCategories.size() - 1).get(0);
        if (root.contains(null)){
//            return this.chart;
            return null;
        }
        return this.chart;
    }


    private ArrayList<Set<Category>> getCategoriesFromLexicon(String[] sentenceArray){
        ArrayList<Set<Category>> categories = new ArrayList<>();
        Set<String> notFound = new HashSet<>();
        for(int i = 0 ; i < sentenceArray.length ; i++){
            String word = sentenceArray[i];
            if(lexicon.containsKey(word)){
                categories.add(this.lexicon.get(word));
            }
            else{
                notFound.add(word);

            }

        }
        if(!notFound.isEmpty()){
            System.out.println(String.format("Word%s %s not found in the given lexicon.",(notFound.size() > 1 ? "s" : ""), notFound));
            return null;
        }
        return categories;
    }

    private void buildChartCells(){
        this.chart.add(this.sentenceCategories);
        for(int i = 1; i < this.sentenceCategories.size() ; i++){
            ArrayList<Set<Category>> chartRow = new ArrayList<>();
            for (int j = 0 ; j < this.sentenceCategories.size()-i ; j++){
                chartRow.add(new HashSet<>());
            }
            this.chart.add(chartRow);
        }

    }

    public void clearChart(){
        this.chart = new ArrayList<>();
    }
    public ArrayList<ArrayList<Set<Category>>> getChart(){
        return this.chart;
    }
}
