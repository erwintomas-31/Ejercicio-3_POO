import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestionTaller gestionTaller = new GestionTaller();
    private static OrdenServicio ordenServicio;

    public static void main(String[] args) {
        mostrarMenu();
        scanner.close();
    }

    public static void mostrarMenu(){
       boolean salir = false;
 
        while (!salir) {
            System.out.println("1. Registrar orden");
            System.out.println("2. Consultar órdenes");
            System.out.println("3. Buscar orden");
            System.out.println("4. Modificar orden");
            System.out.println("5. Cancelar orden");
            System.out.println("6. Consultar órdenes por placa");
            System.out.println("7. Reporte de costos");
            System.out.println("8. Orden de mayor costo");
            System.out.println("9. Cantidad de órdenes");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
 
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número de opción válido");
                continue;
            }
 
            switch (opcion) {
                case 1:
                    registrarOrden();
                    break;
                case 2:
                    consultarOrdenes();
                    break;
                case 3:
                    buscarOrden();
                    break;
                case 4:
                    modificarOrden();
                    break;
                case 5:
                    cancelarOrden();
                    break;
                case 6:
                    consultarPorPlaca();
                    break;
                case 7:
                    reporteDeCostos();
                    break;
                case 8:
                    ordenMayorCosto();
                    break;
                case 9:
                    cantidadDeOrdenes();
                    break;
                case 10:
                    salir = true;
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente");
            }
        }
    } 

    public static void registrarOrden(){
        try{
            System.out.print("Número de orden: ");
            int numeroOrden = Integer.parseInt(scanner.nextLine().trim());
 
            System.out.print("Nombre del propietario: ");
            String nombrePropietario = scanner.nextLine().trim();
 
            System.out.print("Placa del vehículo: ");
            String placaVehiculo = scanner.nextLine().trim();
 
            System.out.print("Descripción del servicio: ");
            String descripcionServicio = scanner.nextLine().trim();
 
            System.out.print("Costo estimado: ");
            double costoEstimado = Double.parseDouble(scanner.nextLine().trim());
 
            ordenServicio = new OrdenServicio(numeroOrden, nombrePropietario, placaVehiculo,
                    descripcionServicio, costoEstimado);
 
            gestionTaller.agregarOrden(ordenServicio);
            System.out.println("Orden registrada exitosamente");
        }
        catch (NumberFormatException e) {
            System.out.println("El número de orden y el costo deben ser valores numéricos");
        }
        catch (GestionTaller.DatosInvalidosException | GestionTaller.OrdenDuplicadaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally{
            System.out.println("Registro finalizado");
        }
    }
    
    private static void consultarOrdenes() {
        List<OrdenServicio> ordenes = gestionTaller.getTodasOrdenes();
        if (ordenes.isEmpty()) {
            System.out.println("No hay órdenes registradas");
        } else {
            System.out.println("Órdenes registradas: ");
            for (OrdenServicio o : ordenes) {
                System.out.println(o);
            }
        }
    }

    private static void buscarOrden() {
        try {
            System.out.print("Número de orden a buscar: ");
            int numeroOrden = Integer.parseInt(scanner.nextLine().trim());
 
            OrdenServicio orden = gestionTaller.buscarPorNumero(numeroOrden);
            System.out.println("Orden encontrada: ");
            System.out.println(orden);
 
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar un número de orden válido");
        } catch (GestionTaller.OrdenNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Proceso de búsqueda finalizado");
        }
    }

    private static void modificarOrden() {
        try {
            System.out.print("Número de orden a modificar: ");
            int numeroOrden = Integer.parseInt(scanner.nextLine().trim());
 
            System.out.print("Nueva descripción del servicio: ");
            String nuevaDescripcion = scanner.nextLine().trim();
 
            System.out.print("Nuevo costo estimado: ");
            double nuevoCosto = Double.parseDouble(scanner.nextLine().trim());
 
            gestionTaller.modificarOrden(numeroOrden, nuevaDescripcion, nuevoCosto);
            System.out.println("Orden modificada exitosamente");
 
        } catch (NumberFormatException e) {
            System.out.println("Error: el número de orden y el costo deben ser valores numéricos");
        } catch (GestionTaller.OrdenNoEncontradaException | GestionTaller.DatosInvalidosException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Proceso de modificación finalizado");
        }
    }

    private static void cancelarOrden() {
        try {
            System.out.print("Número de orden a cancelar: ");
            int numeroOrden = Integer.parseInt(scanner.nextLine().trim());
 
            gestionTaller.cancelarOrden(numeroOrden);
            System.out.println("Orden cancelada exitosamente");
 
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar un número de orden válido");
        } catch (GestionTaller.OrdenNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Proceso de cancelación finalizado");
        }
    }

    private static void consultarPorPlaca() {
        System.out.print("Placa del vehículo a consultar: ");
        String placa = scanner.nextLine().trim();
 
        List<OrdenServicio> ordenes = gestionTaller.buscarPorPlaca(placa);
        if (ordenes.isEmpty()) {
            System.out.println("No se encontraron órdenes para la placa " + placa);
        } else {
            System.out.println("Órdenes encontradas para la placa " + placa);
            for (OrdenServicio o : ordenes) {
                System.out.println(o);
            }
        }
    }
 
    private static void reporteDeCostos() {
        double total = gestionTaller.calcularCostoTotal();
        double promedio = gestionTaller.calcularCostoPromedio();
        System.out.println("Costo total de las órdenes activas: " + total);
        System.out.println("Costo promedio de las órdenes activas: " + promedio);
    }
 
    private static void ordenMayorCosto() {
        try {
            OrdenServicio orden = gestionTaller.getOrdenCostoMasAlto();
            System.out.println("Orden con el costo estimado más alto:");
            System.out.println(orden);
        } catch (GestionTaller.OrdenNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Consulta de mayor costo finalizada ");
        }
    }

    private static void cantidadDeOrdenes() {
        System.out.println("Cantidad de órdenes registradas: " + gestionTaller.getCantidadOrdenes());
    }

}