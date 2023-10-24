package cineworldcinema;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class root extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        checkTicketExpiredDate();
        Parent signIn = FXMLLoader.load(getClass().getResource("/FXMLs/signIn.fxml"));
        Scene scn = new Scene(signIn);
        primaryStage.setScene(scn);
        primaryStage.setTitle("Sign In");
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    void checkTicketExpiredDate() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "select ShowDate from booking";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                String s_date = rs.getString("ShowDate");

                Date currDate = Calendar.getInstance().getTime();
                Date d_end = new SimpleDateFormat("dd-MMM-yyyy").parse(s_date);

                long t1 = currDate.getTime();
                long t2 = d_end.getTime();
              

                if (t1 > t2) {
                    String sql = "update booking set Status=? where ShowDate=?";
                    PreparedStatement pstm = con.prepareStatement(sql);

                    pstm.setString(1, "Expired");
                    pstm.setString(2, s_date);

                    pstm.executeUpdate();
                   
                }
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
