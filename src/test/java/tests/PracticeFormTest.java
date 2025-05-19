package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;

public class PracticeFormTest extends BaseTest {
    private static final String ERROR_BORDER_COLOR = "rgb(220, 53, 69)";
    private final String FIRST_NAME = "Сергей";
    private final String LAST_NAME = "Петров";
    private final String EMAIL = "SergeyPetrov@mail.ru";
    private final String GENDER = "Male";
    private final String MOBILE = "8905888533";
    private final String BIRTH_DAY = "19";
    private final String BIRTH_MONTH = "July";
    private final String BIRTH_YEAR = "2025";
    private final String BIRTH_DATE = BIRTH_DAY + " " + BIRTH_MONTH + "," + BIRTH_YEAR;
    private final String[] SUBJECTS = {"Chemistry", "Maths"};
    private final String[] HOBBIES = {"Sports", "Reading", "Music"};
    private final String FILE_NAME = "test.jpg";
    private final String ADDRESS = "г.Москва, Сентябрьский проезд 2/2";
    private final String STATE = "NCR";
    private final String CITY = "Delhi";

    @Test
    @DisplayName("Заполнение формы валидными значениями + сравнение введенных данных с таблицей (popup)")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveInputForm() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.inputFirstName(FIRST_NAME);
        practiceFormPage.inputLastName(LAST_NAME);
        practiceFormPage.inputEmail(EMAIL);
        practiceFormPage.selectGender(0);
        practiceFormPage.inputMobileNumber(MOBILE);
        practiceFormPage.selectDate(Integer.parseInt(BIRTH_DAY), BIRTH_MONTH, Integer.parseInt(BIRTH_YEAR));

        for (String subject : SUBJECTS) {
            practiceFormPage.selectSubject(subject.substring(0, 1), subject);
        }

        practiceFormPage.selectHobbies(0, 1, 2);
        practiceFormPage.importFile("C:\\Users\\lvvls\\OneDrive\\Изображения\\" + FILE_NAME);
        practiceFormPage.inputAddress(ADDRESS);
        practiceFormPage.selectState(STATE);
        practiceFormPage.selectCity(CITY);
        practiceFormPage.clickSubmit();

        practiceFormPage.verifyModalTitle()
                .shouldHave(text("Thanks for submitting the form"));

        practiceFormPage.verifySubmittedData("Student Name", FIRST_NAME + " " + LAST_NAME);
        practiceFormPage.verifySubmittedData("Student Email", EMAIL);
        practiceFormPage.verifySubmittedData("Gender", GENDER);
        practiceFormPage.verifySubmittedData("Mobile", MOBILE);
        practiceFormPage.verifySubmittedData("Date of Birth", BIRTH_DATE);
        practiceFormPage.verifySubmittedData("Subjects", String.join(", ", SUBJECTS));
        practiceFormPage.verifySubmittedData("Hobbies", String.join(", ", HOBBIES));
        practiceFormPage.verifySubmittedData("Picture", FILE_NAME);
        practiceFormPage.verifySubmittedData("Address", ADDRESS);
        practiceFormPage.verifySubmittedData("State and City", STATE + " " + CITY);
    }

    @Test
    @DisplayName("Негатив. Проверка валидации обязательных полей - Все обязательные поля пустые")
    @Severity(SeverityLevel.NORMAL)
    public void validationAllRequiredFieldsEmpty() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.clickSubmit();
        practiceFormPage.getFirstNameInput().shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getLastNameInput().shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getMobileNumberInput().shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getGenderRadioButtons().findBy(checked).shouldNot(exist);
        practiceFormPage.getModalTitle().shouldNot(exist);
    }

    @Test
    @DisplayName("Негатив. Проверка валидации обязательных полей - Не заполнено имя")
    @Severity(SeverityLevel.NORMAL)
    public void validationFirstNameEmpty() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.inputLastName(LAST_NAME);
        practiceFormPage.selectGender(0);
        practiceFormPage.inputMobileNumber(MOBILE);
        practiceFormPage.clickSubmit();
        practiceFormPage.getFirstNameInput().shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getLastNameInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getMobileNumberInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getModalTitle().shouldNot(exist);
    }

    @Test
    @DisplayName("Негатив. Проверка валидации обязательных полей - Не заполнена фамилия")
    @Severity(SeverityLevel.NORMAL)
    public void validationLastNameEmpty() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.inputFirstName(FIRST_NAME);
        practiceFormPage.selectGender(0);
        practiceFormPage.inputMobileNumber(MOBILE);
        practiceFormPage.clickSubmit();
        practiceFormPage.getFirstNameInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getLastNameInput().shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getMobileNumberInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getModalTitle().shouldNot(exist);
    }

    @Test
    @DisplayName("Негатив. Проверка валидации обязательных полей - Пол не выбран")
    @Severity(SeverityLevel.NORMAL)
    public void validationGenderNotSelected() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.inputFirstName(FIRST_NAME);
        practiceFormPage.inputLastName(LAST_NAME);
        practiceFormPage.inputMobileNumber(MOBILE);
        practiceFormPage.clickSubmit();
        practiceFormPage.getFirstNameInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getLastNameInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getMobileNumberInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getSelectedGender().shouldNot(exist);
        practiceFormPage.getModalTitle().shouldNot(exist);
    }

    @Test
    @DisplayName("Негатив. Проверка валидации обязательных полей - Номер телефона не заполнен")
    @Severity(SeverityLevel.NORMAL)
    public void validationMobileNumberEmpty() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.inputFirstName(FIRST_NAME);
        practiceFormPage.inputLastName(LAST_NAME);
        practiceFormPage.selectGender(0);
        practiceFormPage.clickSubmit();
        practiceFormPage.getFirstNameInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getLastNameInput().shouldNotHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getMobileNumberInput().shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        practiceFormPage.getModalTitle().shouldNot(exist);
    }
}