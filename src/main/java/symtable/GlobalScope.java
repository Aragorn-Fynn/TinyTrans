package symtable;

/**
 * 全局作用域
 */
public class GlobalScope extends BaseScope {
    public GlobalScope() {
        super(null);
    }

    public String getScopeName() {
        return "global";
    }
}
