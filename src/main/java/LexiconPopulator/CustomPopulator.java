package LexiconPopulator;

import Categories.*;
import Language.Lexicon;

public class CustomPopulator extends LexiconPopulator{
    Lexicon lexicon;

    public CustomPopulator(Lexicon lexicon){
        super(lexicon);
    }

    private Category ADJ(){
        return ADJ.getInstance();
    }
    private Category DET(){
        return Det.getInstance();
    }
    private Category TV(){
        return RSlash(LSlash(S(),N()),N());
                //LeftSlash(S.getInstance(), NP.getInstance()), NP.getInstance())
    }
    @Override
    public void populate_all() {
        lexicon.addEntry("Tom", N());
        lexicon.addEntry("likes", TV());
        lexicon.addEntry("apples", N());

        lexicon.addEntry("I", N());
        lexicon.addEntry("booked", TV());
        lexicon.addEntry("a", DET());
        lexicon.addEntry("to", lexicon.buildCategory(new RightSlash(PP(), N())));
        lexicon.addEntry("flight", lexicon.buildCategory(new LeftSlash(lexicon.buildCategory(new RightSlash(N(), PP())), DET())));
        lexicon.addEntry("KTM", N());

        lexicon.addEntry("than", lexicon.buildCategory(new RightSlash(PP(), N())));
        lexicon.addEntry("1",N());
        lexicon.addEntry("5",N());
        lexicon.addEntry("is", lexicon.buildCategory(new RightSlash(lexicon.buildCategory(new LeftSlash(S(),N())),ADJ())));
        lexicon.addEntry("less", lexicon.buildCategory(new RightSlash(ADJ(),PP())));

        lexicon.addEntry("10", N());
        lexicon.addEntry("equal", lexicon.buildCategory(new RightSlash(ADJ(),PP())));
        lexicon.addEntry("greater", lexicon.buildCategory(new RightSlash(ADJ(),PP())));
        lexicon.addEntry("or", lexicon.buildCategory(new LeftSlash(lexicon.buildCategory(new RightSlash(ADJ(),ADJ())), ADJ())));
        lexicon.addEntry("or", lexicon.buildCategory(new RightSlash(lexicon.buildCategory(new RightSlash(ADJ(),N())),lexicon.buildCategory(new RightSlash(ADJ(),N())))));
        lexicon.addEntry("float", N());
        lexicon.addEntry("any", lexicon.buildCategory(new RightSlash(lexicon.buildCategory(new RightSlash(lexicon.buildCategory(new RightSlash(S(),lexicon.buildCategory(new LeftSlash(S(),N())))),ADJ())),N())));
//        lexicon.addEntry("that", lexicon.buildCategory(new LeftSlash(new RightSlash(N(), new LeftSlash(S(),N())),N())));
        lexicon.addEntry("that", lexicon.buildCategory(new RightSlash(lexicon.buildCategory(new LeftSlash(N(),N())),lexicon.buildCategory(new RightSlash(S(),N())))));
        lexicon.addEntry("passing",ADJ());
    }
}
