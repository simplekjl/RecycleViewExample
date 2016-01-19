package company.example.recycleviewexample;

/**
 * Created by admin on 1/19/2016.
 */
public class Contact {

    private String name;
    private String surname;

    public Contact() {
    }

    public Contact(String name, String surname) {

        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}