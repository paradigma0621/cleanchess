package paradigma.cleanchess.novos.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paradigma.cleanchess.MainApp;
import paradigma.cleanchess.novos.model.StageSingleton;

import java.io.IOException;

public class Loader {
    public static void setRoot(String fxml, String title) throws IOException {
        Stage stage = StageSingleton.getInstance();
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
