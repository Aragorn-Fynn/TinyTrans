package visitor;

import ast.AST;
import ast.Block;
import lexer.FileLexer;
import lexer.Lexer;
import parser.LLParser;
import parser.Parser;

public class TestSemanticChecker {
    public static void main(String[] args) {
        String fileName = "E:\\workspace\\TinyTrans\\src\\main\\resources\\test";
        testSemantic(fileName);
    }

    private static void testSemantic(String fileName) {
        Lexer lexer = new FileLexer(fileName);
        Parser parser = new LLParser(lexer);
        AST ast = parser.parse();
        IVisitor checker = new SemanticChecker();
        checker.visit((Block)ast);
    }
}
