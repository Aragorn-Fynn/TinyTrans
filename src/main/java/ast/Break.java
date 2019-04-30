package ast;

import lexer.Token;

public class Break extends Statement {
    private Token token;

    public Break(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
