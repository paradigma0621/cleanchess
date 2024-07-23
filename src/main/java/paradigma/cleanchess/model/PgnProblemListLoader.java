package paradigma.cleanchess.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PgnProblemListLoader {

    public static Map<Integer, List<String>> loadPgnFile(String filePath) {
        Map<Integer, List<String>> gameMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int gameCount = 0;
            List<String> currentGame = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.startsWith("[Event \"")) {
                    if (!currentGame.isEmpty()) {
                        gameMap.put(gameCount, new ArrayList<>(currentGame));
                        currentGame.clear();
                    }
                    gameCount++;
                }
                currentGame.add(line);
            }

            if (!currentGame.isEmpty()) {
                gameMap.put(gameCount, currentGame);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameMap;
    }
}
