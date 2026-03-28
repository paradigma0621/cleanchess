package paradigma0621.cleanchess.controller;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnException;
import com.github.bhlangonijr.chesslib.pgn.PgnIterator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import paradigma0621.cleanchess.model.*;
import paradigma0621.cleanchess.novos.model.StageSingleton;
import paradigma0621.cleanchess.view.GuiBoard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static paradigma0621.cleanchess.model.PgnProblemListLoader.loadPgnFile;

public class BCKP_ConstructChessBoard {
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

	private FenProcessor FENProcessor;
	private GuiBoard boardGUI;
	private MovePiece movePiece;
	private int mousePressedColumn, mousePressedRow, mouseReleasedColumn, mouseReleasedRow;
	private int actualMove = 0;
	//private PgnReader actualPgnPlaying;
	private String numVariante;
	private Game game;
    private MoveList moveList;
	//private PgnReader toPlayGame;
	//private PgnReader problemToSolve;
	private Map<Integer, List<String>> gameMap;
//	private boolean isOpeningPlaying = false;
	private Timeline debounceTimeline;
	private boolean isDebouncing = false;
	private final String initialBoardPostionFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq";
    private PgnIterator listOfGamesInPGN;
    private Board board;
    private int indexMove = -1;
    private final String completeInitialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private boolean isOpeningPlaying;


