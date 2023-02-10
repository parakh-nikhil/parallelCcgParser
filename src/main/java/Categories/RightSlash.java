package Categories;

public class RightSlash extends SlashCategory {
    public RightSlash(Category target, Category argument) {
        super(target, argument);
    }

    @Override
    final public Boolean isRightSlash(){
        return true;
    }

}
