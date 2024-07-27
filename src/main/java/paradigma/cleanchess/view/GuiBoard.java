package paradigma.cleanchess.view;

import paradigma.cleanchess.model.FenProcessor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


@SuppressWarnings("all")
public class GuiBoard {
	// ChessBoard chessBoard;
	private double scaleFactor = 0.8;
	private GridPane gridPane;
	private FenProcessor FENProcessor;
	private StackPane stackPane;
	private ImageView imageSq[][] = new ImageView[9][9];
   	private ImageView imageEmptyBoard[][] = new ImageView[9][9];
   	private boolean needToRotateTheBoard = false;
   	int angleToRotateTheBoard=0;
   	
	private Image imgCasaBranca;
	private Image imgCasaPreta;

	private Image imgBrancasPeaoCasaBranca;
	private Image imgBrancasPeaoCasaPreta;

	private Image imgBrancasTorreCasaBranca;
	private Image imgBrancasTorreCasaPreta;

	private Image imgBrancasCavaloCasaBranca;
	private Image imgBrancasCavaloCasaPreta;

	private Image imgBrancasBispoCasaBranca;
	private Image imgBrancasBispoCasaPreta;

	private Image imgBrancasDamaCasaBranca;
	private Image imgBrancasDamaCasaPreta;

	private Image imgBrancasReiCasaBranca;
	private Image imgBrancasReiCasaPreta;
	// -------------------------------------------------------------

	private Image imgPretasPeaoCasaBranca;
	private Image imgPretasPeaoCasaPreta;

	private Image imgPretasTorreCasaBranca;
	private Image imgPretasTorreCasaPreta;

	private Image imgPretasCavaloCasaBranca;
	private Image imgPretasCavaloCasaPreta;

	private Image imgPretasBispoCasaBranca;
	private Image imgPretasBispoCasaPreta;

	private Image imgPretasDamaCasaBranca;
	private Image imgPretasDamaCasaPreta;

	private Image imgPretasReiCasaBranca;
	private Image imgPretasReiCasaPreta;

	public GuiBoard(FenProcessor FENProcessor, StackPane stackPane) { /// , BorderPane stackPane) {
		this.FENProcessor = FENProcessor;
		this.stackPane = stackPane;
		//gridPane = new GridPane();
		loadImagesIntoMemory();
		System.out.println("Foi atribuido corretamente o stackPane na classe GUIBoard");
		// gridPane = new GridPane();
		// stackPane.setCenter(gridPane);
	}
    
