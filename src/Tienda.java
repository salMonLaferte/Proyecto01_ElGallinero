import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
/**
 * Implementa metodos para atender la visita de un usuario a la tienda de manera que este pueda realizar
 * una compra
 */
public class Tienda {

    /** Scanner que leera lo ingresado en la consola por el cliente */
    private static Scanner scan = new Scanner(System.in);
    /** FabricaRegional que establece la interfaz de usuario para la detección del idioma */
    private static FabricaRegional fabricaRegional = new FabricaRegional();
    /** InterfazDeUsuario para el cambio de idiomas */
    private static InterfazDeUsuario interfazDeUsuario = null;
    /** ProxyCliente para representar al cliente real que realizara
     * la compra.
     */
    private static ProxyCliente proxyCliente = null;

    /**
     * Metodo que permite el arranque del programa.
     * @param args Array de argumentos
     */
    public static void main(String args[] ){
        //Lee la lista de clientes desde un archivo de texto
        Cliente.leerClientes();
        //Genera las ofertas regionales y los guarda en ofertas.txt
        Ofertas.generaOfertas();
        Ofertas.enviaOfertas();
        //Notifica a cada uno de los clientes sobre sus ofertas de este día, agregandolo a la bitacora de cada cliente
        Ofertas ofertas = Ofertas.obtenerInstanciaUnica();
        ofertas.notificarObservadores();
        //Crea un representante del catalogo.
        CatalogoProxy catalogoProxy = new CatalogoProxy();
        while(true){
            //Si el inicio de sesión no es exitoso entonces se cierra el sistema
            boolean inicio = iniciarSesion();
            if(!inicio){
                break;
            }
            //Se saluda al usuario
            interfazDeUsuario.saludar();
            System.out.print(" " + proxyCliente.getNombre() + "\n");
            int opcion = 1;
            //1.Ver catalogo / 2.Comprar algo / 3.Salir
            while(opcion == 1){
                //Se le muestra el menu principal al cliente 
                interfazDeUsuario.mostrarMenuPrincipal();
                int[] opciones = {1,2,3};
                opcion = obtenerEntradaDelUsuario( opciones);    
                //Si elije ver el catalogo, se muestra el catalogo y luego se vuelve a mostrar el menu principal
                if(opcion == 1){
                    catalogoProxy.ImprimirCatalogo();
                }
            }
            //Si se elige comprar algo se muestra el catalogo
            if(opcion == 2){
                catalogoProxy.ImprimirCatalogo();
                //Establecemos los inputs válidos que podrá realizar el usuario en este punto
                int[] opciones = new int[catalogoProxy.obtenerTamano()+2];
                for(int i=-1; i <= catalogoProxy.obtenerTamano(); i++ ){
                    opciones[i+1] = i;
                }
                //Hasta que el usuario cancele la compra o termine de agregar productos le seguimos dando
                //la opcion de agregar productos. La opcion 0 es para terminar de seleccionar y -1 para cancelar la compra
                while(opcion !=0 && opcion !=-1){
                    interfazDeUsuario.mostrarIndicacionCarrito();
                    opcion = obtenerEntradaDelUsuario( opciones);
                    if(opcion == 0 || opcion == -1)//Si selecciona una de estas opciones entonces hemos terminado
                        break;
                    proxyCliente.agregarAlCarrito(catalogoProxy.obtenerProducto(opcion));
                    interfazDeUsuario.productoAgregadoExitosamente();
                }
                //Si la opcion seleccionada es 0 entonces se procede con la compra
                if(opcion == 0){
                    //Se le avisa al usuario cuanto es el monto de la compra
                    interfazDeUsuario.mostrarMensajeMonto();
                    double monto = calcularMontoDeLaCompra();
                    System.out.print(monto+"\n");
                    interfazDeUsuario.mostrarMensajeCuentaBancaria();
                    //Se le pide al usuario introducir su cuenta bancaria
                    scan = new Scanner(System.in);
                    String account = scan.nextLine();
                    //Se valida la cuenta introducida
                    if(proxyCliente.validarCuenta(account)){
                        //Si la cuenta introducida es correcta, se le pregunta por última vez al usuario si quiere 
                        //continuar con la compra
                        interfazDeUsuario.mostrarConfirmacion();
                        int[] opcionesConfirmacion = {1,2};
                        opcion = obtenerEntradaDelUsuario(opcionesConfirmacion);
                        if(opcion == 1){//Si el usuario acepta continuar con la compra
                            if(proxyCliente.realizarCompra(calcularMontoDeLaCompra())){//Si la compra se puede concretar
                                //Mostrar que la compra ha sido concretada e imprimir el ticket
                                System.out.println(interfazDeUsuario.getMensaje("CompraConcretada"));
                                imprimirTicket();
                                //Calcular al azar cuanto tardara la orden en llegar
                                Random rand = new Random();
                                int diasDeRetraso = rand.nextInt() % 10;
                                //Calcular la fecha de entrega del paquete 
                                String fechaEntrega = LocalDate.now().plusDays(diasDeRetraso).toString();
                                //Mostrar la fecha de entrega del paquete
                                System.out.println(interfazDeUsuario.getMensaje("FechaEntrega") + " " + fechaEntrega);
                                interfazDeUsuario.despedir();
                            }else{
                                //Si el proxyCliente no pudo realizar la compra es porque el saldo es insuficiente
                                System.out.println(interfazDeUsuario.getMensaje("SaldoInsuficiente"));
                            }
                           
                        }
                    }else{
                        //Mostrar que la cuenta bancaria fue incorrecta y que se cancelo la compra
                        System.out.println(interfazDeUsuario.getMensaje("CuentaIncorrecta"));
                    }
                }
                else{
                    //Mostrar que se seleccionó cancelar la cuenta
                    System.out.println(interfazDeUsuario.getMensaje("CompraCancelada"));
                }
            }
            //Mostrar que se esta regresando a la pantalla de inicio de sesión
            System.out.println(interfazDeUsuario.getMensaje("Regresando"));  
        }
    }

