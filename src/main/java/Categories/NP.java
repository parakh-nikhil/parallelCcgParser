package Categories;

//Definition: Noun Phrase
public class NP extends AtomicCategory{
    private static NP instance;

    private NP(){}

    public static NP getInstance()
    {
        if (instance == null)
        {
            instance = new NP();
        }
        return instance;
    }
    @Override
    public String toString(){
        return("NP");
    }
}
