package visitor;

import ast.*;
import ast.Float;
import symtable.ArrayType;
import symtable.SymTable;

import javax.swing.*;

/**
 * 中间代码生成
 */
public class IRGenerator implements IVisitor {

    private static int label=0;

    public void visit(Access access) {
        if (!(access.getId() instanceof Access)) {
            access.setArray(access.getId().getScope().resolve(access.getId().getName()));
            Temp addr = new Temp();
            access.setAddr(addr);
            access.getIndex().accept(this);
            System.out.printf("%s=%s*%d%n",
                    addr.getName(),
                    access.getIndex().getAddr().getName(),
                    access.getType().getWidth());
        } else {
            access.getId().accept(this);
            access.setArray(access.getId().getArray());
            Temp temp = new Temp();
            Temp addr = new Temp();
            access.setAddr(addr);
            access.getIndex().accept(this);
            System.out.printf("%s=%s*%d%n",
                    temp.getName(),
                    access.getIndex().getAddr().getName(),
                    access.getType().getWidth());
            System.out.printf("%s=%s+%s%n",
                    addr.getName(),
                    access.getId().getAddr().getName(),
                    temp.getName());
            if (!access.isLeft()) {
                Temp addrs = new Temp();
                access.setAddr(addrs);
                System.out.printf("%s=%s[%s]%n",
                        addrs.getName(),
                        access.getArray().getName(),
                        access.getId().getAddr().getName());
            }
        }

    }

    public void visit(ArrayTypeNode arrayTypeNode) {

    }

    public void visit(Assign assign) {
        assign.getLoc().accept(this);
        assign.getVal().accept(this);
        if (assign.getLoc() instanceof Access) {
            System.out.printf("%s[%s]=%s%n",
                    ((Access) assign.getLoc()).getArray().getName(),
                    assign.getLoc().getAddr().getName(),
                    assign.getVal().getAddr().getName());
        } else {
            System.out.printf("%s=%s%n",
                    assign.getLoc().getAddr().getName(),
                    assign.getVal().getAddr().getName());
        }
    }

    public void visit(Block block) {
        String label = newLabel();
        block.getStmts().setNextLabel(label);
        block.getStmts().accept(this);
        /*label(label);
        System.out.println();*/
    }

    private void label(String label) {
        System.out.printf("%s: ", label);
    }

    public void visit(Bool bool) {
        if (bool.getTrueLabel()!=null || bool.getFalseLabel()!=null) {
            if (bool.getToken().getText().equals("true"))
                System.out.println(space()+"goto "+bool.getTrueLabel());
            else
                System.out.println(space()+"goto "+bool.getFalseLabel());

        } else {
            bool.setAddr(bool);
        }
    }

    private String space() {
        return "    ";
    }

    public void visit(Break aBreak) {

    }

    public void visit(BuiltInTypeNode builtInTypeNode) {

    }

    public void visit(Condition expr) {
        if (expr.getTrueLabel()!=null || expr.getFalseLabel()!=null) {
            if ("OR".equals(expr.getToken().getText())) {
                expr.getLeft().setTrueLabel(expr.getTrueLabel());
                String label = newLabel();
                expr.getLeft().setFalseLabel(label);
                expr.getRight().setTrueLabel(expr.getTrueLabel());
                expr.getRight().setFalseLabel(expr.getFalseLabel());
                expr.getLeft().accept(this);
                label(label);
                expr.getRight().accept(this);
            } else if ("AND".equals(expr.getToken().getText())) {
                String label = newLabel();
                expr.getLeft().setTrueLabel(label);
                expr.getLeft().setFalseLabel(expr.getFalseLabel());
                expr.getRight().setTrueLabel(expr.getTrueLabel());
                expr.getRight().setFalseLabel(expr.getFalseLabel());
                expr.getLeft().accept(this);
                label(label);
                expr.getRight().accept(this);
            }
        } else {
            expr.getLeft().accept(this);
            expr.getRight().accept(this);
            Temp addr = new Temp();
            expr.setAddr(addr);
            System.out.printf(space()+"%s=%s%s%s%n", addr,
                    expr.getLeft().getAddr().getName(),
                    expr.getToken().getText(),
                    expr.getRight().getAddr().getName());
        }
    }

    public void visit(Declare declare) {

    }

    public void visit(DeclareSeq declareSeq) {

    }

