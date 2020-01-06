package com.gabmonteiro.cubegame;

public enum DirecaoBala {

     CIMA(1),
    BAIXO(2),
    ESQUERDA(3),
    DIREITA(4);

    private int valor;

    DirecaoBala(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
