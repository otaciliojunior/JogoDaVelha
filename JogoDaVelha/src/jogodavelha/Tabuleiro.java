package jogodavelha;

import java.util.Scanner;

public class Tabuleiro {
    private char[][] tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;

    public Tabuleiro(Jogador p1, Jogador p2) {
        this.jogador1 = p1;
        this.jogador2 = p2;
        this.tabuleiro = new char[3][3];
        this.jogadorAtual = jogador1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = '*';
            }
        }
    }

    private void exibirTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean jogar() {
        int linha, coluna;
        do {
            linha = jogadorAtual.escolherLinha();
            coluna = jogadorAtual.escolherColuna();
        } while (tabuleiro[linha][coluna] != '*');

        tabuleiro[linha][coluna] = jogadorAtual.getMarcador();

        return verificarVencedor() || verificarEmpate();
    }

    private boolean verificarVencedor() {
        // verifica linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != '*') {
                return true;
            }
        }

        // verifica colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != '*') {
                return true;
            }
        }

        // verifica diagonais
        if ((tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != '*') ||
                (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != '*')) {
            return true;
        }

        return false;
    }

    private boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '*') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Jogador jogador1 = new Jogador('X');
        Jogador jogador2 = new Jogador('O');

        Tabuleiro jogoDaVelha = new Tabuleiro(jogador1, jogador2);

        System.out.println("Tabuleiro inicial:");
        jogoDaVelha.exibirTabuleiro();

        int rodada = 1;
        boolean venceu = false;
        while (!venceu && rodada <= 9) {
            System.out.println("Jogada " + rodada + ":");
            venceu = jogoDaVelha.jogar();
            jogoDaVelha.exibirTabuleiro();

            if (venceu) {
                System.out.println("Você ganhou, parabéns! :), " + jogoDaVelha.jogadorAtual.getMarcador());
            } else if (rodada == 9) {
                System.out.println("Empate!");
            } else {
                jogoDaVelha.jogadorAtual = (jogoDaVelha.jogadorAtual == jogador1) ? jogador2 : jogador1;
                rodada++;
            }
        }
    }
}