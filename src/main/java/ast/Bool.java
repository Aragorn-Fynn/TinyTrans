package ast;

import lexer.Token;

/**
 * 布尔值
 * 'true'/'false'
 */
public class Bool extends Expr {
    public Bool(Token token) {
        super(token);
    }
}
