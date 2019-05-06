package symtable;

/**
 * 符号表
 */
public class SymTable {
    private GlobalScope globals = new GlobalScope();

    public GlobalScope getGlobals() {
        return globals;
    }

    public void setGlobals(GlobalScope globals) {
        this.globals = globals;
    }
}
