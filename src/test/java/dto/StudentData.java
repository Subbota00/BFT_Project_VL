package dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Data
@Accessors(chain = true)
@Builder
public class StudentData {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String mobile;
    private LocalDate birthDate;
    private List<String> subjects;
    private List<String> hobbies;
    private String filePath;
    private String address;
    private String state;
    private String city;

    public String getFormattedBirthDate() {
        return birthDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    }

    public String getFileName() {
        return new File(filePath).getName();
    }
}
