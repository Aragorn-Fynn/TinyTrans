package ast;

import lexer.Token;
import visitor.IVisitor;

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

    public abstract void accept(IVisitor visitor);
}
