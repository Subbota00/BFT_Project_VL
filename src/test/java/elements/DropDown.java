package elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

public class DropDown {
    public DropDown(String name, SelenideElement selector) {
        this.name = name;
        this.selector = selector;
        this.inputField = selector.find("input");
    }

    @Getter
    private String name;
    private SelenideElement selector;
    private SelenideElement inputField;

    @Step("Выбор значение с текстом {optionText}")
    public void selectOption(String optionText) {
        if (optionText == null || optionText.isEmpty()) {
            throw new NullPointerException("Значение 'optionText' не может быть пустым");
        }
        selector.click();
        Selenide.$(byText(optionText)).click();
    }

    @Step("Ввод текста '{inputText}' и выбор значения '{optionText}' для элемента {name}")
    public void typeAndSelectOption(String inputText, String optionText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException("Текст для ввода не может быть пустым");
        }
        if (optionText == null || optionText.isEmpty()) {
            throw new IllegalArgumentException("Значение 'optionText' не может быть пустым");
        }

        selector.click();
        inputField.setValue(inputText);
        Selenide.$(byText(optionText))
                .shouldBe(visible)
                .click();
    }
}
