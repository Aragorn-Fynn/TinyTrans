package ast;

import lexer.Token;

/**
 * 一般变量
 */
public class VariableLocation extends Location {
    private Token token;

    public VariableLocation(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
