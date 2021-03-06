package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 算数表达式
 * expr/term
 */
public class Operation extends Expr {
    private Expr left;
    private Expr right;

    public Operation(Token token, Expr left, Expr right) {
        super(token);
        this.left = left;
        this.right = right;
    }

    public Expr getLeft() {
        return left;
    }

    public void setLeft(Expr left) {
        this.left = left;
    }

    public Expr getRight() {
        return right;
    }

    public void setRight(Expr right) {
        this.right = right;
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return left.toString()+getToken().getText()+right.toString();
    }
}
