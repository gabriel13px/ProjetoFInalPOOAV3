package Tipos;

public abstract class ConteudoAudiovisual implements Midia, Calculos {
    private String tipo;
    private String nome;
    private String imagem;
    private int anoDeLancamento;
    private String diretor;
    private String sinopse;
    private int duracaoEmMinutos;
    private int somaAvaliacoes;
    private int totalDeAvaliacoes;

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

    @Override
    public void avaliar(double nota) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public int getSomaAvaliacoes() {
        return somaAvaliacoes;
    }

    public void setSomaAvaliacoes(int somaAvaliacoes) {
        this.somaAvaliacoes = somaAvaliacoes;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setTotalDeAvaliacoes(int totalDeAvaliacoes) {
        this.totalDeAvaliacoes = totalDeAvaliacoes;
    }
}