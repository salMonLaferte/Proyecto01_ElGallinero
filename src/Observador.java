/** Clase que representa la interfaz del observador */
public interface Observador {
    /**
     * Actualiza los datos del observador, la idea
     * es que se llame a este metodo desde un sujeto
     * de interes al ocurrir un evento.
     */
    public void update();
}
