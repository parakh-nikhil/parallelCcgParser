package Categories;

//Definition: Determiners
public class Det extends AtomicCategory{

    private static Det instance;

    private Det(){}

    public static Det getInstance(){
        if(instance == null){
            instance = new Det();
        }
        return instance;
    }

    @Override
    public String toString(){
        return("DET");
    }
}
