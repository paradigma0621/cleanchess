package paradigma0621.cleanchess.view;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewerImageCacheTest {

    @BeforeAll
    static void initJavaFx() throws InterruptedException {
        AtomicBoolean started = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);

        try {
            Platform.startup(() -> {
                started.set(true);
                latch.countDown();
            });
        } catch (IllegalStateException ex) {
            // JavaFX runtime já inicializado
            started.set(true);
            latch.countDown();
        }

        assertTrue(latch.await(5, TimeUnit.SECONDS), "JavaFX toolkit was not initialized");
        assertTrue(started.get(), "JavaFX toolkit startup failed");
    }

    @Test
    void shouldLoadAllImagesIntoMemory() {
        ViewerImageCache cache = new ViewerImageCache();

        assertTrue(cache.isLoaded());
    }

    @Test
    void shouldLoadSquareImages() {
        ViewerImageCache cache = new ViewerImageCache();

        Image whiteSquare = cache.getSquareImage(ViewerImageCache.SquareColor.WHITE);
        Image blackSquare = cache.getSquareImage(ViewerImageCache.SquareColor.BLACK);

        assertNotNull(whiteSquare);
        assertNotNull(blackSquare);
    }

    @Test
    void shouldLoadBarImages() {
        ViewerImageCache cache = new ViewerImageCache();

        Image horizontalBar = cache.getBarImage(ViewerImageCache.BarType.HORIZONTAL);
        Image verticalBar = cache.getBarImage(ViewerImageCache.BarType.VERTICAL);

        assertNotNull(horizontalBar);
        assertNotNull(verticalBar);
    }

    @Test
    void shouldLoadAllPieceImageCombinations() {
        ViewerImageCache cache = new ViewerImageCache();

        for (ViewerImageCache.PieceColor pieceColor : ViewerImageCache.PieceColor.values()) {
            for (ViewerImageCache.PieceType pieceType : ViewerImageCache.PieceType.values()) {
                for (ViewerImageCache.SquareColor squareColor : ViewerImageCache.SquareColor.values()) {
                    Image image = cache.getPieceImage(pieceColor, pieceType, squareColor);

                    assertNotNull(
                            image,
                            () -> "Expected image for pieceColor=" + pieceColor
                                    + ", pieceType=" + pieceType
                                    + ", squareColor=" + squareColor
                    );
                }
            }
        }
    }

    @Test
    void shouldLoadSpecificPieceImage() {
        ViewerImageCache cache = new ViewerImageCache();

        Image whitePawnOnBlackSquare = cache.getPieceImage(
                ViewerImageCache.PieceColor.WHITE,
                ViewerImageCache.PieceType.PAWN,
                ViewerImageCache.SquareColor.BLACK
        );

        assertNotNull(whitePawnOnBlackSquare);
    }
}