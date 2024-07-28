package paradigma.cleanchess.model;
import ictk.boardgame.*;
import ictk.boardgame.io.*;
import ictk.boardgame.chess.*;
import ictk.boardgame.chess.io.*;
import paradigma.cleanchess.model.entity.Square;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

public class PgnReader {
	PGNReader reader;
    PGNWriter writer;
    ChessGame game;
    History history;
   
    public PgnReader() {  //When starts a new game and doesn't specify the file name address
		 this.game = new ChessGame();
		 this.history = game.getHistory();
    }

	public ChessGame getGame() {
		return game;
	}
    
	public PgnReader(String stringPGNname) {

		try {
			URI uri = new URI(stringPGNname);
			File file = new File(uri);
			FileReader fileReader = new FileReader(file);
			reader = new PGNReader(fileReader);

		 //read in the first game in the file
		 game = (ChessGame) reader.readGame();
		// this.game = game;
		history = game.getHistory();
		// displayPGN(game);
		 //displayLastPosition(game);
		  //displayStats(game);

		}
		catch (Exception e) {
		   System.err.println(e);
		}
 	}

    public int getHistorySize() {
    	return game.getHistory().size();
    }

	public String getFEN() {
		ChessBoardNotation fen = new FEN();
		return fen.boardToString(game.getBoard());
	}

 /** gather and display some use(less) statistics about the game */
 public static void displayStats (ChessGame game) {
    ChessMoveNotation san = new SAN();
    ChessAnnotation anno  = null;
    ChessMove move        = null;
    ContinuationList cont = null;
    History history       = game.getHistory();
   
    history.rewind();
    while (history.hasNext()) {
       move = (ChessMove) history.next();
	}
 }


 /** displays the last board position in FEN notation
  */
 public static String displayLastPosition(ChessGame game) {
    ChessBoardNotation fen = new FEN();

       game.getHistory().goToEnd();
       System.out.println("Last board position--------");
       System.out.println("FEN: " + fen.boardToString(game.getBoard()));
	 return fen.boardToString(game.getBoard());
 }

 /** writes the game to STDOUT.  To modify the game presentation
  *  you can change the settings of the writer.
  */
 public static void displayPGN (ChessGame game) {
    PGNWriter writer = null;
    try {
		writer = new PGNWriter(System.out);

		//writer.setColumnWidth(60);
		writer.setIndentVariations(true);
		writer.setIndentComments(true);
		//writer.setAnnotationGlyphStyle(PGNWriter.NUMERIC_GLYPH);
		//writer.exportVariations(false);
		//writer.exportComments(false);

		System.out.println("PGN------------------------");
		writer.writeGame(game);
		System.out.println();
    }
    catch (IOException e) {
       System.err.println(e);
    }
 }
	
	
 
