import java.util.ArrayList;
import java.util.List;

public class GestionTaller {
    private List<OrdenServicio> ordenes;

    public GestionTaller() {
        this.ordenes = new ArrayList<>();
    }

    public boolean agregarOrden(OrdenServicio orden) throws DatosInvalidosException, OrdenDuplicadaException {
        if(orden.getNombrePropietario() == null || orden.getNombrePropietario().trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del propietario no puede estar vacío");
        }
        if(orden.getPlacaVehiculo() == null || orden.getPlacaVehiculo().trim().isEmpty()) {
            throw new DatosInvalidosException("La placa del vehículo no puede estar vacía");
        }
        if (orden.getDescripcionServicio() == null || orden.getDescripcionServicio().trim().isEmpty()) {
            throw new DatosInvalidosException("La descripción del servicio no puede estar vacía");
        }
        if(orden.getCosto()<= 0) {
            throw new DatosInvalidosException("El costo debe ser mayor a 0");
        }
        for(OrdenServicio o: ordenes){
            if(o.getNumeroOrden()==orden.getNumeroOrden()){
                throw new OrdenDuplicadaException("Ya existe una orden registrada con ese número");
            }
        }
        return ordenes.add(orden);
    }

    public List<OrdenServicio> getTodasOrdenes(){
        return ordenes;
    }

    public OrdenServicio buscarPorNumero(int numeroOrden) throws OrdenNoEncontradaException{
        for(OrdenServicio o: ordenes){
            if(o.getNumeroOrden()==numeroOrden){
                return o;
            }
        }
        throw new OrdenNoEncontradaException("El número de orden no existe");
    }

    public boolean modificarOrden(int numeroOrden, String nuevaDescripcion, double nuevoCosto) throws OrdenNoEncontradaException, DatosInvalidosException{
        OrdenServicio orden= buscarPorNumero(numeroOrden);

        if(nuevaDescripcion==null || nuevaDescripcion.trim().isEmpty()){
            throw new DatosInvalidosException("La descripción no puede estar vacía");
        }
        if(nuevoCosto<= 0){
            throw new DatosInvalidosException("El costo debe ser mayor a cero");
        }

        orden.setDescripcionServicio((nuevaDescripcion));
        orden.setCosto(nuevoCosto);
        return true;
    }

    public boolean cancelarOrden(int numeroOrden)throws OrdenNoEncontradaException{
        OrdenServicio orden=buscarPorNumero(numeroOrden);
        return ordenes.remove(orden);
    }

    public List<OrdenServicio> buscarPorPlaca(String placaVehiculo){
        List<OrdenServicio> resultado=new ArrayList<>();
        for(OrdenServicio o: ordenes){
            if(o.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)){
                resultado.add(o);
            }
        }
        return resultado;
    }

    public double calcularCostoTotal(){
        double total=0;
        for(OrdenServicio o: ordenes){
            total+=o.getCosto();
        }
        return total;
    }

    public double calcularCostoPromedio(){
        if(ordenes.isEmpty()){
            return 0;
        }
        return calcularCostoTotal()/ordenes.size();
    }

    public OrdenServicio getOrdenCostoMasAlto()throws OrdenNoEncontradaException{
        if(ordenes.isEmpty()){
            throw new OrdenNoEncontradaException("No hay ordenes registradas");
        }
        OrdenServicio mayor=ordenes.get(0);
        for(OrdenServicio o: ordenes){
            if(o.getCosto()>mayor.getCosto()){
                mayor=o;
            }
        }
        return mayor;
    }

    public int getCantidadOrdenes(){
        return ordenes.size();
    }

    public static class OrdenNoEncontradaException extends Exception{
        public OrdenNoEncontradaException(String mensaje){
            super(mensaje);
        }
    }

    public static class DatosInvalidosException extends Exception{
        public DatosInvalidosException(String mensaje){
            super(mensaje);
        }
    }

    public static class OrdenDuplicadaException extends Exception{
        public OrdenDuplicadaException(String mensaje){
            super(mensaje);
        }
    }
}