import CSV.BancoDeDados;
import InterfaceGráfica.PaginaPrincipal;

public class Main {
        public static void main(String[] args) {
            BancoDeDados arquivos = new BancoDeDados("dados.csv");
            new PaginaPrincipal().iniciar(arquivos);
        }
}


