import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        while (true) {
            GameLoop();

        }

    }

    public static int GameLoop() {

        Scanner scanner = new Scanner(System.in);
        int size = 4;
        int match = 0;
        int tries = 0;
        int hiscore = loadNumberFromFile("hiscore.txt");
        System.out.println("HISCORE: " + hiscore);
        System.out.print("Zvol si delku kódu (pro 4 nepiš nic): ");

        String text = scanner.nextLine();

        if (!text.equals("") && !text.equals("0")) {
            size = Integer.decode(text);
        }

        String topsecretcode = getCode(size);

        System.out.println("Byl ti vygenerován " + size + " místný kód. \nHádej ho");
        while (true) {

            System.out.print("Napiš kód: ");
            text = scanner.nextLine();
            match = getDifference(text.replace(" ", ""), topsecretcode);
            System.out.println(text);
            if (text.equals("r")) {

                System.out.println("HERE");
                return 0;

            }

            if (text.equals("|")) {
                System.out.println(topsecretcode);
                System.out.println("N00B cheater");
                return 0;
            }

            if (match != -1) {
                System.out.println("Stejné jsou " + match + " čísla");
            } else {
                System.out.println("nauč se prosím psát " + size + " čísla, ERROR");

            }

            if (match == size) {
                System.out.println("Gratuluju jessinko,vyhrála jsi s " + tries);
                if (hiscore > tries) {
                    writeNumberToFile(tries, "hiscore.txt");
                }

                break;
            }
            tries++;
        }

        return 0;

    }

    public static char getCharCode() {
        Random random = new Random();
        String string = "" + random.nextInt(0, 9);
        return string.charAt(0);

    }

    public static String getCode(int size) {
        String lala = "";
        for (int i = 0; i < size; i++) {
            lala += getCharCode();
        }
        return lala;

    }

    public static int getDifference(String usernum, String code) {
        int match = 0;
        if (usernum.length() != code.length()) {
            return -1;
        }
        for (int i = 0; i < usernum.length(); i++) {
            if (usernum.charAt(i) == code.charAt(i)) {
                match++;
            }

        }
        return match;
    }

    public static int loadNumberFromFile(String filename) {
        int number = 0;
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            number = Integer.parseInt(bufferedReader.readLine());
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
        return number;
    }

    public static void writeNumberToFile(int number, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(Integer.toString(number));
            writer.close();
            System.out.println("New hiscore " + number);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

}
