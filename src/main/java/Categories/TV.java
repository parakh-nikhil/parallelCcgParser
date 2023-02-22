package Categories;

//Definition: Transitive Verb
public class TV extends RightSlash{
    public TV(){
        super(new LeftSlash(S.getInstance(), NP.getInstance()), NP.getInstance());
    }
}
