package CSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import Tipos.*;

//creio que a forma que vai ser usado é, ao abrir o app ele verifica se o banco de dados existe e coloca para dentro do app
//vai ter uma opção de formulario para adicionar o filme, serie bla bla bla, é bom ver se vai permitir que o usuario escreva
//caso não se for botoes isso simplifica a logica do banco de dados pois poderia se deixar um int ao inves de string
//a função vai atualizar o banco de dados, mas custa ver se é melhor ja adicionar diretamente ao app ou ele recarregar com o novo filme adicionado

public class BancoDeDados {

    private String csvFile;
    private File arquivo;
    private FileWriter writer = null;
    private String linha;
    private ArrayList<ConteudoAudiovisual> Titulos;

    public BancoDeDados(String csvFile){
    this.csvFile = csvFile;
    this.arquivo = new File(this.csvFile);
    aberturaBancoDeDados();
}

    public ArrayList<ConteudoAudiovisual> getTitulos() {
        return Titulos;
    }


    private void aberturaBancoDeDados() {
        if (arquivo.exists()) {
            System.out.println("O banco de dados já existe.");
            LerBancodeDados();
            ReescreverDados();
        } else {
            System.out.println("O banco de dados NÃO existe. sera necessario criar um novo");
            try {
                writer = new FileWriter(csvFile);
                writer.append("Nome,Tipo,Imagem,Data de Lançamento,Duração em Minutos,Diretor,Soma das Avaliações,Quantidade de Avaliações,sinopse,Gêneros,Numero temporada,Numero Epsodio,Tipos.Serie pertencente\n");
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
        Titulos = new ArrayList<ConteudoAudiovisual>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] dados = linha.split(",");
                if(dados.length == 13){
                    if(!dados[1].equals("Episodio")&& BuscarTitulo(dados[0])==-1) {
                        String nome = dados[0];
                        String imagem = dados[2];
                        int dataDeLancamento = Integer.parseInt(dados[3]);
                        int duracao = Integer.parseInt(dados[4]);
                        String diretor = dados[5];
                        int somaAvaliacoes = Integer.parseInt(dados[6]);
                        int quantidadeAvaliacoes = Integer.parseInt(dados[7]);
                        String sinopse = dados[8];
                        String[] partes = dados[9].split("&");
                        Set<String> generos = new HashSet<>();
                        for (String genero : partes) {
                            generos.add(genero.trim());
                        }
                        switch(dados[1]){
                            case "Tipos.Serie":
                                Titulos.add(new Serie(nome,imagem,generos, dataDeLancamento,sinopse,duracao,diretor));
                                break;
                            case "Tipos.Filme":
                                Titulos.add(new Filme(nome,imagem,generos, dataDeLancamento,sinopse,duracao,diretor,quantidadeAvaliacoes, somaAvaliacoes));
                                break;
                            case "Tipos.Documentario":
                                Titulos.add(new Documentario(nome,imagem, dataDeLancamento,sinopse,duracao,diretor,quantidadeAvaliacoes, somaAvaliacoes));
                                break;

                        }
                    }else if(dados[1].equals("Episodio")&&BuscarTitulo(dados[12])!=-1){
                        Titulos.get(BuscarTitulo(dados[12])).adicionarEpisodio(new EpisodioSerie(dados[0], dados[2], Integer.parseInt(dados[3]), dados[8], Integer.parseInt(dados[4]), dados[5], Integer.parseInt(dados[7]), Integer.parseInt(dados[6]), Integer.parseInt(dados[10]), Integer.parseInt(dados[11]), dados[12]));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void AtualizarTitulo(ConteudoAudiovisual Titulo)  {
        if(BuscarTitulo(Titulo.getNome()) != -1){
            Titulos.set(BuscarTitulo(Titulo.getNome()), Titulo);
        }
    ReescreverDados();
    }
    public void AtualizarEpisodio(EpisodioSerie Titulo)  {
        if(BuscarTitulo(Titulo.getNomeSeriePertencente()) != -1){
            Serie serie = (Serie) Titulos.get(BuscarTitulo(Titulo.getSeriePertencente()));
            serie.atualizarEpisodio(Titulo);
            Titulos.set(BuscarTitulo(Titulo.getSeriePertencente()), serie);
        }
        ReescreverDados();
    }
    public void AtualizarBanco(ArrayList<ConteudoAudiovisual> titulos)  {
        try {
            writer = new FileWriter(csvFile);
            writer.append("Nome,Tipo,Imagem,Data de Lançamento,Duração em Minutos,Diretor,Soma das Avaliações,Quantidade de Avaliações,sinopse,Gêneros,Numero temporada,Numero Epsodio,Tipos.Serie pertencente\n");
            for(ConteudoAudiovisual conteudo : titulos) {
                writer.append(conteudo.stringBancoDados());
            }
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

    private int BuscarTitulo(String nome) {
        for(int titulo = 0; titulo < Titulos.size(); titulo++) {
            if(Titulos.get(titulo).getNome().equals(nome)) {
                return titulo;
            }

        }
        return -1;
    }
    private void ReescreverDados(){
        try {
            writer = new FileWriter(csvFile);
            writer.append("Nome,Tipo,Imagem,Data de Lançamento,Duração em Minutos,Diretor,Soma das Avaliações,Quantidade de Avaliações,sinopse,Gêneros,Numero temporada,Numero Epsodio,Tipos.Serie pertencente\n");
            for(ConteudoAudiovisual conteudo : Titulos) {
                writer.append(conteudo.stringBancoDados());
            }
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

}

