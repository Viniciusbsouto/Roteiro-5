package tad.fila;

/**
 * Disciplina: LEDA
 * Autor: Vinicius Bezerra Souto
 * Roteiro: Implementação Fila com listas encadeadas
 * 
 * Implementação de uma Fila Dinâmica utilizando uma estrutura encadeada para
 * armazenar
 * elementos do tipo Integer. Diferente das filas com capacidade fixa, esta
 * estrutura cresce ou diminui conforme a necessidade, limitada apenas pela
 * memória disponível. A classe implementa a interface FilaIF, oferecendo as
 * operações básicas de fila, como enfileirar (enqueue), desenfileirar (dequeue)
 * e verificações de estado.
 */

public class MinhaFilaEncadeada implements FilaIF<Integer> {

    private class No {
        Integer dado;
        No proximo;

        public No(Integer dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No cabeca;
    private No cauda;

    public MinhaFilaEncadeada() {
        cabeca = null;
        cauda = null;
    }

    @Override
    public void enfileirar(Integer item) {
        No novo = new No(item);
        if (isEmpty()) {
            cabeca = novo;
            cauda = novo;
        } else {
            cauda.proximo = novo;
            cauda = novo;
        }
    }

    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException();
        }
        Integer item = cabeca.dado;
        cabeca = cabeca.proximo;
        if (cabeca == null) {
            cauda = null;
        }
        return item;
    }

    @Override
    public Integer verificarCauda() {
        return (cauda != null) ? cauda.dado : null;
    }

    @Override
    public Integer verificarCabeca() {
        return (cabeca != null) ? cabeca.dado : null;
    }

    @Override
    public boolean isEmpty() {
        return cabeca == null;
    }

    @Override
    public boolean isFull() {
        return false; // Lista encadeada só fica "cheia" se faltar memória
    }
}
