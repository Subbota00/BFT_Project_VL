package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.PracticeFormPage;


public class BaseTest {
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.pageLoadStrategy = "none";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;

    }

    static PracticeFormPage practiceFormPage = new PracticeFormPage();

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }
}