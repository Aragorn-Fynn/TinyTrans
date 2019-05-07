package symtable;

/**
 * 符号表
 */
public class SymTable {

    private static GlobalScope globals = new GlobalScope();

    private Scope currentScope;

    public SymTable() {
        currentScope = globals;
        initBuiltInTypes();
    }

    private void initBuiltInTypes() {
        globals.define(new IntType());
        globals.define(new FloatType());
        globals.define(new BoolType());
    }

    /**
     * 解析符号
     * @param name
     * @return
     */
    public Symbol resolve(String name) {
        return currentScope.resolve(name);
    }

    /**
     * 定义符号
     * @param symbol
     */
    public void define(Symbol symbol) {
        currentScope.define(symbol);
    }

    /**
     * 将新的作用域压栈
     * @param scope
     */
    public void push(Scope scope) {
        currentScope = scope;
    }

    /**
     * 将当前作用域弹出栈
     */
    public void pop() {
        currentScope = currentScope.getEnclosingScope();
    }

    /**
     * 获取当前作用域
     * @return
     */
    public Scope getCurrentScope() {
        return currentScope;
    }

    public void setCurrentScope(Scope currentScope) {
        this.currentScope = currentScope;
    }

    public GlobalScope getGlobalScope() {
        return globals;
    }

}
