package Parser;

import Categories.*;
import Language.*;

import java.util.*;

public class Parser {
    private Lexicon lexicon;
    private List<List<ConcurrentSet<ParseTree>>> chart = new ArrayList<>();
//    private ArrayList<ArrayList<Set<ParseTree>>> chart;
    private ArrayList<ConcurrentSet<ParseTree>> sentenceCategories = new ArrayList<>();

    public Parser(Lexicon lexicon, List<List<ConcurrentSet<ParseTree>>> chart) {
        this.lexicon = lexicon;
        this.chart = chart;
    }

    public Parser(Lexicon lexicon) {
        this.lexicon = lexicon;
        this.chart = new ArrayList<>();
    }

    public List<List<ConcurrentSet<ParseTree>>> parse(String sentence) throws Exception {

        String[] sentenceArray = sentence.split(" ");
        this.sentenceCategories = getCategoriesFromLexicon(sentenceArray);
//        for(int i = 0 ; i<this.sentenceCategories.size() ; i++){
//            System.out.println(sentenceArray[i]);
//            System.out.println(this.sentenceCategories.get(i));
//        }
        if(this.sentenceCategories == null){
            return null;
        }
        //start building chart
        this.buildChartCells();

        int nullCount = 0;
        // Log the checkpoint rows.
        // for each span
        for (int span = 2 ; span<this.sentenceCategories.size() + 1 ; span ++){
            //for each start
            for(int start = 0 ; start <= this.sentenceCategories.size() - span ; start++){
                // for each break
                ConcurrentSet<ParseTree> result = new ConcurrentSet<ParseTree>();
                Set<CombineThread> threads = new HashSet<>();
                for(int spanBreak = start+1 ; spanBreak < start+span ; spanBreak++){
                    //TODO: consider if [start - spanBreak] and [spanBreak+1 - span) combines
                    ConcurrentSet<ParseTree> cell1 = this.chart.get(spanBreak-start-1).get(start);
                    ConcurrentSet<ParseTree> cell2 = this.chart.get((start + span ) - spanBreak - 1).get(spanBreak);
                    //if yes for any, update the chart[span-1][start] cell
                    CombineThread t = new CombineThread(cell1,cell2,lexicon,result);
                    threads.add(t);
                    t.start();
                }
                for(CombineThread t: threads){
                    t.join();
                }
                this.chart.get(span-1).set(start, result);
                threads.clear();
            }
            System.out.println();
//            System.out.println(String.format("ROW %d: Parsed | %s", span-1,this.chart.get(span-1) ));
        }
        ConcurrentSet<ParseTree> root = this.chart.get(this.sentenceCategories.size() - 1).get(0);
        if (root.isEmpty()){
            return null;
        }
        Iterator<ParseTree> rootIterator = root.iterator();
        int sCount = 0;
        while(rootIterator.hasNext()){
            ParseTree c = rootIterator.next();
            if(c.getCategory() == S.getInstance()){
                sCount++;
            }
        }
        System.out.println("Total Root Categories: " + root.size());
        System.out.println("Sentences = " + sCount);
        return this.chart;
    }


    private ArrayList<ConcurrentSet<ParseTree>> getCategoriesFromLexicon(String[] sentenceArray){
        ArrayList<ConcurrentSet<ParseTree>> categories = new ArrayList<>();
        Set<String> notFound = new HashSet<>();
        for(int i = 0 ; i < sentenceArray.length ; i++){
            String word = sentenceArray[i];
            if(lexicon.containsKey(word)){
                ConcurrentSet<ParseTree> parseTreeSetFromLexicon = new ConcurrentSet<ParseTree>();
                for (Category category : this.lexicon.get(word)){
                    parseTreeSetFromLexicon.add(new ParseTree(category, null,null, word, null));
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
            ArrayList<ConcurrentSet<ParseTree>> chartRow = new ArrayList<>();
            for (int j = 0 ; j < this.sentenceCategories.size()-i ; j++){
                chartRow.add(new ConcurrentSet<ParseTree>());
            }
            this.chart.add(chartRow);
        }

    }

    public void clearChart(){
        this.chart.clear();
    }

    public List<List<ConcurrentSet<ParseTree>>> getChart(){
        return this.chart;
    }
}
