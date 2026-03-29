package paradigma0621.cleanchess.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import paradigma0621.cleanchess.controller.ConstructChessBoard;
import paradigma0621.cleanchess.model.NEW.ViewerStateEnum;
import paradigma0621.cleanchess.service.ViewerActionService;

public class KeyboardShortcutHandler {

    private final ViewerActionService viewerActionService;
    private final ConstructChessBoard constructChessBoard;

    public KeyboardShortcutHandler(ConstructChessBoard constructChessBoard,   ViewerActionService viewerActionService) {
        this.constructChessBoard = constructChessBoard;
        this.viewerActionService = viewerActionService;
    }

    public void handle(KeyEvent event) {
        constructChessBoard.showKeyPressed(event);

        if (event.isControlDown() && event.getCode() == KeyCode.RIGHT) {
            viewerActionService.dispatch(ViewerStateEnum.NEXT_MOVE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.LEFT) {
            viewerActionService.dispatch(ViewerStateEnum.PREVIOUS_MOVE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.R) {
            viewerActionService.dispatch(ViewerStateEnum.ROTATE_BOARD);
        } else if (event.isControlDown() && event.getCode() == KeyCode.F1) {
            viewerActionService.dispatch(ViewerStateEnum.SHOW_SHORTCUTS_HELP_MESSAGE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.F2) {
            viewerActionService.dispatch(ViewerStateEnum.SHOW_WHO_PLAY_AND_GAME_INFO);
        } else if (event.isControlDown() && event.getCode() == KeyCode.M) {
            viewerActionService.dispatch(ViewerStateEnum.LOAD_SIMPLE_FEN);
        } else if (event.isControlDown() && event.getCode() == KeyCode.A) {
            viewerActionService.dispatch(ViewerStateEnum.LOAD_SIMPLE_PGN);
        } else if (event.isControlDown() && event.getCode() == KeyCode.DOWN) {
            viewerActionService.dispatch(ViewerStateEnum.RESTART_PGN_POSITION);
        } else if (event.isControlDown() && event.getCode() == KeyCode.ADD) {
            viewerActionService.dispatch(ViewerStateEnum.ZOOM_IN);
        } else if (event.isControlDown() && event.getCode() == KeyCode.SUBTRACT) {
            viewerActionService.dispatch(ViewerStateEnum.ZOOM_OUT);
        } else if (event.isControlDown() && event.getCode() == KeyCode.F) {
            viewerActionService.dispatch(ViewerStateEnum.FULL_SCREEN);
        } else if (event.isControlDown() && event.getCode() == KeyCode.SPACE) {
            viewerActionService.dispatch(ViewerStateEnum.SHOW_HIDE_LABEL);
        } else if (event.isControlDown() && event.getCode() == KeyCode.N) {
            viewerActionService.dispatch(ViewerStateEnum.LOAD_PGN_WITH_MANY_GAMES);
        }

    }
}
