package paradigma0621.cleanchess.view;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

public class ViewerImageCache {

    private final Map<SquareColor, Image> squareImages = new EnumMap<>(SquareColor.class);
    private final Map<BarType, Image> barImages = new EnumMap<>(BarType.class);
    private final Map<PieceRenderKey, Image> pieceImages = new java.util.HashMap<>();

    public ViewerImageCache() {
        loadImagesIntoMemory();
    }

    private void loadImagesIntoMemory() {
        loadSquareImages();
        loadBarImages();
        loadPieceImages();
    }

    private void loadSquareImages() {
        squareImages.put(SquareColor.WHITE, load("/images/white-square.jpg"));
        squareImages.put(SquareColor.BLACK, load("/images/black-square.jpg"));
    }

    private void loadBarImages() {
        barImages.put(BarType.HORIZONTAL, load("/images/horizontal-bar-776px-x-4px.jpg"));
        barImages.put(BarType.VERTICAL, load("/images/vertical-bar-4px-x-776px.jpg"));
    }

    private void loadPieceImages() {
        for (PieceColor pieceColor : PieceColor.values()) {
            for (PieceType pieceType : PieceType.values()) {
                for (SquareColor squareColor : SquareColor.values()) {
                    PieceRenderKey key = new PieceRenderKey(pieceColor, pieceType, squareColor);
                    pieceImages.put(key, load(buildPiecePath(pieceColor, pieceType, squareColor)));
                }
            }
        }
    }

    private String buildPiecePath(PieceColor pieceColor, PieceType pieceType, SquareColor squareColor) {
        String square = squareColor == SquareColor.WHITE ? "white" : "black";
        String piece = switch (pieceType) {
            case PAWN -> "pawn";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case ROOK -> "rook";
            case QUEEN -> "queen";
            case KING -> "king";
        };
        String color = pieceColor == PieceColor.WHITE ? "white" : "black";

        return "/images/" + square + "-square-" + piece + "-" + color + ".jpg";
    }

    private Image load(String resourcePath) {
        InputStream stream = getClass().getResourceAsStream(resourcePath);

        if (stream == null) {
            throw new IllegalStateException("Image not found in classpath: " + resourcePath);
        }

        return new Image(stream);
    }

    public Image getSquareImage(SquareColor squareColor) {
        return squareImages.get(squareColor);
    }

    public Image getBarImage(BarType barType) {
        return barImages.get(barType);
    }

    public Image getPieceImage(PieceColor pieceColor, PieceType pieceType, SquareColor squareColor) {
        return pieceImages.get(new PieceRenderKey(pieceColor, pieceType, squareColor));
    }

    public boolean isLoaded() {
        return !squareImages.isEmpty() && !barImages.isEmpty() && !pieceImages.isEmpty();
    }

    public enum SquareColor {
        WHITE, BLACK
    }

    public enum PieceColor {
        WHITE, BLACK
    }

    public enum PieceType {
        PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
    }

    public enum BarType {
        HORIZONTAL, VERTICAL
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    private static final class PieceRenderKey {
        private final PieceColor pieceColor;
        private final PieceType pieceType;
        private final SquareColor squareColor;
    }
}