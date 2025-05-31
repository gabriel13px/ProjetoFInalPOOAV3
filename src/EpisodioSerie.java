public class EpisodioSerie extends ConteudoAudiovisual {
    private int numeroTemporada;
    private int numeroEpisodio;
    private Serie serie;

    public EpisodioSerie(String nome, String imagem, int anoDeLancamento, String sinopse, int duracaoEmMinutos,
                         int numeroTemporada, int numeroEpisodio, Serie serie) {
        super("Episodio", nome, imagem, anoDeLancamento, sinopse, duracaoEmMinutos);
        this.numeroTemporada = numeroTemporada;
        this.numeroEpisodio = numeroEpisodio;
        this.serie = serie;
    }

    public Serie getSerie() {
        return serie;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Temporada: " + numeroTemporada);
        System.out.println("Episódio: " + numeroEpisodio);
        System.out.println("Pertence à série: " + (serie != null ? serie.nome : "Desconhecida"));
    }
}
