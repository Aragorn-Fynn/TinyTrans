package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 整数值
 * NUM
 */
public class Int extends Expr implements Address {

    public Int(Token token) {
        super(token);
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return this.getToken().getText();
    }

    public String toString() {
        return getName();
    }
}
