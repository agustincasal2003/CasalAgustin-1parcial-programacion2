package transporteturistico;

// "extends Vehiculo" = el colectivo HEREDA patente, marca, capacidad y año.

public class ColectivoTuristico extends Vehiculo {

    private int cantidadPisos;

    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros,
                              int anioFabricacion, int cantidadPisos) {
        // super, le pasa los datos comunes al constructor del padre
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    // SOBRESCRITURA: la versión del colectivo del método abstracto
    @Override
    public String realizarServicio() {
        return "Colectivo " + getPatente() + ": hace un recorrido con "
                + getCapacidadPasajeros() + " pasajeros.";
    }

    @Override
    public String getTipo() {
        return "Colectivo";
    }

    // super.toString() trae el texto del padre y le sumo el dato propio
    @Override
    public String toString() {
        return super.toString() + " | Pisos: " + cantidadPisos;
    }
}