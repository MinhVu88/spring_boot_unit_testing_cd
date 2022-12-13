package com.udemy.chaddarby.spring_boot_unit_testing;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @DisplayNameGeneration(value = DisplayNameGenerator.Simple.class)
// @TestMethodOrder(MethodOrderer.MethodName.class)
// @TestMethodOrder(MethodOrderer.DisplayName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoUtilsTest {
	DemoUtils demoUtils;

	@BeforeAll
	static void setUpAll() {
		System.out.println("@BeforeAll executes once before all tests\n");
	}

	@BeforeEach
	void setUpEach() {
		demoUtils = new DemoUtils();

		System.out.println("initialize a DemoUtils instance before each test");
	}

	@AfterEach
	void cleanUpEach() {
		System.out.println("clean up after each test\n");
	}

	@AfterAll
	static void cleanUpAll() {
		System.out.println("@AfterAll executes once after all tests");
	}

	@Test
	@DisplayName("test equals & not equals")
	@Order(1)
	void testEqualsAndNotEquals() {
		System.out.println("running testEqualsAndNotEquals");

		// GIVEN
		int expectedResult = 7;

		// DemoUtils demoUtils = new DemoUtils();

		// WHEN
		int actualResult1 = demoUtils.add(2, 5);
		int actualResult2 = demoUtils.add(3, 6);

		// THEN
		assertEquals(expectedResult, actualResult1, "2 + 5 = 7");
		assertNotEquals(expectedResult, actualResult2, "3 + 6 = 9");
	}

	@Test
	@DisplayName("test multiplication")
	void testMultiplication() {
		System.out.println("running testMultiplication");

		// GIVEN
		int expectedResult = 12;

		// WHEN
		int actualResult = demoUtils.multiply(3, 4);

		// THEN
		assertEquals(expectedResult, actualResult, "3 * 4 = " + actualResult);
	}

	@Test
	@DisplayName("test null & not null")
	@Order(0)
	void testNullAndNotNull() {
		System.out.println("running testNullAndNotNull");

		// GIVEN
		String expectedResult1 = null;
		String expectedResult2 = "fsociety";

		// DemoUtils demoUtils = new DemoUtils();

		// WHEN
		Object actualResult1 = demoUtils.checkNull(expectedResult1);
		Object actualResult2 = demoUtils.checkNull(expectedResult2);

		// THEN
		assertNull(actualResult1, "actualResult1 should be null");
		assertNotNull(actualResult2, "actualResult2 should not be null");
	}

	@Test
	@DisplayName("test same & not same")
	void testSameAndNotSame() {
		String str = "fsociety";

		assertSame(
			demoUtils.getStr1(),
			demoUtils.getStr2(),
			"str1 & str2 should refer to the same object"
		);

		assertNotSame(
			str,
			demoUtils.getStr1(),
			"str & str1 shouldn't refer the same object"
		);
	}

	@Test
	@DisplayName("test true or false")
	@Order(30)
	void testTrueFalse() {
		int val1 = 10;
		int val2 = 5;

		assertTrue(demoUtils.isGreater(val1, val2), "this should return true");
		assertFalse(demoUtils.isGreater(val2, val1), "this should return false");
	}

	@Test
	@DisplayName("test if arrays are the same")
	void testArrays() {
		String[] lettersArray = {"A", "B", "C"};

		assertArrayEquals(
			lettersArray,
			demoUtils.getLetters(),
			"lettersArray & letters should be the same"
		);
	}

	@Test
	@DisplayName("test if iterables are the same")
	void testIterables() {
		List<String> wordsList = List.of("arbeit", "macht", "frei");

		assertIterableEquals(
			wordsList,
			demoUtils.getWords(),
			"wordsList & words should be the same"
		);
	}

	@Test
	@DisplayName("test if lines match each other")
	@Order(50)
	void testLines() {
		List<String> wordsList = List.of("arbeit", "macht", "frei");

		assertLinesMatch(
			wordsList,
			demoUtils.getWords(),
			"lines should match each other"
		);
	}

	@Test
	@DisplayName("test if exception is thrown or not")
	void testExceptions() {
		assertThrows(
			Exception.class,
			() -> demoUtils.throwException(-1),
			"this should throw an exception"
		);

		assertDoesNotThrow(
			() -> demoUtils.throwException(1),
			"this shouldn't throw any exception"
		);
	}

	@Test
	@DisplayName("test timeout")
	void testTimeout() {
		assertTimeoutPreemptively(
			Duration.ofSeconds(3),
			() -> demoUtils.checkTimeout(),
			"method should execute in 3 seconds"
		);
	}
}
