import java.util.Set;

public class Filme extends ConteudoAudiovisual {
    private Set<String> generos;

    public Filme(String nome, String imagem, Set<String> generos,
                 int ano, String sinopse, int duracaoemMinutos,String diretor, int totalDeAvaliacoes, int somaAvaliacoes) {
        super("Filme", nome, imagem, ano, sinopse, duracaoemMinutos,diretor);
        this.generos = generos;
        super.setTotalDeAvaliacoes(totalDeAvaliacoes);
        super.setSomaAvaliacoes(somaAvaliacoes);
    }

    public Set<String> getGeneros() {
        return generos;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Gêneros: " + String.join(", ", generos));
    }

    @Override
    public String stringBancoDados() {
        return new String(super.stringBancoDados()+","+String.join("&", generos)+",-,-,-\n");
    }
}