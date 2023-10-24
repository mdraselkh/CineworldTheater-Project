package cineworldcinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class database {

    static int profile;
    static String UserName;

    void setUserData(String name, String mail, String pass) throws Exception {
        boolean found = false;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from users";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            if (rs.getString("email").equals(mail)) {
                found = true;
                break;
            }
        }
        if (found) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Email already Used");
            a.show();
        } else {
            String qu = "insert into users (name,email,password) values(?,?,?)";

            PreparedStatement pstm = con.prepareStatement(qu);

            pstm.setString(1, name);
            pstm.setString(2, mail);
            pstm.setString(3, pass);

            pstm.executeUpdate();
            System.out.println("User information updated...");

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Sign Up Complete");
            a.show();
        }
        con.close();

    }

    boolean getUserData(String mail, String pass) {
        boolean match = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "select *from users";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("email").equals(mail) && rs.getString("password").equals(pass)) {
                    match = true;
                    profile = rs.getInt("serial");
                    UserName = rs.getString("name");
                    System.out.println("Matching successful...");
                    break;
                } else {
                    match = false;
                }
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("database connection failed");
        }
        return match;
    }
}
