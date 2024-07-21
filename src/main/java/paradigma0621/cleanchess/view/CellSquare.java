package paradigma0621.cleanchess.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

@SuppressWarnings("all")
public class CellSquare {
	// ChessBoard chessBoard;
	private double escala = 1;
	private GridPane gridPane;

	public CellSquare() {
	    ImageView imageView10 = new ImageView();
	    		String strCasaPreta = getClass().getResource("/images/casaPreta96x96.jpg").toExternalForm();
		String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
		String strBrancasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoBranco.jpg").toExternalForm();
		String strBrancasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoBranco.jpg").toExternalForm();

		Image imgCasaBranca = new Image(strCasaBranca);
		imageView10.setImage(imgCasaBranca);
		imageView10.setFitWidth(96 * escala);
	}
}
