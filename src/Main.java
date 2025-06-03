import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BancoDeDados arquivos = new BancoDeDados("dados.csv");

        // Janela principal
        JFrame frame = new JFrame("Catálogo de Filmes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setBackground(Color.BLACK);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(48, 52, 54));
        header.setPreferredSize(new Dimension(800, 60));
        //-----
        JLabel logo = new JLabel("🎬 FLIXTTY");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Gotham", Font.ROMAN_BASELINE, 24));
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        header.add(logo, BorderLayout.WEST);
        //---
        JPanel painelBuscaHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
        painelBuscaHeader.setOpaque(false);
        JTextField campoBusca = new JTextField(20);
        campoBusca.setBackground(Color.WHITE);
        campoBusca.setForeground(Color.DARK_GRAY);
        campoBusca.setFont(new Font("Ghotam", Font.PLAIN, 14));

        campoBusca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campoBusca.setCaretColor(Color.BLUE);
        //------
        JButton botaoBusca = new JButton("Buscar");
        botaoBusca.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoBusca.setBackground(new Color(0, 123, 255));
        botaoBusca.setForeground(Color.WHITE);
        botaoBusca.setFocusPainted(false);
        Color corNormal = new Color(0, 123, 255);
        Color corHover = new Color(222, 71, 71);
        botaoBusca.setBackground(corNormal);
        botaoBusca.setFocusPainted(false);
        botaoBusca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoBusca.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evento) {
                botaoBusca.setBackground(corHover);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evento) {
                botaoBusca.setBackground(corNormal);
            }
        });
        //------
        String[] opcoesTipo = {"Todos", "Filme", "Série", "Documentário"};
        JComboBox<String> filtroTipo = new JComboBox<>(opcoesTipo);
        filtroTipo.setSelectedIndex(0);
        filtroTipo.setBackground(new Color(40, 40, 40));
        filtroTipo.setForeground(Color.WHITE);
        filtroTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        filtroTipo.setPreferredSize(new Dimension(150, 30));
        filtroTipo.setBorder(BorderFactory.createEmptyBorder());
        filtroTipo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(new Color(40, 40, 40));
                button.setForeground(Color.WHITE);
                return button;
            }
        });


        painelBuscaHeader.add(campoBusca);
        painelBuscaHeader.add(botaoBusca);
        painelBuscaHeader.add(filtroTipo);
        header.add(painelBuscaHeader, BorderLayout.CENTER);
        //------
        JButton botaoAdicionar = new JButton("➕ Adicionar Título");
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.addActionListener(e -> abrirFormularioAdicionar(frame));
        header.add(botaoAdicionar, BorderLayout.EAST);
        frame.add(header, BorderLayout.NORTH);

        // Painel para os cards com layout em fluxo
        JPanel painelCards = new JPanel();
        painelCards.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelCards.setPreferredSize(new Dimension(780, 1000));
        painelCards.setBackground(new Color(0, 0, 0));

        // Scroll para os cards
        JScrollPane scrollPane = new JScrollPane(painelCards);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Painel principal com layout de borda
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Atualiza os cards na tela
        Runnable atualizarCards = () -> {
            painelCards.removeAll();
            ArrayList<ConteudoAudiovisual> titulos = arquivos.getTitulos();
            String textoBusca = campoBusca.getText().toLowerCase();
            String tipoSelecionado = filtroTipo.getSelectedItem().toString();

            for (ConteudoAudiovisual titulo : titulos) {
                boolean nomeCorresponde = textoBusca.isEmpty() || titulo.getNome().toLowerCase().contains(textoBusca);
                boolean tipoCorresponde = tipoSelecionado.equals("Todos") ||
                        (tipoSelecionado.equals("Filme") && titulo instanceof Filme) ||
                        (tipoSelecionado.equals("Série") && titulo instanceof Serie) ||
                        (tipoSelecionado.equals("Documentário") && titulo instanceof Documentario);

                if (nomeCorresponde && tipoCorresponde) {
                    painelCards.add(criarCard(titulo));
                }
            }

            painelCards.revalidate();
            painelCards.repaint();
        };
        filtroTipo.addActionListener(e -> atualizarCards.run());
        botaoBusca.addActionListener(e -> atualizarCards.run());
        campoBusca.addActionListener(e -> atualizarCards.run());

        atualizarCards.run();

        frame.add(painelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static JPanel criarCard(ConteudoAudiovisual titulo) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 320));
        card.setBackground(Color.DARK_GRAY);

        ImageIcon originalIcon = new ImageIcon(titulo.getImagem());
        Image imagemRedimensionada = originalIcon.getImage().getScaledInstance(200, 320, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel nomeLabel = new JLabel(titulo.getNome());
        nomeLabel.setForeground(Color.WHITE);
        nomeLabel.setHorizontalAlignment(JLabel.CENTER);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel infoLabel = new JLabel();
        infoLabel.setForeground(Color.LIGHT_GRAY);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Texto específico conforme o tipo
        if (titulo instanceof Filme) {
            Filme filme = (Filme) titulo;
            //infoLabel.setText("🎞 Gênero: " );
        } else if (titulo instanceof Serie) {
            Serie serie = (Serie) titulo;
            infoLabel.setText(serie.getNumeroDeEpisodios()+ " eps.");
        } else if (titulo instanceof Documentario) {
            Documentario documentario = (Documentario) titulo;
            //infoLabel.setText("📚 Tema: " + d.getTema());
        } else {
            infoLabel.setText("🎬 Tipo: " + titulo.getTipo());
        }

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nomeLabel);
        infoPanel.add(infoLabel);

        card.add(imagemLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Container parent = card.getParent();
                while (!(parent instanceof JFrame) && parent != null) {
                    parent = parent.getParent();
                }

                if (parent instanceof JFrame) {
                    JFrame frame = (JFrame) parent;
                    JPanel painelPrincipal = (JPanel) frame.getContentPane().getComponent(1); // considerando header = 0
                    exibirDetalhesNoPainel(painelPrincipal, (JPanel) card.getParent(), titulo);
                }
            }
        });

        return card;
    }

    public static void exibirDetalhesNoPainel(JPanel painelPrincipal, JPanel painelCards, ConteudoAudiovisual titulo) {
        painelPrincipal.removeAll();

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(30, 30, 30));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

