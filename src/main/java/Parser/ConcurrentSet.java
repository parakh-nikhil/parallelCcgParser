package Parser;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ConcurrentSet<T> extends ConcurrentHashMap<T,Boolean> implements Iterable {
    public ConcurrentSet(){
        super();
    }

    public void add(T e){
        this.put(e,true);
    }

    @Override
    public Iterator iterator() {
        return this.keySet().iterator();
    }
}
