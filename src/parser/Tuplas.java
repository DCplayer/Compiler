package parser;

public class Tuplas {
    private String nombre;
    private Elemento elemento;

    public Tuplas(String nombre, Elemento elemento) {
        this.nombre = nombre;
        this.elemento = elemento;
    }

    public String getNombre() {
        return nombre;
    }

    public Elemento getElemento() {
        return elemento;
    }
}
