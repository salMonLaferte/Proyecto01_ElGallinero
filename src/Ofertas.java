import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/** Clase que representa las ofertas */
public class Ofertas implements Observable{
    /** HashMap donde se guardaran las ofertas */
    HashMap<String, Integer> ofertas;
    /** ArrayList donde se guardaran los observadores*/
    ArrayList<Observador> observadores = new ArrayList<>();
    /** Ofertas */
    private static Ofertas instanciaUnica;

    /**
     * Metodo constructor de la clase Ofertas
     */
    private Ofertas(){
        ofertas = new HashMap<>();
    }

    /**
     * Metodo para conseguir las ofertas
     * @return Ofertas
     */
    public static Ofertas obtenerInstanciaUnica(){
        if(instanciaUnica == null){
            instanciaUnica = new Ofertas();
            generaOfertas();
        }
        return instanciaUnica;
    }
    
    /**
     * Metodo que nos genera las ofertas de forma azarosa,
     * pero tomando en cuenta la region del cliente.
     */
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

    /**
     * Metodo que sobreescribe las ofertas en el archivo txt.
     */
    public static void enviaOfertas(){
        String nombreDelArchivo = "./ofertasClientes/ofertas.txt";
        try(PrintStream fout = new PrintStream(nombreDelArchivo)){
            fout.println(instanciaUnica.ofertas);
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.update();
        }
    }
    
}
