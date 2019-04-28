package parser;

import exception.ParseException;
import lexer.Lexer;
import lexer.TokenType;

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
    public void parse() {
        program();
        match(TokenType.EOF);
        System.out.println("Syntax correct!");
    }

    /**
     * 程序
     */
    private void program() {
        block();
    }

    /**
     * 块
     */
    private void block() {
        match(TokenType.LBRACE);
        decls();
        stmts();
        match(TokenType.RBRACE);
    }

    /**
     * 声明语句集合
     */
    private void decls() {
        decl_();
    }

    private void decl_() {
        if (lookAhead.getType()==TokenType.INT.getType()
                || lookAhead.getType()==TokenType.FLOAT.getType()
                || lookAhead.getType()==TokenType.BOOL.getType()) {
            decl();
            decl_();
        } else if (lookAhead.getType()==TokenType.ID.getType()
                || lookAhead.getType()==TokenType.IF.getType()
                || lookAhead.getType()==TokenType.WHILE.getType()
                || lookAhead.getType()==TokenType.DO.getType()
                || lookAhead.getType()==TokenType.BREAK.getType()
                || lookAhead.getType()==TokenType.LBRACE.getType()) {
            return;
        } else {
            throw new ParseException("Expecting decl_, found "+lookAhead);
        }
    }

    /**
     * 声明语句
     */
    private void decl() {
        type();
        match(TokenType.ID);
        match(TokenType.SEMI);
    }

    /**
     * 基本类型
     */
    private void type() {
        basic();
        type_();
    }

    private void type_() {
        if (lookAhead.getType()==TokenType.LBRACK.getType()) {
            match(TokenType.LBRACK);
            match(TokenType.NUM);
            match(TokenType.RBRACK);
            type_();
        } else if (lookAhead.getType()==TokenType.ID.getType()) {
            return;
        } else {
            throw new ParseException("Expecting type_, found "+lookAhead);
        }
    }

    /**
     * 语句集合
     */
    private void stmts() {
        stmts_();
    }

    private void stmts_() {
        if (lookAhead.getType()==TokenType.ID.getType()
                || lookAhead.getType()==TokenType.IF.getType()
                || lookAhead.getType()==TokenType.WHILE.getType()
                || lookAhead.getType()==TokenType.DO.getType()
                || lookAhead.getType()==TokenType.BREAK.getType()
                || lookAhead.getType()==TokenType.LBRACE.getType()) {
            stmt();
            stmts_();
        } else if (lookAhead.getType()==TokenType.RBRACE.getType()) {
            return;
        } else {
            throw new ParseException("Expecting stmts_, found "+lookAhead);
        }
    }

    /**
     * 语句
     */
    private void stmt() {
        if (lookAhead.getType()==TokenType.ID.getType()) {
            loc();
            match(TokenType.ASSIGN);
            bool();
            match(TokenType.SEMI);
        } else if (lookAhead.getType()==TokenType.IF.getType()) {
            ifelse();
        } else if (lookAhead.getType()==TokenType.WHILE.getType()) {
            whileStmt();
        } else if (lookAhead.getType()==TokenType.DO.getType()) {
            doWhile();
        } else if (lookAhead.getType()==TokenType.BREAK.getType()) {
            match(TokenType.BREAK);
            match(TokenType.SEMI);
        } else if (lookAhead.getType()==TokenType.LBRACE.getType()) {
            block();
        } else {
            throw new ParseException("Expecting statement, found "+lookAhead);
        }
    }

    /**
     * if else 语句
     */
    private void ifelse() {
        match(TokenType.IF);
        match(TokenType.LPAREN);
        bool();
        match(TokenType.RPAREN);
        stmt();
        if (lookAhead.getType()== TokenType.ELSE.getType()) {
            match(TokenType.ELSE);
            stmt();
        }
    }

    /**
     * while语句
     */
    private void whileStmt() {
        match(TokenType.WHILE);
        match(TokenType.LPAREN);
        bool();
        match(TokenType.RPAREN);
        stmt();
    }

    /**
     * do while语句
     */
    private void doWhile() {
        match(TokenType.DO);
        stmt();
        match(TokenType.WHILE);
        match(TokenType.LPAREN);
        bool();
        match(TokenType.RPAREN);
        match(TokenType.SEMI);
    }

    private void loc() {
        match(TokenType.ID);
        loc_();
    }

    private void loc_() {
        if (lookAhead.getType()==TokenType.LBRACK.getType()) {
            match(TokenType.LBRACK);
            bool();
            match(TokenType.RBRACK);
            loc_();
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
            return;
        } else {
            throw new ParseException("Expecting loc_, found "+lookAhead);
        }
    }

    private void bool() {
        join();
        bool_();
    }

    private void bool_() {
        if (lookAhead.getType()==TokenType.OR.getType()) {
            match(TokenType.OR);
            join();
            bool_();
        } else if (lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return;
        } else {
            throw new ParseException("Expecting bool_, found "+lookAhead);
        }
    }

    private void join() {
        equality();
        join_();
    }

    private void join_() {
        if (lookAhead.getType()==TokenType.AND.getType()) {
            match(TokenType.AND);
            equality();
            equality_();
        } else if (lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return;
        } else {
            throw new ParseException("Expecting join_, found "+lookAhead);
        }
    }

    private void equality() {
        rel();
        equality_();
    }

    private void equality_() {
        if (lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()) {
            match(TokenType.getType(lookAhead.getType()));
            rel();
            equality_();
        } else if (lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return;
        } else {
            throw new ParseException("Expecting equality_, found "+lookAhead);
        }
    }

    private void rel() {
        expr();
        rel_();
    }

    private void rel_() {
        if (lookAhead.getType()==TokenType.LT.getType()
                || lookAhead.getType()==TokenType.LE.getType()
                || lookAhead.getType()==TokenType.GT.getType()
                || lookAhead.getType()==TokenType.GE.getType()) {
            match(TokenType.getType(lookAhead.getType()));
            expr();
            rel_();
        } else if (lookAhead.getType()==TokenType.EQ.getType()
                || lookAhead.getType()==TokenType.NE.getType()
                || lookAhead.getType()==TokenType.AND.getType()
                || lookAhead.getType()==TokenType.OR.getType()
                || lookAhead.getType()==TokenType.SEMI.getType()
                || lookAhead.getType()==TokenType.RPAREN.getType()
                || lookAhead.getType()==TokenType.RBRACK.getType()) {
            return;
        } else {
            throw new ParseException("Expecting rel_, found "+lookAhead);
        }
    }

    private void expr() {
        term();
        expr_();
    }

    private void expr_() {
        if (lookAhead.getType()== TokenType.ADD.getType()
                || lookAhead.getType()== TokenType.MINUS.getType()) {
            match(TokenType.getType(lookAhead.getType()));
            term();
            expr_();
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
            return;
        } else {
            throw new ParseException("Expecting expr_, found "+lookAhead);
        }
    }

    private void term() {
        unary();
        term_();
    }

    private void term_() {
        if (lookAhead.getType()==TokenType.MULTI.getType()
                || lookAhead.getType()== TokenType.DIV.getType()) {
            match(TokenType.getType(lookAhead.getType()));
            unary();
            term_();
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
            return;
        } else {
            throw new ParseException("Expecting term_, found "+lookAhead);
        }
    }

    private void unary() {
        if (lookAhead.getType()==TokenType.NOT.getType()
                || lookAhead.getType()==TokenType.MINUS.getType()) {
            match(TokenType.getType(lookAhead.getType()));
            unary();
        } else {
            factor();
        }
    }

    private void factor() {
        if (lookAhead.getType()==TokenType.LPAREN.getType()) {
            match(TokenType.LPAREN);
            bool();
            match(TokenType.RPAREN);
        } else if (lookAhead.getType()==TokenType.ID.getType()) {
            loc();
        } else if (lookAhead.getType()==TokenType.NUM.getType()
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
            match(TokenType.getType(lookAhead.getType()));
        } else {
            throw new ParseException("Expecting factor, found "+lookAhead);
        }
    }

    private void basic() {
        if (lookAhead.getType()==TokenType.INT.getType()) {
            match(TokenType.INT);
        } else if (lookAhead.getType()==TokenType.FLOAT.getType()) {
            match(TokenType.FLOAT);
        } else if (lookAhead.getType()==TokenType.BOOL.getType()) {
            match(TokenType.BOOL);
        } else {
            throw new ParseException("Expecting type, found "+lookAhead);
        }
    }
}
