package Parser;

import Categories.N;
import Categories.NP;
import Language.Grammar;
import Language.Lexicon;
import Language.RULES;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class CombineThread extends Thread{

    ConcurrentSet<ParseTree> cell1;
    ConcurrentSet<ParseTree> cell2;
    Lexicon lexicon;
    ConcurrentSet<ParseTree> result;

    public CombineThread(ConcurrentSet<ParseTree> cell1, ConcurrentSet<ParseTree> cell2, Lexicon lexicon,ConcurrentSet<ParseTree> result){
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.lexicon = lexicon;
        this.result = result;
    }

    @Override
    public void run() {
        Iterator<ParseTree> cell1Iterator = cell1.iterator();
        while(cell1Iterator.hasNext()){
            Iterator<ParseTree> cell2Iterator = cell2.iterator();
            ParseTree c1 = cell1Iterator.next();
            while(cell2Iterator.hasNext()){
                ParseTree c2 = cell2Iterator.next();
                ParseTree resultCell = null;
                try {
                    resultCell = Grammar.combine(c1,c2, this.lexicon);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if(resultCell != null){
                    this.result.add(resultCell);
                    // Type raising N to NP
                    if(resultCell.getCategory() == N.getInstance()){
                        Pair<ParseTree, ParseTree> resultChildren = resultCell.children();
                        result.add(new ParseTree(NP.getInstance(), resultChildren.getKey(),resultChildren.getVal(), resultCell.getSentenceFragment(), RULES.TYPE_RAISING));
                    }
                }
            }
        }
    }
}
