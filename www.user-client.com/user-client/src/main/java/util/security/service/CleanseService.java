package util.security.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CleanseService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public String cleanseEmail(String email) {
        String username = email.split("@")[0].toLowerCase();
        String domain = email.split("@")[1];
        String cleansed = username.split(
                "\\+")[0].split(" ")[0].replace(".", "");
        return cleansed + "@" + domain;
    }

    public String cleanseUsername(String username) {
        String lowerCase = username.toLowerCase();
        String noSpace = lowerCase.replace(" ", "");
        return noSpace;
    }

    public String cleanseCredential(String credential) {
        String cleansed;
        if (isEmail(credential)) {
            cleansed = cleanseEmail(credential);
        } else {
            cleansed = cleanseUsername(credential);
        }
        return cleansed;
    }

    public Boolean isEmail(String credential) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(credential);
        return matcher.find();
    }
}
