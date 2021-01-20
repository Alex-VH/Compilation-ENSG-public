/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo_Languages;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Caron
 */
public class Lexer {

    static List<Token> tokens = new ArrayList<>();
    
        // Num = [0-9]
    static boolean testNum(Source s) {
        if (!s.hasNext()) return false;
        return "0123456789".contains(s.current() + "");
    }
    
    // Dot = '.'
    static boolean testChar(Source s, char c) {
        if (!s.hasNext()) return false;
        return s.current() == c;
    }
    
    // Nums = Num+
    static boolean parseNums(Source s) {
        if (!testNum(s)) return false;
        do {
            s.next();
        } while (testNum(s));
        return true;
    }
    
    // Number = Nums ( '.' Nums )?
    static boolean parseNumber(Source s) {
        if (!parseNums(s)) return false;
        if (testChar(s, '.')) {
            s.next();
            parseNums(s);
        }
        
        // Production
        tokens.add(new Token(s.consumeWord(), LexemType.NUMBER));
        return true;
    }
    
    // Symbol = '+' | '-' | ....
    static boolean parseSymbol(Source s) {
        if (testChar(s, '+')) {
            s.next();
            tokens.add(new Token(s.consumeWord(), LexemType.SYMBOL));
            return true;
        }
        if (testChar(s, '-')) {
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
    static boolean ParseExpr(Source s) {
        
        while (s.hasNext()) {
            
            removeNull(s);
            
            parseNumber(s);
            
            parseSymbol(s);
            
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Source s = new Source("5 4.5 + + 7.8");
        
        ParseExpr(s);
        
        tokens.forEach(System.out::println);
        
    }
    
    
    // (45 84)
}
