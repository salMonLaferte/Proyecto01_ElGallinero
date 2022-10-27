import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Ofertas implements Observable{
    
    HashMap<String, Integer> ofertas;
    ArrayList<Observador> observadores = new ArrayList<>();
    private static Ofertas instanciaUnica;


    private Ofertas(){
        ofertas = new HashMap<>();
    }

    public static Ofertas obtenerInstanciaUnica(){
        if(instanciaUnica == null){
            instanciaUnica = new Ofertas();
            generaOfertas();
        }
        return instanciaUnica;
    }
    
    public static void generaOfertas(){
        Random rand = new Random();
        int ofertasAzar = rand.nextInt(10);
        try{
            BufferedReader in = new BufferedReader(new FileReader("./catalogo/catalogo.txt"));
            String line;
            while(((line=in.readLine())!=null)){
                int descuento = rand.nextInt(101);
                if(line.contains("Electrodomesticos")&&line.contains(Integer.toString(ofertasAzar))){
                    String ofertasEsp = "\nESP--";
                    ofertasEsp += line;
                    instanciaUnica.ofertas.put(ofertasEsp, descuento);
                }
                if(line.contains("Alimento")&&line.contains(Integer.toString(ofertasAzar))){
                    String ofertasMex = "\nMX--";
                    ofertasMex += line;
                    instanciaUnica.ofertas.put(ofertasMex, descuento);
                }
                if(line.contains("Electronica")&&line.contains(Integer.toString(ofertasAzar))){
                    String ofertasUSA = "\nUSA--";
                    ofertasUSA += line;
                    instanciaUnica.ofertas.put(ofertasUSA, descuento);
                }
                    
        }
            in.close();
           }catch(FileNotFoundException e){
            System.out.println(e);
           }catch(IOException ioe){
            System.out.println(ioe);
           }
    }

    public static void enviaOfertas(){
        String nombreDelArchivo = "./ofertasClientes/ofertas.txt";
        try(PrintStream fout = new PrintStream(nombreDelArchivo)){
            fout.println(instanciaUnica.ofertas);
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.update();
        }
    }
    
}
