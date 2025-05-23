package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import static com.codeborne.selenide.Condition.*;

public class Calendar {
    public Calendar(String name, SelenideElement selector, SelenideElement datePicker, SelenideElement month,
                    SelenideElement year, ElementsCollection dayElements, String outsideMonthDay) {
        this.name = name;
        this.selector = selector;
        this.datePicker = datePicker;
        this.month = month;
        this.year = year;
        this.dayElements = dayElements;
        this.outsideMonthDay = outsideMonthDay;
    }

    @Getter
    private String name;
    private SelenideElement selector;
    private SelenideElement datePicker;
    private SelenideElement month;
    private SelenideElement year;
    private ElementsCollection  dayElements;
    private String outsideMonthDay;

    @Step("Выбор даты в календаре")
    public void selectDate(int day, String month, int year) {
        selector.click();
        datePicker.shouldBe(visible);
        this.month.selectOption(month);
        this.year.selectOption(String.valueOf(year));
        dayElements
                .excludeWith(cssClass(outsideMonthDay))
                .findBy(text(String.valueOf(day)))
                .shouldBe(visible)
                .click();
    }
}
