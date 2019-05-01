package ast;

import lexer.Token;

/**
 * 表达式基类
 */
public abstract class Expr extends AST {
    public Expr(Token token) {
        super(token);
    }
}
