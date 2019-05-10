package visitor;

import ast.*;
import ast.Float;

/**
 * 中间代码生成
 */
public class IRGenerator implements IVisitor {

    public void visit(Access access) {

    }

    public void visit(ArrayTypeNode arrayTypeNode) {

    }

    public void visit(Assign assign) {
        assign.getLoc().accept(this);
        assign.getVal().accept(this);
        System.out.println(assign.getLoc().getAddr().getName()+"="+assign.getVal().getAddr().getName());
    }

    public void visit(Block block) {
        block.getStmts().accept(this);
    }

    public void visit(Bool bool) {
        bool.setAddr(bool);
    }

    public void visit(Break aBreak) {

    }

    public void visit(BuiltInTypeNode builtInTypeNode) {

    }

    public void visit(Condition condition) {
        condition.getLeft().accept(this);
        condition.getRight().accept(this);
        Temp addr = new Temp();
        condition.setAddr(addr);
        System.out.println(addr
                +"="+condition.getLeft().getAddr().getName()
                +condition.getToken().getText()
                +condition.getRight().getAddr().getName());
    }

    public void visit(Declare declare) {

    }

    public void visit(DeclareSeq declareSeq) {

    }

    public void visit(Do aDo) {

    }

    public void visit(Else anElse) {

    }

    public void visit(Float aFloat) {
        aFloat.setAddr(aFloat);
    }

    public void visit(ID id) {
        id.setAddr(id);
    }

    public void visit(If anIf) {

    }

    public void visit(Int anInt) {
        anInt.setAddr(anInt);
    }

    public void visit(Minus minus) {
        minus.getExpr().accept(this);
        Temp addr = new Temp();
        minus.setAddr(addr);
        System.out.println(addr.getName()+" = minus "+minus.getExpr().getAddr().getName());
    }

    public void visit(Not not) {
        not.getExpr().accept(this);
        Temp addr = new Temp();
        not.setAddr(addr);
        System.out.println(addr.getName()+" = not "+not.getExpr().getAddr());
    }

    public void visit(Operation operation) {
        operation.getLeft().accept(this);
        operation.getRight().accept(this);
        Temp addr = new Temp();
        operation.setAddr(addr);
        System.out.println(addr.getName()+"="
            +operation.getLeft().getAddr().getName()
            +operation.getToken().getText()
            +operation.getRight().getAddr().getName());
    }

    public void visit(Relation relation) {
        relation.getLeft().accept(this);
        relation.getRight().accept(this);
        Temp addr = new Temp();
        relation.setAddr(addr);
        System.out.println(addr.getName()+"="
                +relation.getLeft().getAddr().getName()
                +relation.getToken().getText()
                +relation.getRight().getAddr().getName());
    }

    public void visit(StatementSeq statementSeq) {
        if (statementSeq != null) {
            statementSeq.getCur().accept(this);
            if (statementSeq.getNext()!=null)
                statementSeq.getNext().accept(this);
        }
    }

    public void visit(While aWhile) {

    }

    public void visit(Statement statement) {
    }

    public void visit(Expr expr) {

    }
}
