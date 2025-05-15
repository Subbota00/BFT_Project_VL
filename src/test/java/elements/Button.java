package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;

public class Button {
    public Button(String name, SelenideElement selector) {
        this.name = name;
        this.selector = selector;
    }
    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Нажать на кнопку")
    public void clickButton () {
        if (selector == null) {
            throw new NullPointerException("Значение локатора не может быть пустым");
        }
        selector.click();
    }
}
