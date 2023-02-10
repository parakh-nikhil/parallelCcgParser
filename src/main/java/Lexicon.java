import Categories.*;

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

    public void initializeEntries(){
        NP np = new NP();
        TV tv = new TV();
        Det det = new Det();
        PP pp_to = new PP("to");
        V v = new V();

        this.addEntry("Tom", np);
        this.addEntry("likes", tv);
        this.addEntry("apples", np);

        this.addEntry("I", np);
        this.addEntry("booked", tv);
        this.addEntry("a", det);
        this.addEntry("to", new RightSlash(pp_to, np));
        this.addEntry("flight", new RightSlash(np, pp_to));
//        this.addEntry("flight", v);
        this.addEntry("KTM", np);
    }
}
