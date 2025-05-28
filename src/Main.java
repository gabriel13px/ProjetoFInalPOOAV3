public class Main {
    public static void main(String[] args) {
        BancoDeDados Arquivos = new BancoDeDados("dados.csv");
        Arquivos.AdicionarTitulo();
        Arquivos.LerBancodeDados();
    }
}
