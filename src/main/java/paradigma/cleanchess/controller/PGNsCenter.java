package paradigma.cleanchess.controller;

//import javafx.scene.Scene;
//import javafx.stage.Stage;

import paradigma.cleanchess.MainApp;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

//import groupId.artifactId.view.controladorFXML;


@SuppressWarnings("all")
public class PGNsCenter {

       private static Stage stage;
    
    public PGNsCenter(Stage stagRef) {
	
	//		PGNfile filePgn1 = new PGNfile();
	//
		    load(stagRef);
    }

    
    	public static void load(Stage stageRef2){
System.out.println("passed in #01");
	    stage=stageRef2;
	    setRoot("PGNloader","");

	}

	    
    static void setRoot(String fxml, String title){
	System.out.println("passed in #02");
		Scene scene = new Scene(loadFXML(fxml));
		stage.setTitle(title);
		    stage.setScene(scene);
		    stage.show();

		    
	//	Game game = new Game(scene);
	
	//   	game.addObserver(squares[64]);

	
    }
    //Penso de fazer uma classe "casa" para representar cada um dos quadradinhos do tabuleiro.
    //Posso extender Observable nessa classe

    private static Parent loadFXML(String fxml) {
        try {
			FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
			System.out.println("passed in #03");
			System.out.println("local= " + MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
			return fxmlLoader.load();
		} catch (Exception e) {
			System.out.println("erro dentro da loadFXML");
			e.printStackTrace();
		}
        return null;
    }


}
