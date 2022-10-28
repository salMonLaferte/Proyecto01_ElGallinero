import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Tienda {

    /** Scanner que leera lo ingresado en la consola por el cliente */
    private static Scanner scan = new Scanner(System.in);
    /** FabricaRegional para la detección del idioma */
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
        CatalogoProxy catalogoProxy = new CatalogoProxy();
        while(true){
            boolean inicio = iniciarSesion();
            if(!inicio){
                break;
            }
            interfazDeUsuario.saludar();
            System.out.print(" " + proxyCliente.getNombre() + "\n");
            int opcion = 1;
            while(opcion == 1){
                interfazDeUsuario.mostrarMenuPrincipal();
                int[] opciones = {1,2,3};
                opcion = obtenerEntradaDelUsuario( opciones);
                if(opcion == 1){
                    catalogoProxy.ImprimirCatalogo();
                }
            }
            if(opcion == 2){
                catalogoProxy.ImprimirCatalogo();
                int[] opciones = new int[catalogoProxy.obtenerTamano()+2];
                for(int i=-1; i <= catalogoProxy.obtenerTamano(); i++ ){
                    opciones[i+1] = i;
                }
                while(opcion !=0 && opcion !=-1){
                    interfazDeUsuario.mostrarIndicacionCarrito();
                    opcion = obtenerEntradaDelUsuario( opciones);
                    if(opcion == 0 || opcion == -1)
                        break;
                    proxyCliente.agregarAlCarrito(catalogoProxy.obtenerProducto(opcion));
                    interfazDeUsuario.productoAgregadoExitosamente();
                }
                if(opcion == 0){
                    interfazDeUsuario.mostrarMensajeMonto();
                    double monto = calcularMontoDeLaCompra();
                    System.out.print(monto+"\n");
                    interfazDeUsuario.mostrarMensajeCuentaBancaria();
                    scan = new Scanner(System.in);
                    String account = scan.nextLine();
                    if(proxyCliente.validarCuenta(account)){
                        interfazDeUsuario.mostrarConfirmacion();
                        int[] opcionesConfirmacion = {1,2};
                        opcion = obtenerEntradaDelUsuario(opcionesConfirmacion);
                        if(opcion == 1){
                            if(proxyCliente.realizarCompra(calcularMontoDeLaCompra())){
                                System.out.println(interfazDeUsuario.getMensaje("CompraConcretada"));
                                imprimirTicket();
                                interfazDeUsuario.despedir();
                            }else{
                                System.out.println(interfazDeUsuario.getMensaje("SaldoInsuficiente"));
                            }
                           
                        }
                    }else{
                        System.out.println(interfazDeUsuario.getMensaje("CuentaIncorrecta"));
                    }
                }
                else{
                    System.out.println(interfazDeUsuario.getMensaje("CompraCancelada"));
                }
            }
            System.out.println(interfazDeUsuario.getMensaje("Regresando"));  
        }
    }

    public static double calcularMontoDeLaCompra(){
        Iterator<Producto> it = proxyCliente.obtenerIterador();
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

    public static void imprimirTicket(){
        HashMap<String, Integer> ticket = new HashMap<>();
        Iterator<Producto> it = proxyCliente.obtenerIterador();
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
