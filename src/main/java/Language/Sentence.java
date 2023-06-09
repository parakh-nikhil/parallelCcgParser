package Language;

import java.util.ArrayList;
import java.util.List;

public final class Sentence {
    public static List<String> getSimpleSentences(){
        List<String> sentences = new ArrayList<>();
        //-------------------COMPARISON----------------------------
//        sentences.add("one is less than five"); // 32ms
        sentences.add("I like eating apples"); // 1ms
//        sentences.add("It is a beautiful day"); // 5ms
//        sentences.add("I booked a flight to PHILADELPHIA from CALIFORNIA"); // 37ms
//        sentences.add("I want five apples"); // 1ms
//        sentences.add("I want to fly a kite in the sky");
//        sentences.add("I want to drive a car on narrow road"); // 98ms
//        sentences.add("A quick brown fox jumps over the lazy dog"); // 50ms
//        sentences.add("I want to drive a big yellow car on bumpy road today"); // 1263ms
//        sentences.add("I want to drive a big yellow car on road today"); // 445ms
//        sentences.add("I want to drive a big car on the narrow bumpy road"); // 3168ms
        //---------------------------------------------------------
//        sentences.add("Why is the weather so bad today");
//        sentences.add("I think it is going to rain heavily near the PHILADELPHIA airport today");
//        sentences.add("one is less than five");
//        sentences.add("I like eating apples");
//        sentences.add("It is a beautiful day");
//        sentences.add("I booked a flight to PHILADELPHIA from CALIFORNIA");
//        sentences.add("I want five apples");
//        sentences.add("I want to fly a kite in the sky");
//        sentences.add("I want to fly a car in the sky");
        //TODO: next sentence still does not parse correctly
//        sentences.add("any float that is passing is greater than or equal to one or less than or equal to five");
        return sentences;
    }

    public static List<String> getComplexSentences(){
        List<String> sentences = new ArrayList<>();
//        sentences.add("The quick brown fox jumped over the lazy dog , who was lying in the shade of a tall oak tree , enjoying the cool breeze and the sound of the nearby river , while the fox , having successfully made the leap , ran off into the distance , his bushy tail waving in the wind .");
//        sentences.add("In the distant future , long after humanity has colonized other planets and encountered alien civilizations , a team of explorers sets out on a perilous mission to chart the unexplored regions of the galaxy , facing many challenges and dangers along the way , including hostile creatures , treacherous terrain , and unknown technologies , all in the pursuit of knowledge and discovery .");
//        sentences.add("As the sun slowly rose over the misty mountains , casting a golden glow over the lush valleys below , the hiker continued his ascent , navigating the rocky terrain and steep slopes with skill and determination , until he reached the summit , where he was rewarded with a breathtaking view of the surrounding landscape , stretching as far as the eye could see .");
//        sentences.add("As the sun slowly rose over the mountains , casting a golden glow over the lush towns below , the hiker continued his ascent , going through the rocky terrain and steep slopes with skill and determination , until he reached the summit , where he was rewarded with a breathtaking view of the surrounding landscape , stretching as far as the eye could see .");
        sentences.add("In the aftermath of the devastating earthquake , which shook the entire region to its core , the rescue teams worked tirelessly to search for survivors buried under the rubble , using sophisticated equipment and advanced techniques to locate and extract the victims , many of whom were surprisingly rescued after days of being trapped in the debris .");
        return sentences;
    }

    public static List<String> getComplexSentenceUsingBasicCombinatoryRules(){
        List<String> sentences = new ArrayList<>();
//        sentences.add("The young girl with the curly hair , who was reading a book about ancient civilizations , suddenly noticed a bright light shining in the sky above her , which turned out to be a spaceship from a distant planet , piloted by a friendly alien who had come to visit Earth and learn about its cultures and customs .");
        sentences.add("The young girl with the curly hair , who was reading a book about ancient times , suddenly noticed a bright light shining in the sky above her , which turned out to be a car from a distant planet , driven by a friendly alien who had come to visit Earth and learn about its cultures and customs .");
        return sentences;
    }

    public static List<String> getSoftwareSentences(){
        List<String> softwareSentences = new ArrayList<>();
//        softwareSentences.add("one is less than five");
//        softwareSentences.add("one is even");
//        softwareSentences.add("every number is even");
//        softwareSentences.add("every number is a number");
//        softwareSentences.add("one is equal to five");
//        softwareSentences.add("one is less than or equal to five");
//        softwareSentences.add("one is passing");
//        softwareSentences.add("one is not passing");
        softwareSentences.add("any float that is greater than or equal to one and less than five is not passing");
        softwareSentences.add("any float that is greater than or equal to one and less than five is passing");
//        softwareSentences.add("any float is not passing");
        softwareSentences.add("any float that is passing is greater than or equal to three or less than or equal to five");
//        softwareSentences.add("one is an exception");
//        softwareSentences.add("one throws an exception");
        return softwareSentences;
    }

    public static List<String> getFinalSentences(){
        List<String> sentences = new ArrayList<>();
        sentences.add("one is less than five");
        sentences.add("one is even");
        sentences.add("every number is even");
        sentences.add("every number is a number");
        sentences.add("one is equal to five");
        sentences.add("one is less than or equal to five");
        sentences.add("one is passing");
        sentences.add("one is not passing");
        sentences.add("any float that is greater than or equal to one and less than five is not passing");
        sentences.add("any float that is greater than or equal to one and less than five is passing");
        sentences.add("one is an exception");
        sentences.add("one throws an exception");
        sentences.add("any float is not passing");
        sentences.add("I booked a flight to PHILADELPHIA from CALIFORNIA");
        sentences.add("I want five apples");
        sentences.add("I want to drive a big yellow car on bumpy road today");
        sentences.add("I want to drive a big yellow car on the narrow bumpy road today");
        sentences.add("I want to drive a big yellow car on the narrow bumpy road to home today");
        return sentences;
    }
}

