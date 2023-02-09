package Categories;

public abstract class SlashCategory extends Category{
    Category target = null;
    Category argument = null;

    public SlashCategory(Category target, Category argument) {
        super();
        this.target = target;
        this.argument = argument;
    }

    public abstract Boolean getIsRightSlash();

    public abstract Category getResult();
    public Category getArgument(){
        return this.argument;
    }

    public Category getTarget(){
        return this.target;
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
