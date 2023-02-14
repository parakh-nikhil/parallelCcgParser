package Categories;

//Definition: Verb
public class V extends AtomicCategory{
    private static V instance;

    private V(){}

    public static V getInstance(){
        if(instance == null){
            instance = new V();
        }
        return instance;
    }
    @Override
    public String toString() {
        return "V";
    }
}