// === Imagem com informações abaixo ===
        JPanel imagemPanel = new JPanel();
        imagemPanel.setLayout(new BoxLayout(imagemPanel, BoxLayout.Y_AXIS));
        imagemPanel.setBackground(new Color(30, 30, 30));
        imagemPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        ImageIcon imagemOriginal = new ImageIcon(titulo.getImagem());
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagemPanel.add(imagemLabel);

// === Nome, Avaliação e Botão abaixo da Imagem ===
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(30, 30, 30));

        JLabel nome = new JLabel(titulo.getNome());
        nome.setFont(new Font("Arial", Font.BOLD, 26));
        nome.setForeground(Color.WHITE);
        nome.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nome);

        JLabel avaliacao = new JLabel("⭐ Avaliação: " + titulo.getMediaAvaliacoes() + "/10");
        avaliacao.setFont(new Font("Arial", Font.PLAIN, 18));
        avaliacao.setForeground(Color.LIGHT_GRAY);
        avaliacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(avaliacao);

// Se for filme ou documentário, exibe o botão abaixo da avaliação
        if (titulo.getTipo().equalsIgnoreCase("Filme") || titulo.getTipo().equalsIgnoreCase("Documentario")) {
            infoPanel.add(Box.createVerticalStrut(10));
            JButton avaliarBtn = new JButton("⭐ Adicionar Avaliação");
            avaliarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            avaliarBtn.setBackground(new Color(70, 130, 180));
            avaliarBtn.setForeground(Color.WHITE);
            avaliarBtn.setFocusPainted(false);
            avaliarBtn.setFont(new Font("Arial", Font.PLAIN, 14));
            infoPanel.add(avaliarBtn);
        }

// Adicionando a parte com nome, avaliação e botão abaixo da imagem
        imagemPanel.add(infoPanel);

// === Painel de conteúdo à direita ===
        JPanel detalhesPanel = new JPanel();
        detalhesPanel.setLayout(new BoxLayout(detalhesPanel, BoxLayout.Y_AXIS));
        detalhesPanel.setBackground(new Color(30, 30, 30));

        JLabel anoDiretor = new JLabel(titulo.getAnoDeLancamento() + " | Dirigido por: " + titulo.getDiretor());
        anoDiretor.setFont(new Font("Arial", Font.PLAIN, 14));
        anoDiretor.setForeground(Color.GRAY);

        JLabel descricao = new JLabel("<html><div style='width: 500px;'>" + titulo.getSinopse() + "</div></html>");
        descricao.setFont(new Font("Arial", Font.PLAIN, 14));
        descricao.setForeground(Color.WHITE);

// === Abas com informações específicas ===
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(new Color(45, 45, 45));
        abas.setForeground(Color.WHITE);
        abas.setFont(new Font("Arial", Font.PLAIN, 14));

// Aba: Informações Gerais
        JPanel abaInfoGeral = new JPanel();
        abaInfoGeral.setLayout(new BoxLayout(abaInfoGeral, BoxLayout.Y_AXIS));
        abaInfoGeral.setBackground(new Color(45, 45, 45));
        abaInfoGeral.setForeground(Color.WHITE);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        abaInfoGeral.add(anoDiretor);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        abaInfoGeral.add(descricao);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        abaInfoGeral.add(new JLabel("⏱ Duração: " + titulo.getDuracaoEmMinutos() + " min"));

        abas.addTab("Informações Gerais", abaInfoGeral);

