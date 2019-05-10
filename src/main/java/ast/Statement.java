package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 语句基类
 * stmt
 */
public abstract class Statement extends AST {

    //用于语义检查
    private String nextLabel;

    public Statement(Token token) {
        super(token);
    }

    public String getNextLabel() {
        return nextLabel;
    }

    public void setNextLabel(String nextLabel) {
        this.nextLabel = nextLabel;
    }
}
