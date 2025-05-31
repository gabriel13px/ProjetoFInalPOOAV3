public class Documentario extends ConteudoAudiovisual {
    public Documentario(String nome, String imagem, int ano, String sinopse, int duracao, int totalDeAvaliacoes, int somaAvaliacoes) {
        super("Documentario", nome, imagem, ano, sinopse, duracao);
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.somaAvaliacoes = somaAvaliacoes;


    }
}
