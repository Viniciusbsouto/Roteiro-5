package tad.pilha;

/**
 * Disciplina: LEDA
 * Autor: Vinicius Bezerra Souto
 * Roteiro: Implementação de Pilha Encadeada
 * 
 * /**
 * Esta classe implementa uma estrutura de dados do tipo Pilha (Stack)
 * utilizando uma lista encadeada.
 * Cada elemento da pilha é armazenado em um "nó" que mantém uma referência
 * para o próximo nó na sequência, permitindo uma gestão dinâmica do espaço.
 * Ela oferece as operações essenciais de uma pilha: adicionar (empilhar),
 * remover (desempilhar), consultar o elemento no topo e verificar se a pilha
 * está vazia.
 */

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	private No topo = null;

	private static class No {
		Integer dado;
		No proximo;

		public No(Integer dado) {
			this.dado = dado;
		}
	}

	/**
	 * Insere um novo elemento no topo da pilha.
	 * 
	 * param item o elemento a ser empilhado
	 * throws PilhaCheiaException nunca ocorre nesta implementação (não limitada)
	 */
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		No novoNo = new No(item);
		novoNo.proximo = topo;
		topo = novoNo;
	}

	/**
	 * Remove e retorna o elemento no topo da pilha.
	 * 
	 * return o elemento removido do topo
	 * throws PilhaVaziaException se a pilha estiver vazia
	 */
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		Integer valor = topo.dado;
		topo = topo.proximo;
		return valor;
	}

	/**
	 * Retorna o elemento no topo da pilha sem removê-lo.
	 * 
	 * return o elemento no topo ou {@code null} se a pilha estiver vazia
	 */
	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return topo.dado;
	}

	/**
	 * Retorna uma nova pilha contendo os {@code k} elementos do topo.
	 * A ordem dos elementos na nova pilha é preservada.
	 * 
	 * param k a quantidade de elementos a serem copiados
	 * return nova pilha com os {@code k} elementos do topo
	 */
	@Override
	public PilhaIF<Integer> multitop(int k) {
		MinhaPilhaEncadeada novaPilha = new MinhaPilhaEncadeada();
		No atual = topo;
		int contador = 0;
		while (atual != null && contador < k) {
			try {
				novaPilha.empilhar(atual.dado);
			} catch (PilhaCheiaException e) {
				break;
			}
			atual = atual.proximo;
			contador++;
		}
		return inverterPilha(novaPilha);
	}

	/**
	 * Inverte a ordem dos elementos de uma pilha encadeada.
	 * 
	 * param pilhaOriginal a pilha a ser invertida
	 * return pilha invertida
	 */
	private MinhaPilhaEncadeada inverterPilha(MinhaPilhaEncadeada pilhaOriginal) {
		MinhaPilhaEncadeada pilhaInvertida = new MinhaPilhaEncadeada();
		try {
			while (!pilhaOriginal.isEmpty()) {
				pilhaInvertida.empilhar(pilhaOriginal.desempilhar());
			}
		} catch (PilhaVaziaException | PilhaCheiaException e) {
			// Ignorado propositalmente
		}
		return pilhaInvertida;
	}

	/**
	 * Verifica se a pilha está vazia.
	 * 
	 * return {@code true} se estiver vazia, {@code false} caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return topo == null;
	}
}
