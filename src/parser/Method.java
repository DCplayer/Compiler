package parser;

import java.util.ArrayList;
import java.util.Stack;

public class Method extends Elemento {
    private ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
    private ArrayList<ArrayList<String>> typeValue= new ArrayList<>();
    private Stack<SyTable> symbolTable;

    public Method(String name, String type, ArrayList<String> signature, ArrayList<String> returnValue, ArrayList<String> typeValue ,Stack<SyTable> symbolTable) {
        super(name, type, signature);
        this.returnValue.add(returnValue);
        this.symbolTable = symbolTable;
        this.typeValue.add(typeValue);
    }

    public ArrayList<ArrayList<String>> getReturnValue() {
        return returnValue;
    }

    public Stack<SyTable> getSymbolTable() {
        return symbolTable;
    }

    public ArrayList<ArrayList<String>> getTypeValue() {
        return typeValue;
    }
}
