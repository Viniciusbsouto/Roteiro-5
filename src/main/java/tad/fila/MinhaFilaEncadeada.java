package tad.fila;

/**
 * Autor: Vinicius Bezerra Souto
 * Disciplina: LEDA
 * Roteiro: Implementação Fila com listas encadeadas
 * 
 * Classe que implementa uma fila dinâmica encadeada de inteiros (Integer).
 * Diferente das filas com tamanho fixo, esta estrutura pode crescer
 * dinamicamente à medida que novos elementos são inseridos, estando limitada
 * apenas pela memória disponível. Implementa a interface FilaIF, fornecendo
 * operações básicas de fila, como enfileirar, desenfileirar e verificação.
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
