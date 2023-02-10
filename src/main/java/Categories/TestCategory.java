package Categories;

public class TestCategory extends AtomicCategory{
    public String val;

    public TestCategory(String x){
        super();
        this.val = x;
    }

    @Override
    public String toString(){
        return this.val;
    }
}
