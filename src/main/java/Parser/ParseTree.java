package Parser;

import Categories.*;
public class ParseTree {
        Category category;
        ParseTree leftChild;
        ParseTree rightChild;

        String sentenceFragment = "";

    public String getSentenceFragment() {
        return sentenceFragment;
    }

    public ParseTree(Category category, ParseTree leftChild, ParseTree rightChild, String sentenceFragment) {
        this.category = category;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.sentenceFragment = sentenceFragment;
    }

    public ParseTree(Category category, ParseTree leftChild, ParseTree rightChild) {
        this.category = category;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
//        this.sentenceFragment = String.format("%s %s", leftChild.getSentenceFragment() + rightChild.getSentenceFragment());
        this.sentenceFragment = leftChild.getSentenceFragment() + " " + rightChild.getSentenceFragment();
    }

    public Category getCategory() {
        return this.category;
    }

    public Pair<ParseTree,ParseTree> children() {
        return new Pair<>(leftChild, rightChild);
    }
//        public Pair<String,String> childFragments() { }

    public String toString(){
            return this.getCategory().toString();
        }

}
