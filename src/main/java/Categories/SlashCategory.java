package Categories;

public abstract class SlashCategory extends Category{
    Category result = null;
    Category argument = null;

    public SlashCategory(Category result, Category argument) {
        super();
        this.result = result;
        this.argument = argument;
    }

    public abstract Boolean isRightSlash();

    public Category getArgument(){
        return this.argument;
    }

    public Category getResult(){
        return this.result;
    }
    @Override
    final public Boolean isSlash(){
        return true;
    }

    @Override
    public SlashCategory asSlash() throws Exception {
        return this;
    }



}
