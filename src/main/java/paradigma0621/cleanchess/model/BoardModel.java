package paradigma0621.cleanchess.model;

import paradigma0621.cleanchess.model.entity.Square;

public class BoardModel {

	private Square squares[];
	private String FEN;
	private char charFEN[][];
	private char parametersFEN[][];
	private char boardASCII[];
    
	public BoardModel() {

	    Square squares[] = new Square[89];

	    //	    squares = new Square[89];
		boardASCII = new char[89];
		

	}

	public void mudaLabel() {
		// ChessBoard cb = ChessBoard.getLinkToTheBoard();

		// cb.getLabelAlvo().setText("estou mudando a label de dentro do model");

	}

	public void passFENtoLines(String stringFEN) {

		char charFEN[][] = new char[10][9];
		char parametersFEN[][] = new char[7][7];

		String sTemp[] = stringFEN.split("/");
		String strLastLineAndParameters[] = sTemp[7].split(" "); // the last line of the FEN doesn't has the "/", so I
									// store the last line and the parameters of the
									// game in the vector strLastLineAndParameters


		for (int i = 8; i >= 2; i--) {
			String sFEN = sTemp[8 - i];
			charFEN[8 - i] = sFEN.toCharArray();
		}
		charFEN[7] = strLastLineAndParameters[0].toCharArray(); // the last line of the FEN doesn't has the "/", it
									// seams like: .../R1BbK2R w KQkq - 0 1

	
		for (int k = 1; k < strLastLineAndParameters.length; k++) { // jump the first because it has the last FEN line... was passed to the
		    parametersFEN[k - 1] = strLastLineAndParameters[k].toCharArray();	//charFEN[7] above

		}

		this.charFEN = charFEN;
		this.parametersFEN = parametersFEN;

	}

	public void decriptFEN() {
		char currentCharacter;
		int numberEmptySquares = 0;
		int posXstrFEN = 0, posXboard;
		int lineStrSize;

		for (int posY = 1; posY <= 8; posY++) {
			posXboard = 1;
			lineStrSize = charFEN[posY - 1].length;
			for (posXstrFEN = 0; posXstrFEN < lineStrSize; posXstrFEN++) {
				currentCharacter = charFEN[posY - 1][posXstrFEN];
				if ((currentCharacter == '1') || (currentCharacter == '2') || (currentCharacter == '3')
						|| (currentCharacter == '4') || (currentCharacter == '5') || (currentCharacter == '6')
						|| (currentCharacter == '7') || (currentCharacter == '8')) {
					numberEmptySquares = currentCharacter - 48;// subtract the ascii number to get the int of the char

					for (int c = 0; c <= (numberEmptySquares - 1); c++)
						boardASCII[10 * (posXboard + c) + posY] = ' ';
					posXboard += numberEmptySquares;
				}

				if ((currentCharacter == 'p') || (currentCharacter == 'P') || (currentCharacter == 'r')
						|| (currentCharacter == 'R') || (currentCharacter == 'b') || (currentCharacter == 'B')
						|| (currentCharacter == 'n') || (currentCharacter == 'N') || (currentCharacter == 'q')
						|| (currentCharacter == 'Q') || (currentCharacter == 'k') || (currentCharacter == 'K')) {
					boardASCII[10 * (posXboard) + posY] = currentCharacter;
					posXboard++;
				}
			}
		}

	}

	public char[][] getParametersFEN() {
		return parametersFEN;
	}
	
	public char[] getBoardASCII() {
		return boardASCII;
	}

	public void printBoardASCII() {
		for (int i = 1; i <= 8; i++) {
		    for (int j = 1; j <= 8; j++) {
				System.out.print(boardASCII[10 * j + i]);
			}
			System.out.println("");
		}
	}
    /*
    public Square getSquare(int column, int line) {
	return squares[10*column+line];
	}*/
 }
