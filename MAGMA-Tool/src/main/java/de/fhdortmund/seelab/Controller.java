package de.fhdortmund.seelab;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Collections;
import java.util.Properties;

/**
 * @author Jonas Fleck
 * This is the main de.fhdortmund.seelab.Controller for the FXML application
 */
public class Controller {

    @FXML
    private CheckBox checkAuth;
    @FXML
    private CheckBox checkDiscovery;
    @FXML
    private CheckBox checkUsermanagement;
    @FXML
    private CheckBox checkResource;
    @FXML
    private TextField txtGroupId;
    @FXML
    private TextField txtartifactId;
    @FXML
    private TextField txtPackage;
    @FXML
    private TextField txtCertPath;
    @FXML
    private TextField txtCertPassword;
    @FXML
    private TextField txtKeyPair;
    @FXML
    private TextArea txtPublicKey;
    @FXML
    private TextField txtInstallationPath;
    @FXML
    private ImageView imgDiagram;


    @FXML
    public void initialize() {
        configureCheckBox();
    }


    /**
     * Configures the checkboxes to include or exclude required components.
     */
    private void configureCheckBox() {
        checkAuth.selectedProperty().addListener((ov, o, n) -> {
            if(!n) {
                checkDiscovery.setSelected(false);
                imgDiagram.setImage(null);
            } else {
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AuthComponents.png"));
                imgDiagram.setImage(img);
            }
        });
        checkDiscovery.selectedProperty().addListener((ov, o, n) -> {
            if(!n) {
                checkUsermanagement.setSelected(false);
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AuthComponents.png"));
                imgDiagram.setImage(img);
            } else {
                checkAuth.setSelected(true);
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AuthAndEurekaComponents.png"));
                imgDiagram.setImage(img);
            }
        });
        checkUsermanagement.selectedProperty().addListener((ov, o, n) -> {
            if(!n) {
               checkResource.setSelected(false);
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AuthAndEurekaComponents.png"));
                imgDiagram.setImage(img);
            } else {
                checkDiscovery.setSelected(true);
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AllComponents.png"));
                imgDiagram.setImage(img);
            }
        });
        checkResource.selectedProperty().addListener((ov, o, n) -> {
            if(!n) {
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AllComponents.png"));
                imgDiagram.setImage(img);
            } else {

                checkUsermanagement.setSelected(true);
                Image img = new Image(getClass().getClassLoader().getResourceAsStream("images/AllComponents.png"));
                imgDiagram.setImage(img);
            }
        });

    }


    /**
     * Configures and starts the maven build using the Maven Invoker
     */
    @FXML
    private void build() {
        System.out.println(txtartifactId.getText());
        InvocationRequest request = new DefaultInvocationRequest();
        Properties properties = new Properties();
        properties.setProperty("groupId", txtGroupId.getText());
        properties.setProperty("artifactId", txtartifactId.getText());
        properties.setProperty("version", "1");
        properties.setProperty("CertKeyPair", txtKeyPair.getText());
        properties.setProperty("archetypeGroupId", "Infrastruktur");
        properties.setProperty("CertPassword", txtCertPassword.getText());
        properties.setProperty("CertPublicKey", txtPublicKey.getText());
        properties.setProperty("CertPath", txtCertPath.getText());
        properties.setProperty("package", txtPackage.getText());

        //Select archetype
        String archetype;
        if(checkResource.isSelected()) {
            archetype = "Basiskomponenten-archetype";
        } else if (checkUsermanagement.isSelected()) {
           archetype = "GW_Auth_SD_UM-BasicInfrastructure-Archtype";
        } else if(checkDiscovery.isSelected()) {
            archetype = "GW_Auth_SD_BasicInfrastructure-Archtype";
        } else if(checkAuth.isSelected()) {
            archetype = "GW_Auth-Infrastructure-archetype";
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Build failed");
            alert.setHeaderText("No components selected");
            alert.setContentText("You need to select at least one component to genereate the infrastructure.");
            alert.showAndWait();
            return;
        }

        System.out.println(archetype);

        properties.setProperty("archetypeArtifactId", archetype);

        request.setProperties(properties);
        request.setBaseDirectory(new File(txtInstallationPath.getText()));
        request.setGoals(Collections.singletonList("archetype:generate -B"));

        Invoker invoker = new DefaultInvoker();
        String mavenHome = System.getenv("M2_HOME");
        if(mavenHome == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Build failed");
            alert.setHeaderText("Maven not found");
            alert.setContentText("Please make sure that you have exported the M2_HOME enviroment variable.");
            alert.showAndWait();
            return;
        }
        invoker.setMavenHome(new File(System.getenv("M2_HOME")));
        try {
            InvocationResult result = invoker.execute( request );
            if(result.getExitCode() != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Build failed");
                alert.setHeaderText("The Maven build failed!");
                alert.setContentText("Please check your parameters and make sure that you have installed" +
                        " the archetypes");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("The Maven build succeeded!");
                alert.setContentText("Please check your installation path for the generated infrastructure");
                alert.showAndWait();
            }
        } catch (MavenInvocationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Build failed");
            alert.setHeaderText("The Maven build failed!");
            alert.setContentText("Exception message:" + e.getMessage());
            alert.showAndWait();
        }

    }




}
