import java.util.ArrayList;

public class ProxyCliente implements ClienteInterface {
    private Cliente clienteReal;
    private ArrayList<Producto> carrito;
    
    ProxyCliente (Cliente cliente){
        clienteReal = cliente;
    }

    public void agregarAlCarrito(Producto producto){
        carrito.add(producto);
    }

    public boolean validarCuenta(String cuenta) {
        return clienteReal.getCuentaBancaria().equals(cuenta);
    }

    @Override
    public boolean realizarCompra(double amount) {
        if(clienteReal.getDineroDisponible() >= amount){
            clienteReal.realizarCompra(amount);
            return true;
        }
        return false;
    }

    @Override
    public String getNombre() {
        return clienteReal.getNombre();
    }

    @Override
    public String getPais() {
        return clienteReal.getPais();
    }

    @Override
    public String getDireccion() {
        return clienteReal.getDireccion();
    }

    @Override
    public String getTelefono() {
        return clienteReal.getDireccion();
    }

    @Override
    public String getNombreDeUsuario() {
        return clienteReal.getNombreDeUsuario();
    }


}
