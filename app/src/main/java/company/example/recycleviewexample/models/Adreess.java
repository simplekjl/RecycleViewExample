package company.example.recycleviewexample.models;

/**
 * Created by admin on 1/21/2016.
 */
public class Adreess {

    private int     id_user;
    private String  street;
    private String  number;
    private String  state;
    private int     fk;

    public Adreess(int id_user, String street, String number, String state, int fk) {
        this.id_user = id_user;
        this.street = street;
        this.number = number;
        this.state = state;
        this.fk = fk;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
