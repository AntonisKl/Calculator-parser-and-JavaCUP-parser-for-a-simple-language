import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // while (true) {
            try {
                CalculatorParser parser = new CalculatorParser(System.in);
                parser.parse();

            } catch (IOException | ParseError e) {
                System.err.println(e.getMessage());
            }
        // }
    }
}
