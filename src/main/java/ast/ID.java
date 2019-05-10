package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 变量表达式
 * ID
 */
public class ID extends Expr implements Address {

    public ID(Token token) {
        super(token);
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }


    public String getName() {
        return this.getToken().getText();
    }
}
