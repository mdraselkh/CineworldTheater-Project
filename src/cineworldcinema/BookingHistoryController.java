package cineworldcinema;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BookingHistoryController implements Initializable {

    ComboBox<String> combo;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView<Booking> bookingSummary;
    @FXML
    private TableColumn<Booking, String> ticketNumber;
    @FXML
    private TableColumn<Booking, String> cusName;
    @FXML
    private TableColumn<Booking, String> date;
    @FXML
    private TableColumn<Booking, String> status;

    ObservableList<Booking> List = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Booking, String> time;
    @FXML
    private TableColumn<Booking, String> bookTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combo = new ComboBox<>();
        combo.setPromptText("Choose A Movie");
        combo.setPrefSize(230, 30);
        combo.setLayoutX(5);
        combo.setLayoutY(20);
        mainPane.getChildren().add(combo);
        Info();
    }

    void Info() {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "select *from movies";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {

                if (rs.getString("Status").equals("Showing")) {
                    combo.getItems().add(rs.getString("MovieName"));
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        combo.setOnAction(e -> tableView());
    }

    void tableView() {
        bookingSummary.getItems().clear();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "select *from booking";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("movieName").equals(combo.getValue())) {
                    List.add(new Booking(rs.getInt("TicketID"), rs.getString("userName"), rs.getString("ShowTime"), rs.getString("ShowDate"), rs.getString("Status"),rs.getString("BookingTime")));
                }
            }

            ticketNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
            cusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            date.setCellValueFactory(new PropertyValueFactory<>("showDate"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            bookTime.setCellValueFactory(new PropertyValueFactory<>("bookTime"));

            bookingSummary.setItems(List);
            con.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
