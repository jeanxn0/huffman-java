# Huffman em Java

Projeto do seminário de Estrutura de Dados II, apresentado por Jean e Lucas.

O programa lê uma palavra ou frase, conta as frequências, constrói a árvore de Huffman e mostra os códigos, a mensagem codificada e o texto recuperado.

## Executar

É necessário ter o JDK instalado. Na pasta do projeto:

```sh
mkdir -p bin
javac -encoding UTF-8 -d bin src/*.java
java -cp bin App
```

No Windows, crie a pasta `bin` com `mkdir bin` caso ela não exista.

Digite, por exemplo, `BANANA`. Os bits são exibidos como texto para demonstração; o programa não cria um arquivo binário compactado.

## Arquivos

- `src/App.java`: entrada e demonstração.
- `src/Huffman.java`: construção da árvore, codificação e decodificação.
- `src/NoHuffman.java`: representação dos nós.
