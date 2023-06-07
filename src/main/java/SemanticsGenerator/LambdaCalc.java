package SemanticsGenerator;

import LambdaCalculus.lambda.Expression;
import LambdaCalculus.lambda.Lambda;

public class LambdaCalc extends Lambda {

    public void run() {
    //        // I like NYC
    //        Expression NYC = var("NYC");
    //        System.out.println("NYC = " + NYC.toString());

        Expression True = λ("x", λ("y", "x"));
        System.out.println("true = " + True);
    }
}
