package transporteturistico;

public class LanchaTuristica extends Vehiculo {

    // Solo puede valer "NAFTA", "DIESEL" o "ELECTRICO" (se controla en Main)
    private String tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros,
                           int anioFabricacion, String tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = tipoMotor;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    @Override
    public String realizarServicio() {
        return "Lancha " + getPatente() + ": hace un paseo náutico con motor "
                + tipoMotor + " para " + getCapacidadPasajeros() + " pasajeros.";
    }

    @Override
    public String getTipo() {
        return "Lancha";
    }

    @Override
    public String toString() {
        return super.toString() + " | Motor: " + tipoMotor;
    }
}
