package transporteturistico;

// CLASE ABSTRACTA: es el molde común de todos los vehículos.
// No se puede hacer "new Vehiculo(...)". Solo se crean sus hijos.
public abstract class Vehiculo {

    // private = solo se pueden usar dentro de esta clase (encapsulamiento)
    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;

    // Constructor: se ejecuta al crear un objeto y guarda los datos
    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion) {
        this.patente = patente;
        this.marca = marca;
        this.capacidadPasajeros = capacidadPasajeros;
        this.anioFabricacion = anioFabricacion;
    }

    // Getters: como los atributos son private, así los leemos desde afuera
    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    // MÉTODOS ABSTRACTOS: no tienen código acá.
    // Cada hijo está obligado a escribir su propia versión.
    public abstract String realizarServicio();

    public abstract String getTipo();

    // Texto que se muestra al imprimir el vehículo
    @Override
    public String toString() {
        return getTipo() + " | Patente: " + patente
                + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Año: " + anioFabricacion;
    }
}
