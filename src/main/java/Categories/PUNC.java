package Categories;

public class PUNC extends AtomicCategory{
    private static PUNC instance;

    private PUNC(){}

    public static PUNC getInstance(){
        if (instance==null){
            instance = new PUNC();
        }
        return instance;
    }
    @Override
    public String toString() {
        return "PUNC";
    }
}
