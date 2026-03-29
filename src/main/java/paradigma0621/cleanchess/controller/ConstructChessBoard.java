package paradigma0621.cleanchess.controller;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnIterator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import paradigma0621.cleanchess.input.KeyboardShortcutHandler;
import paradigma0621.cleanchess.model.*;
import paradigma0621.cleanchess.novos.model.StageSingleton;
import paradigma0621.cleanchess.service.PgnNavigationService;
import paradigma0621.cleanchess.service.ViewerActionService;
import paradigma0621.cleanchess.service.VisualActionService;
import paradigma0621.cleanchess.view.GuiBoard;
import paradigma0621.cleanchess.view.HelpDialog;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

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
	private GuiBoard guiBoard;
	private MovePiece movePiece;
	private int mousePressedColumn, mousePressedRow, mouseReleasedColumn, mouseReleasedRow;
	private int actualMove = 0;
	private String numVariante;
	private Game game;
    private MoveList moveList;
	private Map<Integer, List<String>> gameMap;
	private Timeline debounceTimeline;
	private boolean isDebouncing = false;
    private PgnIterator listOfGamesInPGN;
    private Board board;
    private int indexMove = -1;
	private final String completeInitialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private boolean isOpeningPlaying;
	private KeyboardShortcutHandler keyboardShortcutHandler;

    public ConstructChessBoard() {
		FENProcessor = new FenProcessor();

		// Configurar debounce Timeline
		debounceTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> isDebouncing = false));
		debounceTimeline.setCycleCount(1);
	}
	
	public void initialize() {
		FenProcessor fenProcessor = new FenProcessor();
		guiBoard = new GuiBoard(fenProcessor, stackPane);

		PgnNavigationService pgnNavigationService = new PgnNavigationService(guiBoard, this);
		VisualActionService visualActionService = new VisualActionService(guiBoard);
		HelpDialog helpDialog = new HelpDialog();

		ViewerActionService viewerActionService =
				new ViewerActionService(
						this,
						pgnNavigationService,
						visualActionService,
						helpDialog
				);

		keyboardShortcutHandler = new KeyboardShortcutHandler(this, viewerActionService);

		bigGrid.setOnKeyPressed(this::handleKeyReleased);
	}

	public void centralizaTabuleiro() {
		menuBar.setVisible(false);
		labelRef.setVisible(false);

		stackPane.setAlignment(stackPane, Pos.CENTER);
		stackPane.setAlignment(Pos.CENTER);

	}

	private void handleKeyReleased(KeyEvent event) {
		if (!isDebouncing) {
			isDebouncing = true;
			keyboardShortcutHandler.handle(event);
			debounceTimeline.playFromStart();
		}
	}

	public void pressedAkey() {
		/*bigGrid.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
					keyboardShortcutHandler.handle(key);
				});
		/*
		//bigGrid.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
			if (!isDebouncing) {
				isDebouncing = true;
				boolean showCtrlMessage = true;

				if (key.isControlDown() || key.getCode().equals(KeyCode.COMMAND)) {



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

						guiBoard.drawBoard(initialBoardPostionFEN);
					}




                    if (key.getCode() == KeyCode.F3) {
                        indexMove = -1;
                        game.gotoMove(moveList, indexMove);
                        System.out.println("Back to beginning");

                        guiBoard.drawBoard(isOpeningPlaying ? completeInitialFEN : game.getFen());
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
                                guiBoard.drawBoard(completeInitialFEN);
                            else
                                guiBoard.drawBoard(game.getFen());
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


                    if (key.getCode() == KeyCode.P) {
						Stage stage = StageSingleton.getInstance();
						Platform.runLater(() -> {
							stage.setFullScreenExitHint("");

							stage.setFullScreen(true);
						});
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
						movePiece.drawGrayRectangle(guiBoard, 1, 1);
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
		});*/
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
                guiBoard.drawBoard(completeInitialFEN);
            else
                guiBoard.drawBoard(game.getFen());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void openAndLoadPGNfile(String pgnPath) {

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
                guiBoard.drawBoard(completeInitialFEN);
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
/*
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


 */
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


	public void zoomIn() {
		stackPane.setScaleX(1.2 * stackPane.getScaleX());
		stackPane.setScaleY(1.2 * stackPane.getScaleY());
	}

	public void zoomOut() {
		stackPane.setScaleX(0.8 * stackPane.getScaleX());
		stackPane.setScaleY(0.8 * stackPane.getScaleY());
	}

	public void fullScreen() {
		toogleFullScreen();
		hideEverything();
		Platform.runLater(() -> {
			StageSingleton.getInstance().setFullScreenExitHint("");
			StageSingleton.getInstance().setFullScreen(true);
		});
	}

	public void hideEverything() {
		labelRef.setVisible(false);
		anchorPane.setVisible(false);
		menuBar.setVisible(false);
		gridPaneGeral_Row3.setValignment(VPos.BOTTOM);
		bigGrid.requestFocus();
	}

	public void showLabel() {
		labelRef.setVisible(!labelRef.isVisible());
	}

	public void showKeyPressed(KeyEvent key) {
		labelRef.setText("You pressed " + key.getCode().getName());
	}

	public void showInLabel(String text) {
		labelRef.setText(text);
	}
}
