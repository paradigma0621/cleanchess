package paradigma0621.cleanchess.view;

import paradigma0621.cleanchess.model.FenProcessor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class GuiBoard {
	private double scaleFactor = 0.8;
	private GridPane gridPane;
	private FenProcessor FENProcessor;
	private StackPane stackPane;
	private ImageView imageSq[][] = new ImageView[9][9];
   	private ImageView imageEmptyBoard[][] = new ImageView[9][9];
   	private boolean needToRotateTheBoard = false;
   	int angleToRotateTheBoard=0;
   	private ViewerImageCache viewerImageCache;

	public GuiBoard(FenProcessor FENProcessor, StackPane stackPane) { /// , BorderPane stackPane) {
		this.FENProcessor = FENProcessor;
		this.stackPane = stackPane;
		viewerImageCache = new ViewerImageCache();
		System.out.println("Foi atribuido corretamente o stackPane na classe GUIBoard");
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

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				imageEmptyBoard[i][j] = new ImageView();

				if (((i + j) % 2) == 0)
				    	imageEmptyBoard[i][j].setImage(viewerImageCache.getSquareImage(ViewerImageCache.SquareColor.WHITE));

				if (((i + j) % 2) == 1)
					imageEmptyBoard[i][j].setImage(viewerImageCache.getSquareImage(ViewerImageCache.SquareColor.BLACK));

				imageEmptyBoard[i][j].setFitWidth(96 * scaleFactor);
				imageEmptyBoard[i][j].setFitHeight(96 * scaleFactor);
				gridPane.add(imageEmptyBoard[i][j], i - 1, j - 1);
			}
		}

	}

	public void refreshBoardImage(char[] boardASCII) {
		for (int column = 1; column <= 8; column++) {
			for (int line = 1; line <= 8; line++) {
				char currentChar = boardASCII[column * 10 + line];
				ViewerImageCache.SquareColor squareColor = getSquareColor(column, line);

				if (currentChar == ' ') {
					imageSq[column][line] = imageEmptyBoard[column][line];
					continue;
				}

				ImageView imageView = new ImageView();
				imageView.setImage(getPieceImage(currentChar, squareColor));
				imageView.setFitWidth(96 * scaleFactor);
				imageView.setFitHeight(96 * scaleFactor);

				imageSq[column][line] = imageView;
				gridPane.add(imageView, column - 1, line - 1);
			}
		}

		if (angleToRotateTheBoard == 180) {
			rotateTheBoard();
		}
	}

	private ViewerImageCache.SquareColor getSquareColor(int column, int line) {
		return ((column + line) % 2 == 0)
				? ViewerImageCache.SquareColor.WHITE
				: ViewerImageCache.SquareColor.BLACK;
	}

	private Image getPieceImage(char pieceChar, ViewerImageCache.SquareColor squareColor) {
		ViewerImageCache.PieceColor pieceColor = Character.isUpperCase(pieceChar)
				? ViewerImageCache.PieceColor.WHITE
				: ViewerImageCache.PieceColor.BLACK;

		ViewerImageCache.PieceType pieceType = mapPieceType(pieceChar);

		return viewerImageCache.getPieceImage(pieceColor, pieceType, squareColor);
	}

	private ViewerImageCache.PieceType mapPieceType(char pieceChar) {
		return switch (Character.toUpperCase(pieceChar)) {
			case 'P' -> ViewerImageCache.PieceType.PAWN;
			case 'N' -> ViewerImageCache.PieceType.KNIGHT;
			case 'B' -> ViewerImageCache.PieceType.BISHOP;
			case 'R' -> ViewerImageCache.PieceType.ROOK;
			case 'Q' -> ViewerImageCache.PieceType.QUEEN;
			case 'K' -> ViewerImageCache.PieceType.KING;
			default -> throw new IllegalArgumentException("Peça inválida: " + pieceChar);
		};
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
		drawEmptyBoard();
		refreshBoardImage(FENProcessor.getBoardASCII());
    }
    
    public void changeNeedToRotateTheBoard() {
		needToRotateTheBoard = !needToRotateTheBoard;
    }
    
    public boolean getIfNeedToRotateTheBoard() {
       	return needToRotateTheBoard;
    }

	public double getScaleFactor() {
		return scaleFactor;
	}
}
