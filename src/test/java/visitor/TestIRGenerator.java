package visitor;

import ast.AST;
import ast.Block;
import lexer.FileLexer;
import lexer.Lexer;
import parser.LLParser;
import parser.Parser;

public class TestIRGenerator {
    public static void main(String[] args) {
        String fileName = "E:\\workspace\\TinyTrans\\src\\main\\resources\\test3";
        testIRGenerator(fileName);
    }

    private static void testIRGenerator(String fileName) {
        Lexer lexer = new FileLexer(fileName);
        Parser parser = new LLParser(lexer);
        AST ast = parser.parse();
        IVisitor checker = new IRGenerator();
        checker.visit((Block)ast);
    }
}
