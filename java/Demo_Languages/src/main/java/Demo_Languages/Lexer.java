/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo_Languages;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Caron
 */
public class Lexer {

    static List<Token> tokens = new ArrayList<>();

    // Num = [0-9]
    static boolean testCharIn(Source s, String chars) {
        if (!s.hasNext()) {
            return false;
        }
        return chars.contains(s.current() + "");
    }

    // Dot = '.'
    static boolean testChar(Source s, char c) {
        if (!s.hasNext()) {
            return false;
        }
        return s.current() == c;
    }

    // Nums = Num+
    static boolean parseNums(Source s) {
        if (!testCharIn(s, "0123456789")) {
            return false;
        }
        do {
            s.next();
        } while (testCharIn(s, "0123456789"));
        return true;
    }

    // Number = Nums ( '.' Nums )?
    static boolean parseNumber(Source s) {
        if (!parseNums(s)) {
            return false;
        }
        if (testChar(s, '.')) {
            s.next();
            parseNums(s);
        }

        // Production
        tokens.add(new Token(s.consumeWord(), LexemType.NUMBER));
        return true;
    }

    // Symbol = '+' | '-' | '*' | '/' | '(' | ')'
    static boolean parseSymbol(Source s) {
        if (testCharIn(s, "+-/*()")) {
            s.next();
            tokens.add(new Token(s.consumeWord(), LexemType.SYMBOL));
            return true;
        }

        return false;
    }

    // Null = ' ' | '\t' | '\r' | '\n'
    static void removeNull(Source s) {
        while (s.hasNext() && " \t\r\n".contains(s.current() + "")) {
            s.next();
            s.consumeWord();
        }
    }

    // Expr = Number Symbol Number
    public static List<Token> ParseExpr(Source s) {

        while (s.hasNext()) {

            removeNull(s);

            parseNumber(s);

            parseSymbol(s);

        }

        removeNull(s);

        return tokens;
    }

}
