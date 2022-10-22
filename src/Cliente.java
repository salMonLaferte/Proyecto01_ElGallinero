import java.io.Serializable;

public class Cliente implements Serializable{

    private String nombreDeUsuario, contra, nombre, telefono, direccion, cuentaBancaria;
    private int pais;
 
    public Cliente(String nombreDeUsuario, String contra, String nombre, String telefono, String direccion, String cuentaBancaria, int pais ){
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

    public void setPais(int pais){
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

    public int getPais(){
        return pais;
    }

    public String toString(){
        return "Nombre de Usuario: "+ nombreDeUsuario+", Nombre: "+nombre+", Telefono: "+telefono;
    }
