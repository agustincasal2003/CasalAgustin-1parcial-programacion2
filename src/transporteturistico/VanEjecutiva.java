package transporteturistico;

public class VanEjecutiva extends Vehiculo {

    private boolean aireAcondicionado;

    public VanEjecutiva(String patente, String marca, int capacidadPasajeros,
                        int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    @Override
    public String realizarServicio() {
        return "Van " + getPatente() + ": hace un traslado ejecutivo de "
                + getCapacidadPasajeros() + " pasajeros.";
    }

    @Override
    public String getTipo() {
        return "Van";
    }

    @Override
    public String toString() {
        String aire = "No";
        if (aireAcondicionado == true) {
            aire = "Sí";
        }
        return super.toString() + " | Aire: " + aire;
    }
}

