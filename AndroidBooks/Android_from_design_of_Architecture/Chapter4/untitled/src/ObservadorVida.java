public class ObservadorVida implements  IObservador{

    private IObservable observable;
    public ObservadorVida(IObservable observable){
        this.observable = observable;
        observable.suscribir(this);
    }

    @Override
    public void actualizar(AnimalObservable animalObservable){
        if( animalObservable.getEdad() > animalObservable.getMAX_EDAD() ){

        }
    }

}
