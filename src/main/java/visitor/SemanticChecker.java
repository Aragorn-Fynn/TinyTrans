package visitor;

import ast.*;
import ast.Float;
import exception.SemanticException;
import symtable.*;

import java.util.Stack;

/**
 * 静态检查
 */
public class SemanticChecker implements IVisitor {

    private SymTable symTable;

    private Stack<AST> loopStack;

    public SemanticChecker() {
        this.symTable = new SymTable();
        loopStack = new Stack<AST>();
    }

    public void visit(Access access) {
        access.getId().accept(this);
        if (!(access.getId() instanceof Access)) {
            Symbol symbol = symTable.resolve(access.getId().getToken().getText());
            if (!(symbol.getType() instanceof ArrayType)) {
                throw new SemanticException(access.getId().getToken().getText()+"不是数组类型: "+access.toString());
            }
            access.setType(((ArrayType) symbol.getType()).getElementType());
            access.getId().setScope(symTable.getCurrentScope());
            access.setArray(symbol);
        } else {
            access.setType(((ArrayType)access.getId().getType()).getElementType());
            access.setArray(access.getId().getArray());
        }
        access.getIndex().accept(this);
    }

    public void visit(ArrayTypeNode arrayTypeNode) {

    }

    public void visit(Assign assign) {
        assign.getLoc().accept(this);
        assign.getVal().accept(this);
    }

    public void visit(Block block) {
        symTable.push(new LocalScope(symTable.getCurrentScope()));
        if (block.getDecls()!=null)
            block.getDecls().accept(this);
        if (block.getStmts()!=null) {
            block.getStmts().accept(this);
        }
        symTable.pop();
    }

    public void visit(Bool bool) {
        Symbol symbol = new Symbol(bool.getToken().getText(), symTable.resolve("bool").getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
        bool.setType(symTable.resolve("bool").getType());
    }

    public void visit(Break aBreak) {
        if (loopStack.empty())
            throw new SemanticException("break语句只能在循环中出现: "+aBreak.toString());
    }

    public void visit(BuiltInTypeNode builtInTypeNode) {

    }

    public void visit(Condition condition) {
        condition.getLeft().accept(this);
        if (!(condition.getLeft().getType() instanceof BoolType)) {
            throw new SemanticException("条件语句只能是bool类型: "+condition.getLeft().toString());
        }
        condition.getRight().accept(this);
        if (!(condition.getRight().getType() instanceof BoolType)) {
            throw new SemanticException("条件语句只能是bool类型: "+condition.getRight().toString());
        }
        condition.setType(symTable.resolve("bool").getType());
    }

    public void visit(Declare declare) {
        TypeNode type = declare.getType();
        ID id = declare.getId();
        VariableSymbol symbol = new VariableSymbol(id.getToken().getText(), type.getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);

    }

    public void visit(DeclareSeq stmt) {
        if (stmt!=null) {
            stmt.getDecl().accept(this);
            if (stmt.getNext()!=null) {
                stmt.getNext().accept(this);
            }
        }
    }

    public void visit(Do stmt) {
        loopStack.push(stmt);
        stmt.getStmt().accept(this);
        stmt.getBool().accept(this);
        if (!(stmt.getBool().getType() instanceof BoolType)) {
            throw new SemanticException("do条件只能是bool类型: "+stmt.getBool().toString());
        }
        loopStack.pop();
    }

    public void visit(Else stmt) {
        stmt.getBool().accept(this);
        if (!(stmt.getBool().getType() instanceof BoolType)) {
            throw new SemanticException("if条件只能是bool类型: "+stmt.getBool().toString());
        }
        stmt.getThenStmt().accept(this);
        stmt.getElseStmt().accept(this);
    }

    public void visit(Float expr) {
        Symbol symbol = new Symbol(expr.getToken().getText(), symTable.resolve("float").getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
        expr.setType(symTable.resolve("float").getType());
    }

    public void visit(ID id) {
        id.setType(symTable.resolve(id.getToken().getText()).getType());
    }

    public void visit(If stmt) {
        stmt.getBool().accept(this);
        if (!(stmt.getBool().getType() instanceof BoolType)) {
            throw new SemanticException("if条件只能是bool类型: "+stmt.getBool().toString());
        }
        stmt.getStmt().accept(this);
    }

    public void visit(Int expr) {
        Symbol symbol = new Symbol(expr.getToken().getText(), symTable.resolve("int").getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
        expr.setType(symTable.resolve("int").getType());
    }

    public void visit(Minus minus) {
        minus.getExpr().accept(this);
        if (minus.getExpr().getType() instanceof BoolType) {
            throw new SemanticException("minus语句不能是bool类型: "+minus.toString());
        }
    }

    public void visit(Not not) {
        not.getExpr().accept(this);
        if (!(not.getExpr().getType() instanceof BoolType)) {
            throw new SemanticException("not语句只能是bool类型: "+not.toString());
        }
    }

    public void visit(Operation expr) {
        expr.getLeft().accept(this);
        if (expr.getLeft().getType() instanceof BoolType) {
            throw new SemanticException("not语句不能是bool类型: "+expr.toString());
        }
        expr.getRight().accept(this);
        if (expr.getRight().getType() instanceof BoolType) {
            throw new SemanticException("not语句不能是bool类型: "+expr.toString());
        }
        if (!expr.getLeft().getType().getName().equals(expr.getRight().getType().getName())) {
            throw new SemanticException("算数表达式左右类型应该相同: "+expr.toString());
        }
        expr.setType(expr.getLeft().getType());
    }

    public void visit(Relation expr) {
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        if ((expr.getLeft().getType() instanceof BoolType
                && expr.getRight().getType() instanceof BoolType &&
                (expr.getToken().getText().equals("==")
                        || expr.getToken().getText().equals("!=")))
                || (!(expr.getLeft().getType() instanceof BoolType)
                 && !(expr.getRight().getType() instanceof BoolType))) {
            expr.setType(symTable.resolve("bool").getType());
            return;
        }
        throw new SemanticException("关系表达式语句错误: "+expr.toString());
    }

    public void visit(StatementSeq stmt) {
        if (stmt!=null) {
            stmt.getCur().accept(this);
            if (stmt.getNext()!=null) {
                stmt.getNext().accept(this);
            }
        }
    }

    public void visit(While stmt) {
        loopStack.push(stmt);
        stmt.getBool().accept(this);
        if (!(stmt.getBool().getType() instanceof BoolType)) {
            throw new SemanticException("while条件只能是bool类型: "+stmt.getBool().toString());
        }
        stmt.getStmt().accept(this);
        loopStack.pop();
    }

    public void visit(Statement statement) {
    }

    public void visit(Expr expr) {
    }
}
