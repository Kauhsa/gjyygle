package gjyygle;

import Kayttoliittyma.Kayttoliittyma;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        String input = "5\n3\n";
        Scanner lukija = new Scanner(input);
        Kayttoliittyma liittyma = new Kayttoliittyma(lukija);
        liittyma.kaynnista();
    }
}
