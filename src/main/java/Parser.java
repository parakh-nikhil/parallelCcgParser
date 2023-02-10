import Categories.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
                for(int spanBreak = start+1 ; spanBreak < start+span ; spanBreak++){
                    //TODO: consider if [start - spanBreak] and [spanBreak+1 - span) combines
                    Set<Category> cell1 = this.chart.get(spanBreak-start-1).get(start); //WORKS
                    Set<Category> cell2 = this.chart.get((start + span ) - spanBreak - 1).get(spanBreak); //WORKS
                    //if yes for any, update the chart[span-1][start] cell
                    Set<Category> result = new HashSet<>();
                    Iterator<Category> cell1Iterator = cell1.iterator();
                    Iterator<Category> cell2Iterator = cell2.iterator();
                    while(cell1Iterator.hasNext()){
                        Category c1Entry = cell1Iterator.next();
                        while(cell2Iterator.hasNext()){
                            Category c2Entry = cell2Iterator.next();
                            Category resultCell = this.combine(c1Entry,c2Entry);
                            if(resultCell != null){
                                result.add(resultCell);
                            }

                        }
                    }
                    this.chart.get(span-1).set(start, result);
                }
            }
        }
        return this.chart;
    }

    private Category combine(Category cat1, Category cat2) throws Exception {
        if(cat1 == null || cat2 == null){
            return null;
        }
        if(cat1.isSlash()){
            SlashCategory cat1Slash = cat1.asSlash();
            // (X/Y) + (Y) = X ; forward application
            if(cat1Slash.isRightSlash() && (cat1Slash.getArgument() == cat2)){
                return cat1Slash.getResult();
            }
            // (X/Y) + (Y/Z) = (X/Z)
            else if(cat2.isSlash()){
                SlashCategory cat2Slash = cat1.asSlash();
                if(cat1Slash.isRightSlash() && (cat1Slash.getArgument() == cat2Slash.getResult())){
                    return new RightSlash(cat1Slash.getResult(), cat2Slash.getArgument());
                }
            }
        }
        if (cat2.isSlash()){
            SlashCategory cat2Slash = cat2.asSlash();
            // Y + (X\Y) = X
            if(!cat2Slash.isRightSlash() && (cat1 == cat2Slash.getArgument())){
                return cat2Slash.getResult();
            }

            // (X\Y) + (Y\Z) = (X\Z)
            else if(cat1.isSlash()){
                SlashCategory cat1Slash = cat1.asSlash();
                if(!cat1Slash.isRightSlash() && (cat1Slash.getArgument() == cat2Slash.getResult())){
                    return new LeftSlash(cat1Slash.getResult(), cat2Slash.getArgument());
                }
            }
        }
       return null;
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
        this.chart.add(this.sentenceCategories);
        for(int i = 1; i < this.sentenceCategories.size() ; i++){
            ArrayList<Set<Category>> chartRow = new ArrayList<>();
            for (int j = 0 ; j < this.sentenceCategories.size()-i ; j++){
//                Set<Category> test = new HashSet<>();
//                test.add(new TestCategory("Row: " + i));
//                test.add(new TestCategory("Col: " + j));
//                chartRow.add(test);
                chartRow.add(new HashSet<Category>());
            }
            this.chart.add(chartRow);
        }

    }

    public ArrayList<ArrayList<Set<Category>>> getChart(){
        return this.chart;
    }
}
