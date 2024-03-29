import Categories.*;
import Language.Lexicon;
import Language.Sentence;
import Parser.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// TODO use a profiler (to check which part is taking too long)
public class Main {
    public static void main(String[] args) {
        System.out.println("--- CCG Parser.Parser ---\n");

        //TODO: should keys be case-sensitive or not
        Lexicon lexicon = new Lexicon();
        lexicon.initializeEntries();
        List<String> sentences = Sentence.getFinalSentences();
        List<List<ConcurrentSet<ParseTree>>> parsedChart = new ArrayList<>();
        Parser parser = new Parser(lexicon);
        long totalParseTimeStart = Instant.now().toEpochMilli();
        for(String sentence : sentences){

            sentence = sentence.strip();
            System.out.println("Sentence: " + sentence);
            System.out.println("Parsing...");
            try{
                long start = Instant.now().toEpochMilli();
                parsedChart = parser.parse(sentence);
                System.out.println("CONCURRENT PARSE TIME: " + (Instant.now().toEpochMilli() - start) + "ms");
            }catch (Exception e){
                System.out.println(e);
            }
            if(parsedChart == null || parsedChart.size()==0){
                System.out.println("\tGiven sentence is not grammatically correct!");
            }
            else if(parsedChart.get(sentence.strip().split(" ").length - 1).get(0).size() == 0){
                System.out.println("\tGiven sentence is not grammatically correct!");
            }
            else{
                System.out.println("\tSentence successfully parsed.");
//                printChart(parsedChart);
//                ConcurrentSet<ParseTree> rootTrees = parsedChart.get(sentence.strip().split(" ").length - 1).get(0);
//                Iterator<ParseTree> rootTreesIterator = rootTrees.iterator();
//                while(rootTreesIterator.hasNext()){
//                    ParseTree root = rootTreesIterator.next();
//                    if(root.getCategory() == S.getInstance()){
//                        printRootTreeStackTrace(root,1);
//                        break;
//                    }
////                    System.out.println("\n-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --\n");
//                }
            }
            parser.clearChart();
            System.out.println("\n-----------------------------------------------------------------");

        }

        System.out.println("TOTAL CONCURRENT PARSE TIME: " + (Instant.now().toEpochMilli() - totalParseTimeStart) + "ms");




    }

    public static void printChart(ArrayList<ArrayList<Set<ParseTree>>> chart){
        for (ArrayList<Set<ParseTree>> row : chart){
            for(Set<ParseTree> cell : row){
                System.out.print(String.format("%-30s", cell));
            }
            System.out.println();
        }
    }

    public static void printRootTreeStackTrace(ParseTree root, int spaces){
        if(root == null){
            System.out.println(String.format("%s%s) %s"," ".repeat(2*spaces),spaces,null));
        }
        else{
            System.out.println(String.format("%s%s) %s (%s)", " ".repeat(2*spaces),spaces,root.getCategory(), root.getSentenceFragment()));
            Pair<ParseTree, ParseTree> children = root.children();
            spaces++;
            printRootTreeStackTrace(children.getKey(), spaces);
            printRootTreeStackTrace(children.getVal(), spaces);
        }
    }
}
