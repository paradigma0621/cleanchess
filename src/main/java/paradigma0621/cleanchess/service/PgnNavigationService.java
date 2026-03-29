package paradigma0621.cleanchess.service;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.game.Event;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.game.Round;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnIterator;
import paradigma0621.cleanchess.controller.ConstructChessBoard;
import paradigma0621.cleanchess.view.GuiBoard;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class PgnNavigationService {

    private Iterable<Game> listOfGamesInPGN;
    private Game game;
    private List<Move> moveList;
    private Board board;
    private ConstructChessBoard constructChessBoard;
    private boolean isOpeningPlaying;
    private final String completeInitialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private GuiBoard guiBoard;
    int indexMove = -1;
    Side sideToMove;

    public PgnNavigationService(GuiBoard guiBoard, ConstructChessBoard constructChessBoard) {
        this.guiBoard = guiBoard;
        this.constructChessBoard = constructChessBoard;

        moveList = new MoveList();
        game = new Game(UUID.randomUUID().toString(), new Round(new Event()));
    }

    public void nextMove() {
        if (nonNull(game)) {
            indexMove = (indexMove >= (moveList.size() - 1)) ? indexMove : ++indexMove;
            game.gotoMove((MoveList) moveList, indexMove);
            String actualFEN = game.getBoard().getFen();
            guiBoard.drawBoard(actualFEN);
        }
    }

    public void previousMove() {
        indexMove = (indexMove >= 0) ? --indexMove : -1;
        game.gotoMove((MoveList) moveList, indexMove);
        String actualFEN = game.getBoard().getFen();

        if (indexMove < 0)
            guiBoard.drawBoard(isOpeningPlaying ? completeInitialFEN : game.getFen());
        else
            guiBoard.drawBoard(actualFEN);

        System.out.println("indexMove: " + indexMove + " FEN: " + actualFEN);
    }

    public void loadPGN() {
        String pgnPath = getPgnPath();

        try {
            listOfGamesInPGN = new PgnIterator(pgnPath);
            game = listOfGamesInPGN.iterator().next();
            game.loadMoveText();

            moveList = game.getHalfMoves();
            board = new Board();
            game.setBoard(board);

            if (isNull(game.getFen()))
                guiBoard.drawBoard(completeInitialFEN);
            else
                guiBoard.drawBoard(game.getFen());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getPgnPath() {
        return "/mnt/drive_docs/all/programacao/github/cleanchess/src/main/resources/python/01_VarianteDasTrocas_Variante01.pgn";
    }

    public void restartGamePosition() {
        indexMove = -1;
        game.gotoMove((MoveList) moveList, indexMove);
        System.out.println("Back to beginning");

        guiBoard.drawBoard(isOpeningPlaying ? completeInitialFEN : game.getFen());
    }

    public void loadPGNwithManyGames() {
        ///String pgnPath = "/mnt/drive_docs/all/programacao/github/cleanchess/mateEm2.pgn";
        String pgnPath = "/mnt/drive_docs/all/programacao/github/cleanchess/z.pgn";

        //String pgnPath = "/Users/paradigma0621/IdeaProjects/git/cleanchess/polgarSmall.pgn";
        loadPgnAndUpdateEverything(pgnPath);
        sideToMove = board.getSideToMove();
        System.out.println("carregou game: " + "numVariante" + " - Agora quem move: " + sideToMove.value());
        //labelRef.setText("carregou game: " + numVariante + " - Agora quem move: " + sideToMove.value());
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

    public void showWhoPlayAndGameInfo() {
        Side sideToMove = board.getSideToMove();
        constructChessBoard.showInLabel("side to move = " + sideToMove.value() + " - WHITE: " +
                game.getWhitePlayer() + " - BLACK: " + game.getBlackPlayer());
    }
}
