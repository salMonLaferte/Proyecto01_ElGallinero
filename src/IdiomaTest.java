import java.nio.FloatBuffer;

public class IdiomaTest {
    public static void main(String args[]){
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
        //catalogoooo
        System.out.println("-------- CATALOGO ---------");
        Catalogo catalogo = new Catalogo();
        Catalogo.obtenerTextos();
        ///Producto
        System.out.println(catalogo.get(0).codigo_de_barras);
        System.out.println(catalogo.get(3).nombre);
        System.out.println(catalogo.get(7).departamento);
        System.out.println(catalogo.get(0).precio);

    }
}
