import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: brett
 * Date: 6/22/13
 * Time: 11:41 AM
 */
public class ReverseBinary {

    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
        String line = br.readLine();

        int input = Integer.parseInt(line);

        int reverseAsInt = reverseAsInt(input);

        System.out.println(reverseAsInt);
        }catch(Exception e){
            System.out.println("Error reading input from stdin");
        }
    }

    private static int reverseAsInt(int input) {
        String binary = convertToBinary(input);
        String reverseBinary = reverse(binary);
        return binaryToInt(reverseBinary);
    }

    public static int binaryToInt(String binaryString) {
        int totalVal = 0;

        int pow = 0;
        int currVal;
        for(int i= binaryString.length() -1 ; i >= 0 ; i--){

            int digit = Integer.parseInt(Character.toString(binaryString.charAt(i)));
            int factor = (int)Math.pow(2,pow);
            //System.out.println("digit:" + digit + " factor:" + factor);
            currVal = digit * factor;
            totalVal += currVal;
            pow++;
        }
        //System.out.println("int val:" + totalVal);
        return totalVal;
    }

    public static String convertToBinary(int input){
        int pow = findLargestPowerOfTwo(input);
        String binary = "";

        int remainder = 1;
        while(remainder != 0){
            int quotient = (int) (input / Math.pow(2,pow));
            //System.out.println("quotient:" + quotient);
            int divisor = (int) Math.pow(2, pow);
            //System.out.println("divisor: "+ divisor);
            remainder = input % divisor;
            //System.out.println("remainder:" + remainder);

            binary += quotient;
            input = remainder;
            pow -= 1;
        }

        //System.out.println("Binary:" + binary);
        return binary;
    }

    public static int findLargestPowerOfTwo(int input) {
        int pow = 0;
        while(input / (int) Math.pow(2,pow) > 1 ){
            pow++;
        }
        //System.out.println("largestPow: " + pow);
        return pow;
    }

    public static String reverse(String inputAsBinary) {
        Stack<Character> stack = new Stack<>();

        while(inputAsBinary.length() > 0){
            stack.push(inputAsBinary.charAt(0));
            inputAsBinary = inputAsBinary.substring(1);
        }

        String reverse = "";
        while(!stack.isEmpty()){
            reverse += stack.pop();
        }

        //System.out.println(reverse);
        return reverse;
    }


}
