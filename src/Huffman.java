
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Huffman {
    NoHuffman raiz;

    Map<Character, Integer> frequencias = new LinkedHashMap<>();
    Map<Character, String> codigos = new LinkedHashMap<>();


    public void montarArvore(String texto) {
        frequencias.clear();
        codigos.clear();
        raiz = null;

        for (char letra : texto.toCharArray()) {
            frequencias.put(letra, frequencias.getOrDefault(letra, 0) + 1);
        }


        PriorityQueue<NoHuffman> fila = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.frequencia, b.frequencia)
        );

        for (char letra : frequencias.keySet()) {
            fila.add(new NoHuffman(letra, frequencias.get(letra)));
        }


        while (fila.size() > 1) {
            NoHuffman esquerda = fila.poll();
            NoHuffman direita = fila.poll();

            NoHuffman novo = new NoHuffman(
                '\0', esquerda.frequencia + direita.frequencia
            );

            novo.esquerda = esquerda;
            novo.direita = direita;

            fila.add(novo);
        }


        if (!fila.isEmpty()) {
            raiz = fila.poll();
            gerarCodigos(raiz, "");
        }
    }


    private void gerarCodigos(NoHuffman no, String caminho) {
        if (no.esquerda == null && no.direita == null) {
            codigos.put(no.letra, caminho.isEmpty() ? "0" : caminho);
            return;
        }

        gerarCodigos(no.esquerda, caminho + "0");
        gerarCodigos(no.direita, caminho + "1");
    }


    public String compactar(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (char letra : texto.toCharArray()) {
            resultado.append(codigos.get(letra));
        }

        return resultado.toString();
    }


    public String descompactar(String bits) {
        StringBuilder resultado = new StringBuilder();

        if (raiz == null) return "";

        if (raiz.esquerda == null && raiz.direita == null) {
            for (int i = 0; i < bits.length(); i++) {
                resultado.append(raiz.letra);
            }
            return resultado.toString();
        }


        NoHuffman atual = raiz;

        for (char bit : bits.toCharArray()) {
            if (bit == '0')
                atual = atual.esquerda;
            else
                atual = atual.direita;

            if (atual.esquerda == null && atual.direita == null) {
                resultado.append(atual.letra);
                atual = raiz;
            }
        }

        return resultado.toString();
    }


    public void mostrarFrequencias() {
        for (char letra : frequencias.keySet()) {
            System.out.println(letra + ": " + frequencias.get(letra));
        }
    }


    public void mostrarCodigos() {
        for (char letra : frequencias.keySet()) {
            System.out.println(letra + ": " + codigos.get(letra));
        }
    }
}
