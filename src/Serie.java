import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Serie extends ConteudoAudiovisual {
    private Set<String> generos;
    private List<EpisodioSerie> episodios = new ArrayList<>();

    public Serie(String nome, String imagem, Set<String> generos,
                 int ano, String sinopse, int duracaoEmMinutos, String diretor) {
        super("Serie", nome, imagem, ano, sinopse, duracaoEmMinutos,diretor);
        this.generos = generos;
    }
    @Override
    public void adicionarEpisodio(EpisodioSerie episodio) {
        super.adicionarEpisodio(episodio);
        episodios.add(episodio);
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Gêneros: " + String.join(", ", generos));
        System.out.println("Total de episódios: " + episodios.size());
    }

    @Override
    public String stringBancoDados() {
        String StringReturno = super.stringBancoDados()+","+String.join("&", generos)+",-,-,-\n";
        for (EpisodioSerie episodio : episodios) {
            StringReturno = StringReturno + episodio.stringBancoDados()+"\n";
        }
        return StringReturno;
    }
}