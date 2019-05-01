package parser;

import ast.AST;
import exception.ParseException;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;

public abstract class Parser {
    Lexer input;
    Token lookAhead;

    public Parser(Lexer lexer) {
        this.input = lexer;
        this.lookAhead = input.nextToken();
    }

    public void match(TokenType type) {
        if (lookAhead.getType() == type.getType()) {
            consume();
        } else {
            throw new ParseException("Expecting "+type.getName()+", found "+lookAhead);
        }
    }

    private void consume() {
        this.lookAhead = input.nextToken();
    }

    public abstract AST parse();
}
