public class Main {
    public static void main(String[] args) {
        System.out.println("Hello CCG Parser");
        NP tom = new NP();
        NP apple = new NP();
        S s = new S();
        TV likes = new TV(new LeftSlash(s, tom), apple);

        System.out.println(likes.getArgument());

    }
}
