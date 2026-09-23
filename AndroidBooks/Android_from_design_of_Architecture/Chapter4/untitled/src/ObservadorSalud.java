public class ObservadorSalud implements IObservador{
    private IObservable observable;
    public ObservadorSalud(IObservable observable){
        this.observable = observable;
        observable.suscribir(this);
    }

    @Override
    public void actualizar(AnimalObservable animalObservable){
        if( animalObservable.getPeso() > animalObservable.getMAX_PESO() ){

        }
    }

}
