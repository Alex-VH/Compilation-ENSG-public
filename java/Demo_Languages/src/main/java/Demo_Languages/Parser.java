/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo_Languages;

import java.util.List;

/**
 *
 * @author Yann Caron
 */
public class Parser {

    public static void main(String[] args) {

        Source s = new Source("    2 * 7 + 8 / (2 + 2)   ");

        List<Token> tokens = Lexer.ParseExpr(s);

        tokens.forEach(System.out::println);

    }

}
