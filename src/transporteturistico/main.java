package transporteturistico;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    
    private static Scanner sc = new Scanner(System.in);
    private static GestorVehiculos gestor = new GestorVehiculos();

    private static final int ANIO_MINIMO = 1990;
    private static final int ANIO_ACTUAL = 2026;

    
    public static void main(String[] args) {

        int opcion = 0;

        while (opcion != 8) {
            mostrarMenu();
            opcion = leerNumero("Elija una opción: ");

            if (opcion == 1) {
                agregarVehiculo();
            } else if (opcion == 2) {
                mostrarLista(gestor.getVehiculos(), "TODOS LOS VEHÍCULOS");
            } else if (opcion == 3) {
                realizarServicios();
            } else if (opcion == 4) {
                buscarVehiculo();
            } else if (opcion == 5) {
                mostrarCapacidadMayor();
            } else if (opcion == 6) {
                mostrarLista(gestor.ordenarPorAnioDescendente(), "ORDENADOS POR AÑO (DESCENDENTE)");
            } else if (opcion == 7) {
                mostrarLista(gestor.ordenarPorCapacidadDescendente(), "ORDENADOS POR CAPACIDAD (DESCENDENTE)");
            } else if (opcion == 8) {
                System.out.println("Hasta luego!");
            } else {
                System.out.println("Opción inválida. Elija un número del 1 al 8.");
            }
        }
    }

    // ============================================================
    // MENÚ
    // ============================================================
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== TRANSPORTE TURÍSTICO =====");
        System.out.println("1. Agregar vehículo");
        System.out.println("2. Mostrar todos los vehículos");
        System.out.println("3. Realizar servicios turísticos");
        System.out.println("4. Buscar vehículo por patente");
        System.out.println("5. Mostrar vehículos con capacidad mayor a un valor");
        System.out.println("6. Mostrar vehículos ordenados por año (descendente)");
        System.out.println("7. Mostrar vehículos ordenados por capacidad (descendente)");
        System.out.println("8. Salir");
    }

    // ============================================================
    // OPCIÓN 1: AGREGAR VEHÍCULO
    // ============================================================
    private static void agregarVehiculo() {
        System.out.println();
        System.out.println("--- Agregar vehículo ---");

       
        int tipo = pedirTipo();
        String patente = pedirPatente();
        String marca = pedirMarca();
        int capacidad = pedirCapacidad();
        int anio = pedirAnio();

        // 
        // "nuevo" es de tipo Vehiculo (el padre), pero guarda un hijo.
        Vehiculo nuevo;

        if (tipo == 1) {
            int pisos = pedirPisos();
            nuevo = new ColectivoTuristico(patente, marca, capacidad, anio, pisos);
        } else if (tipo == 2) {
            boolean aire = pedirAire();
            nuevo = new VanEjecutiva(patente, marca, capacidad, anio, aire);
        } else {
            String motor = pedirMotor();
            nuevo = new LanchaTuristica(patente, marca, capacidad, anio, motor);
        }

        boolean agregado = gestor.agregarVehiculo(nuevo);

        if (agregado == true) {
            System.out.println("Vehículo agregado correctamente.");
        } else {
            System.out.println("Error: ya existe un vehículo con esa patente.");
        }
    }

    // ##################################################################
    // OPCIÓN 3: REALIZAR SERVICIOS  (POLIMORFISMO)
    // ============================================================
    private static void realizarServicios() {
        ArrayList<Vehiculo> lista = gestor.getVehiculos();

        if (lista.size() == 0) {
            System.out.println("No hay vehículos cargados.");
            return;
        }

        System.out.println();
        System.out.println("--- Servicios turísticos ---");

        for (int i = 0; i < lista.size(); i++) {
            Vehiculo v = lista.get(i);
            // v es un Vehiculo, pero según sea colectivo, van o lancha,
            
            System.out.println(v.realizarServicio());
        }
    }

    // ============================================================
    // OPCIÓN 4: BUSCAR POR PATENTE
    // ============================================================
    private static void buscarVehiculo() {
        String patente = "";
        boolean ok = false;

        // Pido hasta que escriba algo
        while (ok == false) {
            System.out.print("Ingrese la patente a buscar: ");
            patente = sc.nextLine();

            if (patente.trim().isEmpty()) {
                System.out.println("Error: la patente no puede estar vacía.");
            } else {
                ok = true;
            }
        }

        Vehiculo encontrado = gestor.buscarPorPatente(patente);

        if (encontrado == null) {
            System.out.println("No se encontró ningún vehículo con esa patente.");
        } else {
            System.out.println("Vehículo encontrado:");
            System.out.println(encontrado);
        }
    }

    // ============================================================
    // OPCIÓN 5: CAPACIDAD MAYOR A UN VALOR
    // ============================================================
    private static void mostrarCapacidadMayor() {
        int valor = leerNumero("Mostrar vehículos con capacidad mayor a: ");
        ArrayList<Vehiculo> resultado = gestor.filtrarPorCapacidadMayor(valor);
        mostrarLista(resultado, "CAPACIDAD MAYOR A " + valor);
    }

    // ============================================================
    // MOSTRAR UNA LISTA (la usan las opciones 2, 5, 6 y 7)
    // ============================================================
    private static void mostrarLista(ArrayList<Vehiculo> lista, String titulo) {
        System.out.println();
        System.out.println("--- " + titulo + " ---");

        if (lista.size() == 0) {
            System.out.println("No hay vehículos para mostrar.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }
        }
    }

    // ============================================================
    // LEER UN NÚMERO
    // el TRY-CATCH obligatorio
    
    // Si el usuario escribe letras, Integer.parseInt falla y lanza
    // NumberFormatException. El catch la atrapa, avisa, y el while
    // vuelve a pedir el número. El programa NO se cierra.
    
    // ESTO FUE CON LA IA
    // ============================================================
    private static int leerNumero(String mensaje) {
        int numero = 0;
        boolean ok = false;

        while (ok == false) {
            System.out.print(mensaje);
            String texto = sc.nextLine();

            try {
                numero = Integer.parseInt(texto);
                ok = true;   // si llegó acá, el número era válido
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero. Intente de nuevo.");
            }
        }
        return numero;
    }

    // ---------------------------------------------------------------
    // validaciones
    // --------------------------------------------------------------
    private static int pedirTipo() {
        int tipo = 0;
        boolean ok = false;

        while (ok == false) {
            System.out.println("Tipo de vehículo: 1) Colectivo  2) Van  3) Lancha");
            tipo = leerNumero("Elija el tipo: ");

            if (tipo >= 1 && tipo <= 3) {
                ok = true;
            } else {
                System.out.println("Error: el tipo debe ser 1, 2 o 3.");
            }
        }
        return tipo;
    }

    private static String pedirPatente() {
        String patente = "";
        boolean ok = false;

        while (ok == false) {
            System.out.print("Patente: ");
            patente = sc.nextLine();

            if (patente.trim().isEmpty()) {
                System.out.println("Error: la patente no puede estar vacía.");
            } else if (gestor.buscarPorPatente(patente) != null) {
                System.out.println("Error: ya existe un vehículo con esa patente.");
            } else {
                ok = true;
            }
        }
        return patente.trim();
    }

    private static String pedirMarca() {
        String marca = "";
        boolean ok = false;

        while (ok == false) {
            System.out.print("Marca: ");
            marca = sc.nextLine();

            if (marca.trim().isEmpty()) {
                System.out.println("Error: la marca no puede estar vacía.");
            } else {
                ok = true;
            }
        }
        return marca.trim();
    }

    private static int pedirCapacidad() {
        int capacidad = 0;
        boolean ok = false;

        while (ok == false) {
            capacidad = leerNumero("Capacidad de pasajeros: ");

            if (capacidad <= 0) {
                System.out.println("Error: la capacidad debe ser mayor a cero.");
            } else {
                ok = true;
            }
        }
        return capacidad;
    }

    private static int pedirAnio() {
        int anio = 0;
        boolean ok = false;

        while (ok == false) {
            anio = leerNumero("Año de fabricación: ");

            if (anio < ANIO_MINIMO) {
                System.out.println("Error: el año no puede ser menor a " + ANIO_MINIMO + ".");
            } else if (anio > ANIO_ACTUAL) {
                System.out.println("Error: el año no puede ser mayor a " + ANIO_ACTUAL + ".");
            } else {
                ok = true;
            }
        }
        return anio;
    }

    private static int pedirPisos() {
        int pisos = 0;
        boolean ok = false;

        while (ok == false) {
            pisos = leerNumero("Cantidad de pisos (1 o 2): ");

            if (pisos == 1 || pisos == 2) {
                ok = true;
            } else {
                System.out.println("Error: la cantidad de pisos debe ser 1 o 2.");
            }
        }
        return pisos;
    }

    private static boolean pedirAire() {
        boolean aire = false;
        boolean ok = false;

        while (ok == false) {
            System.out.print("¿Tiene aire acondicionado? (S/N): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                aire = true;
                ok = true;
            } else if (respuesta.equalsIgnoreCase("N")) {
                aire = false;
                ok = true;
            } else {
                System.out.println("Error: responda S o N.");
            }
        }
        return aire;
    }

    private static String pedirMotor() {
        String motor = "";
        boolean ok = false;

        while (ok == false) {
            System.out.print("Tipo de motor (NAFTA, DIESEL, ELECTRICO): ");
            motor = sc.nextLine().trim(); // EL .TRIM  Elimina los espacios en blanco

            if (motor.equalsIgnoreCase("NAFTA")
                    || motor.equalsIgnoreCase("DIESEL")
                    || motor.equalsIgnoreCase("ELECTRICO")) {
                ok = true;
            } else {
                System.out.println("Error: motor inválido. Use NAFTA, DIESEL o ELECTRICO.");
            }
        }
        return motor.toUpperCase(); // el toUPPER CONVIERTE TODO A MAYUSCULA
    }
}

// no hay vehiculos registrados, se hace manual....