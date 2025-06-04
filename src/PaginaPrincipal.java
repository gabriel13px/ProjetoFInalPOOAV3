import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.ArrayList;

public class PaginaPrincipal {
    Color botaoESeletor = new Color(145, 66, 1);
    Color botaoPressionado = new Color(121, 0, 0);
    Color headercor = new Color(48, 52, 54);
    Color painelCardscor = new Color(30, 30, 30);
    String fonteLetras ="SansSerif";
    Color corletrasbotoes = new Color(197, 197, 197);
    public void iniciar(BancoDeDados arquivos) {

        JFrame janalaPrincipal = new JFrame("Catálogo de Filmes");
        janalaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janalaPrincipal.setSize(800, 600);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(headercor);
        header.setPreferredSize(new Dimension(800, 60));

        JLabel logo = new JLabel("🎬 FLIXTTY");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font(fonteLetras, Font.ROMAN_BASELINE, 24));
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        header.add(logo, BorderLayout.WEST);

        //painel busca-------
        JPanel painelBuscaHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
        painelBuscaHeader.setOpaque(false);

        JTextField campoBusca = new JTextField(20);
        campoBusca.setBackground(Color.WHITE);
        campoBusca.setForeground(Color.DARK_GRAY);
        campoBusca.setFont(new Font(fonteLetras, Font.PLAIN, 14));
        campoBusca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100,100), 1, false),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campoBusca.setCaretColor(Color.BLUE);

        JButton botaoBusca = new JButton("Buscar");
        botaoBusca.setFont(new Font(fonteLetras, Font.BOLD, 14));
        botaoBusca.setForeground(Color.WHITE);
        botaoBusca.setBackground(botaoESeletor);
        botaoBusca.setFocusPainted(false);
        botaoBusca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoBusca.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evento) {
                botaoBusca.setBackground(botaoPressionado);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evento) {
                botaoBusca.setBackground(botaoESeletor);
            }
        });
        //filtro-------------
        String[] opcoesTipo = {"Todos", "Filme", "Série", "Documentário"};
        JComboBox<String> filtroTipo = new JComboBox<>(opcoesTipo);
        filtroTipo.setSelectedIndex(0);
        filtroTipo.setBackground(botaoESeletor);
        filtroTipo.setForeground(corletrasbotoes);
        filtroTipo.setFont(new Font(fonteLetras, Font.PLAIN, 14));
        filtroTipo.setPreferredSize(new Dimension(150, 30));
        filtroTipo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setBorder(null);
                button.setBackground(botaoESeletor);
                button.setForeground(corletrasbotoes);
                return button;
            }
        });

        painelBuscaHeader.add(campoBusca);
        painelBuscaHeader.add(botaoBusca);
        painelBuscaHeader.add(filtroTipo);
        header.add(painelBuscaHeader, BorderLayout.CENTER);

        //botão adicionar-----
        JButton botaoAdicionar = new JButton("➕ Adicionar Título");
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setFont(new Font(fonteLetras, Font.BOLD, 14));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setBackground(headercor);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoAdicionar.setPreferredSize(new Dimension(150, 30));
        botaoAdicionar.setBorder(null);
        botaoAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evento) {
                botaoAdicionar.setBackground(botaoPressionado);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evento) {
                botaoAdicionar.setBackground(headercor);
            }
        });
        botaoAdicionar.addActionListener(e -> FormularioAdicionarTitulo.abrir(janalaPrincipal,arquivos.getTitulos()));
        header.add(botaoAdicionar, BorderLayout.EAST);
        janalaPrincipal.add(header, BorderLayout.NORTH);

        JPanel painelCards = new JPanel();
        painelCards.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelCards.setPreferredSize(new Dimension(780, 1000));
        painelCards.setBackground(painelCardscor);

        JScrollPane scrollPane = new JScrollPane(painelCards);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

// Dentro do escopo onde os componentes já estão criados
        filtroTipo.addActionListener(e -> atualizarCards(painelCards, arquivos, campoBusca, filtroTipo));
        botaoBusca.addActionListener(e -> atualizarCards(painelCards, arquivos, campoBusca, filtroTipo));
        campoBusca.addActionListener(e -> atualizarCards(painelCards, arquivos, campoBusca, filtroTipo));

// Primeira chamada
        atualizarCards(painelCards, arquivos, campoBusca, filtroTipo);

        janalaPrincipal.add(painelPrincipal, BorderLayout.CENTER);
        janalaPrincipal.setVisible(true);
    }
    public void atualizarCards(JPanel painelCards, BancoDeDados arquivos, JTextField campoBusca, JComboBox<String> filtroTipo) {
        painelCards.removeAll();
        String textoBusca = campoBusca.getText().toLowerCase();
        String tipoSelecionado = filtroTipo.getSelectedItem().toString();

        for (ConteudoAudiovisual titulo : arquivos.Titulos) {
            boolean nomeCorresponde = textoBusca.isEmpty() || titulo.getNome().toLowerCase().contains(textoBusca);
            boolean tipoCorresponde = tipoSelecionado.equals("Todos") ||
                    (tipoSelecionado.equals("Filme") && titulo instanceof Filme) ||
                    (tipoSelecionado.equals("Série") && titulo instanceof Serie) ||
                    (tipoSelecionado.equals("Documentário") && titulo instanceof Documentario);

            if (nomeCorresponde && tipoCorresponde) {
                CardConteudoAudiovisual card = new CardConteudoAudiovisual(titulo);
                painelCards.add(card.construir());
            }
        }
        painelCards.revalidate();
        painelCards.repaint();
    }
}
