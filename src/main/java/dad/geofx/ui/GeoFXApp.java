package dad.geofx.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GeoFXApp extends Application {

    public static Stage primaryStage;
    private MainController mainController = new MainController();

    @Override
    public void start(Stage primaryStage) {

        GeoFXApp.primaryStage = primaryStage;
        primaryStage.setTitle("GeoFX");
        primaryStage.getIcons().add(new Image("/images/icono.png"));
        primaryStage.setScene(new Scene(mainController.getView()));
        primaryStage.show();

    }

}
