package lexer;

import exception.ParseException;

public abstract class BaseLexer extends Lexer {

    /**
     * 构造方法
     *
     * @param k 缓冲大小
     */
    public BaseLexer(int k) {
        super(k);
    }

    public Token nextToken() {

        for (char c = nextChar(); c!=EOF; c=nextChar()) {
            switch (c) {
                case ' ':
                case '\t':
                case '\r':
                case '\n':
                    consume();
                    continue;
                case '{':
                case '(':
                case '[':
                    consume();
                    return new Token(TokenType.LBRACKET, c+"");
                case '}':
                case ')':
                case ']':
                    consume();
                    return new Token(TokenType.RBRACKET, c+"");
                case '|':
                case '&':
                    return new Token(TokenType.RELATION, relation(c));
                case '+':
                case '-':
                case '*':
                case '/':
                    consume();
                    return new Token(TokenType.OP_ARITH, c+"");
                case '<':
                case '>':
                case '!':
                case '=':
                    return operation(c);
                case ';':
                    consume();
                    return new Token(TokenType.SEMI, c+"");
                default:
                    if (isDigit(c))
                        return new Token(TokenType.NUM, num(c));
                    if (isLetter(c))
                        return id(c);
                    throw new ParseException("Parse Error: "+c);
            }
        }
        return new Token(TokenType.EOF, EOF+"");
    }

    private Token id(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        while (isLetter(c) || isDigit(c)) {
            sb.append(c);
            c = nextChar();
        }

        goBack();
        consume();
        return reserved.get(sb.toString()) != null ?
                reserved.get(sb.toString()) : new Token(TokenType.ID, sb.toString());
    }

    private String num(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        while (isDigit(c)) {
            sb.append(c);
            c = nextChar();
        }

        goBack();
        consume();
        return sb.toString();
    }

    /**
     * 运算符
     * @param c
     * @return
     */
    private Token operation(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        char next = nextChar();
        if (c=='=') {
            if (next=='=') {
                sb.append(next);
                consume();
            } else {
                goBack();
                return new Token(TokenType.ASSIGN, sb.toString());
            }
        } else if (c=='!') {
            if (next=='=') {
                sb.append(next);
                consume();
            } else {
                goBack();
                return new Token(TokenType.NOT, sb.toString());
            }
        } else {
            if (next=='=') {
                sb.append(next);
                consume();
            }
            else
                goBack();
        }

        return new Token(TokenType.OP_RELATION, sb.toString());
    }



    /**
     * 关系运算
     * @param c
     * @return
     */
    private String relation(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        char next = nextChar();
        if (c==next) {
            sb.append(next);
            consume();
            return sb.toString();
        } else {
            throw new ParseException("Expect "+c+", but found "+next);
        }

    }
}