	public void criaGridTabuleiro() {
		System.out.println("rodando loadCriaGrid");
		/*
		gridPane = new GridPane();
		Label list = new Label("textoLabel1");
		Label list2 = new Label("textoLabel2");
		// stackPane.setAlignment(gridPane, Pos.CENTER);
		// gridPane.add(list, 1, 1);
		// gridPane.add(list2, 1, 2);
		// gridPane.add(list, 2, 1);

		//	stackPane.setCenter(gridPane);

		final String strCasaPreta = getClass().getResource("/images/casaPreta96x96.jpg").toExternalForm();
		final String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
		final String strBrancasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoBranco.jpg")
				.toExternalForm();
		final String strBrancasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoBranco.jpg")
		    				.toExternalForm();

		Image imgCasaBranca = new Image(strCasaBranca);

		System.out.println("rodando monta GridPane");
    */


		
		/*	for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				imageSq[i][j] = new ImageView();
				imageSq[i][j].setImage(imgCasaBranca);
				imageSq[i][j].setFitWidth(96 * scaleFactor);
				imageSq[i][j].setFitHeight(96 * scaleFactor);
				gridPane.add(imageSq[i][j], i - 1, j - 1);
			}
			}
			}*/




    
		/*
		 * System.out.println("carrega img"); imageSq[1][1] = new ImageView();
		 * imageSq[1][1].setImage(imgCasaBranca); imageSq[1][1].setFitWidth(96 *
		 * scaleFactor);
		 * 
		 * gridPane.add(imageSq[1][1], 1, 1); System.out.println("rodando mostra img");
		 */
    /*
		// ImageView imageView10 = new ImageView();

		// images[1] = tabuleiro.
		/// final Node foundNode = getNodeFromGridPane(tabuleiro, 1, 2);
		// tabuleiro.add(imageView10, 8,8);
	}

	public void setSqPiece(int column, int lin) {// , PiecesTypes piece) {

		final String strCasaPreta = getClass().getResource("/images/casaPreta96x96.jpg").toExternalForm();
		final String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
		final String strBrancasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoBranco.jpg")
				.toExternalForm();
		final String strBrancasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoBranco.jpg")
				.toExternalForm();

		Image imgCasaPreta = new Image(strCasaPreta);

		System.out.println("rodando set Piece");

		imageSq[3][3] = new ImageView();
		imageSq[3][3].setImage(imgCasaPreta);
		imageSq[3][3].setFitWidth(96 * scaleFactor);
		imageSq[3][3].setFitHeight(96 * scaleFactor);
		gridPane.add(imageSq[3][3], 3, 3);
    */
	}

	
	public void loadImagesIntoMemory() {

		final String strCasaPreta = getClass().getResource("/images/casaPreta96x96.jpg").toExternalForm();
		final String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
		final String strBrancasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoBranco.jpg")
				.toExternalForm();
		final String strBrancasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoBranco.jpg")
				.toExternalForm();

		final String strBrancasTorreCasaBranca = getClass().getResource("/images/casaBranca-torreBranca.jpg")
				.toExternalForm();
		final String strBrancasTorreCasaPreta = getClass().getResource("/images/casaPreta-torreBranca.jpg")
				.toExternalForm();

		final String strBrancasCavaloCasaBranca = getClass().getResource("/images/casaBranca-cavaloBranco.jpg")
				.toExternalForm();
		final String strBrancasCavaloCasaPreta = getClass().getResource("/images/casaPreta-cavaloBranco.jpg")
				.toExternalForm();

		final String strBrancasBispoCasaBranca = getClass().getResource("/images/casaBranca-bispoBranco.jpg")
				.toExternalForm();
		final String strBrancasBispoCasaPreta = getClass().getResource("/images/casaPreta-bispoBranco.jpg")
				.toExternalForm();

		final String strBrancasDamaCasaBranca = getClass().getResource("/images/casaBranca-damaBranca.jpg")
				.toExternalForm();
		final String strBrancasDamaCasaPreta = getClass().getResource("/images/casaPreta-damaBranca.jpg")
				.toExternalForm();

		final String strBrancasReiCasaBranca = getClass().getResource("/images/casaBranca-reiBranco.jpg")
				.toExternalForm();
		final String strBrancasReiCasaPreta = getClass().getResource("/images/casaPreta-reiBranco.jpg")
				.toExternalForm();

		// -------------------------------------------------------------
		final String strPretasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoPreto.jpg")
				.toExternalForm();
		final String strPretasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoPreto.jpg")
				.toExternalForm();

		final String strPretasTorreCasaBranca = getClass().getResource("/images/casaBranca-torrePreta.jpg")
				.toExternalForm();
		final String strPretasTorreCasaPreta = getClass().getResource("/images/casaPreta-torrePreta.jpg")
				.toExternalForm();

		final String strPretasCavaloCasaBranca = getClass().getResource("/images/casaBranca-cavaloPreto.jpg")
				.toExternalForm();
		final String strPretasCavaloCasaPreta = getClass().getResource("/images/casaPreta-cavaloPreto.jpg")
				.toExternalForm();

		final String strPretasBispoCasaBranca = getClass().getResource("/images/casaBranca-bispoPreto.jpg")
				.toExternalForm();
		final String strPretasBispoCasaPreta = getClass().getResource("/images/casaPreta-bispoPreto.jpg")
				.toExternalForm();

		final String strPretasDamaCasaBranca = getClass().getResource("/images/casaBranca-damaPreta.jpg")
				.toExternalForm();
		final String strPretasDamaCasaPreta = getClass().getResource("/images/casaPreta-damaPreta.jpg")
				.toExternalForm();

		final String strPretasReiCasaBranca = getClass().getResource("/images/casaBranca-reiPreto.jpg")
				.toExternalForm();
		final String strPretasReiCasaPreta = getClass().getResource("/images/casaPreta-reiPreto.jpg").toExternalForm();

		imgCasaBranca = new Image(strCasaBranca);
		imgCasaPreta = new Image(strCasaPreta);

		imgBrancasPeaoCasaBranca = new Image(strBrancasPeaoCasaBranca);
		imgBrancasPeaoCasaPreta = new Image(strBrancasPeaoCasaPreta);

		imgBrancasTorreCasaBranca = new Image(strBrancasTorreCasaBranca);
		imgBrancasTorreCasaPreta = new Image(strBrancasTorreCasaPreta);

		imgBrancasCavaloCasaBranca = new Image(strBrancasCavaloCasaBranca);
		imgBrancasCavaloCasaPreta = new Image(strBrancasCavaloCasaPreta);

		imgBrancasBispoCasaBranca = new Image(strBrancasBispoCasaBranca);
		imgBrancasBispoCasaPreta = new Image(strBrancasBispoCasaPreta);

		imgBrancasDamaCasaBranca = new Image(strBrancasDamaCasaBranca);
		imgBrancasDamaCasaPreta = new Image(strBrancasDamaCasaPreta);

		imgBrancasReiCasaBranca = new Image(strBrancasReiCasaBranca);
		imgBrancasReiCasaPreta = new Image(strBrancasReiCasaPreta);

		// -------------------------------------------------------

		imgPretasPeaoCasaBranca = new Image(strPretasPeaoCasaBranca);
		imgPretasPeaoCasaPreta = new Image(strPretasPeaoCasaPreta);

		imgPretasTorreCasaBranca = new Image(strPretasTorreCasaBranca);
		imgPretasTorreCasaPreta = new Image(strPretasTorreCasaPreta);

		imgPretasCavaloCasaBranca = new Image(strPretasCavaloCasaBranca);
		imgPretasCavaloCasaPreta = new Image(strPretasCavaloCasaPreta);

		imgPretasBispoCasaBranca = new Image(strPretasBispoCasaBranca);
		imgPretasBispoCasaPreta = new Image(strPretasBispoCasaPreta);

		imgPretasDamaCasaBranca = new Image(strPretasDamaCasaBranca);
		imgPretasDamaCasaPreta = new Image(strPretasDamaCasaPreta);

		imgPretasReiCasaBranca = new Image(strPretasReiCasaBranca);
		imgPretasReiCasaPreta = new Image(strPretasReiCasaPreta);

		System.out.println("Carregadas todas imagens na memória");

	}

