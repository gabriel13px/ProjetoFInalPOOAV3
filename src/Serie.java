import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Serie extends ConteudoAudiovisual {
    private Set<String> generos;
    private List<EpisodioSerie> episodios = new ArrayList<>();

    public Serie(String nome, String imagem, Set<String> generos,
                 int ano, String sinopse, int duracao) {
        super("Serie", nome, imagem, ano, sinopse, duracao);
        this.generos = generos;
    }

    public void adicionarEpisodio(EpisodioSerie episodio) {
        episodios.add(episodio);
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Gêneros: " + String.join(", ", generos));
        System.out.println("Total de episódios: " + episodios.size());
    }
}