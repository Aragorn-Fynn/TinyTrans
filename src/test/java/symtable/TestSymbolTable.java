package symtable;

import ast.AST;
import ast.Block;
import lexer.FileLexer;
import lexer.Lexer;
import parser.LLParser;
import parser.Parser;
import visitor.IVisitor;
import visitor.SemanticChecker;

public class TestSymbolTable {
    public static void main(String[] args) {
        String fileName = "E:\\workspace\\TinyTrans\\src\\main\\resources\\test";
        testParser(fileName);
    }

    private static void testParser(String fileName) {
        Lexer lexer = new FileLexer(fileName);
        Parser parser = new LLParser(lexer);
        AST ast = parser.parse();
        IVisitor checker = new SemanticChecker();
        checker.visit((Block)ast);
    }
}
