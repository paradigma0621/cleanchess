package paradigma0621.cleanchess.service;

import paradigma0621.cleanchess.controller.ConstructChessBoard;
import paradigma0621.cleanchess.model.NEW.ViewerStateEnum;
import paradigma0621.cleanchess.view.HelpDialog;

public class ViewerActionService {

    private final ConstructChessBoard constructChessBoard;
    private final PgnNavigationService pgnNavigationService;
    private final VisualActionService visualActionService;
    private final HelpDialog helpDialog;

    public ViewerActionService(ConstructChessBoard constructChessBoard,
                               PgnNavigationService pgnNavigationService,
                               VisualActionService visualActionService,
                               HelpDialog helpDialog
    ) {
        this.constructChessBoard = constructChessBoard;
        this.pgnNavigationService = pgnNavigationService;
        this.visualActionService = visualActionService;
        this.helpDialog = helpDialog;
    }

    public void dispatch(ViewerStateEnum action) {

        switch (action) {
            case NEXT_MOVE -> pgnNavigationService.nextMove();
            case PREVIOUS_MOVE -> pgnNavigationService.previousMove();
            case ROTATE_BOARD -> visualActionService.rotateBoard();
            case SHOW_SHORTCUTS_HELP_MESSAGE -> helpDialog.openHelpDialog();
            case SHOW_WHO_PLAY_AND_GAME_INFO -> pgnNavigationService.showWhoPlayAndGameInfo();
            case LOAD_SIMPLE_FEN -> visualActionService.drawFEN();
            case LOAD_SIMPLE_PGN -> pgnNavigationService.loadPGN();
            case RESTART_PGN_POSITION -> pgnNavigationService.restartGamePosition();
            case ZOOM_IN -> constructChessBoard.zoomIn();
            case ZOOM_OUT -> constructChessBoard.zoomOut();
            case FULL_SCREEN -> constructChessBoard.fullScreen();
            case SHOW_HIDE_LABEL -> constructChessBoard.showLabel();
            case LOAD_PGN_WITH_MANY_GAMES -> pgnNavigationService.loadPGNwithManyGames();
        }

    }

}
