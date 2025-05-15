package elements;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import lombok.Getter;

public class RadioButton {
    public RadioButton(String name, ElementsCollection selector) {
        this.name = name;
        this.selector = selector;
    }

    @Getter
    private String name;
    private ElementsCollection selector;

    @Step("Нажатие на радио-баттон с индексом {index}")
    public RadioButton clickRadioButton(int index) {
        if (selector == null) {
            throw new NullPointerException("Значение локатора не может быть пустым");
        } else if (index < 0 || index >= selector.size()) {
            throw new IllegalArgumentException("Передано некорректное значение индекса элемента " + index +
                    ". Должно быть меньше или равно: " + (selector.size() - 1));
        }
        selector.get(index).click();
        return this;
    }
}
