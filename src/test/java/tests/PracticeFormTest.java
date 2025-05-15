package tests;

import elements.Input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import static com.codeborne.selenide.Condition.text;
import static java.lang.Thread.sleep;

public class PracticeFormTest extends BaseTest {

    @Test
    @DisplayName("Заполнение формы валидными значениями")
    public void positiveInputForm() {
        practiceFormPage.openPracticeForm();
        practiceFormPage.inputFirstName("Сергей");
        practiceFormPage.inputLastName("Петров");
        practiceFormPage.inputEmail("SergeyPetrov@mail.ru");
        practiceFormPage.selectGender(0);
        practiceFormPage.inputMobileNumber("89058885333");
        practiceFormPage.selectDate(19,"July" , 2025);
        practiceFormPage.selectSubject("M" , "Chemistry");
        practiceFormPage.selectSubject("M" , "Maths");
        practiceFormPage.selectHobbies(0,1,2);
        practiceFormPage.importFile("C:\\Users\\lvvls\\OneDrive\\Изображения\\5eb82772c8275881ecb2c73f60f87eaf.jpg");
        practiceFormPage.inputAddress("г.Москва, Сентябрьский проезд 2/2");
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");
        practiceFormPage.clickSubmit();

        practiceFormPage.verifyModalTitle()
                .shouldHave(text("Thanks for submitting the form"));

        practiceFormPage.verifySubmittedData("Student Name", "Сергей Петров");
        practiceFormPage.verifySubmittedData("Student Email", "SergeyPetrov@mail.ru");
        practiceFormPage.verifySubmittedData("Gender", "Male");
        practiceFormPage.verifySubmittedData("Mobile", "8905888533");
        practiceFormPage.verifySubmittedData("Date of Birth", "19 July,2025");
        practiceFormPage.verifySubmittedData("Subjects", "Chemistry, Maths");
        practiceFormPage.verifySubmittedData("Hobbies", "Sports, Reading, Music");
        practiceFormPage.verifySubmittedData("Picture", "5eb82772c8275881ecb2c73f60f87eaf.jpg");
        practiceFormPage.verifySubmittedData("Address", "г.Москва, Сентябрьский проезд 2/2");
        practiceFormPage.verifySubmittedData("State and City", "NCR Delhi");
    }
}
