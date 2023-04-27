package Language;

import Categories.Category;
import Categories.LeftSlash;
import Categories.RightSlash;
import Categories.SlashCategory;
import Parser.ParseTree;
import Parser.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Grammar {
    public Grammar(){}

    public static ParseTree combine(ParseTree tree1, ParseTree tree2, Lexicon lexicon) throws Exception {
        if(tree1 == null || tree2 == null){
            return null;
        }
        RULES tree1Rule = tree1.getCreatedByRule();
        RULES tree2Rule = tree2.getCreatedByRule();
        Set<RULES> eisnerNFConstraint1Rules = new HashSet<>(Arrays.asList(RULES.FORWARD_COMPOSITION, RULES.BACKWARD_COMPOSITION, RULES.FORWARD_CROSSING_COMPOSITION, RULES.BACKWARD_CROSSING_COMPOSITION));
        Category cat1 = tree1.getCategory();
        Category cat2 = tree2.getCategory();
        if(cat1 == null || cat2 == null){
            return null;
        }
        SlashCategory slashCat1 = null;
        SlashCategory slashCat2 = null;

        if(cat1.isSlash()){
            slashCat1 = cat1.asSlash();
        }
        if(cat2.isSlash()) {
            slashCat2 = cat2.asSlash();
        }
        if(slashCat1==null && slashCat2 == null){
            return null;
        }

        // Forward Application:
        // TODO: function cant be forward func composition
        if(slashCat1!=null && slashCat2 == null){
            // (X/Y) + (Y) = X
            if(slashCat1.isRightSlash() && slashCat1.getArgument() == cat2){
                Category result = lexicon.buildCategory(slashCat1.getResult());
                return new ParseTree(result,tree1,tree2, RULES.FORWARD_APPLICATION);
            }
        }

        // Backward Application
        else if(slashCat1==null && slashCat2 != null){
            // Y + (X\Y) = X
            if(!slashCat2.isRightSlash() && slashCat2.getArgument() == cat1){
                return new ParseTree(lexicon.buildCategory(slashCat2.getResult()),tree1,tree2, RULES.BACKWARD_APPLICATION);
            }
        }else if(!eisnerNFConstraint1Rules.contains(tree1Rule) && !eisnerNFConstraint1Rules.contains(tree2Rule)){
            // Forward Composition:
            // TODO: left cant be same composition
            if(slashCat1.isRightSlash() && slashCat2.isRightSlash()){
                // (X/Y) + (Y/Z) = (X/Z)
                if(slashCat1.getArgument() == slashCat2.getResult()){
                    return new ParseTree(lexicon.buildCategory(new RightSlash(slashCat1.getResult(), slashCat2.getArgument())),tree1,tree2, RULES.FORWARD_COMPOSITION);
                }
            }

            // Backward Composition:
            else if(!slashCat1.isRightSlash() && !slashCat2.isRightSlash()){
                // (Y\Z) + (X\Y) = (X\Z)
                if(slashCat1.getResult() == slashCat2.getArgument()){
                    return new ParseTree(lexicon.buildCategory(new RightSlash(slashCat2.getResult(), slashCat1.getArgument())),tree1,tree2, RULES.BACKWARD_COMPOSITION);
                }

                // (X\Y) + (Y\Z) = (X\Z)
                else if(slashCat1.getArgument() == slashCat2.getResult()){
                    return new ParseTree(lexicon.buildCategory(new RightSlash(slashCat1.getResult(), slashCat2.getArgument())),tree1,tree2, RULES.BACKWARD_COMPOSITION);
                }
            }

            // Crossing Compositions
            else if(slashCat1.isRightSlash() && !slashCat2.isRightSlash()){
                // Forward Crossing Composition: (X/Y) + (Y\Z) = (X\Z)
                if(slashCat1.getArgument() == slashCat2.getResult()){
                    return new ParseTree(lexicon.buildCategory(new LeftSlash(slashCat1.getResult(), slashCat2.getArgument())),tree1,tree2, RULES.FORWARD_CROSSING_COMPOSITION);
                }
                // Backward Crossing Composition: (Y/Z) + (X\Y) = (X/Z)
                if(slashCat1.getResult() == slashCat2.getArgument()){
                    return new ParseTree(lexicon.buildCategory(new RightSlash(slashCat2.getResult(), slashCat1.getArgument())),tree1,tree2, RULES.BACKWARD_CROSSING_COMPOSITION);
                }
            }

        }
        return null;
    }


}
