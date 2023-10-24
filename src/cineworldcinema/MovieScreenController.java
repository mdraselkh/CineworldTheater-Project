package cineworldcinema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MovieScreenController implements Initializable {

    @FXML
    private Label due1;
    @FXML
    private Label run1;
    @FXML
    private Label due2;
    @FXML
    private Label run2;
    @FXML
    private Label due3;
    @FXML
    private Label run3;
    @FXML
    private Tab screen2;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private Label due4;
    @FXML
    private Label run4;
    @FXML
    private ImageView img5;
    @FXML
    private Label due5;
    @FXML
    private Label run5;
    @FXML
    private ImageView img6;
    @FXML
    private Label due6;
    @FXML
    private Label run6;
    @FXML
    private ImageView img7;
    @FXML
    private Label due7;
    @FXML
    private Label run7;
    @FXML
    private ImageView img8;
    @FXML
    private Label due8;
    @FXML
    private Label run8;
    @FXML
    private ImageView img9;
    @FXML
    private Label due9;
    @FXML
    private Label run9;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private Label name3;
    @FXML
    private Label name4;
    @FXML
    private Label name5;
    @FXML
    private Label name6;
    @FXML
    private Label name7;
    @FXML
    private Label name8;
    @FXML
    private Label name9;
    @FXML
    private Tab screen3;
    @FXML
    private Tab screen1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            screenOne();
            screentwo();
            screenthree();

        } catch (Exception ex) {

        }
    }

    @FXML
    private void screenOne() throws FileNotFoundException, IOException, SQLException {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            String query = "select * from movies";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {

                if (((rs.getString("Status").equals("Showing") && (rs.getString("Screen1").equals("SCREEN 1"))))) {
                    if (rs.getString("ShowTime1").equals("11:30AM- 01:30PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");

                        // img1.setImage(img);
                        img1.setImage(img);
                        name1.setText(rs.getString("MovieName"));
                        due1.setText(rs.getString("Duration"));
                        run1.setText(rs.getString("ShowTime1"));
                    }
                    if (rs.getString("ShowTime2").equals("02:30PM- 05:30PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");
                        img2.setImage(img);
                        name2.setText(rs.getString("MovieName"));
                        due2.setText(rs.getString("Duration"));
                        run2.setText(rs.getString("ShowTime2"));

                    }
                    if (rs.getString("ShowTime3").equals("06:00PM- 09:00PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");
                        img3.setImage(img);
                        name3.setText(rs.getString("MovieName"));
                        due3.setText(rs.getString("Duration"));
                        run3.setText(rs.getString("ShowTime3"));

                    }
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void screentwo() throws FileNotFoundException, IOException {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            String query = "select * from movies";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {

                if (((rs.getString("Status").equals("Showing") && (rs.getString("Screen2").equals("SCREEN 2"))))) {
                    if (rs.getString("ShowTime1").equals("11:30AM- 01:30PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");

                        img4.setImage(img);
                        name4.setText(rs.getString("MovieName"));
                        due4.setText(rs.getString("Duration"));
                        run4.setText(rs.getString("ShowTime1"));

                    }
                    if (rs.getString("ShowTime2").equals("02:30PM- 05:30PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }

                        Image img = new Image("file:photo.jpg");

                        img5.setImage(img);
                        name5.setText(rs.getString("MovieName"));
                        due5.setText(rs.getString("Duration"));
                        run5.setText(rs.getString("ShowTime2"));
                    }
                    if (rs.getString("ShowTime3").equals("06:00PM- 09:00PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");

                        img6.setImage(img);
                        name6.setText(rs.getString("MovieName"));
                        due6.setText(rs.getString("Duration"));
                        run6.setText(rs.getString("ShowTime3"));
                    }
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    @FXML
    private void screenthree() throws FileNotFoundException, IOException {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            String query = "select * from movies";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {

                if (((rs.getString("Status").equals("Showing") && (rs.getString("Screen3").equals("SCREEN 3"))))) {
                    if (rs.getString("ShowTime1").equals("11:30AM- 01:30PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");

                        img7.setImage(img);
                        name7.setText(rs.getString("MovieName"));
                        due7.setText(rs.getString("Duration"));
                        run7.setText(rs.getString("ShowTime1"));

                    }
                    if (rs.getString("ShowTime2").equals("02:30PM- 05:30PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");

                        img8.setImage(img);
                        name8.setText(rs.getString("MovieName"));
                        due8.setText(rs.getString("Duration"));
                        run8.setText(rs.getString("ShowTime2"));

                    }
                    if (rs.getString("ShowTime3").equals("06:00PM- 09:00PM")) {

                        InputStream is = rs.getBinaryStream("Poster");
                        OutputStream os = new FileOutputStream(new File("photo.jpg"));

                        byte[] content = new byte[1024];
                        int size = 0;

                        while ((size = is.read(content)) != -1) {

                            os.write(content, 0, size);
                        }
                        Image img = new Image("file:photo.jpg");

                        img9.setImage(img);
                        name9.setText(rs.getString("MovieName"));
                        due9.setText(rs.getString("Duration"));
                        run9.setText(rs.getString("ShowTime3"));

                    }
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

}
