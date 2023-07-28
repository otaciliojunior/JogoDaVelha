package jogodavelha;

public class Jogador {
    private char marcador;

    public Jogador(char marcador) {
        setMarcador(marcador);
    }

    public int escolherLinha() {
        return (int) (Math.random() * 3);
    }

    public int escolherColuna() {
        return (int) (Math.random() * 3);
    }

    public char getMarcador() {
        return marcador;
    }

    public boolean setMarcador(char marcador) {
        if (marcador == 'X' || marcador == 'O') {
            this.marcador = marcador;
            return true;
        } else {
            System.out.println("Jogador com marca inválida! Utilizando marca padrão!");
            this.marcador = 'X';
            return false;
        }
    }
}