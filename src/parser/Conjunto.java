package parser;

import java.util.ArrayList;

/**
 * Created by Christian12 on 16/04/2018.
 */
public class Conjunto extends Elemento{

    private int cantElementos;
    private ArrayList<Object> contenido = new ArrayList<>();
    private boolean isStruct;
    private String tipoStruct;

    public Conjunto(String name, String type, ArrayList<String> signature, int cantElementos,
                    boolean isStruct, String tipoStruct, boolean symbol, boolean method, boolean conjunto) {
        super(name, type, signature, symbol, method, conjunto);
        this.cantElementos = cantElementos;
        this.isStruct = isStruct;
        this.tipoStruct = tipoStruct;

    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void addElement(Object value){
        contenido.add(value);
    }

    public ArrayList<Object> getContenido() {
        return contenido;
    }

    public boolean isStruct() {
        return isStruct;
    }

    public String getTipoStruct() {
        return tipoStruct;
    }

    public void setTipoStruct(String tipoStruct) {
        this.tipoStruct = tipoStruct;
    }
}
