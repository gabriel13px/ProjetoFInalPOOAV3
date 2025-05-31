import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

//creio que a forma que vai ser usado é, ao abrir o app ele verifica se o banco de dados existe e coloca para dentro do app
//vai ter uma opção de formulario para adicionar o filme, serie bla bla bla, é bom ver se vai permitir que o usuario escreva
//caso não se for botoes isso simplifica a logica do banco de dados pois poderia se deixar um int ao inves de string
//a função vai atualizar o banco de dados, mas custa ver se é melhor ja adicionar diretamente ao app ou ele recarregar com o novo filme adicionado
//private String nome;

public class BancoDeDados {

    private String csvFile;
    //String csvFile = "C:\\Users\\SeuUsuario\\Documents\\dados.csv";
    private File arquivo;
    private FileWriter writer = null;
    private String linha;
    private ArrayList<Objects> Titulos;
    public BancoDeDados(String csvFile){
    this.csvFile = csvFile;
    this.arquivo = new File(this.csvFile);
    aberturaBancoDeDados();
}

    public ArrayList getTitulos() {
        return Titulos;
    }


    private void aberturaBancoDeDados() {
        if (arquivo.exists()) {
            System.out.println("O banco de dados já existe.");
            LerBancodeDados();
        } else {
            System.out.println("O banco de dados NÃO existe. sera necessario criar um novo");
            try {
                writer = new FileWriter(csvFile);
                writer.append("Nome,Tipo,Imagem,Data de Lançamento,Duração em Minutos,Diretor,Classificação,Quantidade de Avaliações,sinopse,Gêneros\n");
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
    }
    public void LerBancodeDados()  {
        Titulos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            boolean primeiraLinha = true;
            // pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] dados = linha.split(",");
                //Nome,Tipo,Imagem,Data de Lançamento,Duração em Minutos,Diretor,Classificação,Quantidade de Avaliações,sinopse,Gêneros

                switch(dados[1]){
                    case "Serie":

                        break;
                    case "Filme":

                        break;
                    case "EpSerie":

                        break;
                    case "Documentario":

                        break;

                }
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

    public void AdicionarTitulo() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile, true);
            //trocar por dados de um titulo novo na função dee adicionar titulo
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
    }
    public boolean IgualdadeTitulo(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null||this.getClass() != obj.getClass()){
            return false;
        }
        teste Ti = (teste) obj;
        return (CodigoIso.equals(pais.CodigoIso)&&CodigoIso.equals(pais.CodigoIso));
    }
}

