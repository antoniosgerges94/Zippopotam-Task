package data;

import org.testng.annotations.DataProvider;

public class ZippopotamTestData {

    @DataProvider(name = "validLocations")
    public static Object[][] validLocations() {

        return new Object[][]{
                {"us", "90210", "United States"},
                {"us", "10001", "United States"},
                {"de", "10115", "Germany"},
                {"fr", "75001", "France"},
                {"ca", "M5V", "Canada"}
        };
    }

    @DataProvider(name = "invalidLocations")
    public static Object[][] invalidLocations() {

        return new Object[][]{
                {"xx", "90210"},
                {"us", "99999"},
                {"us", "ABC-123"},
                {"us", "@@@###"},
                {"us", "123456789012345678901234567890"}
        };
    }
}