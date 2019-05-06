package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 整数值
 * NUM
 */
public class Int extends Expr {

    public Int(Token token) {
        super(token);
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

}
