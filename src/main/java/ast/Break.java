package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * break语句
 * 'break' ';'
 */
public class Break extends Statement {

    public Break(Token token) {
        super(token);
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }

}
