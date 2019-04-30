package ast;

import lexer.Token;

/**
 * 布尔值
 */
public class Bool extends Expr {
    private Token token;

    public Bool(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
