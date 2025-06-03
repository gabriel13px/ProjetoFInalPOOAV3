public class EpisodioSerie extends ConteudoAudiovisual {
    private int numeroTemporada;
    private int numeroEpisodio;
    private String nomeSeriePertencente;

    public EpisodioSerie(String nome, String imagem, int anoDeLancamento, String sinopse, int duracaoEmMinutos,String diretor,int totalDeAvaliacoes, int somaAvaliacoes, int numeroTemporada, int numeroEpisodio, String serie) {
        super("Episodio", nome, imagem, anoDeLancamento, sinopse, duracaoEmMinutos,diretor);
        this.numeroTemporada = numeroTemporada;
        this.numeroEpisodio = numeroEpisodio;
        this.nomeSeriePertencente = serie;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public String getNomeSeriePertencente() {
        return nomeSeriePertencente;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public String getSeriePertencente() {
        return nomeSeriePertencente;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Temporada: " + numeroTemporada);
        System.out.println("Episódio: " + numeroEpisodio);
        System.out.println("Pertence à série: " + (nomeSeriePertencente != null ? nomeSeriePertencente : "Desconhecida"));
    }
    @Override
    public String stringBancoDados() {
        return new String(super.stringBancoDados()+",-,"+numeroTemporada+","+numeroEpisodio+","+ nomeSeriePertencente);
    }
}
