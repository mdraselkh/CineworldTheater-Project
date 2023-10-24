package cineworldcinema;

import static cineworldcinema.MoviesController.movieid;
import static cineworldcinema.database.UserName;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SeatReserveController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label movieName;
    @FXML
    private ImageView moviePoster;
    @FXML
    private Label screenName;
    @FXML
    private Label time;
    @FXML
    private AnchorPane sidePane;
    @FXML
    private ImageView imgA1;
    @FXML
    private ImageView imgA2;
    @FXML
    private ImageView imgA3;
    @FXML
    private ImageView imgA4;
    @FXML
    private ImageView imgA5;
    @FXML
    private ImageView imgB1;
    @FXML
    private ImageView imgB2;
    @FXML
    private ImageView imgB3;
    @FXML
    private ImageView imgB4;
    @FXML
    private ImageView imgB5;
    @FXML
    private ImageView imgC1;
    @FXML
    private ImageView imgC2;
    @FXML
    private ImageView imgC3;
    @FXML
    private ImageView imgC4;
    @FXML
    private ImageView imgC5;
    @FXML
    private ImageView imgD1;
    @FXML
    private ImageView imgD2;
    @FXML
    private ImageView imgD3;
    @FXML
    private ImageView imgD4;
    @FXML
    private ImageView imgD5;
    @FXML
    private Label selectText;

    ComboBox<String> combo;
    String e_Date;
    int price, TOTALPRICE;

    Image availImage, selectImage, bookedImage;
    String bnA1, bnA2, bnA3, bnA4, bnA5;
    String bnB1, bnB2, bnB3, bnB4, bnB5;
    String bnC1, bnC2, bnC3, bnC4, bnC5;
    String bnD1, bnD2, bnD3, bnD4, bnD5;

    ObservableList selectedSeat = FXCollections.observableArrayList();
    ObservableList bookedSeat = FXCollections.observableArrayList();
    @FXML
    private Label ticketPrice;
    @FXML
    private Label totalPrice;

    static int TICKET;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TOTALPRICE = 0;
        initialInfo();
        datePicker();

        try {
            FileInputStream fis1 = new FileInputStream("F:\\Sd Project\\Cineworldtheater\\src\\images\\emptySeat.png");
            FileInputStream fis2 = new FileInputStream("F:\\Sd Project\\Cineworldtheater\\src\\images\\selectSeat.png");
            FileInputStream fis3 = new FileInputStream("F:\\Sd Project\\Cineworldtheater\\src\\images\\bookedSeat.png");
            availImage = new Image(fis1);
            selectImage = new Image(fis2);
            bookedImage = new Image(fis3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        bnA1 = "A1";
        bnA2 = "A2";
        bnA3 = "A3";
        bnA4 = "A4";
        bnA5 = "A5";

        bnB1 = "B1";
        bnB2 = "B2";
        bnB3 = "B3";
        bnB4 = "B4";
        bnB5 = "B5";

        bnC1 = "C1";
        bnC2 = "C2";
        bnC3 = "C3";
        bnC4 = "C4";
        bnC5 = "C5";

        bnD1 = "D1";
        bnD2 = "D2";
        bnD3 = "D3";
        bnD4 = "D4";
        bnD5 = "D5";
        
        
        
    }

    @FXML
    private void BackToMovieInfo(MouseEvent event) {
         try {
                Parent Home = FXMLLoader.load(getClass().getResource("/FXMLs/Home.fxml"));

                Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();

                home.setScene(new Scene(Home));
                home.setTitle("Welcome");
                home.show();
            } catch (IOException ex) {
                System.out.println("Can't load");
            }
    }

    @FXML
    private void seatBookingAction(ActionEvent event) {

        if (selectedSeat.isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("You didn't choose any seat");
            a.show();
        } else {

            //generate ticket id
            int rand = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
            String randomNum = Integer.toString(rand);

            SimpleDateFormat formatter = new SimpleDateFormat("ddMM");
            Date currDate = Calendar.getInstance().getTime();
            String date = formatter.format(currDate);

            String num = date + randomNum;
            int ticketNum = Integer.parseInt(num);
            TICKET = ticketNum;

            System.out.println(ticketNum);
            // System.out.println(currDate.toString());
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
                String qu = "insert into booking (TicketID,userName, movieName, Screen, ShowTime, "
                        + "ShowDate, SeatNo, Status, TotalPrice, BookingTime) values(?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement pstm = con.prepareStatement(qu);

                pstm.setInt(1, ticketNum);
                pstm.setString(2, UserName);
                pstm.setString(3, movieName.getText());
                pstm.setString(4, screenName.getText());
                pstm.setString(5, time.getText());
                pstm.setString(6, combo.getValue());
                pstm.setString(7, selectedSeat.toString());
                pstm.setString(8, "Booked");
                pstm.setInt(9, TOTALPRICE);
                pstm.setString(10, currDate.toString());

                pstm.executeUpdate();
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            try {
                Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/TicketInfo.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(view));
                stage.show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    void initialInfo() {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            Statement stm = con.createStatement();
            String query = "SELECT movieName, Poster, EndDate, Screen1, Screen2, Screen3, "
                    + "ShowTime1, ShowTime2, ShowTime3, Price from movies where Serial=" + movieid;
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                movieName.setText(rs.getString("MovieName"));
                e_Date = rs.getString("EndDate");
                price = rs.getInt("Price");
                ticketPrice.setText(Integer.toString(rs.getInt("Price")));

                InputStream is = rs.getBinaryStream("Poster");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));

                byte[] content = new byte[1024];
                int size = 0;

                while ((size = is.read(content)) != -1) {

                    os.write(content, 0, size);
                }
                Image image = new Image("file:photo.jpg");
                moviePoster.setImage(image);

                if (!(rs.getString("Screen1").equals(""))) {
                    screenName.setText(rs.getString("Screen1"));
                }
                if (!(rs.getString("Screen2").equals(""))) {
                    screenName.setText(rs.getString("Screen2"));
                }
                if (!(rs.getString("Screen3").equals(""))) {
                    screenName.setText(rs.getString("Screen3"));
                }
                if (!(rs.getString("ShowTime1").equals(""))) {
                    time.setText(rs.getString("ShowTime1"));
                }
                if (!(rs.getString("ShowTime2").equals(""))) {
                    time.setText(rs.getString("ShowTime2"));
                }
                if (!(rs.getString("ShowTime3").equals(""))) {
                    time.setText(rs.getString("ShowTime3"));
                }
                con.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void datePicker() {
        try {
            combo = new ComboBox<>();
            combo.setPromptText("Choose Show Date");
            combo.setPrefSize(170, 30);
            combo.setLayoutX(10);
            combo.setLayoutY(230);
            sidePane.getChildren().add(combo);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            Date currDate = Calendar.getInstance().getTime();
            Date d_end = new SimpleDateFormat("dd-MM-yyyy").parse(e_Date);

            long t1 = currDate.getTime();
            long t2 = d_end.getTime();

            if (t1 < t2) {
                for (long i = t1; i <= t2; i += 86400000) {

                    combo.getItems().add(formatter.format(i));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        combo.setOnAction(e -> getInfo());
    }

    void getInfo() {
        bookedSeat.clear();
        selectedSeat.clear();
        selectText.setText("");
        totalPrice.setText("");
        TOTALPRICE=0;
        
        imgA1.setImage(availImage);
        imgA2.setImage(availImage);
        imgA3.setImage(availImage);
        imgA4.setImage(availImage);
        imgA5.setImage(availImage);
        imgB1.setImage(availImage);
        imgB2.setImage(availImage);
        imgB3.setImage(availImage);
        imgB4.setImage(availImage);
        imgB5.setImage(availImage);
        imgC1.setImage(availImage);
        imgC2.setImage(availImage);
        imgC3.setImage(availImage);
        imgC4.setImage(availImage);
        imgC5.setImage(availImage);
        imgD1.setImage(availImage);
        imgD2.setImage(availImage);
        imgD3.setImage(availImage);
        imgD4.setImage(availImage);
        imgD5.setImage(availImage);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            Statement stm = con.createStatement();
            String query = "select *from booking";
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                if ((rs.getString("movieName").equals(movieName.getText())) && (rs.getString("ShowDate").equals(combo.getValue()))) {
                    String str = rs.getString("SeatNo").replace("[", "").replace("]", "").replace(",", "");

                    String[] words = str.split("\\s+");

                    for (String s : words) {
                        bookedSeat.add(s);
                    }
                }
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

      //  System.out.println(bookedSeat);
        if (bookedSeat.contains("A1")) {
            imgA1.setImage(bookedImage);
        }
        if (bookedSeat.contains("A2")) {
            imgA2.setImage(bookedImage);
        }
        if (bookedSeat.contains("A3")) {
            imgA3.setImage(bookedImage);
        }
        if (bookedSeat.contains("A4")) {
            imgA4.setImage(bookedImage);
        }
        if (bookedSeat.contains("A5")) {
            imgA5.setImage(bookedImage);
        }
        if (bookedSeat.contains("B1")) {
            imgB1.setImage(bookedImage);
        }
        if (bookedSeat.contains("B2")) {
            imgB2.setImage(bookedImage);
        }
        if (bookedSeat.contains("B3")) {
            imgB3.setImage(bookedImage);
        }
        if (bookedSeat.contains("B4")) {
            imgB4.setImage(bookedImage);
        }
        if (bookedSeat.contains("B5")) {
            imgB5.setImage(bookedImage);
        }
        if (bookedSeat.contains("C1")) {
            imgC1.setImage(bookedImage);
        }
        if (bookedSeat.contains("C2")) {
            imgC2.setImage(bookedImage);
        }
        if (bookedSeat.contains("C3")) {
            imgC3.setImage(bookedImage);
        }
        if (bookedSeat.contains("C4")) {
            imgC4.setImage(bookedImage);
        }
        if (bookedSeat.contains("C5")) {
            imgC5.setImage(bookedImage);
        }
        if (bookedSeat.contains("D1")) {
            imgD1.setImage(bookedImage);
        }
        if (bookedSeat.contains("D2")) {
            imgD2.setImage(bookedImage);
        }
        if (bookedSeat.contains("D3")) {
            imgD3.setImage(bookedImage);
        }
        if (bookedSeat.contains("D4")) {
            imgD4.setImage(bookedImage);
        }
        if (bookedSeat.contains("D5")) {
            imgD5.setImage(bookedImage);
        }
    }

    void selectionControl(ImageView imgview, String Seat) {
        //check date
        if (combo.getValue() == null) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Please Choose A Date");
            a.show();
        } else {
            //for undo
            if (selectedSeat.contains(Seat) && !(bookedSeat.contains(Seat))) {
                imgview.setImage(availImage);
                selectedSeat.remove(Seat);
                selectText.setText(selectedSeat.toString());
                TOTALPRICE = TOTALPRICE - price;
                totalPrice.setText(Integer.toString(TOTALPRICE));
            } else if (bookedSeat.contains(Seat)) {
                Alert a = new Alert(AlertType.WARNING);
                a.setContentText("Seat Already Booked!!!");
                a.show();
            } else {
                //for selection
                if (selectedSeat.size() < 4) {
                    if (!(bookedSeat.contains(Seat))) {
                        imgview.setImage(selectImage);
                        selectedSeat.add(Seat);
                        selectText.setText(selectedSeat.toString());
                        TOTALPRICE = TOTALPRICE + price;
                        totalPrice.setText(Integer.toString(TOTALPRICE));
                    } else {
                        Alert a = new Alert(AlertType.WARNING);
                        a.setContentText("Seat Already Booked!!!");
                        a.show();
                    }
                } else {
                    Alert a = new Alert(AlertType.WARNING);
                    a.setContentText("You Can't Book Over 4 Seats");
                    a.show();
                }
            }
        }
    }

    @FXML
    private void A1Action(MouseEvent event) {
        selectionControl(imgA1, bnA1);
    }

    @FXML
    private void A2Action(MouseEvent event) {
        selectionControl(imgA2, bnA2);
    }

    @FXML
    private void A3Action(MouseEvent event) {
        selectionControl(imgA3, bnA3);
    }

    @FXML
    private void A4Action(MouseEvent event) {
        selectionControl(imgA4, bnA4);
    }

    @FXML
    private void A5Action(MouseEvent event) {
        selectionControl(imgA5, bnA5);
    }

    @FXML
    private void B1Action(MouseEvent event) {
        selectionControl(imgB1, bnB1);
    }

    @FXML
    private void B2Action(MouseEvent event) {
        selectionControl(imgB2, bnB2);
    }

    @FXML
    private void B3Action(MouseEvent event) {
        selectionControl(imgB3, bnB3);
    }

    @FXML
    private void B4Action(MouseEvent event) {
        selectionControl(imgB4, bnB4);
    }

    @FXML
    private void B5Action(MouseEvent event) {
        selectionControl(imgB5, bnB5);
    }

    @FXML
    private void C1Action(MouseEvent event) {
        selectionControl(imgC1, bnC1);
    }

    @FXML
    private void C2Action(MouseEvent event) {
        selectionControl(imgC2, bnC2);
    }

    @FXML
    private void C3Action(MouseEvent event) {
        selectionControl(imgC3, bnC3);
    }

    @FXML
    private void C4Action(MouseEvent event) {
        selectionControl(imgC4, bnC4);
    }

    @FXML
    private void C5Action(MouseEvent event) {
        selectionControl(imgC5, bnC5);
    }

    @FXML
    private void D1Action(MouseEvent event) {
        selectionControl(imgD1, bnD1);
    }

    @FXML
    private void D2Action(MouseEvent event) {
        selectionControl(imgD2, bnD2);
    }

    @FXML
    private void D3Action(MouseEvent event) {
        selectionControl(imgD3, bnD3);
    }

    @FXML
    private void D4Action(MouseEvent event) {
        selectionControl(imgD4, bnD4);
    }

    @FXML
    private void D5Action(MouseEvent event) {
        selectionControl(imgD5, bnD5);
    }

}
