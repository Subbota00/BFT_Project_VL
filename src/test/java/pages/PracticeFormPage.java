package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.*;
import io.qameta.allure.Step;
import lombok.Getter;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class PracticeFormPage {
    private final Input firstName = new Input("First Name", $x("//*[@id=\"firstName\"]"));
    private final Input lastName = new Input("Last Name", $x("//*[@id=\"lastName\"]"));
    private final Input email = new Input("Email", $x("//*[@id=\"userEmail\"]"));
    private final Input mobileNumber = new Input("Mobile Number", $x("//*[@id=\"userNumber\"]"));
    private final Input address = new Input("Address", $x("//*[@id=\"currentAddress\"]"));
    private final CheckBox hobbies = new CheckBox("Hobbies", $$("div.custom-control.custom-checkbox label.custom-control-label"));
    private final RadioButton gender = new RadioButton("Gender", $$("div.custom-radio label"));
    private final DropDown state = new DropDown("State", $x("//*[@id=\"stateCity-wrapper\"]/div[2]"));
    private final DropDown city = new DropDown("City", $x("//*[@id=\"stateCity-wrapper\"]/div[3]"));
    private final Button submit = new Button("Submit", $x("//*[@id=\"submit\"]"));
    private final Calendar dateOfBirth = new Calendar("Date of Birth",
            $x("//*[@id=\"dateOfBirthInput\"]"),
            $(".react-datepicker"),
            $(".react-datepicker__month-select"),
            $(".react-datepicker__year-select"),
            $$(".react-datepicker__day"),
            "react-datepicker__day--outside-month");
    private final DropDown subject = new DropDown("Subject", $x("//*[@id=\"subjectsContainer\"]"));
    private final ImportFile selectFile = new ImportFile("Select File", $x("//*[@id=\"uploadPicture\"]"));

    @Step("Открыть страницу 'Practice Form' ")
    public void openPracticeForm() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Ввод значения в поле 'First Name' : {value}")
    public PracticeFormPage inputFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    @Step("Ввод значения в поле 'Last Name' : {value}")
    public PracticeFormPage inputLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    @Step("Ввод значения в поле 'Email' : {value}")
    public PracticeFormPage inputEmail(String value) {
        email.setValue(value);
        return this;
    }

    @Step("Ввод значения в поле 'Mobile' : {value}")
    public PracticeFormPage inputMobileNumber(String value) {
        mobileNumber.setValue(value);
        return this;
    }

    @Step("Ввод значения в поле 'Current Address' : {value}")
    public PracticeFormPage inputAddress(String value) {
        address.setValue(value);
        return this;
    }

    @Step("Выбор чек-бокса 'Hobbies' : {indexes}")
    public PracticeFormPage selectHobbies(int... indexes) {
        hobbies.clickMultipleCheckboxes(indexes);
        return this;
    }

    @Step("Выбор значения в поле 'Gender' : {index}")
    public PracticeFormPage selectGender(int index) {
        gender.clickRadioButton(index);
        return this;
    }

    @Step("Выбор значения в поле 'State' : {optionText}")
    public PracticeFormPage selectState(String optionText) {
        state.selectOption(optionText);
        return this;
    }

    @Step("Выбор значения в поле 'City' : {optionText}")
    public PracticeFormPage selectCity(String optionText) {
        city.selectOption(optionText);
        return this;
    }

    @Step("Нажать на кнопку 'Submit' ")
    public PracticeFormPage clickSubmit() {
        submit.clickButton();
        return this;
    }

    @Step("Выбор значения в поле 'Date of Birth' : {day} {month} {year}")
    public PracticeFormPage selectDate(int day, String month, int year) {
        dateOfBirth.selectDate(day, month, year);
        return this;
    }

    @Step("Выбор значения в поле 'Subject' : {optionText} (input: {inputText})")
    public PracticeFormPage selectSubject(String inputText,

                                          String optionText) {
        subject.typeAndSelectOption(inputText, optionText);
        return this;
    }

    @Step("Загрузка файла: {filePath}")
    public PracticeFormPage importFile(String filePath) {
        selectFile.uploadFile(filePath);
        return this;
    }

    @Step("Верифицирующее модальное окно")
    public SelenideElement verifyModalTitle() {
        return $(".modal-title").shouldBe(visible);
    }

    @Step("Проверка введенных данных")
    public void verifySubmittedData(String fieldName, String expectedValue) {
        $$(".table-responsive tr")
                .findBy(text(fieldName))
                .shouldHave(text(expectedValue));
    }

    @Step("Получение текущего значения в поле 'First Name' ")
    public SelenideElement getFirstNameInput() {
        return $("#firstName");
    }

    @Step("Получение текущего значения в поле 'Last Name' ")
    public SelenideElement getLastNameInput() {
        return $("#lastName");
    }

    @Step("Получение текущего значения из поля 'Gender' ")
    public ElementsCollection getGenderRadioButtons() {
        return $$("[name=gender][type=radio]");
    }

    @Step("Поиск модального окна с таблицей значений")
    public SelenideElement getModalTitle() {
        return $("#example-modal-sizes-title-lg");
    }

    @Step("Получение текущего значения из поля 'Mobile' ")
    public SelenideElement getMobileNumberInput() {
        return $("#userNumber");
    }

    @Step("Получение выбранного значения в поле 'Gender' ")
    public SelenideElement getSelectedGender() {
        return $("[name=gender]:checked");
    }
}