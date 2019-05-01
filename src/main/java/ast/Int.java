package ast;

import lexer.Token;

/**
 * 整数值
 * NUM
 */
public class Int extends Expr {

    public Int(Token token) {
        super(token);
    }

}
