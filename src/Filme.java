import java.util.Set;

public class Filme extends ConteudoAudiovisual {
    private Set<String> generos;

    public Filme(String nome, String imagem, Set<String> generos,
                 int ano, String sinopse, int duracao, int totalDeAvaliacoes, int somaAvaliacoes) {
        super("Filme", nome, imagem, ano, sinopse, duracao);
        this.generos = generos;
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.somaAvaliacoes = somaAvaliacoes;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Gêneros: " + String.join(", ", generos));
    }
}