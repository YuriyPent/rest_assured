package tests;

import org.testng.annotations.Test;

import utils.TestBase;

public class SeleniumTest extends TestBase{

    @Test
    public void browserAutomation() {
        System.out.println("browserAutomation");
        logger.debug("I am debugging");
    }

    @Test
    public void elementsUi() {
        System.out.println("elementsUi");

    }
}
