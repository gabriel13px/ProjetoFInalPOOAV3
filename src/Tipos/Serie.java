package Tipos;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Serie extends ConteudoAudiovisual {
    private Set<String> generos;
    private List<EpisodioSerie> episodios = new ArrayList<>();
    private int numeroDeEpisodios;

    public Serie(String nome, String imagem, Set<String> generos,
                 int ano, String sinopse, int duracaoEmMinutos, String diretor) {
        super("Tipos.Serie", nome, imagem, ano, sinopse, duracaoEmMinutos,diretor);
        this.generos = generos;
    }
    @Override
    public void adicionarEpisodio(EpisodioSerie episodio) {
        super.adicionarEpisodio(episodio);
        episodios.add(episodio);
        numeroDeEpisodios=episodios.size();
    }
    public void atualizarEpisodio(EpisodioSerie Titulo)  {
        if(buscarEpisodio(Titulo.getNome()) != -1){
            episodios.set(buscarEpisodio(Titulo.getNome()), Titulo);
        }
    }
    private int buscarEpisodio(String nome) {
        for(int titulo = 0; titulo < episodios.size(); titulo++) {
            if(episodios.get(titulo).getNome().equals(nome)) {
                return titulo;
            }

        }
        return -1;
    }

    public int getNumeroDeEpisodios() {
        return numeroDeEpisodios;
    }
    public Set<String> getGeneros() {
        return generos;
    }

    public List<EpisodioSerie> getEpisodios() {
        return episodios;
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