/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneakerbadge01;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pears
 */
public class MainInterfaceController implements Initializable {

    private Stage stage;
    
    @FXML
    private ScrollPane theScrollPane;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void ready(Stage stage) {
        this.stage = stage;
        
        MeritBadgeGridPane mbGrid = new MeritBadgeGridPane();
        
        theScrollPane.setContent(mbGrid);
        
        ArrayList<String> artMeritBadgeRequirements = new ArrayList<>();
        artMeritBadgeRequirements.addAll(Arrays.asList(
                "1a",
                "1b",
                "1c",
                "2",
                "3",
                "4.1",
                "4.2",
                "4.3",
                "4.4",
                "5",
                "6",
                "7")
        );
        
        Scout stephen = new Scout("Stephen", "Davidson");
        Scout pearse = new Scout("Pearse", "Hutson");
        
        mbGrid.setGrid(artMeritBadgeRequirements);
        mbGrid.addScout(stephen);
        mbGrid.addScout(pearse);
    }
    
    
    
    
}
