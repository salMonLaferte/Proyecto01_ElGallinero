/** Clase que representa la interfaz de observable */
public interface Observable {
    /**
     * Metodo que dado un observador es registrado
     * en los observadores.
     * @param observador Observador a registrar
     */
    public void registrarObservador(Observador observador);

    /**
     * Metodo que dado un observador es removido
     * de los observadores.
     * @param observador Observador a remover
     */
    public void removerObservador(Observador observador);
    /**
     * Metodo que notifica a los observadores.
     */
    public void notificarObservadores();
}
