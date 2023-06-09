import Categories.*;
import Language.Lexicon;
import Language.Sentence;
import Parser.*;
import SemanticsGenerator.Generator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import LambdaCalculus.lambda.Expression;
import SemanticsGenerator.LambdaCalc;

import static LambdaCalculus.lambda.Lambda.var;

// TODO use a profiler (to check which part is taking too long)
public class Main {
    public static void main(String[] args) {
            System.out.println("--- CCG Parser.Parser ---\n");
            //TODO: should keys be case-sensitive or not
            Lexicon lexicon = new Lexicon();
            lexicon.initializeEntries();
            List<String> sentences = Sentence.getSimpleSentences();
//        List<String> sentences = Sentence.getSoftwareSentences();
//        List<String> sentences = Sentence.getComplexSentenceUsingBasicCombinatoryRules();
//        List<String> sentences = Sentence.getIncorrectSentences();
            List<List<ConcurrentSet<ParseTree>>> parsedChart = new ArrayList<>();
            Parser parser = new Parser(lexicon);
            long totalParseTimeStart = Instant.now().toEpochMilli();
            for (String sentence : sentences) {
                sentence = sentence.strip();
                System.out.println("Sentence: " + sentence);
                System.out.println("Parsing...");
                try {
                    long start = Instant.now().toEpochMilli();
                    parsedChart = parser.parse(sentence);
                    System.out.println("CONCURRENT PARSE TIME: " + (Instant.now().toEpochMilli() - start) + "ms");
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (parsedChart == null || parsedChart.size() == 0) {
                    System.out.println("\tGiven sentence is not grammatically correct!");
                } else if (parsedChart.get(sentence.strip().split(" ").length - 1).get(0).size() == 0) {
                    System.out.println("\tGiven sentence is not grammatically correct!");
                } else {
                    System.out.println("\tSentence successfully parsed.");
//                printChart(parsedChart);
                    ConcurrentSet<ParseTree> rootTrees = parsedChart.get(sentence.strip().split(" ").length - 1).get(0);
                    Iterator<ParseTree> rootTreesIterator = rootTrees.iterator();
                    List<ParseTree> sentenceTrees = new ArrayList<>();
                    while (rootTreesIterator.hasNext()) {
                        ParseTree root = rootTreesIterator.next();
                        if (root.getCategory() == S.getInstance()) {
                            sentenceTrees.add(root);
                        }
                    }
                    Generator generator = new Generator(sentenceTrees, sentence);
                    generator.printTrees();
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
}
