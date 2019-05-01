package ast;

import lexer.Token;

/**
 * 变量表达式
 * ID
 */
public class ID extends Expr {

    public ID(Token token) {
        super(token);
    }

}
