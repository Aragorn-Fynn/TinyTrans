package ast;

import lexer.Token;

/**
 * 浮点数值
 * REAL
 */
public class Float extends Expr {

    public Float(Token token) {
        super(token);
    }

}
