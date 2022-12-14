/* FizzBuzz rules:
- If a number is divisible by 3, print "Fizz".
- If a number is divisible by 5, print "Buzz".
- If a number is divisible by 3 & 5, print "FizzBuzz".
- If a number is not divisible by 3 or 5, print that number.
*/
package com.udemy.chaddarby.spring_boot_unit_testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {
	@Test
	@Order(1)
	@DisplayName("test divisible by 3")
	void testDivisibleBy3() {
		String expectedResult = "Fizz";

		assertEquals(
			expectedResult,
			FizzBuzz.compute(3),
			"should return Fizz"
		);
	}

	@Test
	@Order(2)
	@DisplayName("test divisible by 5")
	void testDivisibleBy5() {
		String expectedResult = "Buzz";

		assertEquals(
			expectedResult,
			FizzBuzz.compute(5),
			"should return Buzz"
		);
	}

	@Test
	@Order(3)
	@DisplayName("test divisible by 3 & 5")
	void testDivisibleBy3And5() {
		String expectedResult = "FizzBuzz";

		assertEquals(
			expectedResult,
			FizzBuzz.compute(15),
			"should return FizzBuzz"
		);
	}

	@Test
	@Order(4)
	@DisplayName("test divisible not by 3 or 5")
	void testDivisibleNotBy3Or5() {
		String expectedResult = "1";

		assertEquals(
			expectedResult,
			FizzBuzz.compute(1),
			"should return 1"
		);
	}

	@ParameterizedTest(name = "value={0}, expected result={1}")
	@CsvFileSource(resources = "/test1.csv")
	@DisplayName("test csv file 1")
	@Order(5)
	void testCsvFile1(int value, String expectedResult) {
		assertEquals(expectedResult, FizzBuzz.compute(value));
	}

	@ParameterizedTest(name = "value={0}, expected result={1}")
	@CsvFileSource(resources = "/test2.csv")
	@DisplayName("test csv file 2")
	@Order(6)
	void testCsvFile2(int value, String expectedResult) {
		assertEquals(expectedResult, FizzBuzz.compute(value));
	}

	@ParameterizedTest(name = "value={0}, expected result={1}")
	@CsvFileSource(resources = "/test3.csv")
	@DisplayName("test csv file 3")
	@Order(7)
	void testCsvFile3(int value, String expectedResult) {
		assertEquals(expectedResult, FizzBuzz.compute(value));
	}
}
