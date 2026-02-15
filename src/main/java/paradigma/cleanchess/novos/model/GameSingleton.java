package paradigma.cleanchess.novos.model;

public class GameSingleton {
    private static GameSingleton singleton = new GameSingleton();

    public static GameSingleton getInstance() {
        return singleton;
    }
}
