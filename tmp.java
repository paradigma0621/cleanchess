package groupId.artifactId.controller;



import groupId.artifactId.model.BoardModel;
import groupId.artifactId.model.PGNreader;
import groupId.artifactId.model.ValidMoveAnalyser;
import groupId.artifactId.view.GUIboard;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextInputDialog;

@SuppressWarnings("all")

public class ConstructChessBoard {
	@FXML
	private Label labelRef;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Pane panePano;
	@FXML
	private VBox vbox;
	@FXML
	private StackPane stackPane;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Label labelAlvo;

	@FXML
	private Button bbMenos;
	@FXML
	private Button bbMais;
	@FXML
	private Button bbMenosMenos;
	@FXML
	private Button botaoAlvo;
	@FXML
	private Button trabalhaTabul;

	@FXML
	private GridPane bigGrid;
	@FXML
	private RowConstraints gridPaneGeral_Row0;
	@FXML
	private RowConstraints gridPaneGeral_Row1;
	@FXML
	private RowConstraints gridPaneGeral_Row2;
	@FXML
	private RowConstraints gridPaneGeral_Row3;
	@FXML
	private RowConstraints gridPaneGeral_Row4;


	
	private static ConstructChessBoard chessBoard;
	private BoardModel boardModel;
	private GUIboard boardGUI;
	private int mousePressedColumn, mousePressedRow, mouseReleasedColumn, mouseReleasedRow;
	// double escala=0.5;
	private int actualMove=0;
	private PGNreader abertura01reader;
	private String numVariante, stringPGNname;
	private boolean keyReleased=true;
	
	public ConstructChessBoard() {
		boardModel = new BoardModel();
		boardGUI = new GUIboard(boardModel);
		//System.out.println("criou instancias que eu vou usar");
		
		abertura01reader = new PGNreader("/home/Saul1Sm10_1/xadrez/01_DefesaEslava.pgn");
	}

	public static ConstructChessBoard getLinkToTheBoard() {
		return chessBoard;
	}

	public void entrar() {

		String strCasaPreta = getClass().getResource("/images/casaPreta96x96.jpg").toExternalForm();
		String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
		String strBrancasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoBranco.jpg").toExternalForm();
		String strBrancasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoBranco.jpg").toExternalForm();

		System.out.println("botao pressionado");
		// labelEmail.

	}

	public void centralizaTabuleiro() {
		menuBar.setVisible(false);
		// anchorPane.setLeftAnchor(labelRef, 0.0);
		// anchorPane.setRightAnchor(labelRef, 0.0);
		// labelRef.setAlignment(Pos.CENTER);
		labelRef.setVisible(false);
	
		labelRef.setText("aaaaaaa");
		// tabuleiro.setMaxSize(100,50);
		// borderPane.setAlignment(stackPane, Pos.CENTER);
		/// borderPane.set
		// setAlignment(Pos.CENTER);
		// stackPane.setAlignment(Pos.CENTER);
		// setAlignment(labelRef,);

	}

	public void bMais() {
		String FENatual=abertura01reader.displayNextPosition(actualMove);
		boardModel.passFENtoLines(FENatual); // ex 30 - white
		boardModel.decriptFEN();
		//boardModel.printBoardASCII();
		boardGUI.drawEmptyBoard(stackPane);
		boardGUI.refreshBoardImage(stackPane, boardModel.getBoardASCII());
		actualMove++;
		
	}

	
	public void bMenos() {
		
		String FENatual=abertura01reader.displayNextPosition(actualMove);
		
		boardModel.passFENtoLines(FENatual); // ex 30 - white
																								// checkmate in 3
		
		boardModel.decriptFEN();

		//boardModel.printBoardASCII();

		boardGUI.drawEmptyBoard(stackPane);
		boardGUI.refreshBoardImage(stackPane, boardModel.getBoardASCII());
		actualMove--;
		
	}

	
	public void bMenosMenos() {

		System.out.println("rodando bMenosMenos-----------");

		// squares before (AP????)

		//boardModel.passFENtoLines("r1b2k1r/1p1p1pp1/p2P4/4N1Bp/3p4/8/PPB2P2/2K1R3 w - - 0 1"); // ex 30 - white// checkmate in 3
		boardModel.passFENtoLines("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1"); // ex 30 - white
																								

		boardModel.decriptFEN();

		boardModel.printBoardASCII();

		boardGUI.drawEmptyBoard(stackPane);
		boardGUI.refreshBoardImage(stackPane, boardModel.getBoardASCII());

	}

