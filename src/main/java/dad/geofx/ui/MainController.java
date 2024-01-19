package dad.geofx.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // Controllers

    private LocationController locationController = new LocationController();
    private ConnectionView connectionController = new ConnectionView();
    private SecurityController securityController = new SecurityController();

    @FXML
    private Tab connectionTab;

    @FXML
    private Tab locationTab;

    @FXML
    private Tab securityTab;

    @FXML
    private BorderPane view;

    public MainController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        locationTab.setContent(locationController.getView());
        connectionTab.setContent(connectionController.getView());
        securityTab.setContent(securityController.getView());

    }

    public BorderPane getView() {
        return view;
    }

}
