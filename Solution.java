import java.util.Arrays;
import java.util.Scanner;

public class SolutionThree {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int numberofIntegers = scanner.nextInt();
    int[] input = new int[numberofIntegers];

    for (int i = 0; i < numberofIntegers; i++) {
      input[i] = scanner.nextInt();
    }
    scanner.close();

    int result = calculate_MinMax_XOR_of_twoInputIntegers(input);
    System.out.println(result);
  }

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
