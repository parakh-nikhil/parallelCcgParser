package Parser;

import Categories.N;
import Categories.NP;
import Language.Grammar;
import Language.Lexicon;

import java.util.HashSet;
import java.util.Set;

public class CombineThread extends Thread{

    Set<ParseTree> cell1;
    Set<ParseTree> cell2;
    Lexicon lexicon;
    Set<ParseTree> result;

    public CombineThread(Set<ParseTree> cell1, Set<ParseTree> cell2, Lexicon lexicon,Set<ParseTree> result){
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.lexicon = lexicon;
        this.result = result;
    }

    @Override
    public void run() {
        for(ParseTree c1 : this.cell1){
            for(ParseTree c2 : this.cell2){
                ParseTree resultCell = null;
                try {
                    resultCell = Grammar.combine(c1,c2, this.lexicon);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if(resultCell != null){
                    result.add(resultCell);
                    // Type raising N to NP
                    if(resultCell.getCategory() == N.getInstance()){
                        Pair<ParseTree, ParseTree> resultChildren = resultCell.children();
                        result.add(new ParseTree(NP.getInstance(), resultChildren.getKey(),resultChildren.getVal(), resultCell.getSentenceFragment()));
                    }
                }
            }
        }
    }
}
