import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente implements Serializable, Observador{

    private String nombreDeUsuario, contra, nombre, telefono, direccion, cuentaBancaria, pais;
    private static ArrayList<Cliente> listaDeClientes = new ArrayList<>();
    
    public Cliente(String nombreDeUsuario, String contra, String nombre, String telefono, String direccion, String cuentaBancaria, String pais ){
        this.nombreDeUsuario=nombreDeUsuario;
        this.contra=contra;
        this.nombre=nombre;
        this.telefono=telefono;
        this.direccion=direccion;
        this.cuentaBancaria=cuentaBancaria;
        this.pais=pais;
    }

    public void setNombreDeUsuario(String nombreDeUsuario){
        this.nombreDeUsuario=nombreDeUsuario;
    }

    public void setContra(String contra){
        this.contra=contra;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setTelefono(String telefono){
        this.telefono=telefono;
    }

    public void setDireccion(String direccion){
        this.direccion=direccion;
    }

    public void setCuentaBancaria(String cuentaBancaria){
        this.cuentaBancaria=cuentaBancaria;
    }

    public void setPais(String pais){
        this.pais=pais;
    }

    public String getNombreDeUsuario(){
        return nombreDeUsuario;
    }

    public String getContra(){
        return contra;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getCuentaBancaria(){
        return cuentaBancaria;
    }

    public String getPais(){
        return pais;
    }

    public String toString(){
        return "Nombre de Usuario: "+ nombreDeUsuario+", Nombre: "+nombre+", Telefono: "+telefono;
    }

    public static void crearClientes(){
        File starting = new File("./datosClientes/datos");
        try{
            if(!starting.exists()){
                starting.createNewFile();
            }
            Cliente ClienteMexico= new Cliente("ClienteMexicano", "arribaLasChivas", "Carlos", "5544823369", "Facultad de Ciencias", "MX0123456", "MX");
            Cliente ClienteEspania = new Cliente("ClienteEspanol", "vegeta777", "Camila", "912760000", "Casa", "ES56123", "ESP");
            Cliente ClienteUSA = new Cliente("ClienteUSA","Obanium" ,"Danny ", "01793060836", "Casa blanca", "US98765", "USA");
            listaDeClientes.add(ClienteMexico);
            listaDeClientes.add(ClienteEspania);
            listaDeClientes.add(ClienteUSA);
            Ofertas ofertas = Ofertas.obtenerInstanciaUnica();
            ofertas.registrarObservador(ClienteMexico);
            ofertas.registrarObservador(ClienteEspania);
            ofertas.registrarObservador(ClienteUSA);
            FileOutputStream fos = new FileOutputStream(starting);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaDeClientes);
            oos.close();
            fos.close();
        } catch(IOException e){
            System.out.println(e);
        }
    }

    public static Cliente validarCliente() throws ClassNotFoundException{
        File starting = new File("./datosClientes/datos");
        try{
            Scanner sc = new Scanner(System.in);
            FileInputStream fis = new FileInputStream(starting);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Cliente> c = (ArrayList<Cliente>)ois.readObject();
            System.out.println("Escribe tu nombre de usuario");
            String nombreDeUsuario=sc.nextLine();
            boolean indicadorUsuarioExiste = false;
            for(Cliente cliente:c){
                if(cliente.getNombreDeUsuario().equals(nombreDeUsuario)){
                    indicadorUsuarioExiste = true;
                    System.out.println("Escribe la contrasenia");
                    String contra = sc.nextLine();
                    if (cliente.getContra().equals(contra)){
                        return cliente; 
                    }
                    System.out.println("La contra es incorrecta :-{");
                    break;
                }
            }
            if(!indicadorUsuarioExiste){
                System.out.println("Ese nombre de usuario no existe");
            }
        }catch(IOException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void escribirOfertas() throws IOException{
        try{
        BufferedReader in = new BufferedReader(new FileReader("./ofertasClientes/ofertas.txt"));
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

    public void mostrarOfertaAlCliente() throws IOException{
        String nombreDeUsuario=this.nombreDeUsuario;
        File file = new File("./ofertasClientes/"+nombreDeUsuario+".txt");
        FileWriter fw= new FileWriter(file,true);
        try{
            BufferedReader in = new BufferedReader(new FileReader("./ofertasClientes/ofertas.txt"));
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

    public static void main(String[] args) throws IOException {
        Cliente mexico= new Cliente("ClienteMexicano", "arribaLasChivas", "Carlos", "5544823369", "Facultad de Ciencias", "MX0123456", "ESP");
        mexico.escribirOfertas();
        mexico.mostrarOfertaAlCliente();
    }

    @Override
    public void update() {
        try {
            mostrarOfertaAlCliente();
        } catch (Exception e) {
            System.out.println("No se pudieron mostrar las ofertas al cliente");
        }
    }
}
