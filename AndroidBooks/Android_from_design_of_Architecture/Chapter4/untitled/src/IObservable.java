public interface IObservable {
    void suscribir (IObservador observer);

    void cancelarSuscripcion(IObservador observer);

    void notificarCAmbisEstadoAObservadores();
}
