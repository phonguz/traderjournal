import java.math.BigDecimal;


public class Test {

	public static void main(String[] args) {

        // create 3 BigDecimal objects
        BigDecimal bg1, bg2;

        //Create 2 int Object
        int i1, i2;
        Float te2;
        Float te1= new Float("123400.56");
        bg1 = new BigDecimal(Math.round(te1));
       // te2=bg1;
        //assign a larger value to bg2
        bg2 = new BigDecimal("3383878445");

        // assign the int value of bg1 and bg2 to i1,i2 respectively
        i1 = bg1.intValue();
        i2 = bg2.intValue();

	String str1 = "int value of : " + te1 + " :  is : " + bg1;
	String str2 = "int value of : " + bg2 + " :  is : " + i2;

        // print i1,i2
        System.out.println( str1 );
        System.out.println( str2 );
    }
}
