package parser;

import java.util.ArrayList;

public class SyTable {
    private ArrayList<Tuplas> tablaDeSimbolos;

    public SyTable(ArrayList<Tuplas> tablaDeSimbolos) {
        this.tablaDeSimbolos = tablaDeSimbolos;
    }

    public ArrayList<Tuplas> getTablaDeSimbolos() {
        return tablaDeSimbolos;
    }
}