    public void visit(Do stmt) {
        String trueLabel = newLabel();
        stmt.getBool().setTrueLabel(trueLabel);
        stmt.getBool().setFalseLabel(stmt.getNextLabel());
        stmt.getStmt().setNextLabel(stmt.getNextLabel());
        label(trueLabel);
        stmt.getStmt().accept(this);
        stmt.getBool().accept(this);
    }

    public void visit(Else stmt) {
        String trueLabel = newLabel();
        String falseLabel = newLabel();
        stmt.getThenStmt().setNextLabel(stmt.getNextLabel());
        stmt.getElseStmt().setNextLabel(stmt.getNextLabel());
        stmt.getBool().accept(this);
        label(trueLabel);
        stmt.getThenStmt().accept(this);
        System.out.println(space()+"goto " + stmt.getNextLabel());
        label(falseLabel);
        stmt.getElseStmt().accept(this);
    }

    public void visit(Float aFloat) {
        aFloat.setAddr(aFloat);
    }

    public void visit(ID id) {
        id.setAddr(id);
    }

    public void visit(If stmt) {
        String trueLabel = newLabel();
        Expr bool = stmt.getBool();
        bool.setTrueLabel(trueLabel);
        bool.setFalseLabel(stmt.getNextLabel());
        stmt.getStmt().setNextLabel(stmt.getNextLabel());
        stmt.getBool().accept(this);
        label(trueLabel);
        stmt.getStmt().accept(this);
    }

    public void visit(Int anInt) {
        anInt.setAddr(anInt);
    }

    public void visit(Minus minus) {
        minus.getExpr().accept(this);
        Temp addr = new Temp();
        minus.setAddr(addr);
        System.out.println(space()+addr.getName()+" = minus "+minus.getExpr().getAddr().getName());
    }

    public void visit(Not not) {
        if (not.getTrueLabel()!=null || not.getFalseLabel()!=null) {
            not.getExpr().setTrueLabel(not.getFalseLabel());
            not.getExpr().setFalseLabel(not.getTrueLabel());
            not.getExpr().accept(this);
        } else {
            not.getExpr().accept(this);
            Temp addr = new Temp();
            not.setAddr(addr);
            System.out.println(space()+addr.getName()+" = not "+not.getExpr().getAddr());
        }
    }

    public void visit(Operation operation) {
        operation.getLeft().accept(this);
        operation.getRight().accept(this);
        Temp addr = new Temp();
        operation.setAddr(addr);
        System.out.printf(space()+"%s=%s%s%s%n", addr.getName(),
                operation.getLeft().getAddr().getName(),
                operation.getToken().getText(),
                operation.getRight().getAddr().getName());
    }

    public void visit(Relation expr) {
        if (expr.getTrueLabel()!=null || expr.getFalseLabel()!=null) {
            expr.getLeft().accept(this);
            expr.getRight().accept(this);
            System.out.printf(space()+"if %s%s%s goto %s%n",
                    expr.getLeft().getAddr().getName(),
                    expr.getToken().getText(),
                    expr.getRight().getAddr().getName(),
                    expr.getTrueLabel());
            System.out.printf(space()+"goto %s%n", expr.getFalseLabel());
        } else {
            expr.getLeft().accept(this);
            expr.getRight().accept(this);
            Temp addr = new Temp();
            expr.setAddr(addr);
            System.out.printf("%s=%s%s%s%n", addr.getName(),
                    expr.getLeft().getAddr().getName(),
                    expr.getToken().getText(),
                    expr.getRight().getAddr().getName());
        }
    }

    public void visit(StatementSeq seq) {
        if (seq != null) {
            String label1 = newLabel();
            seq.getCur().setNextLabel(label1);
            if (seq.getNext()!=null) {
                seq.getNext().setNextLabel(seq.getNextLabel());
            }
            seq.getCur().accept(this);
            label(label1);
            System.out.println();
            if (seq.getNext()!=null) {
                seq.getNext().accept(this);
            }
        }
    }

    public void visit(While stmt) {
        String begin = newLabel();
        String trueLabel = newLabel();
        stmt.getBool().setTrueLabel(trueLabel);
        stmt.getBool().setFalseLabel(stmt.getNextLabel());
        stmt.getStmt().setNextLabel(begin);
        label(begin);
        stmt.getBool().accept(this);
        label(trueLabel);
        stmt.getStmt().accept(this);
        System.out.printf(space()+"goto %s%n", begin);
    }

    public void visit(Statement statement) {
    }

    public void visit(Expr expr) {

    }

    private String newLabel() {
        return "L"+label++;
    }
}