 //-------------------------CODIGOS PESSOAIS Tirados das referencias acima -------
 public String displayNextPosition(int actualMoveNumber) {
	    ChessMoveNotation san = new SAN();
	    ChessAnnotation anno  = null;
	    ChessMove move        = null;
	    ContinuationList cont = null;
	    ///History history       = game.getHistory();
	   
	     //	displayPGN(game);
	     //displayLastPosition(game);
	       //displayStats(game);

	    //return fen.boardToString(game.getBoard());
	    System.out.println("History sizE:" + game.getHistory().size());
	    game.getHistory().rewind();
	    
		for (int numeroMeuMov=0; (numeroMeuMov<=actualMoveNumber)&&(game.getHistory().hasNext());numeroMeuMov++ ) {
			game.getHistory().next();
			System.out.println("processando for depois do rewind o lance: " + numeroMeuMov);
		}
	    
	    
	    //history.rewind();
	   // for (int numeroMeuMov=0; (numeroMeuMov<=actualMoveNumber)&&history.hasNext();numeroMeuMov++ ) {
	     //  move = (ChessMove) history.next();
	    //}
	   // history.next();
	    
	 ChessBoardNotation fen = new FEN();
     //  System.out.println("X board position--------");
   //    System.out.println("FEN: " + fen.boardToString(game.getBoard()));
  	///   System.out.println();
  	 System.out.println("actualMove: " + actualMoveNumber);
  	 System.out.println(fen.boardToString(game.getBoard()));
  	   return fen.boardToString(game.getBoard());
  	   
	 }
 //-----------------------------------------------------
public String moveMaker(char[] moveChar, ChessGame game) {
 //public String moveMaker() {
	
	int columOrigin, lineOrigin, columnDestiny, lineDestiny;

     history = game.getHistory();
	 board = game.getBoard();

	    
    //setup the moves
    history = game.getHistory();
	// board = game.getBoard();
	 
    paradigma.cleanchess.model.entity.Square sqOrigin = new paradigma.cleanchess.model.entity.Square(String.valueOf(moveChar[0])+String.valueOf(moveChar[1]));
    paradigma.cleanchess.model.entity.Square sqDestiny = new Square(String.valueOf(moveChar[2])+String.valueOf(moveChar[3]));
    /*stem.out.println("sqOrigin column: " + sqOrigin.getColumn().getColumnInt());
    System.out.println("sqOrigin line: " + sqOrigin.getLine().getLineInt());

    System.out.println("sqDestiny column: " + sqDestiny.getColumn().getColumnInt());
    System.out.println("sqDestiny line: " + sqDestiny.getLine().getLineInt());
*/
	columOrigin=sqOrigin.getColumn().getColumnInt();
	lineOrigin=sqOrigin.getLine().getLineInt();
	columnDestiny = sqDestiny.getColumn().getColumnInt();
	lineDestiny=sqDestiny.getLine().getLineInt();
	
	 try {
		 move = new ChessMove((ChessBoard) board, columOrigin, lineOrigin, columnDestiny, lineDestiny); //1.e4
		 history.add(move);
	 }
	 catch (Exception e) {
	        System.err.println(e);
	     }
	 
	 ChessBoardNotation fen = new FEN();
     //  System.out.println("X board position--------");
   //    System.out.println("FEN: " + fen.boardToString(game.getBoard()));
  	///   System.out.println();
  	  return fen.boardToString(game.getBoard());
  	   
}
	 
//-----------------------------------------------------

    Board         board   = null;
   History     	historyTest =null;
    Move          move    = null,
  		  		move2   = null,
  	    		move2B   = null,
  		  		move3   = null,
                  e4      = null;
    MoveNotation  san     = new SAN();
    MoveNotation  san2     = new SAN();      
    PGNWriter      writer2  = null;
    ChessGameInfo gi      = null;
    ChessPlayer   player  = null;


