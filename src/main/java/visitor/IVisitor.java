package visitor;

import ast.*;
import ast.Float;

/**
 * 访问者模式
 */
public interface IVisitor {

    void visit(Access access);

    void visit(ArrayTypeNode arrayTypeNode);

    void visit(Assign assign);

    void visit(Block block);

    void visit(Bool bool);

    void visit(Break aBreak);

    void visit(BuiltInTypeNode builtInTypeNode);

    void visit(Condition condition);

    void visit(Declare declare);

    void visit(DeclareSeq declareSeq);

    void visit(Do aDo);

    void visit(Else anElse);

    void visit(Float aFloat);

    void visit(ID id);

    void visit(If anIf);

    void visit(Int anInt);

    void visit(Minus minus);

    void visit(Not not);

    void visit(Operation operation);

    void visit(Relation relation);

    void visit(StatementSeq statementSeq);

    void visit(While aWhile);

    void visit(Statement statement);

    void visit(Expr expr);
}
