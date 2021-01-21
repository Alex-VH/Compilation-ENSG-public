package monLangage;


import java.util.ArrayList;
import java.util.List;


public class MonLexer {

    static List<Token> tokens = new ArrayList<>();

    // Symbol = '-' | '+' | '*' | '/' | '=' | '(' | ')'
    static boolean testSymbol(MaSource s){
        if(s.isOutOfBound()) return false;
        return "-+*/=()".contains((s.current()+ ""));
    }

    // Num = [0-9]
    static boolean testNum(MaSource s) {
        if (s.isOutOfBound()) return false;
        return "0123456789".contains(s.current() + "");
    }

    // Dot = '.'
    static boolean testChar(MaSource s, char c) {
        if (s.isOutOfBound()) return false;
        return s.current() == c;
    }

    // Nums = Num+
    static boolean parseNums(MaSource s) {
        if (!testNum(s)) return false;

        do {
            s.next();
        } while (s.hasNext() &&testNum(s));
        return true;

    }

    // Number = Nums ( '.' (Nums)? )?
    static boolean parseNumber(MaSource s) {
        if(!testNum(s)) return false;
        parseNums(s);
        if (testChar(s, '.')) {
            s.next();
            parseNums(s);
        }

        // Production
        tokens.add(new Token(s.consumeWord(), LexemType.NUMBER));
        return true;
    }

    // Symbol = Symbol+
    static boolean parseSymbol(MaSource s) {
        if(!testSymbol(s)) return false;

        do {
            s.next();
        } while (s.hasNext() &&testSymbol(s));
        tokens.add(new Token(s.consumeWord(), LexemType.SYMBOL));
        return true;
    }

    // Null = ' ' | '\t' | '\r' | '\n'
    static void removeNull(MaSource s) {
        while (s.hasNext() && " \t\r\n".contains(s.current() + "")) {
            s.next();
            s.consumeWord();
        }
    }

    // Expr = Number Symbol Number
    static boolean ParseExpr(MaSource s) {

        while (s.hasNext()) {

            removeNull(s);

            parseNumber(s);

            parseSymbol(s);

        }

        return true;
    }

    public static void main(String[] args) {
        MaSource s = new MaSource("(5 4.5 += + 7.8 9.");

        ParseExpr(s);
        tokens.forEach(System.out::println);

    }
}
