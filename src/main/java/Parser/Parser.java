package Parser;

import Categories.*;
import Language.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    private Lexicon lexicon;
    private ArrayList<ArrayList<Set<ParseTree>>> chart;
    private ArrayList<Set<ParseTree>> sentenceCategories = new ArrayList<>();

    public Parser(Lexicon lexicon, ArrayList<ArrayList<Set<ParseTree>>> chart) {
        this.lexicon = lexicon;
        this.chart = chart;
    }

    public Parser(Lexicon lexicon) {
        this.lexicon = lexicon;
        this.chart = new ArrayList<>();
    }

    public ArrayList<ArrayList<Set<ParseTree>>> parse(String sentence) throws Exception {

        String[] sentenceArray = sentence.split(" ");
        this.sentenceCategories = getCategoriesFromLexicon(sentenceArray);
        if(this.sentenceCategories == null){
            return null;
        }
        //start building chart
        this.buildChartCells();

        // for each span
        for (int span = 2 ; span<this.sentenceCategories.size() + 1 ; span ++){
            //for each start
            for(int start = 0 ; start <= this.sentenceCategories.size() - span ; start++){
                // for each break
                Set<ParseTree> result = new HashSet<>();
                for(int spanBreak = start+1 ; spanBreak < start+span ; spanBreak++){
                    //TODO: consider if [start - spanBreak] and [spanBreak+1 - span) combines
                    Set<ParseTree> cell1 = this.chart.get(spanBreak-start-1).get(start);
                    Set<ParseTree> cell2 = this.chart.get((start + span ) - spanBreak - 1).get(spanBreak);

                    //if yes for any, update the chart[span-1][start] cell
                    for(ParseTree c1 : cell1){
                        for(ParseTree c2 : cell2){
                            ParseTree resultCell = Grammar.combine(c1,c2, this.lexicon);
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
        Set<ParseTree> root = this.chart.get(this.sentenceCategories.size() - 1).get(0);
        if (root.contains(null)){
//            return this.chart;
            return null;
        }
        return this.chart;
    }


    private ArrayList<Set<ParseTree>> getCategoriesFromLexicon(String[] sentenceArray){
        ArrayList<Set<ParseTree>> categories = new ArrayList<>();
        Set<String> notFound = new HashSet<>();
        for(int i = 0 ; i < sentenceArray.length ; i++){
            String word = sentenceArray[i];
            if(lexicon.containsKey(word)){
                Set<ParseTree> parseTreeSetFromLexicon = new HashSet<>();
                System.out.println("\n" + word);
                for (Category category : this.lexicon.get(word)){
                    System.out.println("\t" + category);
                    parseTreeSetFromLexicon.add(new ParseTree(category, null,null, word));
                }
                categories.add(parseTreeSetFromLexicon);
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
            ArrayList<Set<ParseTree>> chartRow = new ArrayList<>();
            for (int j = 0 ; j < this.sentenceCategories.size()-i ; j++){
                chartRow.add(new HashSet<>());
            }
            this.chart.add(chartRow);
        }

    }

    public void clearChart(){
        this.chart = new ArrayList<>();
    }
    public ArrayList<ArrayList<Set<ParseTree>>> getChart(){
        return this.chart;
    }
}
