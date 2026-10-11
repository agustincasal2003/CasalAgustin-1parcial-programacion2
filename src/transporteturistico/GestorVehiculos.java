package transporteturistico;

import java.util.ArrayList;

// Esta clase guarda los vehículos en un ArrayList
// agrega, busca, filtra y ordena

public class GestorVehiculos {

    private ArrayList<Vehiculo> vehiculos;

    // Constructor: crea la lista vacía
    public GestorVehiculos() {
        vehiculos = new ArrayList<Vehiculo>();
    }

    // Devuelve la lista completa (Main la usa para mostrar y para los servicios)
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    // ------------------------------------------------------------
    // AGREGAR
    // Devuelve true si lo agregó, false si la patente ya existía
    // ------------------------------------------------------------
    public boolean agregarVehiculo(Vehiculo v) {
        Vehiculo repetido = buscarPorPatente(v.getPatente());

        if (repetido != null) {
            return false;       // ya existe: no se agrega
        }

        vehiculos.add(v);
        return true;
    }

    // ------------------------------------------------------------
    // BÚSQUEDA MANUAL
    // Recorre la lista de a uno. Si encuentra la patente devuelve
    // ese vehículo. Si termina el for sin encontrarlo devuelve null
    // ------------------------------------------------------------
    public Vehiculo buscarPorPatente(String patente) {
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo actual = vehiculos.get(i);

            if (actual.getPatente().equalsIgnoreCase(patente.trim())) {
                return actual;
            }
        }
        return null;
    }

    // ------------------------------------------------------------
    // FILTRAR: arma una lista nueva solo con los que cumplen
    // ------------------------------------------------------------
    public ArrayList<Vehiculo> filtrarPorCapacidadMayor(int valor) {
        ArrayList<Vehiculo> resultado = new ArrayList<Vehiculo>();

        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo actual = vehiculos.get(i);

            if (actual.getCapacidadPasajeros() > valor) {
                resultado.add(actual);
            }
        }
        return resultado;
    }

    // ------------------------------------------------------------
    // ORDENAMIENTO BURBUJA 
    // Se compara cada vehículo con el siguiente. Si están en el
    // orden equivocado, se intercambian.
    // ------------------------------------------------------------
    public ArrayList<Vehiculo> ordenarPorAnioDescendente() {
        ArrayList<Vehiculo> copia = copiarLista();

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {

                Vehiculo actual = copia.get(j);
                Vehiculo siguiente = copia.get(j + 1);

                // Descendente: si el actual es más viejo que el siguiente, se cambian
                if (actual.getAnioFabricacion() < siguiente.getAnioFabricacion()) {
                    copia.set(j, siguiente);
                    copia.set(j + 1, actual);
                }
            }
        }
        return copia;
    }

    public ArrayList<Vehiculo> ordenarPorCapacidadDescendente() {
        ArrayList<Vehiculo> copia = copiarLista();

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {

                Vehiculo actual = copia.get(j);
                Vehiculo siguiente = copia.get(j + 1);

                if (actual.getCapacidadPasajeros() < siguiente.getCapacidadPasajeros()) {
                    copia.set(j, siguiente);
                    copia.set(j + 1, actual);
                }
            }
        }
        return copia;
    }

    // Copia la lista elemento por elemento
    private ArrayList<Vehiculo> copiarLista() {
        ArrayList<Vehiculo> copia = new ArrayList<Vehiculo>();

        for (int i = 0; i < vehiculos.size(); i++) {
            copia.add(vehiculos.get(i));
        }
        return copia;
    }
}