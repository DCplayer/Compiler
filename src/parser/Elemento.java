package parser;

import java.util.ArrayList;

public class Elemento {
    private String name;
    private String type;
    private String value;
    private ArrayList<ArrayList<String>> signature = new ArrayList<>();
    private boolean isMethod;
    private boolean isConjunto;
    private boolean isSymbol;
    private String temporal;

    public Elemento(String name, String type, ArrayList<String> signature, boolean symbol, boolean method, boolean conjunto) {
        this.name = name;
        this.type = type;
        this.signature.add(signature);
        isSymbol = symbol;
        isMethod = method;
        isConjunto = conjunto;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ArrayList<String>> getSignature() {
        return signature;
    }

    public String getType() {
        return type;
    }

    public boolean isMethod() {
        return isMethod;
    }

    public boolean isConjunto() {
        return isConjunto;
    }

    public boolean isSymbol() {
        return isSymbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTemporal() {
        return temporal;
    }

    public void setTemporal(String temporal) {
        this.temporal = temporal;
    }
}
