package paradigma0621.cleanchess.novos.model;

import javafx.stage.Stage;

public class StageSingleton {
    private static Stage singleton;

    public static void setInstance(Stage stage) {
        singleton = stage;
    }

    public static Stage getInstance() {
        return singleton;
    }

}
