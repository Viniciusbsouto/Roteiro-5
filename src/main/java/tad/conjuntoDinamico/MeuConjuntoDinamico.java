package tad.conjuntoDinamico;

/**
 * Disciplina: LEDA
 * Autor: Vinicius Bezerra Souto
 * Roteiro: Implementação de Conjunto Dinâmico com Array
 * 
 * Implementa a interface ConjuntoDinamicoIF, utilizando um array que se
 * redimensiona automaticamente para armazenar elementos do tipo Integer.
 * Esta classe oferece funcionalidades completas para manipulação de conjuntos,
 * incluindo operações de inserção, busca e remoção.
 * Além disso, suporta consultas para identificar o predecessor, sucessor,
 * menor e maior elemento do conjunto.
 */

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private Integer[] meusDados;
    private int posInsercao;

    public MeuConjuntoDinamico() {
        this.meusDados = new Integer[10];
        this.posInsercao = 0;
    }

    @Override
    public void inserir(Integer item) {
        if (posInsercao == meusDados.length) {
            meusDados = aumentarArray();
        }
        meusDados[posInsercao++] = item;
    }

    private Integer[] aumentarArray() {
        Integer[] novoArray = new Integer[meusDados.length * 2];
        for (int i = 0; i < meusDados.length; i++) {
            novoArray[i] = meusDados[i];
        }
        return novoArray;
    }

    @Override
    public Integer remover(Integer item) {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                Integer removido = meusDados[i];
                for (int j = i; j < posInsercao - 1; j++) {
                    meusDados[j] = meusDados[j + 1];
                }
                meusDados[--posInsercao] = null;
                return removido;
            }
        }
        throw new RuntimeException("Item não encontrado para remoção: " + item);
    }

    @Override
    public Integer predecessor(Integer item) {
        if (posInsercao == 0)
            throw new RuntimeException("Conjunto vazio");

        // Varre o array na ordem de inserção
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                // Se for o primeiro elemento, não tem predecessor
                return (i == 0) ? null : meusDados[i - 1];
            }
        }
        throw new RuntimeException("Item não encontrado: " + item);
    }

    @Override
    public Integer sucessor(Integer item) {
        if (posInsercao == 0)
            throw new RuntimeException("Conjunto vazio");

        // Varre o array na ordem de inserção
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                // Se for o último elemento, não tem sucessor
                return (i == posInsercao - 1) ? null : meusDados[i + 1];
            }
        }
        throw new RuntimeException("Item não encontrado: " + item);
    }

    @Override
    public int tamanho() {
        return posInsercao;
    }

    @Override
    public Integer buscar(Integer item) {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        throw new RuntimeException("Item não encontrado: " + item);
    }

    @Override
    public Integer minimum() {
        if (posInsercao == 0) {
            throw new RuntimeException("Conjunto vazio");
        }
        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] < min) {
                min = meusDados[i];
            }
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if (posInsercao == 0) {
            throw new RuntimeException("Conjunto vazio");
        }
        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] > max) {
                max = meusDados[i];
            }
        }
        return max;
    }
}
