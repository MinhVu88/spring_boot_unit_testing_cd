package com.udemy.chaddarby.spring_boot_unit_testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {
	@Test
	@Disabled("don't run this until Jira #2 is resolved")
	void testDisabled() {}

	@Test
	@EnabledOnOs(OS.LINUX)
	void testLinux() {}

	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testWindows() {}

	@Test
	@EnabledOnOs(OS.MAC)
	void testMac() {}

	@Test
	@EnabledOnOs({OS.WINDOWS, OS.MAC})
	void testWindowsAndMac() {}

	@Test
	@EnabledOnJre(JRE.JAVA_17)
	void testJava17() {}

	@Test
	@EnabledOnJre(JRE.JAVA_13)
	void testJava13() {}

	@Test
	@EnabledForJreRange(min = JRE.JAVA_8)
	void testMinJavaVersion() {}

	@Test
	@EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_18)
	void testRangeOfJavaVersions() {}

	@Test
	@EnabledIfEnvironmentVariable(
		named = "Deutschland_erwache",
		matches = "DEV"
	)
	void testEnvironmentVariable() {}

	@Test
	@EnabledIfSystemProperty(
		named = "Deutschland_Über_Alles",
		matches = "CI_CD_DEPLOY"
	)
	void testSystemProperty() {}
}
