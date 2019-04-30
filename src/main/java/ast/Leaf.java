package ast;

import lexer.Token;

/**
 * 叶子节点
 */
public class Leaf extends AST {

    private Token token;

    public Leaf(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
