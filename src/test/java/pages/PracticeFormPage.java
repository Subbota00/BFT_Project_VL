package pages;

import com.codeborne.selenide.SelenideElement;
import elements.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    protected final Input firstName = new Input("Поле для ввода имени", $x("//*[@id=\"firstName\"]"));
    protected final Input lastName = new Input("Поле для ввода фамилии", $x("//*[@id=\"lastName\"]"));
    protected final Input eMail = new Input("Поле для ввода Email", $x("//*[@id=\"userEmail\"]"));
    protected final Input mobileNumber = new Input("Поле для ввода номера телефона", $x("//*[@id=\"userNumber\"]"));
    protected final Input address = new Input("Поле для ввода адреса", $x("//*[@id=\"currentAddress\"]"));
    protected final CheckBox hobbies = new CheckBox("Выбор значения  в поле Hobbies", $$("div.custom-control.custom-checkbox label.custom-control-label"));
    protected final RadioButton gender = new RadioButton("Выбор значения в поле Gender", $$("div.custom-radio label"));
    protected final DropDown state = new DropDown("Выбор значения в поле State", $x("//*[@id=\"stateCity-wrapper\"]/div[2]"));
    protected final DropDown city = new DropDown("Выбор значения в поле City", $x("//*[@id=\"stateCity-wrapper\"]/div[3]"));
    protected final Button submit = new Button("Нажатие на кнопку Submit", $x("//*[@id=\"submit\"]"));
    protected final Calendar dateOfBirth = new Calendar("Поле для выбора Даты рождения", $x("//*[@id=\"dateOfBirthInput\"]"),
            $(".react-datepicker"), $(".react-datepicker__month-select"), $(".react-datepicker__year-select"),
            $$(".react-datepicker__day"), "react-datepicker__day--outside-month");
    protected final DropDown subject = new DropDown("Выбор значения в поле Subject", $x("//*[@id=\"subjectsContainer\"]"));
    protected final ImportFile selectFile = new ImportFile("Кнопка 'Выберите файл'" , $x("//*[@id=\"uploadPicture\"]"));

    @Step("Открыть страницу Practice Form")
    public void openPracticeForm() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Ввод значения в поле Имя")
    public void inputFirstName(String value) {
        firstName.setValue(value);
    }

    @Step("Ввод значение в поле Фамилия")
    public void inputLastName(String value) {
        lastName.setValue(value);
    }

    @Step("Ввод значения в поле EMAIL")
    public void inputEmail(String value) {
        eMail.setValue(value);
    }

    @Step("Ввод значения в поле Номер телефона")
    public void inputMobileNumber(String value) {
        mobileNumber.setValue(value);
    }

    @Step("Ввод значения в поле Адрес")
    public void inputAddress(String value) {
        address.setValue(value);
    }

    @Step("Выбор значения в Hobbies")
    public void selectHobbies(int... indexes) {
        hobbies.clickMultipleCheckboxes(indexes);
    }

    @Step("Выбор значения в Gender")
    public void selectGender(int index) {
        gender.clickRadioButton(index);
    }

    @Step("Выбор значения в State")
    public void selectState(String optionText) {
        state.selectOption(optionText);
    }

    @Step("Выбор значения в City")
    public void selectCity(String optionText) {
        city.selectOption(optionText);
    }

    @Step("Нажатие кнопки Submit")
    public void clickSubmit() {
        submit.clickButton();
    }

    @Step("Выбор даты в поле Date of Birth")
    public void selectDate(int day, String month, int year) {
        dateOfBirth.selectDate(day, month, year);
    }

    @Step("Выбор значения в поле Subject")
    public void selectSubject(String inputText, String optionText) {
        subject.typeAndSelectOption(inputText, optionText);
    }
    @Step("Загрузка файла")
    public void importFile(String filePath) {
        selectFile.uploadFile(filePath);
    }
    public SelenideElement verifyModalTitle() {
        return $(".modal-title").shouldBe(visible);
    }

    public void verifySubmittedData(String fieldName, String expectedValue) {
        $$(".table-responsive tr").findBy(text(fieldName))
                .shouldHave(text(expectedValue));
    }
}
