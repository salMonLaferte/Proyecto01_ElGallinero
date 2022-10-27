import java.util.Scanner;

public class Tienda {

    private static Scanner scan = new Scanner(System.in);
    private static FabricaRegional fabricaRegional = new FabricaRegional();
    private static InterfazDeUsuario interfazDeUsuario = null;
    private static Cliente cliente = null;


    public static void main(String args[] ){
        //Crea la base de datos de clientes de prueba
        Cliente.leerClientes();
        //Genera las ofertas regionales y los guarda en ofertas.txt
        Ofertas.generaOfertas();
        //Notifica a cada uno de los clientes
        Ofertas ofertas = Ofertas.obtenerInstanciaUnica();
        ofertas.notificarObservadores();
        //Pedir el inicio de sesión de la clase
        try {
            cliente = Cliente.validarCliente();
            if(cliente == null){
                return;
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
            return;
        }
        interfazDeUsuario = fabricaRegional.crearInterfaz(cliente.getPais());
        interfazDeUsuario.saludar();
    }

     /**
     * Pregunta al usuario por un valor de entrada de tipo int hasta que el usuario
     * introduzca un valor válido.
     * @param valoresAdmitidos  Valores enteros que se admiten por parte del usuario.
     * @param mensaje Mensaje que se imprime en consola antes de pedir la entrada del usuario.
     * @return Regresa el primer valor válido introducido por el usuario.
     */
    public static int obtenerEntradaDelUsuario(String mensaje, int[] valoresAdmitidos){
        boolean error = true;
        int entrada = 0;
        System.out.println(mensaje);
        while(true){
            error = false;
            //Intenta leer el input del usuario.
            try{
                entrada = scan.nextInt();
            }catch(Exception e){
                error = true;
                scan.next();
            }
            //Si no ocurrió una excepcion, entonces buscamos el valor dentro del conjunto de valores válidos.
            boolean entradaEncontrada = false;
            if(!error){
                for(int i = 0; i < valoresAdmitidos.length;i++){
                    if(entrada == valoresAdmitidos[i]){
                        entradaEncontrada = true;
                        break;
                    }
                }
            }
            //Si la entrada del usuario fue encontrada en el conjunto de valores válidos entonces terminamos, en caso contrario pedimos una entrada válida.
            if(entradaEncontrada){
                break;
            }
            System.out.println("Elige una opción válida");
        }
        return entrada;
    }
}
