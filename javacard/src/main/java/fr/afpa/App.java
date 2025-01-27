package fr.afpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // TODO : créer sous-dossier ".javacard" dans le dossier utilisateur
        // https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
        // Files.createDirectories(Paths.get("C:\\Users\\.javacard"));

         // *** TITLE IN STAGE ***
        stage.setTitle(" JAVA CARD MANAGER");

        // ICON
        Image icon = new Image(getClass().getResourceAsStream("logo_javaCard.png"));
        stage.getIcons().add(icon); // Add icon in stage

        // *** SCENE ***

        // INSTANCIATION
        scene = new Scene(loadFXML("visit_card_manager"), 1024, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}