import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
/** Clase que representa la interfaz de Usuario */
public abstract class InterfazDeUsuario {
    /** HashMap donde se van guardando los textos de los idiomas */
    protected HashMap<String,String>  textos = new HashMap<>(); 
   
    /**
     * Metodo constructor que manda a llamar el metodo
     * de obtenerTextos()
     */
    InterfazDeUsuario(){
        obtenerTextos();
    }
    
    /**
     * Metodo para obtener el idioma
     * @return Nombre del idioma
     */
    abstract String obtenerIdioma();
    
    /**
     * Metodo que permite leer los archivos txt de cada
     * idioma, donde se encuentra contenidas las opciones 
     * de los idiomas y asi mismo irlos agregando al HashMap.
     */
    protected void obtenerTextos(){
        File archivo = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            archivo = new File("./idiomas/" + obtenerIdioma() + ".txt");
            fileReader = new FileReader(archivo);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while( (linea = bufferedReader.readLine() ) != null){
                String[] keyVal = linea.split("--");
                if(keyVal.length == 2){
                    textos.put(keyVal[0], keyVal[1]);
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

    public void saludar(){
        System.out.println(textos.get("Saludo"));
    }

    public void despedir(){
        System.out.println(textos.get("Despedida"));
    }

    public void mostrarMenuPrincipal(){
        System.out.println(textos.get("MenuTexto"));
        System.out.println("1." + textos.get("OpcionCatalogo"));
        System.out.println("2." + textos.get("OpcionCompra"));
        System.out.println("3." + textos.get("OpcionSalir"));
    }

    public void mostrarIndicacionCarrito(){
        System.out.println(textos.get("Indicacion"));
        System.out.println(textos.get("Indicacion2"));
        System.out.println(textos.get("Indicacion3"));
    }

    public void productoAgregadoExitosamente(){
        System.out.println(textos.get("ProductoAgregado"));
    }

    public void mostrarMensajeCuentaBancaria(){
        System.out.println(textos.get("CompraSegura"));
        System.out.println(textos.get("IntroduceCuenta"));
    }

    public void mostrarConfirmacion(){
        System.out.println(textos.get("CuentaValidada"));
        System.out.println(textos.get("Confirmacion"));
        System.out.println("1." + textos.get("Si"));
        System.out.println("2." + textos.get("No"));
    }
}
