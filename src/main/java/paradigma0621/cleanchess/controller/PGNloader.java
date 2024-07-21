package paradigma0621.cleanchess.controller;



import javafx.fxml.FXML;


import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;
import paradigma0621.cleanchess.MainApp;
//import groupId.artifactId.model.Board;



@SuppressWarnings("all")


public class PGNloader {

    private static Stage stage;
	
	@FXML
     public void loadPGN() {
	 	createNewStage();
      // 	 Board board = new Board();	//model do tabuleiro
	// GUIboard guiBoard = new GUIboard(); //view do tabuleiro
     }
	 
     @FXML
     public void mudaLabelYo() {

	}

     public void createNewStage() {
	    
	 
		System.out.println("agora ok");
		Stage stageNovo = new Stage();	
	       	try {
		    load(stageNovo);
		} catch (IOException e) {
			 System.out.println("Ocorreu o erro: " + e.getMessage());
		}
     }

    
 
    
	public static void load(Stage stageDaqui) throws IOException {
		stage=stageDaqui;
        	setRoot("newBoard","");
	}

	public static Stage getStage() {
		return stage;
	}



	    
    static void setRoot(String fxml, String title) throws IOException {
		Scene scene = new Scene(loadFXML(fxml));
		stage.setTitle(title);
        	stage.setScene(scene);
       		stage.show();


		stage.setFullScreen(true);
		//stagRef.setMaximized(true);

					
	//	Game game = new Game(scene);
	
	//   	game.addObserver(squares[64]);

		
    }
    //Penso de fazer uma classe "casa" para representar cada um dos quadradinhos do tabuleiro.
    //Posso extender Observable nessa classe
 
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }



    
}