	/*
	 * private Node getNodeFromGridPane(GridPane gridPane, int col, int row) { for
	 * (Node node : gridPane.getChildren()) { if (GridPane.getColumnIndex(node) ==
	 * col && GridPane.getRowIndex(node) == row) { return node; } } return null; }
	 */
	/*
	 * public void createBoard(){ tabuleiro.getChildren().add(imageView); for(int i
	 * =0;i < 8; i++){ for(int j = 0; j < 8; j++){ //Tile tile = new Tile(i, j);
	 * GridPane.setConstraints(tile.getPane(), i, j);
	 * tabuleiro.getChildren().add(tile.getPane()); } }
	 * 
	 * }
	 */

	/*
	public void drawBoard() {
		// boardGUI.drawEmptyBoard(stackPane); // To draw the pieces in the board it's
		// necessary to creaty instances os all
		// squares before (AP????)

		//boardModel.passFENtoLines("rnb1k1nr/ppp3pp/6q1/4p1B1/1b2P3/2N2N2/PPP3PP/3RKB1R w Kkq - 0 1");
		boardModel.decriptFEN();
		//boardModel.printBoardASCII();
		boardGUI.refreshBoardImage(stackPane, boardModel.getBoardASCII());
		//boardGUI.rotateTheImagesSqIfNeeded();
	}
*/
	public Label getLabelAlvo() {
		return labelAlvo;
	}

	public void mudaTextoDaLabelAlvo() {
		/*
		 * labelRef.setVisible(false); labelAlvo.setVisible(false);
		 * 
		 * bbMenos.setVisible(false); bbMais.setVisible(false);
		 * bbMenosMenos.setVisible(false); botaoAlvo.setVisible(false);
		 * trabalhaTabul.setVisible(false);
		 */
		anchorPane.setVisible(false);
		menuBar.setVisible(false);

		gridPaneGeral_Row3.setValignment(VPos.BOTTOM);

		
		bigGrid.requestFocus();
		// stackPane.setAlignment(vbox, Pos.TOP_LEFT);
		//bigGrid.requestFocus();
		
		// vbox.setAlignment(Pos.TOP_CENTER);
		// vbox.setMargin(stackPane, new Insets(0,10,10,0));
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// frame.setLocation(dim.width/2-frame.getSize().width/2,
		// dim.height / 2 - frame.getSize().height / 2);

	}

