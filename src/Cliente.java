import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cliente implements Serializable, Observador{

    private String nombreDeUsuario, contra, nombre, telefono, direccion, cuentaBancaria, pais;
    File starting = new File("./datos");
 
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

    public void crearClientes(){
        try{
            if(!starting.exists()){
                starting.createNewFile();
            }else{
                Cliente mexico= new Cliente("ClienteMexicano", "arribaLasChivas", "Carlos", "5544823369", "Facultad de Ciencias", "MX0123456", "MX");
                Cliente espania = new Cliente("ClienteEspanol", "vegeta777", "Camila", "912760000", "Casa", "ES56123", "ESP");
                Cliente usa = new Cliente("ClienteUSA","Obanium" ,"Danny ", "01793060836", "Casa blanca", "US98765", "USA");
                ArrayList<Cliente> listaDeClientes = new ArrayList<>();
                listaDeClientes.add(mexico);
                listaDeClientes.add(espania);
                listaDeClientes.add(usa);
                FileOutputStream fos = new FileOutputStream(starting);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(listaDeClientes);
                oos.close();
                fos.close();
                System.out.println("guardado!");
            }
        } catch(IOException e){
            System.out.println(e);
        }
    }

    public Cliente regresaCliente() throws ClassNotFoundException{
        try{
            Scanner sc = new Scanner(System.in);
            FileInputStream fis = new FileInputStream(starting);
            ObjectInputStream ois = new ObjectInputStream(fis);
             ArrayList<Cliente> c = (ArrayList<Cliente>)ois.readObject();
             System.out.println("Escribe tu nombre de usuario");
                String nombreDeUsuario=sc.nextLine();
            for(Cliente cliente:c){
                if(cliente.getNombreDeUsuario().equals(nombreDeUsuario)){
                    System.out.println("Escribe la contrasenia");
                    String contra = sc.nextLine();
                    if (cliente.getContra().equals(contra)){
                        return cliente; 
                    }
                    System.out.println("La contra es incorrecta :-{");
                    break;
                }
                System.out.println("Ese nombre de usuario no existe");
                break;
            }
        }catch(IOException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void update(HashMap <Long, Float> ofertas) throws IOException {
    
    }
    
    public static void escribir(Cliente cliente) throws IOException{
       try{
        BufferedReader in = new BufferedReader(new FileReader("./ofertas.txt"));
        String line;
        while(((line=in.readLine())!=null)){ 
            String nuevo = line.replace("{","").replace("}","").replace("=", ", Con un % de descuento: ");
            if(nuevo.contains(cliente.getPais())){
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

    public static void main(String[] args) throws IOException {
        Cliente mexico= new Cliente("ClienteMexicano", "arribaLasChivas", "Carlos", "5544823369", "Facultad de Ciencias", "MX0123456", "ESP");
        escribir(mexico);
    }
}
