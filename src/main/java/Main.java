import Categories.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello CCG Parser");

        //TODO: should keys be case-sensitive or not
        Lexicon lexicon = new Lexicon();
        NP np = new NP();
        TV tv = new TV();
        Det det = new Det();
        PP pp_to = new PP("to");

        lexicon.addEntry("Tom", np);
        lexicon.addEntry("likes", tv);
        lexicon.addEntry("apples", np);

        lexicon.addEntry("I", np);
        lexicon.addEntry("booked", tv);
        lexicon.addEntry("a", det);
        lexicon.addEntry("to", new RightSlash(pp_to, np));
        lexicon.addEntry("flight", new RightSlash(np, pp_to));
        lexicon.addEntry("KTM", np);

        String sentence = "Tom likes apples";
        String sentence2 = "I booked a flight to KTM";
        Parser parser = new Parser(lexicon);

        parser.parse(sentence);
        System.out.println(parser.getChart());
    }
}
