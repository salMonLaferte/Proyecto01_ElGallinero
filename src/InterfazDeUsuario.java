import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public abstract class InterfazDeUsuario {
    
    protected HashMap<String,String>  textos = new HashMap<>(); 
   
    protected HashMap<Long, Float> ofertas = new HashMap<>();

    InterfazDeUsuario(){
        obtenerTextos();
    }
    
    abstract String obtenerIdioma();
    
    abstract void establecerOfertas();
    
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

    protected void saludar(){
        System.out.println(textos.get("Saludo"));
    }

    protected void despedir(){
        System.out.println(textos.get("Despedida"));
    }

}
