import java.util.Scanner;

public class StartGame {
    public static void main(String[] args) throws InterruptedException {
        Game g = Legends.getInstance();
        g.start();
    }
}
