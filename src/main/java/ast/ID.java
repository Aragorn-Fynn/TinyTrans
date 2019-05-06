package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 变量表达式
 * ID
 */
public class ID extends Expr {

    public ID(Token token) {
        super(token);
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

}
