package ast;

import lexer.Token;

/**
 * 基本类型
 */
public class BasicType extends Type {
    private Token type;

    public BasicType(Token type) {
        this.type = type;
    }

    public Token getType() {
        return type;
    }

    public void setType(Token type) {
        this.type = type;
    }
}
