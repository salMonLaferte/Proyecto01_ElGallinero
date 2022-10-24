import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Ofertas {
    
    public static HashMap<String, Integer> generaOfertas(){
        HashMap<String,Integer>listaDeDescuento=new HashMap<>();
        Random rand = new Random();
        int ofertasAzar = rand.nextInt(10);
        try{
            BufferedReader in = new BufferedReader(new FileReader("./catalogo/catalogo.txt"));
            String line;
            while(((line=in.readLine())!=null)){
                int descuento = rand.nextInt(101);
                if(line.contains("Electrodomesticos")&&line.contains(Integer.toString(ofertasAzar))){
                    String ofertasEsp = "\nESP: ";
                    ofertasEsp += line;
                    listaDeDescuento.put(ofertasEsp, descuento);

                }
                if(line.contains("Alimento")&&line.contains(Integer.toString(ofertasAzar))){
                    String ofertasMex = "\nMX: ";
                    ofertasMex += line;
                    listaDeDescuento.put(ofertasMex, descuento);
                }
                if(line.contains("Electronica")&&line.contains(Integer.toString(ofertasAzar))){
                    String ofertasUSA = "\nUSA: ";
                    ofertasUSA += line;
                    listaDeDescuento.put(ofertasUSA, descuento);
                }
                    
        }
            in.close();
            return listaDeDescuento;
           }catch(FileNotFoundException e){
            System.out.println(e);
           }catch(IOException ioe){
            System.out.println(ioe);
           }
        return listaDeDescuento;
    }

    public static void enviaOfertas(HashMap<String, Integer> hashMap){
        String nombreDelArchivo = "ofertas.txt";
        try(PrintStream fout = new PrintStream(nombreDelArchivo)){
            fout.println(hashMap);
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    
}
