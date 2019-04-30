package ast;

import lexer.Token;

/**
 * 单值表达式
 */
public class ValExpr extends Expr {
    private Token token;

    public ValExpr(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
