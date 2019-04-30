package ast;

import lexer.Token;

/**
 * 整数值
 */
public class Int extends Expr {
    private Token token;

    public Int(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
