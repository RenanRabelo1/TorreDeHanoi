import java.util.Arrays;

public class Torredehanoi {

    private int[] elementos;
    private int topo;
    public static int contadorMovimentos = 0; // Variável global para contar movimentos reais

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





    // Meu código começa aqui
    public static void moverDisco(Torredehanoi origem, Torredehanoi destino) {
        // Se a torre de destino estiver vazia, qualquer disco pode entrar

        if(destino.isEmpty()){
            int temp = origem.pop(); // Retira do topo da origem
            destino.push(temp);      // Coloca no topo do destino
        }

        // O disco que chega deve ser menor que o que já está lá
        else if (origem.peek() < destino.peek()) {

            int temp = origem.pop();
            destino.push(temp);

        } else {

            // Caso tente colocar um disco maior sobre um menor
            throw new RuntimeException("Movimento inválido: disco maior sobre menor.");
        }

        // Incrementa o contador global a cada movimento realizado
        contadorMovimentos++;
    }

    public static void HanoiPilhas(int n, Torredehanoi torreOrigem, Torredehanoi torreDestino, Torredehanoi torreAuxiliar) {

        // Se houver apenas 1 disco, basta movê-lo direto para o destino

        if(n == 1){
            moverDisco(torreOrigem, torreDestino);
            return;
        }

        // Caso Recursivo:
        if (n > 1){
            // Aqui estou movendo os discos da origem para a auxiliar (usando o destino como suporte)

            HanoiPilhas(n-1, torreOrigem, torreAuxiliar, torreDestino);

            // Aqui estou movendo disco maior da origem (o que restou) para o destino definitivo

            moverDisco(torreOrigem, torreDestino);

            // Aqui estou movendo os discos que estavam na auxiliar para o destino (usando a origem como suporte)
            HanoiPilhas(n-1, torreAuxiliar, torreDestino, torreOrigem);
        }
    }

    public static void main(String[] args) {

        Torredehanoi torre1 = new Torredehanoi(10);
        Torredehanoi torre2 = new Torredehanoi(10);
        Torredehanoi torre3 = new Torredehanoi(10);

        // Estado Inicial: Preenchendo a Torre 1 (maior no fundo, menor no topo)
        torre1.push(10); torre1.push(9); torre1.push(8);
        torre1.push(7);  torre1.push(6); torre1.push(5);
        torre1.push(4);  torre1.push(3); torre1.push(2);
        torre1.push(1);

        System.out.println("--- ESTADO INICIAL ---");
        System.out.print("Torre 1: "); torre1.mostrar();

        System.out.println("\nIniciando resolução...");


        long tempoInicial = System.nanoTime();

        // Chamada do algoritmo
        HanoiPilhas(10, torre1, torre2, torre3);


        long tempoFinal = System.nanoTime();


        long tempoDeExecucao = tempoFinal - tempoInicial;

        System.out.println("\n--- ESTADO FINAL ---");
        System.out.println("Torre 1 (Origem) vazia? " + torre1.isEmpty());
        System.out.println("Torre 2 (Destino) finalizada:");
        torre2.mostrar();


        System.out.println("\n--- RELATÓRIO DE EXECUÇÃO ---");
        System.out.println("Quantidade de movimentos realizados: " + contadorMovimentos);
        System.out.println("Tempo de execução: " + tempoDeExecucao + " nanossegundos");
        System.out.println("Complexidade de Tempo: O(2^n) - Exponencial");
        System.out.println("(2^n - 1) para 10 discos: 1023 movimentos.");
    }
}