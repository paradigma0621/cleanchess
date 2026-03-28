package paradigma0621.cleanchess.novos.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paradigma0621.cleanchess.MainApp;
import paradigma0621.cleanchess.novos.model.StageSingleton;

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
