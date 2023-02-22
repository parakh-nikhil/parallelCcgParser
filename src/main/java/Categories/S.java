package Categories;

//Definition: Sentence
public class S extends AtomicCategory{

    private static S instance;

    private S(){}

    public static S getInstance(){
        if(instance == null){
            instance = new S();
        }
        return instance;
    }
    @Override
    public String toString(){
        return("S");
    }
}
