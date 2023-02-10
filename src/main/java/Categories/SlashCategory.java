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

    public abstract String getSlashSign();
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(this.getResult().isSlash()){
            sb.append("(");
            sb.append(this.getResult());
            sb.append(")");
        }else{
            sb.append(this.getResult());
        }
        sb.append(this.getSlashSign());
        if(this.getArgument().isSlash()){
            sb.append("(");
            sb.append(this.getArgument());
            sb.append(")");
        }else{
            sb.append(this.getArgument());
        }
        return sb.toString();
    }


}
