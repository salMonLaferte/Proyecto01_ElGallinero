import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
/** Clase que representa el cliente */
public class Cliente implements Serializable, Observador, ClienteInterface{
    /** Informacion representativa del cliente */
    private String nombreDeUsuario, contra, nombre, telefono, direccion, cuentaBancaria, pais;
    /** Dinero disponible del cliente */
    double dineroDisponible;
    /** ArrayList donde se guardan los clientes */
    private static ArrayList<Cliente> listaDeClientes = new ArrayList<>();
    
    /**
     * Metodo constructor de nuestra clase Cliente
     * @param nombreDeUsuario Nombre de usuario del cliente
     * @param contra Contraseña de nuestro cliente
     * @param nombre Nombre real del cliente
     * @param telefono Telefono del cliente
     * @param direccion Dirección del cliente
     * @param cuentaBancaria Cuenta bancaria del cliente
     * @param pais Pais del cliente 
     * @param dinero Dinero disponible del cliente
     */
    public Cliente(String nombreDeUsuario, String contra, String nombre, String telefono, String direccion, String cuentaBancaria, String pais, double dinero ){
        this.nombreDeUsuario=nombreDeUsuario;
        this.contra=contra;
        this.nombre=nombre;
        this.telefono=telefono;
        this.direccion=direccion;
        this.cuentaBancaria=cuentaBancaria;
        this.pais=pais;
        this.dineroDisponible = dinero;
    }

    /**
     * Metodo setter del nombre de usuario del cliente
     * @param nombreDeUsuario Nombre de usuario del cliente
     */
    public void setNombreDeUsuario(String nombreDeUsuario){
        this.nombreDeUsuario=nombreDeUsuario;
    }

    /**
     * Metodo setter de la contraseña del cliente
     * @param contra Contraseña del cliente
     */
    public void setContra(String contra){
        this.contra=contra;
    }

