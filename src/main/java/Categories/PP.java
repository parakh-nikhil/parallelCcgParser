package Categories;

//Definition: Prepositional Phrase
public class PP extends AtomicCategory{
    String preposition;

    public PP(String preposition){
        super();
        this.preposition = preposition;

    }

    @Override
    public String toString(){
        return(String.format("PP[%s]",this.preposition));
    }
}
