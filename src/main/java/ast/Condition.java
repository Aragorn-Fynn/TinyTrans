package ast;

import lexer.Token;

/**
 * 条件表达式
 * bool/join
 */
public class Condition extends Expr {
    private Expr left;
    private Expr right;

    public Condition(Token token, Expr left, Expr right) {
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
}
