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

    /**
     * Metodo que denomina la palabra "Saludo"
     * como key del HashMap.
     */
    protected void saludar(){
        System.out.println(textos.get("Saludo"));
    }

    /**
     * Metodo que denomina la palabra "Despedida"
     * como key del HashMap.
     */
    protected void despedir(){
        System.out.println(textos.get("Despedida"));
    }

    /**
     * Metodo que denomina la palabra "Opciones"
     * como key del HashMap.
     */
    protected void muestraOpciones(){
        System.out.println(textos.get("Opciones"));
    }

    /**
     * Metodo que denomina la palabra "Menu"
     * como key del HashMap.
     */
    protected void mostrarMenuPrincipal(){
        System.out.println(textos.get("Menu"));
    }

}
