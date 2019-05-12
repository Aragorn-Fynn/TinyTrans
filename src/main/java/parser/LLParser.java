package parser;

import ast.*;
import ast.Float;
import exception.ParseException;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import symtable.BoolType;
import symtable.FloatType;
import symtable.IntType;

/**
 * 将文法改为LL(1)文法，使用递归下降解析器进行解析
 */
public class LLParser extends Parser {
    public LLParser(Lexer lexer) {
        super(lexer);
    }

    /**
     * 解析代码
     */
    public AST parse() {
        AST program = program();
        match(TokenType.EOF);
        System.out.println("Syntax correct!");
        return program;
    }

    /**
     * 程序
     */
    private AST program() {
        return block();
    }

    /**
     * 块
     */
    private Block block() {
        match(TokenType.LBRACE);
        DeclareSeq decls = decls();
        StatementSeq stmts = stmts();
        match(TokenType.RBRACE);
        Block block = new Block(null, decls, stmts);
        return block;
    }

    /**
     * 声明语句集合
     */
    private DeclareSeq decls() {
        return decl_();
    }

    private DeclareSeq decl_() {
        if (lookAhead.getType()==TokenType.INT.getType()
                || lookAhead.getType()==TokenType.FLOAT.getType()
                || lookAhead.getType()==TokenType.BOOL.getType()) {
            Declare decl = decl();
            DeclareSeq next = decl_();
            return new DeclareSeq(null, decl, next);
        } else if (lookAhead.getType()==TokenType.ID.getType()
                || lookAhead.getType()==TokenType.IF.getType()
                || lookAhead.getType()==TokenType.WHILE.getType()
                || lookAhead.getType()==TokenType.DO.getType()
                || lookAhead.getType()==TokenType.BREAK.getType()
                || lookAhead.getType()==TokenType.LBRACE.getType()) {
            return null;
        } else {
            throw new ParseException("Expecting decl_, found "+lookAhead);
        }
    }

    /**
     * 声明语句
     */
    private Declare decl() {
        TypeNode type = type();
        ID id = new ID(lookAhead);
        match(TokenType.ID);
        match(TokenType.SEMI);
        return new Declare(null, type, id);
    }

    /**
     * 基本类型
     */
    private TypeNode type() {
        BuiltInTypeNode basic = basic();
        return type_(basic);
    }

    private TypeNode type_(TypeNode type) {
        if (lookAhead.getType()==TokenType.LBRACK.getType()) {
            match(TokenType.LBRACK);
            Token index = lookAhead;
            match(TokenType.NUM);
            match(TokenType.RBRACK);
            TypeNode arrayType =  type_(type);
            return new ArrayTypeNode(null, arrayType, new Int(index));
        } else if (lookAhead.getType()==TokenType.ID.getType()) {
            return type;
        } else {
            throw new ParseException("Expecting type_, found "+lookAhead);
        }
    }

    /**
     * 语句集合
     */
    private StatementSeq stmts() {
        return stmts_();
    }

    private StatementSeq stmts_() {
        if (lookAhead.getType()==TokenType.ID.getType()
                || lookAhead.getType()==TokenType.IF.getType()
                || lookAhead.getType()==TokenType.WHILE.getType()
                || lookAhead.getType()==TokenType.DO.getType()
                || lookAhead.getType()==TokenType.BREAK.getType()
                || lookAhead.getType()==TokenType.LBRACE.getType()) {
            Statement stmt = stmt();
            StatementSeq stmts = stmts_();
            return new StatementSeq(null, stmt, stmts);
        } else if (lookAhead.getType()==TokenType.RBRACE.getType()) {
            return null;
        } else {
            throw new ParseException("Expecting stmts_, found "+lookAhead);
        }
    }

    /**
     * 语句
     */
    private Statement stmt() {
        if (lookAhead.getType()==TokenType.ID.getType()) {
            return Assign();
        } else if (lookAhead.getType()==TokenType.IF.getType()) {
            return ifelse();
        } else if (lookAhead.getType()==TokenType.WHILE.getType()) {
            return whileStmt();
        } else if (lookAhead.getType()==TokenType.DO.getType()) {
            return doWhile();
        } else if (lookAhead.getType()==TokenType.BREAK.getType()) {
            match(TokenType.BREAK);
            match(TokenType.SEMI);
            return new Break(input.getReserved(TokenType.BREAK.getName()));
        } else if (lookAhead.getType()==TokenType.LBRACE.getType()) {
            return block();
        } else {
            throw new ParseException("Expecting statement, found "+lookAhead);
        }
    }

