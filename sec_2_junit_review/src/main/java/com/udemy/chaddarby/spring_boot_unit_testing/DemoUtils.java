package com.udemy.chaddarby.spring_boot_unit_testing;

import java.util.List;

public class DemoUtils {
	private String str1 = "arbeit macht frei";
	private String str2 = str1;
	private String[] letters = {"A", "B", "C"};
	private List<String> words = List.of("arbeit", "macht", "frei");

	public String getStr1() {
		return str1;
	}

	public String getStr2() {
		return str2;
	}

	public String[] getLetters() {
		return letters;
	}

	public List<String> getWords() {
		return words;
	}

	public int add(int a, int b) {
		return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public Object checkNull(Object obj) {
		if (obj != null) {
			return obj;
		}

		return null;
	}

	public Boolean isGreater(int n1, int n2) {
		return n1 > n2;
	}

	public String throwException(int a) throws Exception {
		if (a < 0) {
			throw new Exception("Value should be greater than or equal to 0");
		}

		return "Value is greater than or equal to 0";
	}

	public void checkTimeout() throws InterruptedException {
		System.out.println("I am going to sleep");
		Thread.sleep(2000);
		System.out.println("Sleeping over");
	}
}
