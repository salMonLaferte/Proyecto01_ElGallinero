public class Tienda {

    public static void main(String args[] ){
        Cliente cliente = null;
        Cliente.crearClientes();
        try {
            cliente = Cliente.validarCliente();
            if(cliente == null)
                return;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
            return;
        }
        System.out.println(cliente.getPais());
    }
}
