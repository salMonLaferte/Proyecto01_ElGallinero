import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente implements Serializable, Observador{

    private String nombreDeUsuario, contra, nombre, telefono, direccion, cuentaBancaria, pais;
    double dineroDisponible;
    private static ArrayList<Cliente> listaDeClientes = new ArrayList<>();
    
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

    protected static void leerClientes(){
        File archivo = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            archivo = new File("./datosClientes/clientes.txt");
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

    public static Cliente validarCliente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu nombre de usuario");
        String nombreDeUsuario=sc.nextLine();
        boolean indicadorUsuarioExiste = false;
        for (Cliente cliente : listaDeClientes) {
            if(cliente.getNombreDeUsuario().equals(nombreDeUsuario)){
                indicadorUsuarioExiste = true;
                System.out.println("Escribe la contrasenia");
                String contra = sc.nextLine();
                if(cliente.getContra().equals(contra)){
                    return cliente;
                }
                System.out.println("La contra es incorrecta :-{");
                break;
            }
        }
        if(!indicadorUsuarioExiste){
            System.out.println("Ese nombre de usuario no existe");
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

    @Override
    public void update() {
        try {
            mostrarOfertaAlCliente();
        } catch (Exception e) {
            System.out.println("No se pudieron mostrar las ofertas al cliente");
        }
    }
}
