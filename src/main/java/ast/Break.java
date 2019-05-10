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

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return getToken().getText();
    }

}
