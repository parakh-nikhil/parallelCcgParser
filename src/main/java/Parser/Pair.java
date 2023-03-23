package Parser;

public class Pair<K,V> {
    K key;
    V val;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public Pair(K key, V val){
        this.key = key;
        this.val = val;
    }
}
