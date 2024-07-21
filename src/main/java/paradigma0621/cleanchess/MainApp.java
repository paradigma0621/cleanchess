package paradigma0621.cleanchess;

import javafx.application.Application;
import javafx.stage.Stage;
import paradigma0621.cleanchess.controller.PGNsCenter;

@SuppressWarnings("all")
public class MainApp extends Application {
    //private static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {

	try {
 	PGNsCenter center = new PGNsCenter(stage);
		} catch (Exception e) {
			 System.out.println("Ocorreu o erro dentro start: " + e.getMessage());
			 }

    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //emacs lsp cannot evaluate because of "java.lang.IllegalStateException": Project cannot be found
}
