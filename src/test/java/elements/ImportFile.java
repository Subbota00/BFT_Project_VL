package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ImportFile {
    public ImportFile(String name, SelenideElement selector) {
        this.name = name;
        this.selector = selector;
    }
    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Загрузка файла '{filePath}' в элемент {name}")
    public void uploadFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым");
        }
        selector.shouldBe(visible).shouldBe(enabled);
        selector.uploadFile(new File(filePath));

        // Проверяем, что файл загружен (пример для demoqa.com)
//        $(".form-control-file").shouldHave(text("your_file_name"));
    }
}
