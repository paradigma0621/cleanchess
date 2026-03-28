package paradigma0621.cleanchess.novos.controller;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import javafx.stage.Stage;
import paradigma0621.cleanchess.controller.RavTree;
import paradigma0621.cleanchess.novos.model.GameSingleton;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public Controller(Stage stage) {
        GameSingleton gameSingleton1 = new GameSingleton();
    }

    public static String fenAfterSanLine(List<String> sanLine) throws Exception {
        String san = String.join(" ", sanLine);

        MoveList list = new MoveList();
        list.loadFromSan(san); // aceita SAN com ou sem numeração :contentReference[oaicite:2]{index=2}

        Board board = new Board();
        for (Move m : list) {
            board.doMove(m);
        }
        return board.getFen(); // ou board.getFen() dependendo da versão
    }

        // coleta SAN seguindo .next até o fim
    public static List<String> collectMainlineSans(RavTree.Node start) {
        List<String> out = new ArrayList<>();
        RavTree.Node cur = start;
        while (cur != null) {
            out.add(cur.san);
            cur = cur.next;
        }
        return out;
    }
}

