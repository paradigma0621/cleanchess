package paradigma0621.cleanchess.view;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

public class PieceImageCache {

    private final Map<PieceImageKey, Image> cache = new EnumMap<>(PieceImageKey.class);

    public PieceImageCache() {
        preload();
    }

    public Image get(PieceImageKey key) {
        return cache.get(key);
    }

    private void preload() {
        for (PieceImageKey key : PieceImageKey.values()) {
            cache.put(key, loadImage(key.getPath()));
        }
    }

    private Image loadImage(String path) {
        InputStream stream = getClass().getResourceAsStream(path);

        if (stream == null) {
            throw new IllegalStateException("Imagem não encontrada no classpath: " + path);
        }

        return new Image(stream);
    }

    public enum PieceImageKey {
        WHITE_PAWN("/images/pieces/wp.png"),
        WHITE_KNIGHT("/images/pieces/wn.png"),
        WHITE_BISHOP("/images/pieces/wb.png"),
        WHITE_ROOK("/images/pieces/wr.png"),
        WHITE_QUEEN("/images/pieces/wq.png"),
        WHITE_KING("/images/pieces/wk.png"),

        BLACK_PAWN("/images/pieces/bp.png"),
        BLACK_KNIGHT("/images/pieces/bn.png"),
        BLACK_BISHOP("/images/pieces/bb.png"),
        BLACK_ROOK("/images/pieces/br.png"),
        BLACK_QUEEN("/images/pieces/bq.png"),
        BLACK_KING("/images/pieces/bk.png");

        private final String path;

        PieceImageKey(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}