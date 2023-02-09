package Categories;

public class LeftSlash extends SlashCategory {
    public LeftSlash(Category result, Category argument) {
        super(result, argument);
    }

    @Override
    final public Boolean getIsRightSlash(){
        return false;
    }

    @Override
    public Category getResult() {
        return null;
    }

}
