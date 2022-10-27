

public class FabricaRegional {
    InterfazDeUsuario crearInterfaz(String idioma){
        if(idioma.equals("USA")){
            return new InterfazIngles();
        }
        if(idioma.equals("ESP")){
            return new InterfazEspEspana();
        }
        //Español mexicano como idioma por defecto
        return new InterfazEspanol();
    }

}
