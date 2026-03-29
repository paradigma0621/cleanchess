package paradigma0621.cleanchess.service;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.game.Event;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.game.Round;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnIterator;
import paradigma0621.cleanchess.view.GuiBoard;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class PgnNavigationService {

    private Iterable<Game> listOfGamesInPGN;
    private Game game;
    private List<Move> moveList;
    private Board board;
    private boolean isOpeningPlaying;
    private final String completeInitialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private GuiBoard guiBoard;
    int indexMove = -1;

    public PgnNavigationService(GuiBoard guiBoard) {
        this.guiBoard = guiBoard;
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
/*        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("/mnt/drive_docs/all/programacao/github/cleanchess/src/main/resources/python/01_VarianteDasTrocas_Variante01.pgn");
*/
        String pgnPath = "/mnt/drive_docs/all/programacao/github/cleanchess/src/main/resources/python/01_VarianteDasTrocas_Variante01.pgn";
        //String pgnPath = "/mnt/drive_docs/all/programacao/github/cleanchess/matesJogando.pgn";
       /* try {
            pgnPath = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        return pgnPath;
    }
}
