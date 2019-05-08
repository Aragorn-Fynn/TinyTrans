package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 语句基类
 * stmt
 */
public abstract class Statement extends AST {
    public Statement(Token token) {
        super(token);
    }
}
