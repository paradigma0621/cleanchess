package paradigma0621.cleanchess.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.AllArgsConstructor;
import paradigma0621.cleanchess.model.NEW.ViewerStateEnum;
import paradigma0621.cleanchess.service.ViewerActionService;

//@AllArgsConstructor
public class KeyboardShortcutHandler {

    private final ViewerActionService viewerActionService;

    public KeyboardShortcutHandler(ViewerActionService viewerActionService) {
        this.viewerActionService = viewerActionService;
    }

    public void handle(KeyEvent event) {
        System.out.println("xxxxxxxx");
        if (event.isControlDown() && event.getCode() == KeyCode.RIGHT) {
            viewerActionService.dispatch(ViewerStateEnum.NEXT_MOVE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.LEFT) {
            viewerActionService.dispatch(ViewerStateEnum.PREVIOUS_MOVE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.R) {
            viewerActionService.dispatch(ViewerStateEnum.ROTATE_BOARD);
        } else if (event.isControlDown() && event.getCode() == KeyCode.F1) {
            viewerActionService.dispatch(ViewerStateEnum.SHOW_SHORTCUTS_HELP_MESSAGE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.M) {
            System.out.println("g0");
            viewerActionService.dispatch(ViewerStateEnum.LOAD_SIMPLE_FEN);
        }
    }
}
