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

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
