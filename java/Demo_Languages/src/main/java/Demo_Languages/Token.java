/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo_Languages;

/**
 *
 * @author Yann Caron
 */
public class Token {

    private final String word;
    private final LexemType type;

    public Token(String word, LexemType type) {
        this.word = word;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Token{" + "word=" + word + ", type=" + type + '}';
    }

}
