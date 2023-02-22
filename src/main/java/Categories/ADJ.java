package Categories;

//Definition: Adjective
public class ADJ extends AtomicCategory{
    private static ADJ instance;

    private ADJ(){}

    public static ADJ getInstance(){
        if(instance == null){
            instance = new ADJ();
        }
        return instance;
    }
    @Override
    public String toString() {
        return "ADJ";
    }
}
