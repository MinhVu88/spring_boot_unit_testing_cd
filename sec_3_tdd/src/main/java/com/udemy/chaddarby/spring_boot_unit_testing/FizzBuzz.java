/* FizzBuzz rules:
- If a number is divisible by 3, print "Fizz".
- If a number is divisible by 5, print "Buzz".
- If a number is divisible by 3 & 5, print "FizzBuzz".
- If a number is not divisible by 3 or 5, print that number.
*/
package com.udemy.chaddarby.spring_boot_unit_testing;

public class FizzBuzz {
	/*
	public static String compute(int value) {
		if(value % 3 == 0 && value % 5 == 0){
			return "FizzBuzz";
		} else if(value % 3 == 0) {
			return "Fizz";
		} else if(value % 5 == 0) {
			return "Buzz";
		}

		return "1";
	}
	*/

	public static String compute(int value) {
		StringBuilder result = new StringBuilder();

		if(value % 3 == 0) {
			result.append("Fizz");
		}

		if(value % 5 == 0) {
			result.append("Buzz");
		}

		if(result.isEmpty()) {
			result.append(value);
		}

		return result.toString();
	}

	public static void main(String[] args) {
		for(int i = 1; i <= 100; i++) {
			System.out.println(FizzBuzz.compute(i));
		}
	}
}
