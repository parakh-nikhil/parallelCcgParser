import Categories.Category;
import Categories.LeftSlash;
import Categories.RightSlash;
import Categories.SlashCategory;

public class Rules {
    public Rules(){}

    public static Category combine(Category cat1, Category cat2, Lexicon lexicon) throws Exception {
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

        // Forward Application
        if(slashCat1!=null && slashCat2 == null){
            // (X/Y) + (Y) = X
            if(slashCat1.isRightSlash() && slashCat1.getArgument() == cat2){
                return lexicon.buildCategory(slashCat1.getResult());
            }
        }

        // Backward Application
        else if(slashCat1==null && slashCat2 != null){
            // Y + (X\Y) = X
            if(!slashCat2.isRightSlash() && slashCat2.getArgument() == cat1){
                return lexicon.buildCategory(slashCat2.getResult());
            }
        }

        // Forward Composition:
        else if(slashCat1.isRightSlash() && slashCat2.isRightSlash()){
            // (X/Y) + (Y/Z) = (X/Z)
            if(slashCat1.getArgument() == slashCat2.getResult()){
                return lexicon.buildCategory(new RightSlash(slashCat1.getResult(), slashCat2.getArgument()));
            }
        }

        // Backward Composition:
        else if(!slashCat1.isRightSlash() && !slashCat2.isRightSlash()){
            // (Y\Z) + (X\Y) = (X\Z)
            if(slashCat1.getResult() == slashCat2.getArgument()){
                return lexicon.buildCategory(new RightSlash(slashCat2.getResult(), slashCat1.getArgument()));
            }

            // (X\Y) + (Y\Z) = (X\Z)
            else if(slashCat1.getArgument() == slashCat2.getResult()){
                return lexicon.buildCategory(new RightSlash(slashCat1.getResult(), slashCat2.getArgument()));
            }
        }

        // Crossing Compositions
        else if(slashCat1.isRightSlash() && !slashCat2.isRightSlash()){
            // Forward Crossing Composition: (X/Y) + (Y\Z) = (X\Z)
            if(slashCat1.getArgument() == slashCat2.getResult()){
                return lexicon.buildCategory(new LeftSlash(slashCat1.getResult(), slashCat2.getArgument()));
            }
            // Backward Crossing Composition: (Y/Z) + (X\Y) = (X/Z)
            if(slashCat1.getResult() == slashCat2.getArgument()){
                return lexicon.buildCategory(new RightSlash(slashCat2.getResult(), slashCat1.getArgument()));
            }
        }

        return null;
    }


}
