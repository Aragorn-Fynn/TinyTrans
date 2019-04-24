package lexer;

public class StringLexer extends Lexer {

    private String source;
    private int current;

    public StringLexer(String source) {
        super(5);
        current=0;
        this.source = source;
        fillBuffer(buffers[current]);
    }

    protected void fillBuffer(char[] buffer) {
        int j=0;
        for (; current<source.length()&&j<k; current++,j++) {
            buffer[j] = source.charAt(current);
        }

        if (current==source.length() && j<k) {
            buffer[j]=EOF;
        }
    }

    protected Token nextToken() {
        return null;
    }
}