    public void roda2() {
    try {
       System.out.println("SimpleChessGameExample: "
	    + "demonstrates how to enter the moves of a game with the API");
	 System.out.println();

	 game = new ChessGame();

	 gi = new ChessGameInfo();
	 //white player
	 player = new ChessPlayer();
	 player.setFirstName("Bobby");
	 player.setLastName("Fischer");
	 gi.setWhite(player);

       //black player
	 player = new ChessPlayer();
	 player.setFirstName("Boris");
	 player.setLastName("Spasky");
	 gi.setBlack(player);

	 game.setGameInfo(gi);


       //setup the moves
       history = game.getHistory();
	 board = game.getBoard();

	 //1st way of adding a move to the move list
	 move = new ChessMove((ChessBoard) board, 5, 2, 5, 4); //1.e4
	 history.add(move);

		
	/* //move = (ChessMove) san.stringToMove(board, "e4");
	 Move[] listaX;
	 listaX=history.getContinuationList().find(move)
	 for (Move mx:listaX) {
		 System.out.println(san.moveToString(mx) +  "  h0-  ");
	 }*/
	 
	 //2nd way
	 move = (ChessMove) san.stringToMove(board, "e5");
	 history.add(move);

	 //3rd way
	 move = (ChessMove) san.stringToMove(board, "c4");
	 history.add(move);
	 
	 move = (ChessMove) san2.stringToMove(board, "c6");
	 history.add(move);



	 //to add a variation of Black's first move (e5 above)
	    //goto the move before "e5"
	 history.prev();
	 history.prev();
	 history.prev();  
	 e4 = history.getCurrentMove();
	    //this time history.add() will add a variation
	 move = san.stringToMove(board, "c5");
	 move.setAnnotation(new ChessAnnotation("The Sicilian"));
	 history.add(move);

	 //the next add will continue down the variation
	 move3 = new ChessMove((ChessBoard) board, 7, 1, 6, 3);
	 history.add(move3);
	 
	 move3 = new ChessMove((ChessBoard) board, 3, 5, 3, 4);
	 history.add(move3);
	 
	 move3 = new ChessMove((ChessBoard) board, 8, 2, 8, 3);
	 history.add(move3);
	 
	 move3 = new ChessMove((ChessBoard) board, 8, 7, 8, 6);
	 history.add(move3);
	 
	 
	 
	 //to return to the mainline
	 history.rewindToLastFork();
		 
	 //No bloco abaixoo eu faço a history ir até o fim dela mesma, para depois 
	 //poder adicionar mais movimentos na mainline
	 System.out.println("aa" + san.moveToString(move));	 
	 System.out.println("Está indo pro fim da mainline:");
	 while (!history.getContinuationList().isTerminal()) {
		 move = history.getContinuationList().getMainLine();
		 history.next();
		 System.out.println("MainNoComeço: "+san.moveToString(move));
	 }
	 
	 
	 
	 move = (ChessMove) san.stringToMove(board, "h4");
	  
	 history.add(move);
	 
	// move = (ChessMove) san2.stringToMove(board, "h5");
	// history.add(move);

	 
	 move2B= new ChessMove((ChessBoard) board, 6, 8, 1, 3);
	// move2B = (ChessMove) san.stringToMove(board, "b4");
	 history.add(move2B);
	   
	 move2B= new ChessMove((ChessBoard) board, 2, 2, 2, 3);
	// move2B = (ChessMove) san.stringToMove(board, "b4");
	 history.add(move2B);
	   

	 move2B= new ChessMove((ChessBoard) board, 2, 7, 2, 6);
	// move2B = (ChessMove) san.stringToMove(board, "b4");
	 history.add(move2B);
	   /*
	 //ChessMoveNotation notation = new SAN();
	 //notation = (ChessMoveNotation) move2B.get  
	 ///System.out.println(move2B);
		 
		 
		 
	 //set the game's result
	 game.setResult(new ChessResult(ChessResult.DRAW));
*/
	 writer = new PGNWriter(System.out);
	 	writer.setIndentVariations(true);
	 	writer.setIndentComments(true);

	 writer.writeGame(game);
	 	 
   
	// writer2 = new PGNWriter(System.out);
//	writer2.writeBoard(board);
	 //history.setInitialMoveNumber(4);
///	 move =game.getHistory().getFirst();
	 /*
	// history.setInitialMoveNumber(2);
	move =history.getFirst();
	
	int a1 =history.getCurrentMoveNumber();
			//move=history.getNext();

	 System.out.println(san.moveToString(move) +  "  -  "+ a1);
	 
*/
	 System.out.println("Está na mainline:");
	 while (!history.getContinuationList().isTerminal()) {
		 move = history.getContinuationList().getMainLine();
		 history.next();
		 System.out.println("Main: "+san.moveToString(move));
	 }
	 
	 
	 history.prev();
	 history.prev();
	 history.prev();  
	 history.prev();
	 history.prev();
	 
	 history.prev();
	 history.prev();  
	 System.out.println("Esta é a variante:");

	 move = history.next(1);
	 System.out.println("Variante: "+san.moveToString(move));
	 while (!history.getContinuationList().isTerminal()) {
	//for (int i=0;i<3;i++) {
//System.out.println("hasNext0: " + history.getContinuationList().get(1).getAnnotation().toString());
//System.out.println("Variante: "+san.moveToString(move));
move = history.next();		 
System.out.println("Variante: "+san.moveToString(move));
}
	 
	 /*
	 history.next();
	 history.next();
	 history.next();
	 move =history.getNext(1); //aqui eu deixei no  movimento emcima da bifurcação
	 a1 =history.getCurrentMoveNumber();
	 System.out.println(san.moveToString(move) +  "  -  "+ a1);
	 
	 */
	// history.next(1);
	// move =history.getContinuationList().get(1).getNext().getNext();
	// move =history.getContinuationList().get(0);
	 ///move =history.getNext();
	 //move =history.getNext();
	 //int a1 =history.getCurrentMoveNumber();
	 //System.out.println(san.moveToString(move) +  "  -  "+ a1);
	 
	// System.out.println(san.moveToString(history.getFirst()));
//	 Reader minhaAberturaReader =  new Reader("/home/Saul1Sm10_1/01_DefesaEslava.pgn");
	// PGNReader minhaAbertura01 = new PGNReader(minhaAberturaReader);
		 
	 
    }
    catch (Exception e) {
       e.printStackTrace();
    }
 }

}
