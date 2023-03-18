package Language;

import Categories.*;
import LexiconPopulator.*;

import java.io.File;
import java.util.*;

public class Lexicon extends HashMap<String, Set<Category>> {

    private Map<String, Category> stringCategoryMap = new HashMap<>();
    public void addEntry(String str, Category category){
        Set<Category> newSet;
        if(this.containsKey(str)){
            newSet = this.get(str);
        }
        else{
            newSet = new HashSet<>();
        }
        newSet.add(category);
        this.put(str, newSet);
    }

    public void initializeEntries(){
        List<LexiconPopulator> populators = new ArrayList<>();
        File ccgPopulator1 = new File("/src/main/java/LexiconPopulator/Populator1.java");
        File ccgPopulator2 = new File("/src/main/java/LexiconPopulator/Populator2.java");

        if (ccgPopulator1.exists()){
            LexiconPopulator populator1 = new Populator1(this);
            populators.add(populator1);
        }
        if (ccgPopulator2.exists()){
            LexiconPopulator populator2 = new Populator2(this);
            populators.add(populator2);
        }
        if(!ccgPopulator1.exists() && !ccgPopulator2.exists()){
            LexiconPopulator customPopulator = new CustomPopulator(this);
            populators.add(customPopulator);
        }

        for(LexiconPopulator populator : populators){
            populator.populate_all();
        }

    }

    public Category buildCategory(Category category){
        //TODO: maybe try adding this code in the constructor itself. Would also make the slash types somewhat singleton.
        //TODO: make this recursive so it builds from groundUp
        String categoryString = category.toString();
        if(this.stringCategoryMap.containsKey(categoryString)){
            return this.stringCategoryMap.get(categoryString);
        }
        this.stringCategoryMap.put(categoryString, category);
        return category;
    }
}
