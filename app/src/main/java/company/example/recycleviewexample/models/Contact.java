package company.example.recycleviewexample.models;

/**
 * Created by admin on 1/15/2016.
 */
public class Contact {
    private int     id;
    private int     phoneNumber;
    private String  name;
    private int     number;
    private String  state;
    private String  street;

    public Contact(int id, String name, int phoneNumber,String street , int number, String state) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.number = number;
        this.state = state;
        this.street = street;
    }
    public Contact(int id, String name, int phoneNumber){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;

    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Contact()
    {

    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
