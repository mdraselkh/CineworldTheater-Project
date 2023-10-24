
package cineworldcinema;

public class adminList {
    int Serial;
    String name, userName, mail;

    public adminList(int Serial, String name, String userName, String mail) {
        this.Serial = Serial;
        this.name = name;
        this.userName = userName;
        this.mail = mail;
    }

    public int getSerial() {
        return Serial;
    }

    public void setSerial(int Serial) {
        this.Serial = Serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

 
    
}
