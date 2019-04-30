package ast;

import lexer.Token;

/**
 * 变量表达式
 */
public class ID extends Expr {
    private Token token;

    public ID(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
