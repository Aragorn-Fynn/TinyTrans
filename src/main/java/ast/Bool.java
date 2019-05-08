package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 布尔值
 * 'true'/'false'
 */
public class Bool extends Expr {
    public Bool(Token token) {
        super(token);
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
