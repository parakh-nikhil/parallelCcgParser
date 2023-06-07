package Categories;

public class CONJ extends AtomicCategory{
    private static CONJ instance;
    private CONJ(){}

    public static CONJ getInstance(){
        if (instance==null){
            instance = new CONJ();
        }
        return instance;
    }
    @Override
    public String toString() {
        return "CONJ";
    }
}
