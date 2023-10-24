package cineworldcinema;

public class userList {

    int sn;
    String name, mail;

    public userList(int sn, String name, String mail) {
        this.sn = sn;
        this.name = name;
        this.mail = mail;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

   
}
