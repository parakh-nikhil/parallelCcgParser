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
        NP np = NP.getInstance();
        TV tv = new TV();
        Det det = Det.getInstance();
        PP pp_to = new PP("to");
        PP pp_than = new PP("than");
        S s = S.getInstance();
        ADJ adj = ADJ.getInstance();


        this.addEntry("Tom", np);
        this.addEntry("likes", tv);
        this.addEntry("apples", np);

        this.addEntry("I", np);
        this.addEntry("booked", tv);
        this.addEntry("a", det);
        this.addEntry("to", new RightSlash(pp_to, np));
        this.addEntry("flight", new LeftSlash(new RightSlash(np, pp_to), det));
        this.addEntry("KTM", np);

        this.addEntry("than", new RightSlash(pp_than, np));
        this.addEntry("1",np);
        this.addEntry("5",np);
        this.addEntry("is", new RightSlash(new LeftSlash(s,np),adj));
        this.addEntry("less", new RightSlash(adj,pp_than));
    }
}
