package elbaldi.GUI;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) throws IOException {
        super(children);
        // Add action handler for mouse clicked
       
 this.setOnMouseClicked(e -> {
        try {
            Stage stage = new Stage();
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/elbaldi/GUI/showReservationBack.fxml"));
            //AnchorPane anchorpane = fxmlLoader.load();
             Parent root = fxmlLoader.load();
            // Set the value of the date field in the controller
            ShowReservationBackController controller = fxmlLoader.getController();
            controller.setDate(java.sql.Date.valueOf(date));
            controller.initialize(null,null);
            
            // Initialize the scene with the anchorpane
            Scene scene = new Scene(root, 405, 392);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AnchorPaneNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
        //System.out.println("This pane's date is: " + date)
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
