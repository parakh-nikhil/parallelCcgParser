package SemanticsGenerator;

import Parser.Pair;
import Parser.ParseTree;

import java.util.List;

public class Generator {
    List<ParseTree> sentenceTrees;
    String sentence;
    public Generator(List<ParseTree> sentenceTrees, String sentence){
        this.sentenceTrees = sentenceTrees;
        this.sentence = sentence;
    }

    public void printTrees(){
        for(ParseTree parse : this.sentenceTrees){
            System.out.println("\nTree printed from SemanticsGenerator Package");
            this.printRootTreeStackTrace(parse,1);
            break;
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
