package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 关系表达式
 * equality/rel
 */
public class Relation extends Expr {
    private Expr left;
    private Expr right;

    public Relation(Token token, Expr left, Expr right) {
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
}
