import java.util.Iterator;

public class Pruebas {
    public static void main(String args[]){
        ///Interfaz
        System.out.println("-------- INTERFAZ ---------");
        FabricaRegional fabricaRegional = new FabricaRegional();
        InterfazDeUsuario interfaz = fabricaRegional.crearInterfaz("espanol");
        System.out.println("Interfaz español: ");
        interfaz.saludar();
        interfaz.despedir();
        System.out.println("Interfaz ingles: ");
        interfaz = fabricaRegional.crearInterfaz("ingles");
        interfaz.saludar();
        interfaz.despedir();
        System.out.println("Interfaz español de España: ");
        interfaz = fabricaRegional.crearInterfaz("esp_espana");
        interfaz.saludar();
        interfaz.despedir();
        //Catalogo
        System.out.println("-------- CATALOGO ---------");
        CatalogoProxy catalogo = new CatalogoProxy();
        ///Producto
        catalogo.ImprimirCatalogo();
        //Ofertas
        Ofertas.generaOfertas();
        Ofertas.enviaOfertas();
        

    }
}
