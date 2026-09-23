public class Main {
    public static void main(String[] args) {

        AnimalObservable elefante = new AnimalObservable(0,100,60,6000);
        IObservador obsVida = new ObservadorVida(elefante);

        IObservador obsSalud = new ObservadorSalud(elefante);
        elefante.setEdad(elefante.getEdad()+70 );
        elefante.setPeso(elefante.getPeso()+6500 );

    }
}