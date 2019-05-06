package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 浮点数值
 * REAL
 */
public class Float extends Expr {

    public Float(Token token) {
        super(token);
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

}
