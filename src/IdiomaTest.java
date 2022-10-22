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
    }
}
