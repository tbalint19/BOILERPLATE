package util.security.model;

public interface AuthEntity {

    String getPassword();

    void setPassword(String password);

    Long getId();

    String getUsername();

    Boolean getConfirmed();

    Boolean getActive();
}
