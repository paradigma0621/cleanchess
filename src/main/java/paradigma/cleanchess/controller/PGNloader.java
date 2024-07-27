package paradigma.cleanchess.controller;



import javafx.fxml.FXML;


import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;
import paradigma.cleanchess.MainApp;
//import groupId.artifactId.model.Board;



@SuppressWarnings("all")


public class PGNloader {

    private static Stage stage;
	
	//@FXML
	public PGNloader(Stage stage) {
		this.stage = stage;
	 	createNewStage();
      // 	 Board board = new Board();	//model do tabuleiro
	// GUIboard guiBoard = new GUIboard(); //view do tabuleiro
     }
	 
	@FXML
	public void mudaLabelYo() {

	}

	public void createNewStage() {
	    
		try {
		    load(stage);
		} catch (IOException e) {
			 System.out.println("### Ocurred error: " + e.getMessage());
		}
     }

	public static void load(Stage stageDaqui) throws IOException {
		stage=stageDaqui;
        	setRoot("newBoard","Clean Chess Interface");
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
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

}


