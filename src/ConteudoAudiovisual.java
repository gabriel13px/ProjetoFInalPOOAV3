public abstract class ConteudoAudiovisual implements Midia {
    protected String tipo;
    protected String nome;
    protected String imagem;
    protected int anoDeLancamento;
    protected String diretor;
    protected String sinopse;
    protected int duracaoEmMinutos;
    protected int somaAvaliacoes;
    protected int totalDeAvaliacoes;

    public ConteudoAudiovisual(String tipo, String nome, String imagem, int anoDeLancamento, String sinopse, int duracaoEmMinutos,String diretor) {
        this.tipo = tipo;
        this.nome = nome;
        this.imagem = imagem;
        this.anoDeLancamento = anoDeLancamento;
        this.sinopse = sinopse;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.diretor = diretor;
        this.somaAvaliacoes = 0;
        this.totalDeAvaliacoes = 0;
    }
    public String getNome() {
        return nome;
    }
    public String getImagem() {
        return imagem;}
    public String getSinopse() {return sinopse;}
    @Override
    public void avaliar(int nota) {
        somaAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    @Override
    public double getMediaAvaliacoes() {
        if (totalDeAvaliacoes == 0) return 0;
        return (double) somaAvaliacoes / totalDeAvaliacoes;
    }

    public void adicionarEpisodio(EpisodioSerie episodio){
    };

    // Pode ser sobrescrito nas subclasses
    @Override
    public void exibirInformacoes() {
        System.out.println("Título: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Ano: " + anoDeLancamento);
        System.out.println("Duração: " + duracaoEmMinutos + " min");
        System.out.println("Sinopse: " + sinopse);
        System.out.printf("Média de Avaliação: %.1f\n", getMediaAvaliacoes());
    }
    @Override
    public String stringBancoDados(){
        return new String(nome+","+tipo+","+imagem+","+anoDeLancamento+","+duracaoEmMinutos+","+diretor+","+somaAvaliacoes+","+totalDeAvaliacoes+","+sinopse);
    }


}