package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {

    public void setScreen(String screen, ActionEvent actionEvent) {
        Stage current = getCurrentScene(actionEvent);
        setScene(screen,current);
    }

    private void setScene(String nextScene, Stage current) {
        Parent newScene = null;
        try {
            newScene = FXMLLoader.load(getClass().getResource(String.format("/%s.fxml", nextScene)));
        } catch(IOException e) {
            e.printStackTrace();
        }

        Parent finalNewScene = newScene;
            Platform.runLater(() -> {
                assert finalNewScene != null;
                current.setScene(new Scene(finalNewScene));
            });

    }

    public Stage getCurrentScene(ActionEvent actionEvent){
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }
}