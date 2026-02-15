package paradigma.cleanchess.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import paradigma.cleanchess.view.GuiBoard;

public class MovePiece {
    public void drawGrayRectangle(GuiBoard boardGUI, int columnIndex, int rowIndex) {
        final String strCasaBranca = getClass().getResource("/images/casaBranca96x96.jpg").toExternalForm();
        Image imgCasaBranca = new Image(strCasaBranca);
        ImageView imageView = new ImageView();
        Double scaleFactor = boardGUI.getScaleFactor();
        imageView.setFitWidth(imgCasaBranca.getWidth() * scaleFactor);
        imageView.setFitHeight(imgCasaBranca.getHeight() * scaleFactor);

        double borderWidth = 5;
        Rectangle border = new Rectangle(
                borderWidth / 2,
                borderWidth / 2,
                imageView.getFitWidth() - borderWidth,
                imageView.getFitHeight() - borderWidth
        );
        border.setStroke(Color.GRAY);
        border.setStrokeWidth(borderWidth);
        border.setFill(Color.TRANSPARENT);

        Pane paneImageView = new Pane(imageView, border);

        boardGUI.getGridPane().add(paneImageView, columnIndex, rowIndex);
    }

}
