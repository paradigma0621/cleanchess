package paradigma0621.cleanchess.service;

import paradigma0621.cleanchess.model.NEW.ViewerStateEnum;
import paradigma0621.cleanchess.view.HelpDialog;

public class ViewerActionService {

    private final PgnNavigationService pgnNavigationService;
    private final VisualActionService visualActionService;
    private final HelpDialog helpDialog;

    public ViewerActionService(
            PgnNavigationService pgnNavigationService,
            VisualActionService visualActionService,
            HelpDialog helpDialog
    ) {
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
            case LOAD_SIMPLE_FEN -> visualActionService.drawFEN();
            case LOAD_SIMPLE_PGN -> pgnNavigationService.loadPGN();
        }

    }

}