    /**
     * Calcula el monto de la compra basado en el carrito de compras del cliente y las ofertas aplicables.
     * @return
     */
    public static double calcularMontoDeLaCompra(){
        Iterator<Producto> it = proxyCliente.obtenerIteradorCarrito();
        double amount = 0;
        while(it.hasNext()){
            Producto producto = it.next();
            int descuento = Ofertas.buscarOferta(proxyCliente.getPais(), producto.getCodigoDeBarras().toString());
            if(descuento == -1)
                amount += producto.getPrecio();
            else
                amount += producto.getPrecio() *((double)1- (double)descuento/(double)100);
        }
        return amount;
    }

    /**
     * Imprime el ticket de compra del usuario basado en el carrito de compras del cliente actual,
     * mostrando los descuentos aplicados y el total
     */
    public static void imprimirTicket(){
        HashMap<String, Integer> ticket = new HashMap<>();
        Iterator<Producto> it = proxyCliente.obtenerIteradorCarrito();
        while(it.hasNext()){
            Producto producto = it.next();
            String description = producto.getCodigoDeBarras() + "--" + producto.getNombre() + "--" + producto.getPrecio();
            if(ticket.containsKey(description)){
                ticket.put(description, ticket.get(description)+1);
            }
            else{
                ticket.put(description, 1);
            }
        }
        String textTicket ="";
        for (String llave : ticket.keySet()) {
            textTicket += llave + "-- "+ interfazDeUsuario.getMensaje("Cantidad") + ": " + ticket.get(llave);
            String[] param = llave.split("--");
            int descuento = Ofertas.buscarOferta(proxyCliente.getPais(), param[0]);
            if(descuento != -1){
                textTicket += "--" + interfazDeUsuario.getMensaje("Descuento") + ": %" + descuento; 
            }
            textTicket +="\n";
        }
        textTicket += interfazDeUsuario.getMensaje("Total") + ": " + calcularMontoDeLaCompra(); 
        System.out.println(textTicket);
    }

    /**
     * Pide los datos para iniciar sesion.
     * @return true si se inicio sesion exitosamente, false en caso contrario
     */
    public static boolean iniciarSesion(){
        //Pedir el inicio de sesión de la clase
        try {
            proxyCliente = Cliente.validarCliente();
            if(proxyCliente == null){
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
            return false;
        }
        interfazDeUsuario = fabricaRegional.crearInterfaz(proxyCliente.getPais());
        return true;
    }

    /**
     * Pregunta al usuario por un valor de entrada de tipo int hasta que el usuario
     * introduzca un valor válido.
     * @param valoresAdmitidos  Valores enteros que se admiten por parte del usuario.
     * @return Regresa el primer valor válido introducido por el usuario.
     */
    public static int obtenerEntradaDelUsuario( int[] valoresAdmitidos){
        boolean error = true;
        int entrada = 0;
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
