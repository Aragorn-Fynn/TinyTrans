package lexer;

import java.util.HashMap;
import java.util.Map;

public abstract class Lexer {
    public static char EOF = (char)-1;

    int lexemBegin;
    int forward;
    int current;

    int k;
    char[][] buffers;

    Map<String, Token> reserved;

    /**
     * 构造方法
     * @param k 缓冲大小
     */
    public Lexer(int k) {
        lexemBegin = 0;
        forward = 0;
        current = 0;

        this.k = k;
        buffers = new char[2][k+1];

        reserved = new HashMap<String, Token>();
        reserve(new Token(TokenType.IF.getType(), "if"));
        reserve(new Token(TokenType.ELSE.getType(), "else"));
        reserve(new Token(TokenType.WHILE.getType(), "while"));
        reserve(new Token(TokenType.DO.getType(), "do"));
        reserve(new Token(TokenType.BREAK.getType(), "break"));
        reserve(new Token(TokenType.TRUE.getType(), "true"));
        reserve(new Token(TokenType.FALSE.getType(), "false"));
        reserve(new Token(TokenType.INT.getType(), "int"));
        reserve(new Token(TokenType.FLOAT.getType(), "float"));
        reserve(new Token(TokenType.BOOL.getType(), "bool"));
        reserve(new Token(TokenType.OR.getType(), "OR"));
        reserve(new Token(TokenType.AND.getType(), "AND"));
    }

    //保存关键字
    public void reserve(Token token) {
        this.reserved.put(token.getText(), token);
    }

    //获取关键字
    public Token getReserved(String name) {
        return reserved.get(name);
    }

    /**
     * 获取下一个字符
     * @return
     */
    public char nextChar() {
        if (buffers[current][forward]==EOF) {
            return EOF;
        }

        if (forward==k) {
            current=1-current;
            fillBuffer(buffers[current]);
            forward=0;
        }

        return buffers[current][forward++];
    }

    /**
     * 消耗lexemBegin到forward之间的字符
     */
    public void consume() {
        this.lexemBegin=forward;
    }

    public void goBack() {
        forward--;
    }

    public boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    public boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    public boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }

    protected abstract void fillBuffer(char[] buffer);

    public abstract Token nextToken();
}
