package transporteturistico;

// CLASE ABSTRACTA: es lo que tienen en comun de todos los vehículos.

public abstract class Vehiculo {

    // private = solo se pueden usar dentro de esta clase (encapsulamiento)
    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;

    // setters
    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion) {
        this.patente = patente;
        this.marca = marca;
        this.capacidadPasajeros = capacidadPasajeros;
        this.anioFabricacion = anioFabricacion;
    }

    // Getters
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

    
    public abstract String realizarServicio();

    public abstract String getTipo();

    //se muestra al imprimir el vehículo
    @Override
    public String toString() {
        return getTipo() + " | Patente: " + patente
                + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Año: " + anioFabricacion;
    }
}
