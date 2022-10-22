

public class FabricaRegional {
    InterfazDeUsuario crearInterfaz(String idioma){
        if(idioma == "ingles"){
            return new InterfazIngles();
        }
        if(idioma == "esp_espana"){
            return new InterfazEspEspana();
        }
        //Español como idioma por defecto
        return new InterfazEspanol();
    }

}
