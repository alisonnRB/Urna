import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Utilities {
    public Scanner scanner = new Scanner(System.in);

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void awaitTime(int time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String CatchInput(){
        String option = this.scanner.nextLine();

        return option;
    }
}
