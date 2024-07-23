package paradigma.cleanchess.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PgnProblemListSaver {

    public static void savePgnFile(String filePath, Map<Integer, List<String>> gameMap) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Integer, List<String>> entry : gameMap.entrySet()) {
                List<String> game = entry.getValue();
                for (String line : game) {
                    bw.write(line);
                    bw.newLine();
                }
                bw.newLine(); // Adiciona uma linha em branco entre jogos
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}