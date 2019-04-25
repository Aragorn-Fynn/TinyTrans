package lexer;

import exception.ParseException;

public abstract class BaseLexer extends Lexer {

    private int line;
    /**
     * 构造方法
     *
     * @param k 缓冲大小
     */
    public BaseLexer(int k) {
        super(k);
        line = 1;
    }

    public Token nextToken() {

        for (char c = nextChar(); c!=EOF; c=nextChar()) {
            switch (c) {
                case ' ':
                case '\t':
                case '\r':
                case '\n':
                    consume();
                    if (c=='\n')
                        line++;
                    continue;
                case '{':
                    consume();
                    return new Token(TokenType.LBRACE.getType(), c+"");
                case '(':
                    consume();
                    return new Token(TokenType.LPAREN.getType(), c+"");
                case '[':
                    consume();
                    return new Token(TokenType.LBRACK.getType(), c+"");
                case '}':
                    consume();
                    return new Token(TokenType.RBRACE.getType(), c+"");
                case ')':
                    consume();
                    return new Token(TokenType.RPAREN.getType(), c+"");
                case ']':
                    consume();
                    return new Token(TokenType.RBRACK.getType(), c+"");
                case '|':
                    return or(c);
                case '&':
                    return and(c);
                case '+':
                    consume();
                    return new Token(TokenType.ADD.getType(), c+"");
                case '-':
                    consume();
                    return new Token(TokenType.MINUS.getType(), c+"");
                case '*':
                    consume();
                    return new Token(TokenType.MULTI.getType(), c+"");
                case '/':
                    consume();
                    return new Token(TokenType.DIV.getType(), c+"");
                case '<':
                    return lte(c);
                case '>':
                    return gte(c);
                case '!':
                    return neNot(c);
                case '=':
                    return eassign(c);
                case ';':
                    consume();
                    return new Token(TokenType.SEMI.getType(), c+"");
                default:
                    if (isDigit(c))
                        return num(c);
                    if (isLetter(c))
                        return id(c);
                    throw new ParseException("Parse Error in Line "+line);
            }
        }
        return new Token(TokenType.EOF.getType(), EOF+"");
    }

    //等于和赋值
    private Token eassign(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        if (c=='=') {
            sb.append(c);
            consume();
            return new Token(TokenType.EQ.getType(), sb.toString());
        } else {
            goBack();
            consume();
            return new Token(TokenType.ASSIGN.getType(), sb.toString());
        }
    }

    //不等于和非
    private Token neNot(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        if (c=='=') {
            sb.append(c);
            consume();
            return new Token(TokenType.NE.getType(), sb.toString());
        } else {
            goBack();
            consume();
            return new Token(TokenType.NOT.getType(), sb.toString());
        }
    }

    //大于和大于等于
    private Token gte(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        if (c=='=') {
            sb.append(c);
            consume();
            return new Token(TokenType.GE.getType(), sb.toString());
        } else {
            goBack();
            consume();
            return new Token(TokenType.GT.getType(), sb.toString());
        }
    }

    //小于和小于等于
    private Token lte(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        if (c=='=') {
            sb.append(c);
            consume();
            return new Token(TokenType.LE.getType(), sb.toString());
        } else {
            goBack();
            consume();
            return new Token(TokenType.LT.getType(), sb.toString());
        }
    }

    //与和按位与
    private Token and(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        if (c=='&') {
            sb.append(c);
            consume();
            return new Token(TokenType.AND.getType(), sb.toString());
        }
        else {
            goBack();
            consume();
            return new Token(TokenType.BITAND.getType(), sb.toString());
        }
    }

    //或和按位或
    private Token or(char c) {
        StringBuffer sb = new StringBuffer(c+"");
        c = nextChar();
        if (c=='|') {
            sb.append(c);
            consume();
            return new Token(TokenType.OR.getType(), sb.toString());
        }
        else {
            goBack();
            consume();
            return new Token(TokenType.BITOR.getType(), sb.toString());
        }
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
        return  getReserved(sb.toString()) != null
                ? getReserved(sb.toString())
                : new Token(TokenType.ID.getType(), sb.toString());
    }

    private Token num(char c) {
        StringBuffer sb = new StringBuffer();
        do {
            sb.append(c);
            c = nextChar();
        } while (isDigit(c));

        if (c=='.') {
            do {
                sb.append(c);
                c = nextChar();
            } while (isDigit(c));

            //数字后面不能跟字母
            if (isLetter(c) || c=='.')
                throw new ParseException("Expect num, but get "+c+" in line "+line);

            goBack();
            consume();
            return new Token(TokenType.REAL.getType(), sb.toString());
        } else if (isLetter(c)){
            throw new ParseException("Expect num, but get "+c+" in line "+line);
        } else {
            goBack();
            consume();
            return new Token(TokenType.NUM.getType(), sb.toString());
        }

    }

}
