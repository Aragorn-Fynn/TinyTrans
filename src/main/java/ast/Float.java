package ast;

import lexer.Token;

/**
 * 浮点数值
 */
public class Float extends Expr {
    private Token token;

    public Float(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
