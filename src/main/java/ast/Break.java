package ast;

import lexer.Token;

/**
 * break语句
 * 'break' ';'
 */
public class Break extends Statement {

    public Break(Token token) {
        super(token);
    }

}
