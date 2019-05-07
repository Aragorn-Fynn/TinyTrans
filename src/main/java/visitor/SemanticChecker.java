package visitor;

import ast.*;
import ast.Float;
import exception.SemanticException;
import symtable.LocalScope;
import symtable.SymTable;
import symtable.Symbol;
import symtable.VariableSymbol;

import java.util.Stack;

/**
 * 语义检查
 */
public class SemanticChecker implements IVisitor {

    private SymTable symTable;

    private Stack<AST> loopStack;

    public SemanticChecker() {
        this.symTable = new SymTable();
        loopStack = new Stack<AST>();
    }

    public void visit(Access access) {
        Symbol symbol = symTable.resolve(access.getId().getToken().getText());
        if (!"array".equals(symbol.getType())) {
            throw new SemanticException(access.getId().getToken().getText()+"不是数组类型!");
        }
    }

    public void visit(ArrayTypeNode arrayTypeNode) {

    }

    public void visit(Assign assign) {
        assign.getLoc().visit(this);
        assign.getVal().visit(this);
    }

    public void visit(Block block) {
        symTable.push(new LocalScope(symTable.getCurrentScope()));
        DeclareSeq decls = block.getDecls();
        visit(decls);

        StatementSeq stmts = block.getStmts();
        visit(stmts);
        symTable.pop();
    }

    public void visit(Bool bool) {
        Symbol symbol = new Symbol(bool.getToken().getText(), symTable.resolve("bool").getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
    }

    public void visit(Break aBreak) {
        if (loopStack.empty())
            throw new SemanticException("break语句只能在循环中出现");
    }

    public void visit(BuiltInTypeNode builtInTypeNode) {

    }

    public void visit(Condition condition) {

    }

    public void visit(Declare declare) {
        TypeNode type = declare.getType();
        ID id = declare.getId();
        VariableSymbol symbol = new VariableSymbol(id.getToken().getText(), type.getType().getName());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
    }

    public void visit(DeclareSeq declareSeq) {
        if (declareSeq!=null) {
            Declare decl = declareSeq.getDecl();
            visit(decl);
            visit(declareSeq.getNext());
        }
    }

    public void visit(Do aDo) {
        loopStack.push(aDo);
        visit(aDo.getStmt());
        visit(aDo.getBool());
        loopStack.pop();
    }

    public void visit(Else anElse) {

    }

    public void visit(Float aFloat) {
        Symbol symbol = new Symbol(aFloat.getToken().getText(), symTable.resolve("float").getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
    }

    public void visit(ID id) {

    }

    public void visit(If anIf) {

    }

    public void visit(Int anInt) {
        Symbol symbol = new Symbol(anInt.getToken().getText(), symTable.resolve("int").getType());
        symbol.setScope(symTable.getCurrentScope());
        symTable.define(symbol);
    }

    public void visit(Minus minus) {

    }

    public void visit(Not not) {

    }

    public void visit(Operation operation) {

    }

    public void visit(Relation relation) {

    }

    public void visit(StatementSeq statementSeq) {
        if (statementSeq!=null) {
            statementSeq.getCur().visit(this);
            visit(statementSeq.getNext());
        }
    }

    public void visit(While aWhile) {
        loopStack.push(aWhile);
        visit(aWhile.getBool());
        aWhile.getStmt().visit(this);
        loopStack.pop();
    }

    public void visit(Statement statement) {
    }

    public void visit(Expr expr) {
    }
}
