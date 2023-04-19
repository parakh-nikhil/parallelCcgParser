package Parser;

import Categories.*;
import Language.RULES;

public class ParseTree {
        Category category;
        ParseTree leftChild;
        ParseTree rightChild;
        RULES createdByRule;
        String sentenceFragment = "";

    public String getSentenceFragment() {
        return sentenceFragment;
    }

    public ParseTree(Category category, ParseTree leftChild, ParseTree rightChild, String sentenceFragment, RULES createdByRule) {
        this.category = category;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.sentenceFragment = sentenceFragment;
        this.createdByRule = createdByRule;
    }

    public ParseTree(Category category, ParseTree leftChild, ParseTree rightChild, RULES createdByRule) {
        this.category = category;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
//        this.sentenceFragment = String.format("%s %s", leftChild.getSentenceFragment() + rightChild.getSentenceFragment());
        this.sentenceFragment = leftChild.getSentenceFragment() + " " + rightChild.getSentenceFragment();
        this.createdByRule = createdByRule;
    }

    public Category getCategory() {
        return this.category;
    }

    public RULES getCreatedByRule() {
        return this.createdByRule;
    }

    public Pair<ParseTree,ParseTree> children() {
        return new Pair<>(leftChild, rightChild);
    }
//        public Pair<String,String> childFragments() { }

    public String toString(){
            return this.getCategory().toString();
        }

}
