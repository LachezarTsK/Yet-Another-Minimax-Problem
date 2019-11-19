import java.util.Arrays;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int numberOfIntegers = scanner.nextInt();
    int[] input = new int[numberOfIntegers];

    for (int i = 0; i < numberOfIntegers; i++) {
      input[i] = scanner.nextInt();
    }
    scanner.close();

    int result = calculate_MinMax_XOR_of_twoInputIntegers(input);
    System.out.println(result);
  }

/**
  *------------------------------
  * Notes on method 'calculate_MinMax_XOR_of_twoInputIntegers(int[] input)':
  *------------------------------
  * The minimum maximum XOR of two input inetgers will occur between 
  * one of the integers with the greatest leading bit (Group Two) and one of 
  * the rest of integers that a have smaller leading bit (Group One).
  * EXAMPLE:
  * Group One:
  * 01100
  * 01101
  * Group Two:
  * 11010
  * 11011
  * 11100
  *
  *------------------------------
  * Notes on variable 'leadingBit_toDecimal':
  *------------------------------
  * The variable 'leadingBit_toDecimal' is needed two establish the boundary between
  * these two groups of integers. The variable need not be present in the actual input.
  * EXAMPLE:
  * 01100
  * 01101
  * 11010
  * 11011
  * 11100 
  * 'leadingBit_toDecimal' = 64 (binary: 10000).  
  *
  *------------------------------
  * Notes on loop 'while (leadingBit_toDecimal <= smallestInput)':
  *------------------------------
  * If the leading bit is the same for all intput integers, then move it one bit to the right.
  * Repeat, until from the thus formed integers, there is at least one integer with a leading 
  * bit that is greater than the leading bits of one or more of the other integers.
  * EXAMPLE:
  * 110001
  * 111001
  * 111101
  * After first iteration:
  * 10001
  * 11001
  * 11101
  * After second, and final, iteration:
  * 0001
  * 1001
  * 1101
  *
  *------------------------------
  * Notes on variable 'subtract_leadingBits':
  *------------------------------
  * It stores the sum of the leading bits (as decimals) that are the same for all input 
  * integers, if any. It is applied for controlling the loops in which the XOR is calculated.
  *
  * @return An integer, representing the minimum maximum XOR of two input integers.
  */
  private static int calculate_MinMax_XOR_of_twoInputIntegers(int[] input) {

    Arrays.sort(input);
    if (input[0] == input[input.length - 1]) {
      return 0;
    }

    String largestInput_toBinary = Integer.toBinaryString(input[input.length - 1]);
    int leadingBit = largestInput_toBinary.length() - 1;
    int leadingBit_toDecimal = (int) Math.pow(2, leadingBit);
    int smallestInput = input[0];
    int subtract_leadingBits = 0;

    while (leadingBit_toDecimal <= smallestInput) {

      subtract_leadingBits = subtract_leadingBits + leadingBit_toDecimal;
      smallestInput = smallestInput - leadingBit_toDecimal;
      leadingBit_toDecimal = leadingBit_toDecimal >> 1;
    }

    int result = Integer.MAX_VALUE;
    for (int i_max = input.length - 1;
        (input[i_max] - subtract_leadingBits) >= leadingBit_toDecimal;
        i_max--) {

      for (int i_min = 0; (input[i_min] - subtract_leadingBits) < leadingBit_toDecimal; i_min++) {

        int xor = input[i_max] ^ input[i_min];

        if (xor < result) {
          result = xor;
        }
      }
    }

    return result;
  }
}
