package androidCalculatorApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class AndroidCalculatorTest {

    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        caps.setCapability("noReset", true);

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    // Reusable method to tap digits and operator
    public String performOperation(String a, String op, String b) {
        // Tap all digits of operand a
        for (char ch : a.toCharArray()) {
            driver.findElementById("com.android.calculator2:id/digit_" + ch).click();
        }

        // Tap the operator
        switch (op) {
            case "+":
                driver.findElementByAccessibilityId("plus").click();
                break;
            case "-":
                driver.findElementByAccessibilityId("minus").click();
                break;
            case "*":
                driver.findElementByAccessibilityId("multiply").click();
                break;
            case "/":
                driver.findElementByAccessibilityId("divide").click();
                break;
        }

        // Tap all digits of operand b
        for (char ch : b.toCharArray()) {
            driver.findElementById("com.android.calculator2:id/digit_" + ch).click();
        }

        // Tap equals and return result
        driver.findElementByAccessibilityId("equals").click();
        return driver.findElementById("com.android.calculator2:id/result").getText();
    }

    @Test
    public void testAddition() {
        String result = performOperation("12", "+", "8");
        System.out.println("Addition Result: " + result);
        Assert.assertEquals(result, "20");
    }

    @Test
    public void testSubtraction() {
        String result = performOperation("50", "-", "20");
        System.out.println("Subtraction Result: " + result);
        Assert.assertEquals(result, "30");
    }

    @Test
    public void testMultiplication() {
        String result = performOperation("7", "*", "6");
        System.out.println("Multiplication Result: " + result);
        Assert.assertEquals(result, "42");
    }

    @Test
    public void testDivision() {
        String result = performOperation("36", "/", "6");
        System.out.println("Division Result: " + result);
        Assert.assertEquals(result, "6");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
