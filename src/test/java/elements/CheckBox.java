package elements;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import lombok.Getter;
import java.util.Arrays;

public class CheckBox {
    public CheckBox(String name, ElementsCollection selector) {
        this.name = name;
        this.selector = selector;
    }

    @Getter
    private String name;
    private ElementsCollection selector;

    @Step("Выбор чек-боксов по индексам {indexes}")
    public CheckBox clickMultipleCheckboxes(int... indexes) {
        if (indexes.length == 0) {
            return this;
        }
        int maxAllowedIndex = selector.size() - 1;
        Arrays.stream(indexes)
                .filter(index -> index < 0 || index > maxAllowedIndex)
                .findFirst()
                .ifPresent(invalidIndex -> {
                    throw new IllegalArgumentException(String.format(
                            "Передано некорректное значение индекса: " +
                                    invalidIndex + ". Максимально допустимое значение: " + maxAllowedIndex
                    ));
                });
        Arrays.stream(indexes)
                .distinct()
                .forEach(index -> selector.get(index).click());

        return this;
    }
}


