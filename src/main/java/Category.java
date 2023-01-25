abstract class Category {
    Boolean isSlash = null;
    public Category(){
    }

    public abstract Boolean isSlash();

    public abstract Category asSlash() throws Exception;
}



