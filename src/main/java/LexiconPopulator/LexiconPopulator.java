package LexiconPopulator;

import Categories.*;
import Language.Lexicon;

public abstract class LexiconPopulator {
    Lexicon lexicon;
    String tmp = null;

    LexiconPopulator(Lexicon lexicon){
        this.lexicon = lexicon;
    }

    protected Category N(){
        return N.getInstance();
    }

    protected Category S(){
        return S.getInstance();
    }

    protected Category PP(){
        return PP.getInstance();
    }

    protected Category NP(){
        return NP.getInstance();
    }

    protected Category PUNC(){
        return new PUNC();
    }

    protected Category conj(){
        return new CONJ();
    }
    protected Category RSlash(Category result, Category argument){
        return new RightSlash(result, argument);
    }

    protected Category LSlash(Category result, Category argument){
        return new LeftSlash(result, argument);
    }

    public abstract void populate_all();
}
