public class Titulo {

    private String nome;
    private int anoDeLancamento;
    private String sinopse;
    private int duracaoEmMinutos;
    private int somaAvaliacoes;
    private int totalDeAvaliacoes;
    private String diretor;

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setTotalDeAvaliacoes(int totalDeAvaliacoes) {
        this.totalDeAvaliacoes = totalDeAvaliacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
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

    public void exibeFicha(){
        System.out.println("Nome: " +nome);
        System.out.println("Ano de lançamento: "+anoDeLancamento);
    }

    public void avalia(double nota){
        somaAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double Media(){
        return somaAvaliacoes/totalDeAvaliacoes;
    }

}
