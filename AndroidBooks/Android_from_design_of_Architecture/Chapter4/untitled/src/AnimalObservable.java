import java.util.ArrayList;

public class AnimalObservable implements IObservable {

    private int MAX_EDAD = 100;
    private int MAX_PESO = 200;

    private int edad = 0;
    private int peso = 0;

    ArrayList<IObservador> observadores = new ArrayList<>();

    public AnimalObservable(int edad, int peso, int MAX_EDAD, int MAX_PESO)
    {
        this.edad = edad;
        this.peso = peso;
        this.MAX_EDAD = MAX_EDAD;
        this.MAX_PESO = MAX_PESO;
    }


    public int getMAX_EDAD() {
        return MAX_EDAD;
    }

    public int getMAX_PESO() {
        return MAX_PESO;
    }

    public int getPeso() {
        return peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if(this.edad != edad){
            this.edad = edad;
            notificarCAmbisEstadoAObservadores();
        }
    }

    public void setPeso(int peso) {
        if(this.peso != peso){
            this.peso = peso;
            notificarCAmbisEstadoAObservadores();
        }
    }

    @Override
    public void suscribir(IObservador observer) {
        if( !observadores.contains(observer) ){
            observadores.add(observer);
        }
    }

    @Override
    public void cancelarSuscripcion(IObservador observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarCAmbisEstadoAObservadores() {
        for(IObservador observador: observadores){
            observador.actualizar(this);
        }
    }
}