    /**
     * Metodo setter del nombre real del cliente
     * @param nombre Nombre real del cliente
     */
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    /**
     * Metodo setter del telefono del cliente
     * @param telefono Telefono del cliente
     */
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }

    /**
     * Metodo setter de la dirección del cliente
     * @param direccion Dirección del cliente
     */
    public void setDireccion(String direccion){
        this.direccion=direccion;
    }

    /**
     * Metodo setter de la cuenta bancaria del cliente
     * @param cuentaBancaria Cuenta bancaria del cliente
     */
    public void setCuentaBancaria(String cuentaBancaria){
        this.cuentaBancaria=cuentaBancaria;
    }

    /**
     * Metodo setter del pais del cliente
     * @param pais Pais del cliente
     */
    public void setPais(String pais){
        this.pais=pais;
    }

    /**
     * Metodo getter del nombre de usuario del cliente
     * @return Nombre de usuario
     */
    @Override
    public String getNombreDeUsuario(){
        return nombreDeUsuario;
    }

    /**
     * Metodo getter de la contraseña del cliente
     * @return Contraseña del cliente
     */
    public String getContra(){
        return contra;
    }

    /**
     * Metodo getter del nombre real del cliente
     * @return Nombre del cliente
     */
    @Override
    public String getNombre(){
        return nombre;
    }

    /**
     * Metodo getter del telefono del cliente
     * @return Telefono del cliente
     */
    @Override
    public String getTelefono(){
        return telefono;
    }

    /**
     * Metodo getter de la dirección del cliente
     * @return Dirección del cliente
     */
    @Override
    public String getDireccion(){
        return direccion;
    }

    /**
     * Metodo getter de la cuenta bancaria del cliente
     * @return Cuenta bancaria del cliente
     */
    public String getCuentaBancaria(){
        return cuentaBancaria;
    }

    /**
     * Metodo getter del pais del cliente
     * @return Pais del cliente
     */
    @Override
    public String getPais(){
        return pais;
    }
    
    /**
     * Metodo getter del dinero disponible del
     * cliente.
     * @return Dinero disponible
     */
    public double getDineroDisponible(){
        return dineroDisponible;
    }

    /**
     * Metodo que nos imprime en cadena la 
     * información del cliente.
     */
    public String toString(){
        return "Nombre de Usuario: "+ nombreDeUsuario+", Nombre: "+nombre+", Telefono: "+telefono;
    }

    /**
     * Metodo que permite leer el archivo txt donde se 
     * encuentran contenidos los clientes y asi mismo
     * crear los clientes que se irán agregando al ArrayList.
     */
    protected static void leerClientes(){
        File archivo = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            archivo = new File("../datosClientes/clientes.txt");
            fileReader = new FileReader(archivo);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while( (linea = bufferedReader.readLine() ) != null){
                String[] param = linea.split("--");
                if(param.length ==8){
                    Cliente cliente = new Cliente(param[0], param[1], param[2], param[3], param[4], param[5], param[6], Double.parseDouble(param[7]) );
                    listaDeClientes.add(cliente);
                    Ofertas ofertas = Ofertas.obtenerInstanciaUnica();
                    ofertas.registrarObservador(cliente);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
         }finally{
            try{                    
               if( null != fileReader ){   
                  fileReader.close();     
               }                  
            }catch (Exception e2){ 
               e2.printStackTrace();
            }
         }
    }

    /**
     * Metodo que nos ayuda a validar el inicio de sesion
     * del cliente comprobando el nombre y la contraseña
     * que se esta ingresando con el registrado, en caso de
     * ingresar se indagara con un cliente Proxy, en caso
     * contrario se niega el acceso al sistema. 
     * @return
     */
    public static ProxyCliente validarCliente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu nombre de usuario");
        String nombreDeUsuario=sc.nextLine();
        boolean indicadorUsuarioExiste = false;
        for (Cliente cliente : listaDeClientes) {
            if(cliente.getNombreDeUsuario().equals(nombreDeUsuario)){
                indicadorUsuarioExiste = true;
                System.out.println("Escribe la contrasena");
                String contra = sc.nextLine();
                if(cliente.getContra().equals(contra)){
                    return new ProxyCliente(cliente);
                }
                System.out.println("La contrasena es incorrecta :-{");
                break;
            }
        }
        if(!indicadorUsuarioExiste && !nombreDeUsuario.equals("terminar")){
            System.out.println("Ese nombre de usuario no existe");
        }
        return null;
    }
    
    /**
     * Metodo que lee el archivo ofertas.txt y le presenta las ofertas
     * al cliente en la terminal.
     * @throws IOException Posible excepcion en el acceso al archivo
     */
    public void escribirOfertas() throws IOException{
        try{
        BufferedReader in = new BufferedReader(new FileReader("../ofertasClientes/ofertas.txt"));
        String line;
        while(((line=in.readLine())!=null)){ 
            String nuevo = line.replace("{","").replace("}","").replace("=", ", Con un % de descuento: ");
            if(nuevo.contains(this.getPais())){
                System.out.println(nuevo);
            }
            
        }
        in.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    /**
     * Metodo que crea un buzon para el cliente y le da las
     * promociones validas al cliente, para esto crea un archivo
     * txt para cada cliente y lo lee.
     * @throws IOException Posible excepcion en el acceso al archivo
     */
    public void mostrarOfertaAlCliente() throws IOException{
        String nombreDeUsuario=this.nombreDeUsuario;
        File file = new File("../ofertasClientes/"+nombreDeUsuario+".txt");
        FileWriter fw= new FileWriter(file,true);
        try{
            BufferedReader in = new BufferedReader(new FileReader("../ofertasClientes/ofertas.txt"));
            String line;
            while(((line=in.readLine())!=null)){ 
                String nuevo = line.replace("{","").replace("}","").replace("=", ", Con un % de descuento: ");
                if(nuevo.contains(this.getPais())){
                 fw.write(nuevo+"\n");
                }
            }
            fw.write(">>>>>>>>>> Válido mientras no existan ofertas debajo de esta linea <<<<<<<<<<<<<<<\n");
            fw.close();
            in.close();
            }catch(FileNotFoundException e){
                System.out.println(e);
            }catch(IOException ioe){
                System.out.println(ioe);
            }
    }

    /**
     * Metodo que actualiza las ofertas del cliente en su bitacora,
     * en caso de lo contrario se imprime que no se
     * pudo mostrar. 
     */
    @Override
    public void update() {
        try {
            mostrarOfertaAlCliente();
        } catch (Exception e) {
            System.out.println("No se pudieron mostrar las ofertas al cliente");
        }
    }

    /**
     * Metodo que nos confirma si el monto de la compra fue realizada,
     * tomando en cuenta el monto de la compra y el dinero del cliente.
     * @param amount Monto de la compra.
     * @return Regresa false cuando ha sido descontado el monto.
     */
    @Override
    public boolean realizarCompra(double amount) {
        dineroDisponible -= amount;
        return false;
    }
}
