package paradigma0621.cleanchess;

import javafx.application.Application;
import javafx.stage.Stage;
import paradigma0621.cleanchess.novos.view.PGNloader;
import paradigma0621.cleanchess.novos.model.StageSingleton;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
            StageSingleton.setInstance(stage);
            PGNloader center = new PGNloader();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
