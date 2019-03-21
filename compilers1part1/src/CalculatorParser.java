import java.io.IOException;
import java.io.InputStream;

public class CalculatorParser {

    private int lookaheadToken;
    private InputStream in;

    public CalculatorParser(InputStream in) throws IOException {
        this.in = in;
        lookaheadToken = in.read();
    }

    private int charToInt(int ch) {
        return Character.getNumericValue(ch);
    }

    private boolean tokenIsValidId() {
        return (lookaheadToken >= '0' && lookaheadToken <= '9') || (lookaheadToken == '(' || lookaheadToken == ')');
    }

    private boolean tokenIsValidNumber() {
        return lookaheadToken >= '0' && lookaheadToken <= '9';
    }

    private void consume(int symbol) throws IOException, ParseError {
        if (lookaheadToken != symbol)
            throw new ParseError();
        lookaheadToken = in.read();
    }

    private int exp() throws IOException, ParseError {
        int tempLookaheadToken1, tempLookaheadToken2;

        tempLookaheadToken1 = exp2();
//        System.out.println("token 1: " + tempLookaheadToken1);
        tempLookaheadToken2 = xor();
        System.out.println("token 1: " + tempLookaheadToken1);

        System.out.println("token 2: " + tempLookaheadToken2);

        if (tempLookaheadToken2 == -1)
            return tempLookaheadToken1;

        if (tempLookaheadToken1 == -1)
            return tempLookaheadToken2;

        return tempLookaheadToken1 ^ tempLookaheadToken2;
    }

    private int xor() throws IOException, ParseError {
        if (lookaheadToken != 94)   // 94 = ascii caret (^)
            return -1;                 // ε (empty)

        consume(94);
        return exp();
    }

    private int exp2() throws IOException, ParseError {
        int tempLookaheadToken1, tempLookaheadToken2;
        tempLookaheadToken1 = factor();
        tempLookaheadToken2 = and();

        System.out.println("exp2: token1->" + tempLookaheadToken1 + ", token2->" + tempLookaheadToken2);

        if (tempLookaheadToken2 == -1)
            return tempLookaheadToken1;

        if (tempLookaheadToken1 == -1)
            return tempLookaheadToken2;

        System.out.println("will calculate: token1->" + tempLookaheadToken1 + ", token2->" + tempLookaheadToken2);

        return tempLookaheadToken1 & tempLookaheadToken2;
    }

    private int and() throws ParseError, IOException {
        if (lookaheadToken != '&')
            return -1;                 // ε (empty)

        consume('&');
        return exp2();
    }

    private int factor() throws ParseError, IOException {
        if (!tokenIsValidId() || lookaheadToken == ')')
            throw new ParseError();

        if (tokenIsValidNumber()) {
            int tempLookaheadToken = lookaheadToken;
            consume(lookaheadToken);
            return charToInt(tempLookaheadToken);
        }

        consume(lookaheadToken);

        // token is definitely '('
        int tempResult = exp();
        consume(')');

        return tempResult;
    }

    public void parse() throws IOException, ParseError {
        int result = exp();
        if (lookaheadToken != '\n' && lookaheadToken != -1)
            throw new ParseError();

        System.out.println("Result: " + result);
    }
}
