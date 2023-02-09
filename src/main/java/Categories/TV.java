package Categories;

//Definition: Transitive Verb
public class TV extends RightSlash{
    public TV(){
        super(new LeftSlash(new S(), new NP()), new NP());
    }
}
