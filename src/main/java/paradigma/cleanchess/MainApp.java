package paradigma.cleanchess;

import javafx.application.Application;
import javafx.stage.Stage;
import paradigma.cleanchess.controller.PGNloader;

@SuppressWarnings("all")
public class MainApp extends Application {
    //private static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {

	try {
 	PGNloader center = new PGNloader(stage);
		} catch (Exception e) {
			 System.out.println("Ocorreu o erro dentro start: " + e.getMessage());
			 }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
