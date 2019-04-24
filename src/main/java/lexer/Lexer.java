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
        reserved.put("if", new Token(TokenType.RESERVE, "if"));
        reserved.put("else", new Token(TokenType.RESERVE, "else"));
        reserved.put("while", new Token(TokenType.RESERVE, "while"));
        reserved.put("do", new Token(TokenType.RESERVE, "do"));
        reserved.put("break", new Token(TokenType.RESERVE, "break"));
        reserved.put("true", new Token(TokenType.RESERVE, "true"));
        reserved.put("false", new Token(TokenType.RESERVE, "false"));
        reserved.put("int", new Token(TokenType.RESERVE, "int"));
        reserved.put("float", new Token(TokenType.RESERVE, "float"));
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

    protected abstract void fillBuffer(char[] buffer);

    protected abstract Token nextToken();
}
