public class Documentario extends ConteudoAudiovisual {
    public Documentario(String nome, String imagem, int ano, String sinopse, int duraçãoEmMinutos, String diretor, int totalDeAvaliacoes, int somaAvaliacoes) {
        super("Documentario", nome, imagem, ano, sinopse, duraçãoEmMinutos,diretor);
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.somaAvaliacoes = somaAvaliacoes;


    }

    @Override
    public String stringBancoDados() {
        return new String(super.stringBancoDados()+",-,-,-,-\n");
    }
}
