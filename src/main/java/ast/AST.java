package ast;

import lexer.Token;

public abstract class AST {
    private Token token;

    public AST(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
