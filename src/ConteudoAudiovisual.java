public abstract class ConteudoAudiovisual implements Midia {
    protected String tipo;
    protected String nome;
    protected String imagem;
    protected int anoDeLancamento;
    protected String sinopse;
    protected int duracaoEmMinutos;
    protected int somaAvaliacoes;
    protected int totalDeAvaliacoes;

    public ConteudoAudiovisual(String tipo, String nome, String imagem, int ano, String sinopse, int duracao) {
        this.tipo = tipo;
        this.nome = nome;
        this.imagem = imagem;
        this.anoDeLancamento = ano;
        this.sinopse = sinopse;
        this.duracaoEmMinutos = duracao;
        this.somaAvaliacoes = 0;
        this.totalDeAvaliacoes = 0;
    }

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
}