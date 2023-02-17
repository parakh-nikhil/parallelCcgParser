import Categories.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Category np = this.buildCategory(NP.getInstance());
        Category tv = this.buildCategory(new TV());
        Category det = this.buildCategory(Det.getInstance());
        Category pp_to = this.buildCategory(new PP("to"));
        Category pp_than = this.buildCategory(new PP("than"));
        Category s = this.buildCategory(S.getInstance());
        Category adj = this.buildCategory(ADJ.getInstance());


        this.addEntry("Tom", np);
        this.addEntry("likes", tv);
        this.addEntry("apples", np);

        this.addEntry("I", np);
        this.addEntry("booked", tv);
        this.addEntry("a", det);
        this.addEntry("to", this.buildCategory(new RightSlash(pp_to, np)));
        this.addEntry("flight", this.buildCategory(new LeftSlash(new RightSlash(np, pp_to), det)));
        this.addEntry("KTM", np);

        this.addEntry("than", this.buildCategory(new RightSlash(pp_than, np)));
        this.addEntry("1",np);
        this.addEntry("5",np);
        this.addEntry("is", this.buildCategory(new RightSlash(new LeftSlash(s,np),adj)));
        this.addEntry("less", this.buildCategory(new RightSlash(adj,pp_than)));
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