    public void clearStackPane() {
	//	gridPane.getChildren().clear();
	//	stackPane.getChildren().clear();
	
	//	stackPane.setBorder(Border.EMPTY);
	///stackPane.setStyle("-fx-border-width:0; -fx-border-color:WHITE;");
	
    }
    

	public void drawEmptyBoard() {
		System.out.println("rodando loadCriaGrid");
		gridPane = new GridPane();
		
		
	
		stackPane.getChildren().add(gridPane);
		double goodMeasure = Math.round(770 * scaleFactor) + 2;
	
		stackPane.setPrefWidth(goodMeasure); //this "+1" is to acomodate the left border, otherwise it doesn't appear. For scale 0.8 have to put +2 in goodMeasure (don't know why)
		stackPane.setPrefHeight(goodMeasure);  //this "+1" is to acomodate the left border, otherwise it doesn't appear. For scale 0.8 have to put +2 in goodMeasure  (don't know why)
	
		//stackPane.setPrefWidth(770 * scaleFactor+1); //this "+1" is to acomodate the left border, otherwise it doesn't appear
		//stackPane.setPrefHeight(770 * scaleFactor+1); //this "+1" is to acomodate the inferior border, otherwise it doesn't appear


		
		/*
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				imageEmptyBoard[i][j] = new ImageView();
				imageEmptyBoard[i][j].setImage(imgCasaBranca);
				imageEmptyBoard[i][j].setFitWidth(96 * scaleFactor);
				imageEmptyBoard[i][j].setFitHeight(96 * scaleFactor);
				gridPane.add(imageSq[i][j], i - 1, j - 1);
			}
		*/

		
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				imageEmptyBoard[i][j] = new ImageView();

				if (((i + j) % 2) == 0) {
				    	imageEmptyBoard[i][j].setImage(imgCasaBranca);
					//imageEmptyBoard[i][j].setImage(imgPretasReiCasaPreta);
				    
				}

				if (((i + j) % 2) == 1) {
					imageEmptyBoard[i][j].setImage(imgCasaPreta);
				}

				imageEmptyBoard[i][j].setFitWidth(96 * scaleFactor);
				imageEmptyBoard[i][j].setFitHeight(96 * scaleFactor);
				gridPane.add(imageEmptyBoard[i][j], i - 1, j - 1);
			}
		}

	}

	public void refreshBoardImage(char[] boardASCII) {
		char currentChar;
		boolean isWhiteSquare = true;

		for (int column = 1; column <= 8; column++) {
			for (int line = 1; line <= 8; line++) {
				currentChar = boardASCII[column * 10 + line];

				if (((column + line) % 2) == 0) {
					isWhiteSquare = true;
				}

				if (((column + line) % 2) == 1) {
					isWhiteSquare = false;
				}

			
				switch (currentChar) {
					case ' ':
						// System.out.println("Você escolheu 1");
					    	  imageSq[column][line] = imageEmptyBoard[column][line];
						break;
					case 'P':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgBrancasPeaoCasaBranca);
						else
							imageSq[column][line].setImage(imgBrancasPeaoCasaPreta);
						break;

					case 'R':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgBrancasTorreCasaBranca);
						else
							imageSq[column][line].setImage(imgBrancasTorreCasaPreta);

						break;

					case 'N':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgBrancasCavaloCasaBranca);
						else
							imageSq[column][line].setImage(imgBrancasCavaloCasaPreta);

						break;

					case 'B':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgBrancasBispoCasaBranca);
						else
							imageSq[column][line].setImage(imgBrancasBispoCasaPreta);

						break;

					case 'Q':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgBrancasDamaCasaBranca);
						else
							imageSq[column][line].setImage(imgBrancasDamaCasaPreta);

						break;

					case 'K':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgBrancasReiCasaBranca);
						else
							imageSq[column][line].setImage(imgBrancasReiCasaPreta);

						break;

					case 'p':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgPretasPeaoCasaBranca);
						else
							imageSq[column][line].setImage(imgPretasPeaoCasaPreta);

						break;

					case 'r':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgPretasTorreCasaBranca);
						else
							imageSq[column][line].setImage(imgPretasTorreCasaPreta);

						break;

					case 'n':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgPretasCavaloCasaBranca);
						else
							imageSq[column][line].setImage(imgPretasCavaloCasaPreta);

						break;

					case 'b':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgPretasBispoCasaBranca);
						else
							imageSq[column][line].setImage(imgPretasBispoCasaPreta);

						break;

					case 'q':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgPretasDamaCasaBranca);
						else
							imageSq[column][line].setImage(imgPretasDamaCasaPreta);

						break;

					case 'k':
						imageSq[column][line] = new ImageView();
						if (isWhiteSquare)
							imageSq[column][line].setImage(imgPretasReiCasaBranca);
						else
							imageSq[column][line].setImage(imgPretasReiCasaPreta);

						break;

					default:
						System.out.println("Número inválido");
				}

					if (currentChar != ' ') {
						imageSq[column][line].setFitWidth(96 * scaleFactor);
						imageSq[column][line].setFitHeight(96 * scaleFactor);
						gridPane.add(imageSq[column][line], column - 1, line - 1);
					}
				}

			}
		
		if (angleToRotateTheBoard==180) rotateTheBoard();
	}


    /*

    public void clickGrid(javafx.scene.input.MouseEvent event) {
	System.out.println("mouse clicou");
	/*    Node clickedNode = event.getPickResult().getIntersectedNode();
    if (clickedNode != gridPane) {
        // click on descendant node
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
	}*/
	
    //}


    public void movePieceInBoardASCII(int mousePressedColumn, int mousePressedRow, int mouseReleasedColumn, int mouseReleasedRow,char[] boardASCII) {
		boardASCII[10*(mouseReleasedColumn+1)+mouseReleasedRow+1] = boardASCII[10*(mousePressedColumn+1)+ mousePressedRow+1];
		boardASCII[10*(mousePressedColumn+1) +mousePressedRow+1] = ' ';	
	//	char currentChar = boardASCII[10*(mouseReleasedColumn+1)+ mouseReleasedRow+1];
    //	if (currentChar == ' ') {
	//	imageSq[mouseReleasedColumn][mouseReleasedRow] = imageSq[mousePressedColumn][mousePressedRow];
		//        imageSq[mousePressedColumn][mousePressedRow] = imageEmptyBoard[mousePressedColumn][mousePressedRow];	
		//	}
   
    }

    public GridPane getGridPane() {
    	return gridPane;
    }

    public void incScaleFactor() {
	scaleFactor+=0.1;
    }

	public void subtractScaleFactor() {
	    scaleFactor -=0.1;
    }
	
    public void printScale() {
	System.out.println("scale= " + String.valueOf(scaleFactor));
    }
   
    public void setAngleToRotate() {
     	angleToRotateTheBoard = angleToRotateTheBoard + 180;
    	
    	if (angleToRotateTheBoard == 360)
    		angleToRotateTheBoard = 0;
    }
    
    public void rotateTheBoard() {
    	System.out.println("Rotate ativado - angle: " + angleToRotateTheBoard);
    	gridPane.setRotate(angleToRotateTheBoard);
		for (int i=1; i<=8;i++) 
			for (int j=1;j<=8;j++) 
				imageSq[i][j].setRotate(angleToRotateTheBoard); //Rotate individual images, because
															//the board rotate rotates them too
    }
    
    public void drawBoard(String FENatual) { 
		FENProcessor.passFENtoLines(FENatual);
		FENProcessor.decriptFEN();
		//boardModel.printBoardASCII();
		drawEmptyBoard();
		refreshBoardImage(FENProcessor.getBoardASCII());
    }
    
    public void changeNeedToRotateTheBoard() {
		needToRotateTheBoard = !needToRotateTheBoard;
    }
    
    public boolean getIfNeedToRotateTheBoard() {
       	return needToRotateTheBoard;
    }
}
