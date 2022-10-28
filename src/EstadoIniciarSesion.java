public class EstadoIniciarSesion implements TiendaEstado{

    Tienda tienda;

    EstadoIniciarSesion(Tienda tienda){
        this.tienda = tienda;
    }

    @Override
    public void start() {
        boolean error = true;
        while(error){
            try {
                ProxyCliente pCliente = Cliente.validarCliente();
                if(pCliente == null){
                    error = true;
                }
            } catch (Exception e) {
                error = true;
                System.out.println("Ha ocurrido un error.");
            }
        }
        
    }
    
}
