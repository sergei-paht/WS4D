package sample.POJO;

public class User {
    public int idUsers;
    private String number;
    private String password;

    public User(String number, String password) {
        this.number = number;
        this.password = password;
    }

    public User() {

    }


    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}