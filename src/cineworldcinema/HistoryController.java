package cineworldcinema;

import static cineworldcinema.database.UserName;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class HistoryController implements Initializable {

    @FXML
    private TableView<historyList> historyTable;
    @FXML
    private TableColumn<historyList, String> name;
    @FXML
    private TableColumn<historyList, String> film;
    @FXML
    private TableColumn<historyList, String> ticketid;
    @FXML
    private TableColumn<historyList, String> screen;
    @FXML
    private TableColumn<historyList, String> showdate;
    @FXML
    private TableColumn<historyList, String> showtime;
    @FXML
    private TableColumn<historyList, String> seat;
    @FXML
    private TableColumn<historyList, String> totalprice;

    ObservableList<historyList> hList = FXCollections.observableArrayList();
    @FXML
    private JFXButton removebtn;
    @FXML
    private JFXButton refreshbtn1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            historydetails();
        } catch (SQLException ex) {
            System.out.println("can't load");
        }
    }

    void historydetails() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from booking";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            if (rs.getString("userName").equals(UserName)) {

                hList.add(new historyList(rs.getInt("Serial"), rs.getInt("TicketID"), rs.getInt("TotalPrice"), 
                        rs.getString("movieName"), rs.getString("Screen"), rs.getString("ShowTime"), 
                        rs.getString("ShowDate"), rs.getString("SeatNo")));
            }
        }

        film.setCellValueFactory(new PropertyValueFactory<>("film"));
        ticketid.setCellValueFactory(new PropertyValueFactory<>("ticketid"));
        screen.setCellValueFactory(new PropertyValueFactory<>("screen"));
        showdate.setCellValueFactory(new PropertyValueFactory<>("showdate"));
        showtime.setCellValueFactory(new PropertyValueFactory<>("showtime"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        totalprice.setCellValueFactory(new PropertyValueFactory<>("totalprice"));

        historyTable.setItems(hList);
        con.close();
    }

    @FXML
    private void RemoveAction(ActionEvent event) throws SQLException {
        if (historyTable.getSelectionModel().getSelectedItem() != null) {

            historyList data = historyTable.getSelectionModel().getSelectedItem();
            int id = data.Serial;

            int returnValue = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Want to Remove?", JOptionPane.YES_NO_OPTION);
            System.out.println(returnValue);

            if (returnValue == JOptionPane.YES_OPTION) {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

                String query = "delete from booking where Serial=?";

                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setInt(1, id);
                pstm.execute();
                con.close();
                historyTable.getItems().remove(data);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Select a row");
        }
    }

}
