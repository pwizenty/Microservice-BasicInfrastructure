package de.fhdortmund.seelab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jonas on 26.05.17.
 */
public class MainFrame extends Application {


    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/main.fxml"));

        Scene scene = new Scene(root, 700, 450);
        stage.setTitle("MAGMA-Tool");
        stage.setScene(scene);
        stage.show();
    }




    public static void main (String[] args) {
        launch(args);
    }
}
