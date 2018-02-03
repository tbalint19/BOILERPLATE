package util.security.service;

import org.springframework.stereotype.Service;

@Service
public class CleanseService {

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
        String emailRegexp = "";
        String cleansed;
        if (credential.matches(emailRegexp)) {
            cleansed = cleanseEmail(credential);
        } else {
            cleansed = cleanseUsername(credential);
        }
        return cleansed;
    }
}
