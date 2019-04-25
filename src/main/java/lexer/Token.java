package lexer;

public class Token {
    private int type;
    private String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        if (type==TokenType.EOF.getType()) {
            return "<"+TokenType.getName(type)+", EOF>";
        }
        return "<"+TokenType.getName(type)+", "+text+">";
    }
}
