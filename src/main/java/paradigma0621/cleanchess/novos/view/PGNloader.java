package paradigma0621.cleanchess.novos.view;

import javafx.stage.StageStyle;
import paradigma0621.cleanchess.novos.model.StageSingleton;

import java.io.IOException;

public class PGNloader {

	public PGNloader() throws IOException {
		StageSingleton.getInstance().initStyle(StageStyle.UNDECORATED); // Remove a barra de título
		System.out.println("depois UNDECORATED");
		System.out.println("depois createNewStage");
		Loader.setRoot("newBoard","Clean Chess Interface");
     }
	 
}


