import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
//creio que a forma que vai ser usado é, ao abrir o app ele verifica se o banco de dados existe e coloca para dentro do app
//vai ter uma opção de formulario para adicionar o filme, serie bla bla bla, é bom ver se vai permitir que o usuario escreva
//caso não se for botoes isso simplifica a logica do banco de dados pois poderia se deixar um int ao inves de string
//a função vai atualizar o banco de dados, mas custa ver se é melhor ja adicionar diretamente ao app ou ele recarregar com o novo filme adicionado
public class LeituraBancoDeDados {
    public static void main(String[] args) {
        String csvFile = "dados.csv";
        //String csvFile = "C:\\Users\\SeuUsuario\\Documents\\dados.csv";
        File arquivo = new File(csvFile);
        FileWriter writer = null;
        String linha;

        //abertura do banco de dados
        if (arquivo.exists()) {
            System.out.println("O banco de dados já existe.");
        } else {
            System.out.println("O banco de dados NÃO existe. sera necessario criar um novo");
            try {
                writer = new FileWriter(csvFile);

                writer.append("Nome,Idade,Email\n");

                System.out.println("Arquivo CSV criado com sucesso!");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


        //atualização do banco de dados
        try {
            writer = new FileWriter(csvFile, true);
            //trocar por dados de um titulo novo na função dee adicionar titulo
            writer.append("Alice,30,alice@example.com\n");
            writer.append("Bob,25,bob@example.com\n");
            writer.append("Charlie,35,charlie@example.com\n");

            System.out.println("Arquivo CSV atualizado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //letura dos dados por linha, só subsituir por um objeto java(provavelmente vai ser usado no primeiro
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            boolean primeiraLinha = true;
            // pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split(",");

                // Exibe os dados da linha(trocar por objeto)
                for (String campo : dados) {
                    System.out.print(campo + " | ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}