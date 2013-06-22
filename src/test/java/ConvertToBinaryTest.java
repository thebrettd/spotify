import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: brett
 * Date: 6/22/13
 * Time: 11:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConvertToBinaryTest {

    @Test
    public void testFindLargestPower(){
        assertTrue(ReverseBinary.findLargestPowerOfTwo(0) == 0);
        assertTrue(ReverseBinary.findLargestPowerOfTwo(1) == 0);
        assertTrue(ReverseBinary.findLargestPowerOfTwo(2) == 1);
        assertTrue(ReverseBinary.findLargestPowerOfTwo(8) == 3);
        assertTrue(ReverseBinary.findLargestPowerOfTwo(13) == 3);
        assertTrue(ReverseBinary.findLargestPowerOfTwo(1024) == 10);
    }

    @Test
    public void testConvertToBinary(){
        assertTrue(ReverseBinary.convertToBinary(13).equals("1101"));
        assertTrue(ReverseBinary.convertToBinary(47).equals("101111"));
    }

    @Test
    public void testReverse(){
        assertTrue(ReverseBinary.reverse("1101").equals("1011"));
        assertTrue(ReverseBinary.reverse("101111").equals("111101"));
    }

    @Test public void testBinaryToInt(){
        assertTrue(ReverseBinary.binaryToInt("0") == 0);
        assertTrue(ReverseBinary.binaryToInt("1") == 1);
        assertTrue(ReverseBinary.binaryToInt("01") == 1);
        assertTrue(ReverseBinary.binaryToInt("101") == 5);
        assertTrue(ReverseBinary.binaryToInt("00000101") == 5);

        assertTrue(ReverseBinary.binaryToInt("1011") == 11);
        assertTrue(ReverseBinary.binaryToInt("111101") == 61);

    }




}