// Aba: Gêneros (Filme ou Documentário)
        if (titulo.getTipo().equalsIgnoreCase("Filme") || titulo.getTipo().equalsIgnoreCase("Documentario")) {
            if (titulo instanceof Filme filme) {
                JPanel abaGeneros = new JPanel();
                abaGeneros.setLayout(new BoxLayout(abaGeneros, BoxLayout.Y_AXIS));
                abaGeneros.setBackground(new Color(45, 45, 45));
                abaGeneros.setForeground(Color.WHITE);

                JLabel generoLabel = new JLabel("🎞 Gêneros: " + String.join(", ", filme.getGeneros()));
                generoLabel.setForeground(Color.WHITE);
                abaGeneros.add(generoLabel);

                abas.addTab("Gêneros", abaGeneros);
            }
        }

// Aba: Episódios (Série)
        if (titulo.getTipo().equalsIgnoreCase("Serie") && titulo instanceof Serie serie) {
            JPanel abaEpisodios = new JPanel();
            abaEpisodios.setLayout(new BoxLayout(abaEpisodios, BoxLayout.Y_AXIS));
            abaEpisodios.setBackground(new Color(45, 45, 45));

            JLabel infoEps = new JLabel("📺 " + serie.getNumeroDeEpisodios() + " episódios:");
            infoEps.setForeground(Color.WHITE);
            infoEps.setFont(new Font("Arial", Font.BOLD, 14));
            abaEpisodios.add(infoEps);
            abaEpisodios.add(Box.createVerticalStrut(10));

            for (EpisodioSerie ep : serie.getEpisodios()) {
                JPanel epPanel = new JPanel(new BorderLayout());
                epPanel.setBackground(new Color(60, 60, 60));
                epPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JLabel tituloEp = new JLabel("S" + ep.getNumeroTemporada() + "E" + ep.getNumeroEpisodio() + " - " + ep.getNome());
                tituloEp.setForeground(Color.WHITE);

                JButton avaliarEpBtn = new JButton("Avaliar");
                avaliarEpBtn.setFont(new Font("Arial", Font.PLAIN, 12));
                avaliarEpBtn.setFocusPainted(false);
                avaliarEpBtn.setBackground(new Color(100, 100, 100));
                avaliarEpBtn.setForeground(Color.WHITE);

                epPanel.add(tituloEp, BorderLayout.WEST);
                epPanel.add(avaliarEpBtn, BorderLayout.EAST);
                abaEpisodios.add(epPanel);
                abaEpisodios.add(Box.createVerticalStrut(5));
            }

            abas.addTab("Episódios", new JScrollPane(abaEpisodios));
        }

        detalhesPanel.add(nome);
        detalhesPanel.add(Box.createVerticalStrut(5));
        detalhesPanel.add(avaliacao);
        detalhesPanel.add(Box.createVerticalStrut(10));
        detalhesPanel.add(abas);

// Botão Voltar
        JButton voltar = new JButton("⬅ Voltar");
        voltar.setFont(new Font("Arial", Font.PLAIN, 14));
        voltar.setFocusPainted(false);
        voltar.setBackground(new Color(70, 70, 70));
        voltar.setForeground(Color.WHITE);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.addActionListener(e -> {
            painelPrincipal.removeAll();
            painelPrincipal.add(new JScrollPane(painelCards), BorderLayout.CENTER);
            painelPrincipal.revalidate();
            painelPrincipal.repaint();
        });

        detalhesPanel.add(Box.createVerticalStrut(20));
        detalhesPanel.add(voltar);

        container.add(imagemPanel, BorderLayout.WEST);
        container.add(detalhesPanel, BorderLayout.CENTER);

        painelPrincipal.add(container, BorderLayout.CENTER);
        painelPrincipal.revalidate();
        painelPrincipal.repaint();


    }

    public static void abrirFormularioAdicionar(JFrame parent) {
        JFrame form = new JFrame("Adicionar Novo Título");
        form.setSize(400, 400);
        form.setLocationRelativeTo(parent);
        form.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField nomeField = new JTextField();
        JTextField imagemField = new JTextField();
        JTextField sinopseField = new JTextField();
        JTextField avaliacaoField = new JTextField();

        form.add(new JLabel("Nome:"));
        form.add(nomeField);

        form.add(new JLabel("Caminho da Imagem:"));
        form.add(imagemField);

        form.add(new JLabel("Sinopse:"));
        form.add(sinopseField);

        form.add(new JLabel("Avaliação (0-10):"));
        form.add(avaliacaoField);

        JButton salvar = new JButton("Salvar");
        salvar.addActionListener(e -> {
            try {
                String nome = nomeField.getText().trim();
                String imagem = imagemField.getText().trim();
                String sinopse = sinopseField.getText().trim();
                double nota = Double.parseDouble(avaliacaoField.getText().trim());

                // Aqui você poderá criar a instância concreta e adicionar ao CSV no BancoDeDados
                System.out.println("Novo título criado (mas ainda não adicionado): " + nome);
                JOptionPane.showMessageDialog(form, "Título salvo com sucesso (simulado).\nAtualize manualmente a lista.");
                form.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(form, "Avaliação inválida. Digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> form.dispose());

        form.add(salvar);
        form.add(cancelar);

        form.setVisible(true);
    }
}
