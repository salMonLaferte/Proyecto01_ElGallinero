/** Clase que crea las interfaces de cada idioma */
public class FabricaRegional {

    /**
     * Metodo que dado el idioma crea la interfaz,
     * en este caso para el idioma ingles, español(España)
     * y Español(México).
     */
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