	public void releasedTheKey() {
		keyReleased = true;

		System.out.println("de fato key released");
	}
	
	
	public void pressedAkey() {

		bigGrid.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			
			if (keyReleased) {
		
				if (key.getText().equals("+")) {
					System.out.println("You pressed +...");
					if (actualMove < abertura01reader.getHistorySize()) actualMove++;
					String FENatual=abertura01reader.displayNextPosition(actualMove-1); //actual move
									//starts at 0 in abertura01reader, but here whe have the initial
									//value of actualMove=1
					boardGUI.drawBoard(FENatual);
				}
		
				if (key.getText().equals("-")) {
					System.out.println("You pressed -...");
					if (actualMove >0) actualMove--; 
					
					String FENatual=abertura01reader.displayNextPosition(actualMove-1); //actual move
					//starts at 0 in abertura01reader, but here whe have the initial
					//value of actualMove=1
					boardGUI.drawBoard(FENatual);
					}
					

				if (key.getText().equals("s")) {
					System.out.println("You pressed s");
					boardGUI.drawEmptyBoard(stackPane);
					boardGUI.refreshBoardImage(stackPane, boardModel.getBoardASCII());
					if (boardGUI.getIfNeedToRotateTheBoard()) boardGUI.rotateTheBoard();
				}
				
				if (key.getText().equals("m")) {//mate em 3
					String FENatual="5rk1/p4Npp/1p6/3n1R2/3PR3/8/P4KPP/2r5"); // ex 30 - white// checkmate in 3
					boardGUI.drawBoard(FENatual);
					boardGUI.drawBoard(FENatual);	
				}
				
				if (key.getText().equals("r")) {//rotate the board
					//boardGUI.changeNeedToRotateTheBoard();
					boardGUI.setAngleToRotate();
					boardGUI.rotateTheBoard();
					
				}
				
				
				if (key.getText().equals("o")) {
					System.out.println("You pressed o");
					
				    Button btnNome = new Button("Escolher");
			   
			            TextInputDialog dialogoNumero = new TextInputDialog();

			            dialogoNumero.setTitle("Entrar com número da variante2");
			            dialogoNumero.setHeaderText("Entrar com número da variante3");
			            dialogoNumero.setContentText("Número:");
			            dialogoNumero.showAndWait().ifPresent(v -> numVariante = v);
			        System.out.println("NumVar:#" + numVariante);
					
					if (numVariante.equals("001")) {
						stringPGNname = "/media/Saul1Sm10_1/data/Qt/xadrez/aberturas/PeaoDama/01_VarianteDasTrocas_Variante01.pgn";
						System.out.println("001 de variante: VarianteDasTrocas_Variante01.pgn");
					}
					
					if (numVariante.equals("002")) {
						stringPGNname = "/media/Saul1Sm10_1/data/Qt/xadrez/aberturas/PeaoDama/02_VarianteDasTrocas_Variante02.pgn";
						System.out.println("002 de variante: VarianteDasTrocas_Variante02.pgn");
					}

					if (numVariante.equals("003")) {
						stringPGNname = "/media/Saul1Sm10_1/data/Qt/xadrez/aberturas/PeaoDama/03_VarianteDasTrocas_Variante03.pgn";
						System.out.println("002 de variante: VarianteDasTrocas_Variante03.pgn");
					}					
//----------------------------DEF FRANCESA
					if (numVariante.equals("100")) {
						stringPGNname = "/media/Saul1Sm10_1/data/Qt/xadrez/aberturas/DefesaFrancesa/DefesaFrancesa_A.01.pgn";
						System.out.println("001 de variante: DefesaFrancesa_A.pgn");
					}
					
					if (numVariante.equals("101")) {
						stringPGNname = "/media/Saul1Sm10_1/data/Qt/xadrez/aberturas/DefesaFrancesa/DefesaFrancesa_B.01.pgn";
						System.out.println("002 de variante: DefesaFrancesa_B.pgn");
					}

					if (numVariante.equals("102")) {
						stringPGNname = "/media/Saul1Sm10_1/data/Qt/xadrez/aberturas/DefesaFrancesa/DefesaFrancesa_D.01.pgn";
						System.out.println("002 de variante: DefesaFrancesa_D.pgn");
					}					

					abertura01reader = new PGNreader(stringPGNname);
					actualMove=0;
					bMenosMenos();
				}			
			}
				keyReleased=false;
				
			});	
			
		keyReleased=true;
		}
	


	public void clickGrid(javafx.scene.input.MouseEvent event) {
		/*
		 * System.out.println("clicou no pane"); Node clickedNode =
		 * event.getPickResult().getIntersectedNode(); /// if (clickedNode != gridPane)
		 * { // click on descendant node Integer colIndex =
		 * bigGrid.getColumnIndex(clickedNode); Integer rowIndex =
		 * bigGrid.getRowIndex(clickedNode); System.out.println("Mouse clicked cell: " +
		 * colIndex + " And: " + rowIndex);
		 * System.out.println("Mouse clicked converted cell: " + (colIndex + 1) +
		 * " And: " + (8 - rowIndex)); // }
		 */
	}

	public void clickPressed(javafx.scene.input.MouseEvent event) {

		/// System.out.println("clicou no pane");
		Node clickedNode = event.getPickResult().getIntersectedNode();
		/// if (clickedNode != gridPane) {
		// click on descendant node
		Integer colIndex = bigGrid.getColumnIndex(clickedNode);
		Integer rowIndex = bigGrid.getRowIndex(clickedNode);
		System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
		// System.out.println("Mouse pressed in: " + (colIndex + 1) + " And: " + (8 -
		// rowIndex));
		mousePressedColumn = colIndex;
		mousePressedRow = rowIndex;

		// }
	}

	public void clickReleased(javafx.scene.input.MouseEvent event) {

		//Node target = (Node) event.getTarget();
		//Node parent = target.getParent();

		// System.out.println(target.getParent());

		// if (target instanceof GridPane) {
		/// System.out.println("clicou no pane");
		Node clickedNode = event.getPickResult().getIntersectedNode();

		Integer colIndex = bigGrid.getColumnIndex(clickedNode);
		Integer rowIndex = bigGrid.getRowIndex(clickedNode);
		mouseReleasedColumn = colIndex;
		mouseReleasedRow = rowIndex;

		 //if (!((mouseReleasedRow<0)||(mouseReleasedRow>7)||(mouseReleasedColumn<0)||(mouseReleasedColumn>7)))
		 //{
		 if (ValidMoveAnalyser.isValidMove(mousePressedColumn, mousePressedRow, mouseReleasedColumn, mouseReleasedRow)) {
			System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
			// System.out.println("VALID in: " + (colIndex + 1) + " And: " + (8 -
			// rowIndex));
			boardGUI.movePieceInBoardASCII(mousePressedColumn, mousePressedRow, mouseReleasedColumn, mouseReleasedRow,
					boardModel.getBoardASCII());

			boardModel.printBoardASCII();
			boardGUI.drawEmptyBoard(stackPane);
			boardGUI.refreshBoardImage(stackPane, boardModel.getBoardASCII());
			
			//if (boardGUI.getIfNeedToRotateTheBoard()&&(boardGUI.getAngleToRotateTheBoard()==0)) boardGUI.rotateTheBoard(); 
		}

		// }

	}

}

