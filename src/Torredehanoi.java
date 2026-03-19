import java.util.Arrays;

public class Torredehanoi {

    private int[] elementos;
    private int topo;
    public static int contadorMovimentos = 0;

    public Torredehanoi(int capacidade) {
        elementos = new int[capacidade];
        topo = -1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public void push(int valor) {
        if (topo == elementos.length - 1) {
            throw new RuntimeException("Essa torre tá cheia, retire");
        }
        elementos[++topo] = valor;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("A pilha está vazia");
        }
        return elementos[topo--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("A pilha está vazia");
        }
        return elementos[topo];
    }

    public int size() {
        return topo + 1;
    }
    public void mostrar() {
        if (isEmpty()) {
            return;
        }
        for (int i = topo; i >= 0; i--){
            System.out.println(elementos[i]);
        }
    }

    public static void moverDisco(Torredehanoi origem, Torredehanoi destino) {

        if(destino.isEmpty()){
            int temp = origem.peek();

            origem.pop();

            destino.push(temp);
        }

        else if (origem.peek() < destino.peek()) {

            int temp = origem.peek();

            origem.pop();

            destino.push(temp);

        } else{
          throw new RuntimeException("Você tá tentando pôr um maior em cima de um menor, contradiz com uma das" +
                  " duas regras");
        }

        contadorMovimentos++;
    }

    public static void HanoiPilhas(int n, Torredehanoi torre1, Torredehanoi torre2, Torredehanoi torre3) {



        if(n == 1){
            moverDisco(torre1, torre2);
            return;
        }
        if (n > 1){

           HanoiPilhas(n-1, torre1, torre3, torre2);

           moverDisco(torre1, torre2);

           HanoiPilhas(n-1, torre3, torre2, torre1);

        }


    }

    public static void main(String[] args) {


        Torredehanoi torre1 = new Torredehanoi(10);
        Torredehanoi torre2 = new Torredehanoi(10);
        Torredehanoi torre3 = new Torredehanoi(10);


        torre1.push(10);
        torre1.push(9);
        torre1.push(8);
        torre1.push(7);
        torre1.push(6);
        torre1.push(5);
        torre1.push(4);
        torre1.push(3);
        torre1.push(2);
        torre1.push(1);




        System.out.println("A torre 1 está assim: ");
        torre1.mostrar();
        System.out.println("A torre 2 está assim: ");
        torre2.mostrar();
        System.out.println("A torre 3 está assim: ");
        torre3.mostrar();


        System.out.println();
        System.out.println("Agora vamos fazer todas as operações: ");
        System.out.println();

        long tempoInicial = System.nanoTime();
        HanoiPilhas(10, torre1, torre2, torre3);
        long tempoFinal = System.nanoTime();
        long tempoDeExecucao = tempoFinal - tempoInicial;

        System.out.println("Agora a torre 1 está assim: ");
        torre1.mostrar();
        System.out.println("A torre 2 está assim: ");
        torre2.mostrar();
        System.out.println("A torre 3 está assim: ");
        torre3.mostrar();


        System.out.println("A quantidade de movimentos necessários foram: " + contadorMovimentos);

        System.out.println("O tempo de execução do código foi de " + tempoDeExecucao);

        System.out.println("Complexidade de Tempo: O(2^n) - Exponencial");
        System.out.println("Isso significa que para n discos, o número esperado de movimentos é 2^n-1");




    }

}


