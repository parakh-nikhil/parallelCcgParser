import Categories.Category;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Lexicon extends HashMap<String, Set<Category>> {
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

}