    public BCKP_ConstructChessBoard() {
		FENProcessor = new FenProcessor();
		movePiece = new MovePiece();
		String pgnExamplePath =
				getClass().getResource("/pgn/01_DefesaEslava_VarianteMaisImportante(b)_FonteXB.pgn").toExternalForm();
		System.out.println("aqui2");


		// Configurar debounce Timeline
		debounceTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> isDebouncing = false));
		debounceTimeline.setCycleCount(1);
	}
	
	public void initialize() {
		boardGUI = new GuiBoard(FENProcessor, stackPane);
		System.out.println("aqui");
	}

	public void centralizaTabuleiro() {
		menuBar.setVisible(false);
		// anchorPane.setLeftAnchor(labelRef, 0.0);
		// anchorPane.setRightAnchor(labelRef, 0.0);
		// labelRef.setAlignment(Pos.CENTER);
		labelRef.setVisible(false);

		//labelRef.setText("aaaaaaa");
		// tabuleiro.setMaxSize(100,50);
		stackPane.setAlignment(stackPane, Pos.CENTER);
		/// borderPane.set
		// setAlignment(Pos.CENTER);
		stackPane.setAlignment(Pos.CENTER);
		// setAlignment(labelRef,);

	}

	public void bMais() {
		//String FENatual = actualPgnPlaying.displayNextPosition(actualMove);
		//FENProcessor.passFENtoLines(FENatual); // ex 30 - white
		FENProcessor.decriptFEN();
		//boardModel.printBoardASCII();
		boardGUI.drawEmptyBoard();
		boardGUI.refreshBoardImage(FENProcessor.getBoardASCII());
		actualMove++;
		
	}

	
	public void bMenos() throws Exception {
        try {
            int ply = 1;
            for (Move move : moveList) {
                board.doMove(move);
                System.out.println(ply + ": " + move.toString() +  " - FEN: " + board.getFen());
                ply++;
            }

            System.out.println("Final FEN: " + board.getFen());
        } catch (PgnException e) {
            System.out.println(">>> [AVISO] Não foi possível carregar os lances deste game:");
            System.out.println("    Motivo: " + e.getMessage());
            // aqui você pode simplesmente ignorar os lances, mas ainda aproveita os headers
        }
        /*
		//String FENatual= actualPgnPlaying.displayNextPosition(actualMove);
        String FENatual="rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq";
		FENProcessor.passFENtoLines(FENatual); // ex 30 - white

		FENProcessor.decriptFEN();

		boardGUI.drawEmptyBoard();
		boardGUI.refreshBoardImage(FENProcessor.getBoardASCII());
		actualMove--;
		*/
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

	public void hideEverything() {
        labelRef.setVisible(false);
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

	}
	
	
	public void pressedAkey() {

		bigGrid.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
			if (!isDebouncing) {
				isDebouncing = true;
				boolean showCtrlMessage = true;

				if (key.isControlDown() || key.getCode().equals(KeyCode.COMMAND)) {
					if ((key.getCode() == key.getCode().LEFT)) {
                        indexMove = (indexMove >= 0) ? --indexMove : -1;
                        game.gotoMove(moveList, indexMove);
                        String FENatual = game.getBoard().getFen();
                        System.out.println("Previous move");

                        if (indexMove < 0)
                            boardGUI.drawBoard(isOpeningPlaying ? completeInitialFEN : game.getFen());
                        else
                            boardGUI.drawBoard(FENatual);
                    }

					if (key.getCode() == key.getCode().RIGHT) {
                        indexMove = (indexMove >= (moveList.size() - 1)) ? indexMove : ++indexMove;
                        game.gotoMove(moveList, indexMove);
                        String FENatual = game.getBoard().getFen();

                        System.out.println("Next Move");

                        boardGUI.drawBoard(FENatual);
					}

					if ((key.getCode() == key.getCode().DOWN)) {
                        indexMove = -1;
                        game.gotoMove(moveList, indexMove);
                        System.out.println("Back to beginning");

                        boardGUI.drawBoard(isOpeningPlaying ? completeInitialFEN : game.getFen());
                    }


                    if (key.getCode() == key.getCode().F1) {
                        System.out.println("pressionou F1");
                        openHelpDialog();
                    }

					if (key.getCode() == KeyCode.DELETE) {
						System.out.println("DELETING NumVar:#" + numVariante);
						gameMap.remove(Integer.parseInt(numVariante));

                        String filePathToSave = "/mnt/drive_docs/all/programacao/github/cleanchess/mateEm2.pgn";
						PgnProblemListSaver.savePgnFile(filePathToSave, gameMap);

						gameMap = loadPgnFile(filePathToSave);
						loadOneChessProblem(numVariante);
                        String infos = "Infos: " + game.getWhitePlayer() + " - " + game.getBlackPlayer();
                        System.out.println("deletou game: " + numVariante + " - Agora: " + infos);
						labelRef.setText("deletou game: " + numVariante + " - Agora: " + infos);
					}

					if (key.getCode() == KeyCode.S) {
						System.out.println("You pressed s");
						actualMove++;
						System.out.println("actualMove atual desde o começo: " + actualMove);
						PlayAgainstEngine.createScriptFile(initialBoardPostionFEN);
						//boardGUI.drawBoard(lastFEN);
						PlayAgainstEngine.runEngine();
						System.out.println("depois de rodar engine");
						String bruteBestMove = PlayAgainstEngine.readEngineOutput();
						char bestMove[] = PlayAgainstEngine.getTheEngineMove(bruteBestMove);
						System.out.println("best move from file: " + String.copyValueOf(bestMove));

						System.out.println("acutal mve na controlleR: " + actualMove);
						//lastFEN = toPlayGame.moveMaker(bestMove, actualPgnPlaying.getGame()); //actual move
						System.out.println("fenMovida: " + initialBoardPostionFEN);

						boardGUI.drawBoard(initialBoardPostionFEN);
					}


					if (key.getCode() == KeyCode.M) {
						//String FENatual="3B1R1K/4rb1R/5Pp1/1n2k1N1/2p3P1/2P5/2NQ4/8"; // ex 26 - white// checkmate in 2
						String FENatual = "3B1R1K/4rb1R/5Pp1/1n2k1N1/2p3P1/2P5/2NQ4/8"; // mate 26 (em 2 (dos difíceis) - esse que vale com "m"
						System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						boardGUI.drawBoard(FENatual);
					}

					/*if (key.getText().equals("M")) {//mate em 3
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
						showCtrlMessage = false;
					}
*/
					if (key.getCode() == KeyCode.R) {
						//boardGUI.changeNeedToRotateTheBoard();
						boardGUI.setAngleToRotate();
						boardGUI.rotateTheBoard();
					}

					if (key.getCode() == KeyCode.F) {
                        toogleFullScreen();
                        hideEverything();
						Platform.runLater(() -> {
							StageSingleton.getInstance().setFullScreenExitHint("");
							StageSingleton.getInstance().setFullScreen(true);
						});
					}

                    if (key.getCode() == KeyCode.SPACE) {
                        labelRef.setVisible(!labelRef.isVisible());
                    }

                    if (key.getCode() == KeyCode.A) {

						InputStream inputStream = getClass()
								.getClassLoader()
								.getResourceAsStream("/mnt/drive_docs/all/programacao/github/cleanchess/01_VarianteDasTrocas_Variante01.pgn");

						String pgnPath;
						try {
							pgnPath = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}

                        openAndLoadPGNfile(pgnPath);
                    }

                    if (key.getCode() == KeyCode.F3) {
                        indexMove = -1;
                        game.gotoMove(moveList, indexMove);
                        System.out.println("Back to beginning");

                        boardGUI.drawBoard(isOpeningPlaying ? completeInitialFEN : game.getFen());
                    }

                    if (key.getCode() == KeyCode.F2) {
                        try {
                            game = listOfGamesInPGN.iterator().next();

                            // Aqui é onde normalmente estoura o PgnException
                            game.loadMoveText();

                            moveList = game.getHalfMoves();
                            board = new Board();
                            game.setBoard(board);

                            if (isOpeningPlaying)
                                boardGUI.drawBoard(completeInitialFEN);
                            else
                                boardGUI.drawBoard(game.getFen());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (key.getCode() == KeyCode.N) {
						InputStream inputStream = getClass()
								.getClassLoader()
								.getResourceAsStream("/mnt/drive_docs/all/programacao/github/cleanchess/polgarSmall.pgn");

						String pgnPath;
						try {
							pgnPath = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}

						//String pgnPath = "/Users/paradigma0621/IdeaProjects/git/cleanchess/polgarSmall.pgn";
                        loadPgnAndUpdateEverything(pgnPath);
                        Side sideToMove = board.getSideToMove();
                        System.out.println("carregou game: " + numVariante + " - Agora quem move: " + sideToMove.value());
                        labelRef.setText("carregou game: " + numVariante + " - Agora quem move: " + sideToMove.value());
                    }


                    if (key.getCode() == KeyCode.ADD) {
                        System.out.println("zoom in");
                        stackPane.setScaleX(1.2 * stackPane.getScaleX());
                        stackPane.setScaleY(1.2 * stackPane.getScaleY());
                    }


                    if (key.getCode() == KeyCode.SUBTRACT) {
                        stackPane.setScaleX(0.8 * stackPane.getScaleX());
                        stackPane.setScaleY(0.8 * stackPane.getScaleY());
                    }

                    if (key.getCode() == KeyCode.P) {
						Stage stage = StageSingleton.getInstance();
						Platform.runLater(() -> {
							stage.setFullScreenExitHint("");

							stage.setFullScreen(true);
						});
						/*Stage stage = PGNloader.getStage();
                       	stage.initStyle(StageStyle.UNDECORATED);
						stage.setMaximized(true);

							stage.setFullScreen(true);
							stage.setAlwaysOnTop(true);
					*/
                    }

					if (key.getCode() == KeyCode.X) {
						if (isOpeningPlaying)

							labelRef.setText(getPenultimateSegment(OpeningsSources.stringPGNname));

						else {
							//labelRef.setText("Problem: " + numVariante + " who mate: " + getWhoMates());
						}
					}

					if (key.getCode() == KeyCode.O) {
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
							indexMove = -1;
							numVariante = result.get();
							System.out.println("NumVar:#" + numVariante);
							String stringPathPGN = OpeningsSources.getOpeningPath(numVariante);
                            openAndLoadPGNfile(stringPathPGN);
							//actualPgnPlaying = new PgnReader(stringPathPGN);
							//String fen = actualPgnPlaying.getFEN();
							//boardGUI.drawBoard(fen);

						}
					}

					if (key.getCode() == KeyCode.J) {
						Button btnNumber = new Button("Choose");
						TextInputDialog dialogoNumber = new TextInputDialog();

						dialogoNumber.setTitle("Entrar com número do problema");
						dialogoNumber.setHeaderText("Entrar com número do problema3");
						dialogoNumber.setContentText("Número:");

						Optional<String> result = dialogoNumber.showAndWait();

						if (!result.isPresent() || result.get().trim().isBlank()) {
							//Do nothing
						} else {

							String filePath = "/mnt/drive_docs/all/programacao/github/cleanchess/mateEm2.pgn";

							gameMap = loadPgnFile(filePath);

							actualMove = 0;
							numVariante = result.get();
							loadOneChessProblem(numVariante);
							String infos = "Infos: " + game.getWhitePlayer() + " - " + game.getBlackPlayer();
							System.out.println("carregou game: " + numVariante + " - " + infos);
							labelRef.setText("carregou game: " + numVariante + " - " + infos);

						}
					}

					if (key.getCode() == KeyCode.K) {
						System.out.println("rodando  kkkkk");
						movePiece.drawGrayRectangle(boardGUI, 1, 1);
					}
					showCtrlMessage = false;
				} else {
					if (showCtrlMessage) labelRef.setText("You pressed " + key.getCode());
					if (key.getCode() == KeyCode.Z) {
						labelRef.setText("");
					}
				}
				showCtrlMessage = false;
				debounceTimeline.playFromStart();
			}
		});
	}

    private void openHelpDialog() {
        String helpMsg = """
            CTRL + DOWN: volta para o começo do pgn
            CTRL + LEFT/RIGHT: avança/retrocede lance do pgn
            CTRL + M: carrega FEN preestabelecida e chumbada em código
            CTRL + A: carrega PGN preestabelecido e chumbada em código
            CTRL + R: rotaciona tabuleiro
            CTRL + F: AP: maximiza a tela do app - qual é a diff da abaixo?
            CTRL + P: AP: maximiza a tela do app - qual é a diff da acima?
            CTRL + SPACE: mostra/oculta labelRef
            CTRL + N: carrega PGN com várias partidas/problemas dentro
            CTRL + F2: avança as partidas/problemas do carregamento CRTL+N acima
            CTRL + +: Zoom in do tabuleiro
            CTRL + -: Zoom out do tabuleiro
            CTRL + X: se o pgn que estiver aberto for uma abertura, mostra o nome da variante na labelRef
            CTRL + O: mostra msgBox pra escolher numeração de uma abertura (ex de entradAA: 002)
            CTRL + J: carrega variante/problema de número x (msgBox)
            CTRL + DELETE: deleta variante atual e salva arquivo pgn sem ela
            """;

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Informação detalhada");

        TextArea area = new TextArea(helpMsg);
        area.setWrapText(true);
        area.setEditable(false);
        area.setStyle("-fx-font-size: 34px;");

        dialog.getDialogPane().setContent(area);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.setHeight(1240);
        dialog.setWidth(2440);
        dialog.showAndWait();
    }

    private void loadPgnAndUpdateEverything(String pgnPath) {
        File file = new File(pgnPath);

        if (!file.exists()) {
            System.err.println("Arquivo não encontrado: " + pgnPath);
        }

        try {
			//Controller.fenAfterSanLine()
            listOfGamesInPGN = new PgnIterator(pgnPath);

            game = listOfGamesInPGN.iterator().next();

            // Aqui é onde normalmente estoura o PgnException
            game.loadMoveText();

            moveList = game.getHalfMoves();
            board = new Board();
            game.setBoard(board);

            setIsOpeningPlaying();
            if (isOpeningPlaying)
                boardGUI.drawBoard(completeInitialFEN);
            else
                boardGUI.drawBoard(game.getFen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void openAndLoadPGNfile(String pgnPath) {
        File file = new File(pgnPath);

        if (!file.exists()) {
            System.err.println("Arquivo não encontrado: " + pgnPath);
            return;
        }

        try {
            listOfGamesInPGN = new PgnIterator(pgnPath);

            game = listOfGamesInPGN.iterator().next();

            // Aqui é onde normalmente estoura o PgnException
            game.loadMoveText();

            moveList = game.getHalfMoves();
            board = new Board();
            game.setBoard(board);
            setIsOpeningPlaying();

            if (isOpeningPlaying)
                boardGUI.drawBoard(completeInitialFEN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setIsOpeningPlaying() {
        isOpeningPlaying = isNull(game.getFen()) && board.getFen().equals(completeInitialFEN);
    }

    private static void toogleFullScreen() {
		Stage stage = StageSingleton.getInstance();
		stage.setFullScreenExitHint("");
		stage.setMaximized(!stage.isMaximized());
		stage.setFullScreen(false);
    }

    private void loadOneChessProblem(String numVariante) {
		Map<Integer, List<String>> gameMapToSave = new HashMap<>();
		System.out.println("NumVar:#" + numVariante);
		gameMapToSave.put(1, gameMap.get(Integer.parseInt(numVariante)));

		String filePathToSave = "/mnt/drive_docs/all/programacao/github/cleanchess/matesJogando.pgn";
		PgnProblemListSaver.savePgnFile(filePathToSave, gameMapToSave);

        loadPgnAndUpdateEverything(filePathToSave);
	}

	/*private String getWhoMates() {
		return (actualPgnPlaying.getGame().getGameInfo().getEvent() + actualPgnPlaying.getGame().getGameInfo().getPlayers()[1]);
	}*/


	public static String getPenultimateSegment(String filePath) {
		String[] segments = filePath.split("/");

		if (segments.length > 1) {
			return segments[segments.length - 1];
		} else {
			return filePath;
		}
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


		/* Comentado no macOS
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
		*/
		// }

	}

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            // Quando não definido, assume-se 0
            int colIdx = columnIndex == null ? 0 : columnIndex;
            int rowIdx = rowIndex == null ? 0 : rowIndex;

            if (colIdx == col && rowIdx == row) {
                return node;
            }
        }
        return null; // não existe nó nessa posição
    }
}
