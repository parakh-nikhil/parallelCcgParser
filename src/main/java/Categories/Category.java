package Categories;

public abstract class Category {
    Boolean isSlash = null;
    public Category(){
    }

    public abstract Boolean isSlash();

    public abstract SlashCategory asSlash() throws Exception;

    @Override
    public abstract String toString();
}