    private Assign Assign() {
        Expr loc = loc();
        match(TokenType.ASSIGN);
        Expr val = bool();
        match(TokenType.SEMI);
        if (loc instanceof Access) {
            ((Access)loc).setLeft(true);
        }
        return new Assign(input.getReserved(TokenType.ASSIGN.getName()), loc, val);
    }

    /**
     * if else 语句
     */
    private Statement ifelse() {
        match(TokenType.IF);
        match(TokenType.LPAREN);
        Expr expr = bool();
        match(TokenType.RPAREN);
        Statement thenStmt = stmt();
        if (lookAhead.getType()== TokenType.ELSE.getType()) {
            match(TokenType.ELSE);
            Statement elseStmt = stmt();
            return new Else(input.getReserved(TokenType.ELSE.getName()), expr, thenStmt, elseStmt);
        } else {
            return new If(input.getReserved(TokenType.IF.getName()), expr, thenStmt);
        }
    }

    /**
     * while语句
     */
    private While whileStmt() {
        match(TokenType.WHILE);
        match(TokenType.LPAREN);
        Expr expr = bool();
        match(TokenType.RPAREN);
        Statement stmt = stmt();
        return new While(input.getReserved(TokenType.WHILE.getName()),expr, stmt);
    }

    /**
     * do while语句
     */
    private Do doWhile() {
        match(TokenType.DO);
        Statement stmt = stmt();
        match(TokenType.WHILE);
        match(TokenType.LPAREN);
        Expr expr = bool();
        match(TokenType.RPAREN);
        match(TokenType.SEMI);
        return new Do(input.getReserved(TokenType.DO.getName()), stmt, expr);
    }

    private Expr loc() {
        ID id = new ID(lookAhead);
        match(TokenType.ID);
        return loc_(id);
    }

    private ID loc_(ID id) {
        if (lookAhead.getType()==TokenType.LBRACK.getType()) {
            match(TokenType.LBRACK);
            Expr bool = bool();
            match(TokenType.RBRACK);
            return loc_(new Access(id, bool));
        } else if (lookAhead.getType()==TokenType.ASSIGN.getType()
                || lookAhead.getType()==TokenType.NUM.getType()
                || lookAhead.getType()==TokenType.REAL.getType()
                || lookAhead.getType()==TokenType.TRUE.getType()
                || lookAhead.getType()==TokenType.FALSE.getType()
                || lookAhead.getType()== TokenType.ADD.getType()
                || lookAhead.getType()== TokenType.MINUS.getType()
                || lookAhead.getType()==TokenType.LT.getType()
                || lookAhead.getType()==TokenType.LE.getType()
                || lookAhead.getType()==TokenType.GT.getType()
                || lookAhead.getType()==TokenType.GE.getType()
                || lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()
                || lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return id;
        } else {
            throw new ParseException("Expecting loc_, found "+lookAhead);
        }
    }

    private Expr bool() {
        Expr left = join();
        return bool_(left);
    }

    private Expr bool_(Expr left) {
        if (lookAhead.getType()==TokenType.OR.getType()) {
            match(TokenType.OR);
            Expr right = join();
            Condition cond = new Condition(
                    input.getReserved(TokenType.OR.getName()), left, right);
            return bool_(cond);
        } else if (lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return left;
        } else {
            throw new ParseException("Expecting bool_, found "+lookAhead);
        }
    }

    private Expr join() {
        Expr left = equality();
        return join_(left);
    }

    private Expr join_(Expr left) {
        if (lookAhead.getType()==TokenType.AND.getType()) {
            match(TokenType.AND);
            Expr right = equality();
            Condition cond = new Condition(
                    input.getReserved(TokenType.AND.getName()), left, right);
            return equality_(cond);
        } else if (lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return left;
        } else {
            throw new ParseException("Expecting join_, found "+lookAhead);
        }
    }

    private Expr equality() {
        Expr left = rel();
        return equality_(left);
    }

    private Expr equality_(Expr left) {
        if (lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()) {
            Relation rel = new Relation(lookAhead, left, null);
            match(TokenType.getType(lookAhead.getType()));
            Expr right = rel();
            rel.setRight(right);
            return equality_(rel);
        } else if (lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return left;
        } else {
            throw new ParseException("Expecting equality_, found "+lookAhead);
        }
    }

    private Expr rel() {
        Expr left = expr();
        return rel_(left);
    }

