package ru.reksoft.testapp;

/**
 * Contact from notebook.
 * Contains name (String), phone number (String), email address (String)
 */
public class ContactEntity {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public ContactEntity(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return formatted string
     */
    public String toString() {
        //TODO сделать нормальное форматирование
        String s = this.getName() + " | " + this.getPhoneNumber() + " | " + this.getEmailAddress();
        return s;
    }
}
