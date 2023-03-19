package Categories;

public class N extends AtomicCategory{
    private static N instance;

    private N(){}

    public static N getInstance()
    {
        if (instance == null)
        {
            instance = new N();
        }
        return instance;
    }
    @Override
    public String toString() {
        return "N";
    }
}
