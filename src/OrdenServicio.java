public class OrdenServicio {
    private int numeroOrden; 
    private String nombrePropietario;
    private String placaVehiculo;
    private String descripcionServicio;
    private double costo;
    
    public OrdenServicio(int numeroOrden, String nombrePropietario, String placaVehiculo, String descripcionServicio, double costo){
    this.numeroOrden = numeroOrden;
        this.nombrePropietario = nombrePropietario;
        this.placaVehiculo = placaVehiculo;
        this.descripcionServicio = descripcionServicio;
        this.costo = costo;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }
 
    public String getNombrePropietario() {
        return nombrePropietario;
    }
 
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
 
    public String getDescripcionServicio() {
        return descripcionServicio;
    }
 
    public double getCosto() {
        return costo;
    }
    
    public void setDescripcionServicio(String descripcionServicio){
        this.descripcionServicio=descripcionServicio;
    }

    public void setCosto(double costo){
        this.costo=costo;
    }

    @Override
    public String toString() {
        return "Orden: " + numeroOrden + "  Propietario: " + nombrePropietario
                + "  Placa: " + placaVehiculo + "  Servicio: " + descripcionServicio
                + "  Costo: " + costo;
    }

}