    private Expr rel_(Expr left) {
        if (lookAhead.getType()==TokenType.LT.getType()
                || lookAhead.getType()==TokenType.LE.getType()
                || lookAhead.getType()==TokenType.GT.getType()
                || lookAhead.getType()==TokenType.GE.getType()) {
            Relation rel = new Relation(lookAhead, left, null);
            match(TokenType.getType(lookAhead.getType()));
            Expr right = expr();
            rel.setRight(right);
            return rel_(rel);
        } else if (lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()
                || lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return left;
        } else {
            throw new ParseException("Expecting rel_, found "+lookAhead);
        }
    }

    private Expr expr() {
        Expr left = term();
        return expr_(left);
    }

    private Expr expr_(Expr left) {
        if (lookAhead.getType()== TokenType.ADD.getType()
                || lookAhead.getType()== TokenType.MINUS.getType()) {
            Operation op = new Operation(lookAhead, left, null);
            match(TokenType.getType(lookAhead.getType()));
            Expr right = term();
            op.setRight(right);
            return expr_(op);
        } else if (lookAhead.getType()==TokenType.LT.getType()
                || lookAhead.getType()==TokenType.LE.getType()
                || lookAhead.getType()==TokenType.GT.getType()
                || lookAhead.getType()==TokenType.GE.getType()
                || lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()
                || lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return left;
        } else {
            throw new ParseException("Expecting expr_, found "+lookAhead);
        }
    }

    private Expr term() {
        Expr left = unary();
        return term_(left);
    }

    private Expr term_(Expr left) {
        if (lookAhead.getType()==TokenType.MULTI.getType()
                || lookAhead.getType()== TokenType.DIV.getType()) {
            Operation op = new Operation(lookAhead, left, null);
            match(TokenType.getType(lookAhead.getType()));
            Expr right = unary();
            op.setRight(right);
            return term_(op);
        } else if (lookAhead.getType()== TokenType.ADD.getType()
                || lookAhead.getType()== TokenType.MINUS.getType()
                || lookAhead.getType()==TokenType.LT.getType()
                || lookAhead.getType()==TokenType.LE.getType()
                || lookAhead.getType()==TokenType.GT.getType()
                || lookAhead.getType()==TokenType.GE.getType()
                || lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()
                || lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return left;
        } else {
            throw new ParseException("Expecting term_, found "+lookAhead);
        }
    }

    private Expr unary() {
        if (lookAhead.getType()==TokenType.NOT.getType()) {
            Not not = new Not(lookAhead, null);
            match(TokenType.getType(lookAhead.getType()));
            Expr expr = unary();
            not.setExpr(expr);
            return not;
        } else if(lookAhead.getType()==TokenType.MINUS.getType()) {
            Minus minus = new Minus(lookAhead, null);
            match(TokenType.getType(lookAhead.getType()));
            Expr expr = unary();
            minus.setExpr(expr);
            return minus;
        } else {
            return factor();
        }
    }

    private Expr factor() {
        if (lookAhead.getType()==TokenType.LPAREN.getType()) {
            match(TokenType.LPAREN);
            Expr expr = bool();
            match(TokenType.RPAREN);
            return expr;
        } else if (lookAhead.getType()==TokenType.ID.getType()) {
            return loc();
        } else if (lookAhead.getType()==TokenType.NUM.getType()) {
            Int expr = new Int(lookAhead);
            match(TokenType.getType(lookAhead.getType()));
            return expr;
        } else if (lookAhead.getType()==TokenType.REAL.getType()) {
            Float expr = new Float(lookAhead);
            match(TokenType.getType(lookAhead.getType()));
            return expr;
        } else if(lookAhead.getType()==TokenType.TRUE.getType()) {
            Bool expr = new Bool(lookAhead);
            match(TokenType.getType(lookAhead.getType()));
            return expr;
        } else if (lookAhead.getType()==TokenType.FALSE.getType()) {
            Bool expr = new Bool(lookAhead);
            match(TokenType.getType(lookAhead.getType()));
            return expr;
        } else {
            throw new ParseException("Expecting factor, found "+lookAhead);
        }
    }

    private BuiltInTypeNode basic() {
        if (lookAhead.getType()==TokenType.INT.getType()) {
            Token temp = lookAhead;
            match(TokenType.INT);
            return new BuiltInTypeNode(temp, new IntType());
        } else if (lookAhead.getType()==TokenType.FLOAT.getType()) {
            Token temp = lookAhead;
            match(TokenType.FLOAT);
            return new BuiltInTypeNode(temp, new FloatType());
        } else if (lookAhead.getType()==TokenType.BOOL.getType()) {
            Token temp = lookAhead;
            match(TokenType.BOOL);
            return new BuiltInTypeNode(temp, new BoolType());
        } else {
            throw new ParseException("Expecting type, found "+lookAhead);
        }
    }
}
