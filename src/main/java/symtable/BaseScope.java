package symtable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作用域树节点
 */
public abstract class BaseScope implements Scope {

    private Scope enclosingScope;
    Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

    public BaseScope(Scope parent) {
        this.enclosingScope = parent;
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public void define(Symbol sym) {
        symbols.put(sym.getName(), sym);
    }

    public Symbol resolve(String name) {
        Symbol result = symbols.get(name);
        if (result != null) {
            return result;
        } else if (enclosingScope!=null) {
            return enclosingScope.resolve(name);
        } else {
            return null;
        }
    }
}
