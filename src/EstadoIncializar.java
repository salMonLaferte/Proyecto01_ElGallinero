public class EstadoIncializar implements TiendaEstado{
    Tienda tienda;

    EstadoIncializar(Tienda tienda){
        this.tienda = tienda;
    }

    @Override
    public void start() {
        Cliente.leerClientes();
        Ofertas.generaOfertas();
    }
    
}
