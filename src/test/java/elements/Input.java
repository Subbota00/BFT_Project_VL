package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import lombok.Getter;
import java.time.Duration;
import java.util.NoSuchElementException;

public class Input {
    public Input(String name, SelenideElement selector) {
        this.name = name;
        this.selector = selector;
    }

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Ввод значения в поле")
    public Input setValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new NullPointerException("Значение 'value' не может быть пустым");
        }
        if (selector != null) {
            try {
                selector.shouldBe(Condition.enabled, Duration.ofSeconds(5)).setValue(value);
                return this;
            } catch (ElementNotFound enf) {
                throw new NoSuchElementException("Элемент с локатором " + selector + " не найден на странице");
            }
        } else throw new NullPointerException("Значение локатора передано пустым");
    }
}
