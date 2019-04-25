package lexer;

public class StringLexer extends BaseLexer {

    private String source;
    private int curr;

    public StringLexer(String source) {
        super(5);
        this.source = source;
        fillBuffer(buffers[0]);
    }

    protected void fillBuffer(char[] buffer) {
        int j=0;
        for (; curr<source.length()&&j<k; curr++,j++) {
            buffer[j] = source.charAt(curr);
        }

        if (curr==source.length() && j<k) {
            buffer[j]=EOF;
        }
    }

}
