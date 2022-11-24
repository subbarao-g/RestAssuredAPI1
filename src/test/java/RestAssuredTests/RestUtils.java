package RestAssuredTests;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String getPetName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("Doggie"+generatedString);
	}


}
