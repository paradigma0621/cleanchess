package paradigma.cleanchess.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import paradigma.cleanchess.model.*;
import paradigma.cleanchess.view.GuiBoard;
import ictk.boardgame.chess.ChessGame;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextInputDialog;

import static paradigma.cleanchess.model.PgnProblemListLoader.loadPgnFile;

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
	private FenProcessor FENProcessor;
	private GuiBoard boardGUI;
	private int mousePressedColumn, mousePressedRow, mouseReleasedColumn, mouseReleasedRow;
	private int actualMove=0;
	private PgnReader actualPgnPlaying;
	private String numVariante;
	private boolean keyReleased=true;
	private ChessGame game;
	private PgnReader toPlayGame;
	private PgnReader problemToSolve;
	private Integer integerProblemToSolve;
	private Map<Integer, List<String>> gameMap;
	private boolean isOpeningPlaying = false;
	private String whoWins;
	private Timeline debounceTimeline;
	private boolean isDebouncing = false;

	public ConstructChessBoard() {
		FENProcessor = new FenProcessor();
		//System.out.println("criou instancias que eu vou usar");
		String pgnExamplePath =
				getClass().getResource("/pgn/01_DefesaEslava_VarianteMaisImportante(b)_FonteXB.pgn").toExternalForm();
		actualPgnPlaying = new PgnReader(pgnExamplePath);
		System.out.println("aqui2");


		// Configurar debounce Timeline
		debounceTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> isDebouncing = false));
		debounceTimeline.setCycleCount(1);
	}
	
	public void initialize() {
		boardGUI = new GuiBoard(FENProcessor, stackPane);
		System.out.println("aqui");
	}

	/*
        public static ConstructChessBoard getLinkToTheBoard() {
            return chessBoard;
        }

        public void entrar() {

            //String strCasaPreta = getClass().getResource("/images/casaPreta96x96.jpg").toExternalForm();
            //String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
            //String strBrancasPeaoCasaBranca = getClass().getResource("/images/casaBranca-peaoBranco.jpg").toExternalForm();
            //String strBrancasPeaoCasaPreta = getClass().getResource("/images/casaPreta-peaoBranco.jpg").toExternalForm();

            System.out.println("botao pressionado");
            // labelEmail.

        }
    */
	public void centralizaTabuleiro() {
		menuBar.setVisible(false);
		// anchorPane.setLeftAnchor(labelRef, 0.0);
		// anchorPane.setRightAnchor(labelRef, 0.0);
		// labelRef.setAlignment(Pos.CENTER);
		//labelRef.setVisible(false);
	
		//labelRef.setText("aaaaaaa");
		// tabuleiro.setMaxSize(100,50);
		// borderPane.setAlignment(stackPane, Pos.CENTER);
		/// borderPane.set
		// setAlignment(Pos.CENTER);
		// stackPane.setAlignment(Pos.CENTER);
		// setAlignment(labelRef,);

	}

	public void bMais() {
		String FENatual = actualPgnPlaying.displayNextPosition(actualMove);
		FENProcessor.passFENtoLines(FENatual); // ex 30 - white
		FENProcessor.decriptFEN();
		//boardModel.printBoardASCII();
		boardGUI.drawEmptyBoard();
		boardGUI.refreshBoardImage(FENProcessor.getBoardASCII());
		actualMove++;
		
	}

	
	public void bMenos() {
		
		String FENatual= actualPgnPlaying.displayNextPosition(actualMove);
		
		FENProcessor.passFENtoLines(FENatual); // ex 30 - white

		FENProcessor.decriptFEN();

		//boardModel.printBoardASCII();

		boardGUI.drawEmptyBoard();
		boardGUI.refreshBoardImage(FENProcessor.getBoardASCII());
		actualMove--;
		
	}

	
	public void bMenosMenos() {

		System.out.println("rodando bMenosMenos");

		// squares before (AP????)

		//boardModel.passFENtoLines("r1b2k1r/1p1p1pp1/p2P4/4N1Bp/3p4/8/PPB2P2/2K1R3 w - - 0 1"); // ex 30 - white// checkmate in 3

//				"k1/pp4p1/1b2p2p/8/P3b1Nq/BPr4P/R4P1K/4RQ2 b - - 0 1"); //ex 86 - mate em 3. as pretas ganham
		//boardModel.passFENtoLines("3r4/pp3kpQ/5p1p/3q1b2/1B2N3/8/PP3PPP/4R1K1 w - - 0 1"); // ex 30 - white
		//problema 49 do polgar mate em 3 - as brancas ganham													

		FENProcessor.decriptFEN();
		FENProcessor.printBoardASCII();
		boardGUI.drawEmptyBoard();
		boardGUI.refreshBoardImage(FENProcessor.getBoardASCII());
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

		bigGrid.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
			if (!isDebouncing) {
				isDebouncing = true;
				boolean showCtrlMessage = true;
				if (key.isControlDown()) {

					if ((key.getCode() == key.getCode().LEFT)) {
						System.out.println("You pressed left arrow...");
						if (actualMove > 0) actualMove--;

						String FENatual = actualPgnPlaying.displayNextPosition(actualMove - 1); //actual move
						//starts at 0 in abertura01reader, but here whe have the initial
						//value of actualMove=1
						boardGUI.drawBoard(FENatual);
						showCtrlMessage = false;
					}
					if (key.getCode() == key.getCode().RIGHT) {
						System.out.println("You pressed right arrow...");
						if (actualMove < actualPgnPlaying.getHistorySize()) actualMove++;

						String FENatual = actualPgnPlaying.displayNextPosition(actualMove - 1); //actual move
						//starts at 0 in abertura01reader, but here whe have the initial
						//value of actualMove=1
						boardGUI.drawBoard(FENatual);
						showCtrlMessage = false;
					}
					if (key.getCode() == KeyCode.D) {
						System.out.println("You pressed command to delete game in pgn...");

						System.out.println("DELETING NumVar:#" + numVariante);
						gameMap.remove(Integer.parseInt(numVariante));

						String filePathToSave = "/home/lucas/Documentos/xadrez/problemasDeMate/polgar/mateEm3.pgn";
						PgnProblemListSaver.savePgnFile(filePathToSave, gameMap);

						//load the file again
						gameMap = loadPgnFile(filePathToSave);
						loadOneChessProblem(numVariante);
						/*String stringPGNnumber = "file:" + filePathToSave;
						problemToSolve = new PgnReader(stringPGNnumber);
						String fen = problemToSolve.getFEN();
						boardGUI.drawBoard(fen);*/
						//actualPgnPlaying = problemToSolve;
						//isOpeningPlaying = false;
						labelRef.setText("deletou game: " + numVariante);
						showCtrlMessage = false;
					}
					if (showCtrlMessage) labelRef.setText("You pressed control + " + key.getCode());
				} else {
					labelRef.setText("");
					if (key.getText().equals("+")) {
						System.out.println("You pressed +...");
						if (actualMove < actualPgnPlaying.getHistorySize()) actualMove++;

						String FENatual = actualPgnPlaying.displayNextPosition(actualMove - 1); //actual move
						//starts at 0 in abertura01reader, but here whe have the initial
						//value of actualMove=1
						boardGUI.drawBoard(FENatual);
					}

					if (key.getText().equals("-")) {
						System.out.println("You pressed -...");
						if (actualMove > 0) actualMove--;

						String FENatual = actualPgnPlaying.displayNextPosition(actualMove - 1); //actual move
						//starts at 0 in abertura01reader, but here whe have the initial
						//value of actualMove=1
						boardGUI.drawBoard(FENatual);
					}


					if (key.getText().equals("s")) {
						System.out.println("You pressed s");
						actualMove++;
						//String FENatual=abertura01reader.displayNextPosition(actualMove-1);
						//System.out.println("FEN processada: " + FENatual);
						System.out.println("actualMove atual desde o começo: " + actualMove);
						String FENatual;
						if (actualMove == 0) {
							FENatual = "2kr3r/ppp2ppp/8/2bqP3/3nBBb1/8/PPPN2PP/R3KQ1R b KQ - 0 1";
							System.out.println("Running atualMove==0");
						} else {
							FENatual = toPlayGame.displayNextPosition(actualMove - 1);
							System.out.println("Running (actualMove !=0) FENatual=" + FENatual);
						}
						PlayAgainstEngine.createScriptFile(FENatual);
						PlayAgainstEngine.runEngine();
						System.out.println("depois de rodar engine");
						String bruteBestMove = PlayAgainstEngine.readEngineOutput();
						char bestMove[] = PlayAgainstEngine.getTheEngineMove(bruteBestMove);
						System.out.println("best move from file: " + String.copyValueOf(bestMove));
						PlayAgainstEngine.makeMove(bestMove, toPlayGame);


						System.out.println("acutal mve na controlleR: " + actualMove);

						String FENatualMovida = toPlayGame.moveMaker(bestMove, game); //actual move
						System.out.println("fenMovida: " + FENatualMovida);

						boardGUI.drawBoard(FENatualMovida);


					}


					if (key.getText().equals("m")) {//mate em 3
						//String FENatual="3B1R1K/4rb1R/5Pp1/1n2k1N1/2p3P1/2P5/2NQ4/8"; // ex 26 - white// checkmate in 2
						String FENatual = "3B1R1K/4rb1R/5Pp1/1n2k1N1/2p3P1/2P5/2NQ4/8"; // mate 26 (em 2 (dos difíceis) - esse que vale com "m"
						System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						boardGUI.drawBoard(FENatual);
					}

					if (key.getText().equals("M")) {//mate em 3
						String FENstring;
						Button btnNome = new Button("Entrar com FEN");
						TextInputDialog dialogoFEN = new TextInputDialog();

						dialogoFEN.setTitle("Entrar com FEN");
						dialogoFEN.setHeaderText("Entrar com FEN");
						dialogoFEN.setContentText("FEN:");
						dialogoFEN.getEditor().setPrefWidth(500);

						Optional<String> result = dialogoFEN.showAndWait();

						if (!result.isPresent() || result.get().trim().isBlank()) {
							//Do nothing
						} else {
							FENstring = result.get();
							boardGUI.drawBoard(FENstring);
						}
						//boardGUI.drawBoard(FENatual);

					}

					if (key.getText().equals("r")) {//rotate the board
						//boardGUI.changeNeedToRotateTheBoard();
						boardGUI.setAngleToRotate();
						boardGUI.rotateTheBoard();

					}

					if (key.getText().equals("F")) {//rotate the board
						PGNloader.getStage().setMaximized(true);
						PGNloader.getStage().setFullScreen(true);
					}


					if (key.getText().equals("P")) {
						game = new ChessGame();
						toPlayGame = new PgnReader();
					}

					if (key.getText().equals("x")) {
						if (isOpeningPlaying)
							labelRef.setText(OpeningsSources.stringPGNname);
						else {
							labelRef.setText("Problem: " + numVariante + " who mate: " + getWhoMates());
						}
					}

					if (key.getText().equals("o")) {
						System.out.println("You pressed o");

						String FENstring;
						Button btnNumber = new Button("Choose");
						TextInputDialog dialogoNumber = new TextInputDialog();

						dialogoNumber.setTitle("Entrar com número da variante2");
						dialogoNumber.setHeaderText("Entrar com número da variante3");
						dialogoNumber.setContentText("Número:");

						Optional<String> result = dialogoNumber.showAndWait();

						if (!result.isPresent() || result.get().trim().isBlank()) {
							//Do nothing
						} else {
							numVariante = result.get();
							System.out.println("NumVar:#" + numVariante);
							String stringPGNnumber = OpeningsSources.getOpeningPath(numVariante);
							actualPgnPlaying = new PgnReader(stringPGNnumber);
							actualMove = 0;
							String fen = actualPgnPlaying.getFEN();
							boardGUI.drawBoard(fen);
							isOpeningPlaying = true;
						}

					}

					if (key.getText().equals("l")) {
						String filePath = "/home/lucas/Documentos/xadrez/problemasDeMate/polgar/mateEm3.pgn";
						gameMap = loadPgnFile(filePath);
					}

					if (key.getText().equals("d")) {
						labelRef.setText("You pressed d");
					}

					if (key.getText().equals("j")) {

						//gameMapToSave.put(1, gameMap.get(1));
					//	gameMapToSave.put(2, gameMap.get(3));
					//	gameMapToSave.put(3, gameMap.get(5));

						Button btnNumber = new Button("Choose");
						TextInputDialog dialogoNumber = new TextInputDialog();

						dialogoNumber.setTitle("Entrar com número do problema");
						dialogoNumber.setHeaderText("Entrar com número do problema3");
						dialogoNumber.setContentText("Número:");

						Optional<String> result = dialogoNumber.showAndWait();

						if (!result.isPresent() || result.get().trim().isBlank()) {
							//Do nothing
						} else {
							numVariante = result.get();
							loadOneChessProblem(numVariante);
						}
					}
				}
				debounceTimeline.playFromStart();
			}
		});
	}

	private void loadOneChessProblem(String numVariante) {
		Map<Integer, List<String>> gameMapToSave = new HashMap<>();
		System.out.println("NumVar:#" + numVariante);
		gameMapToSave.put(1, gameMap.get(Integer.parseInt(numVariante)));

		String filePathToSave = "/home/lucas/Documentos/xadrez/problemasDeMate/polgar/matesJogando.pgn";
		PgnProblemListSaver.savePgnFile(filePathToSave, gameMapToSave);

		String stringPGNnumber = "file:" + filePathToSave;
		problemToSolve = new PgnReader(stringPGNnumber);
		String fen = problemToSolve.getFEN();
		boardGUI.drawBoard(fen);
		actualPgnPlaying = problemToSolve;
		isOpeningPlaying = false;
	}

	private String getWhoMates() {
		return (actualPgnPlaying.getGame().getGameInfo().getEvent() + actualPgnPlaying.getGame().getGameInfo().getPlayers()[1]);
	}

	/*public void startFlagResetTask() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			passedSomeTimeSinceLastKeyPress = true;
	}
*/
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
					FENProcessor.getBoardASCII());

			FENProcessor.printBoardASCII();
			boardGUI.drawEmptyBoard();
			boardGUI.refreshBoardImage(FENProcessor.getBoardASCII());
			
			//if (boardGUI.getIfNeedToRotateTheBoard()&&(boardGUI.getAngleToRotateTheBoard()==0)) boardGUI.rotateTheBoard(); 
		}

		// }

	}

}
