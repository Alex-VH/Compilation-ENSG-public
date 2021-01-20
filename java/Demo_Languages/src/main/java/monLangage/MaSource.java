package monLangage;

public class MaSource {

    private final String src;
    private int cursor = 0;
    private StringBuilder currentWord = new StringBuilder();

    public MaSource(String src) {
        this.src = src;
    }

    public char current() {
        return src.charAt(cursor);
    }

    public boolean hasNext() {
        if (src.length() == 0){
            return false;
        }
        return cursor < (src.length()-1);
    }

    public boolean isOutOfBound(){
        return cursor >= src.length();
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
