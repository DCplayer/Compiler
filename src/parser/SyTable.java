package parser;

import java.util.ArrayList;

public class SyTable {
    private ArrayList<Tuplas> tablaDeSimbolos;

    public SyTable(ArrayList<Tuplas> tablaDeSimbolos) {
        this.tablaDeSimbolos = new ArrayList<Tuplas>();
    }

    public ArrayList<Tuplas> getTablaDeSimbolos() {
        return tablaDeSimbolos;
    }
}
