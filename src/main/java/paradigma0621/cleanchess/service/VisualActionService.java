package paradigma0621.cleanchess.service;

import javafx.scene.input.KeyCode;
import paradigma0621.cleanchess.view.GuiBoard;

public class VisualActionService {
    private final GuiBoard guiBoard;

    public VisualActionService(GuiBoard guiBoard) {
        this.guiBoard = guiBoard;
    }

    public void rotateBoard() {
        guiBoard.setAngleToRotate();
        guiBoard.rotateTheBoard();
    }

    public void drawFEN() {
        String FENatual = "3B1R1K/4rb1R/5Pp1/1n2k1N1/2p3P1/2P5/2NQ4/8"; // mate 26 (em 2 (dos difíceis) - esse que vale com "m"
        guiBoard.drawBoard(FENatual);
    }


}
