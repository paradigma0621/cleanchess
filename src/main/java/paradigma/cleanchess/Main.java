package paradigma.cleanchess;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnException;
import com.github.bhlangonijr.chesslib.pgn.PgnIterator;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //String pgnPath = "/Users/paradigma0621/IdeaProjects/git/cleanchess/src/main/resources/python/01_VarianteDasTrocas_Variante01.pgn";
        String pgnPath = "/mnt/drive_docs/all/programacao/github/cleanchess/src/main/resources/python/01_VarianteDasTrocas_Variante01.pgn";

        File file = new File(pgnPath);

        if (!file.exists()) {
            System.err.println("Arquivo não encontrado: " + pgnPath);
            return;
        }

        try {
            PgnIterator iterator = new PgnIterator(pgnPath);

            int gameNumber = 1;
            for (Game game : iterator) {
                System.out.println("=== Game " + gameNumber + " ===");
                //System.out.println("Event:  " + game.getEvent());
                System.out.println("White:  " + (game.getWhitePlayer() != null ? game.getWhitePlayer().getName() : "?"));
                System.out.println("Black:  " + (game.getBlackPlayer() != null ? game.getBlackPlayer().getName() : "?"));
                System.out.println("Result: " + game.getResult());

                try {
                    // Aqui é onde normalmente estoura o PgnException
                    game.loadMoveText();

                    MoveList moves = game.getHalfMoves();
                    Board board = new Board();

                    int ply = 1;
                    for (Move move : moves) {
                        board.doMove(move);
                        System.out.println(ply + ": " + move.toString());
                        ply++;
                    }

                    System.out.println("Final FEN: " + board.getFen());
                } catch (PgnException e) {
                    System.out.println(">>> [AVISO] Não foi possível carregar os lances deste game:");
                    System.out.println("    Motivo: " + e.getMessage());
                    // aqui você pode simplesmente ignorar os lances, mas ainda aproveita os headers
                }

                System.out.println();
                gameNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
