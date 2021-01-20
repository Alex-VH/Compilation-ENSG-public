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
class Source {
    
    private final String src;
    private int cursor = 0;
    private StringBuilder currentWord = new StringBuilder();

    public Source(String src) {
        this.src = src;
    }

    public char current() {
        return src.charAt(cursor);
    }

    public boolean hasNext() {
        return cursor < src.length();
    }

    public void next() {
        currentWord.append(current());
        cursor++;
    }
    
    public String consumeWord() {
        String word = currentWord.toString();
        currentWord = new StringBuilder(); // empty the string builder
        return word;
    }
    
}
