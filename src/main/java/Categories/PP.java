package Categories;

//Definition: Prepositional Phrase
public class PP extends AtomicCategory{

    private static PP instance;

    private PP(){
        super();
    }

    public static PP getInstance(){
        if (instance == null){
            instance = new PP();
        }
        return instance;
    }
    @Override
    public String toString(){
        return "PP";
    }
}
