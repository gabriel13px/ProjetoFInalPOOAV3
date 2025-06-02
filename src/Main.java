import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BancoDeDados arquivos = new BancoDeDados("dados.csv");




        // Lista de filmes (nome + imagem + avaliação)
        ArrayList<Filme> filmes = new ArrayList<>();
        filmes.add(new Filme("Matrix", "matrix.jpg", 9.0, "Uma realidade distorcida."));
        filmes.add(new Filme("Interestelar", "interestelar.jpg", 8.5, "Viagem espacial e buracos negros."));
        filmes.add(new Filme("Oppenheimer", "oppenheimer.jpg", 8.8, "A história de criação da bomba atômica."));

        // Janela principal
        JFrame frame = new JFrame("Catálogo de Filmes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Painel para os cards, com layout em grade
        JPanel painelCards = new JPanel();
        painelCards.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Fluxo de cards
        painelCards.setPreferredSize(new Dimension(780, 1000));  // Ajuste para o painel rolável

        // Adiciona cada filme como um card
        ArrayList<ConteudoAudiovisual> Titulos = arquivos.Titulos;
        for (ConteudoAudiovisual Titulo : Titulos) {
            JPanel card = criarCard(Titulo);
            painelCards.add(card);
        }

        // Scroll para o painel de cards
        JScrollPane scrollPane = new JScrollPane(painelCards);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    // Função para criar um card de filme
    public static JPanel criarCard(ConteudoAudiovisual Titulo) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setPreferredSize(new Dimension(200, 300));  // Tamanho do card

        // Imagem do filme
        ImageIcon imagem = new ImageIcon(Titulo.getImagem());
        JLabel imgLabel = new JLabel(imagem);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);

        // Título do filme
        JLabel titulo = new JLabel(Titulo.getNome());
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 14));

        // Adiciona a imagem e título
        card.add(imgLabel, BorderLayout.CENTER);
        card.add(titulo, BorderLayout.SOUTH);

        // Ação ao clicar no card
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exibirDetalhesDoFilme(Titulo);
            }
        });

        return card;
    }

    // Exibe os detalhes do filme em uma nova janela
    public static void exibirDetalhesDoFilme(ConteudoAudiovisual Titulo) {
        JFrame detalheFrame = new JFrame(Titulo.getNome());
        detalheFrame.setSize(400, 300);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Imagem do filme
        ImageIcon imagem = new ImageIcon(Titulo.getImagem());
        JLabel imgLabel = new JLabel(imagem);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Informações do filme
        JLabel titulo = new JLabel(Titulo.getNome());
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel avaliacao = new JLabel("Avaliação: " +Titulo.getMediaAvaliacoes()  + "/10");
        avaliacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("<html><div style='width: 300px;'>" + Titulo.getSinopse() + "</div></html>");
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão para voltar
        JButton voltar = new JButton("Voltar");
        voltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detalheFrame.dispose(); // Fecha a janela de detalhes
            }
        });

        painel.add(imgLabel);
        painel.add(titulo);
        painel.add(avaliacao);
        painel.add(descricao);
        painel.add(voltar);

        detalheFrame.add(painel);
        detalheFrame.setVisible(true);
    }

    // Classe auxiliar para representar um filme
    static class Filme {
        String nome;
        String imagem;
        double avaliacao;
        String descricao;

        Filme(String nome, String imagem, double avaliacao, String descricao) {
            this.nome = nome;
            this.imagem = imagem;
            this.avaliacao = avaliacao;
            this.descricao = descricao;
        }
    }
}