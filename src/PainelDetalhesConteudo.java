import javax.swing.*;
import java.awt.*;

public class PainelDetalhesConteudo  {

    public static void exibir(JPanel painelPrincipal, JPanel painelCards, ConteudoAudiovisual titulo) {
        painelPrincipal.removeAll();
        String fonte = "SansSerif";

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(30, 30, 30));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //imagem
        JPanel imagemPanel = new JPanel();
        imagemPanel.setLayout(new BoxLayout(imagemPanel, BoxLayout.Y_AXIS));
        imagemPanel.setBackground(new Color(30, 30, 30));
        imagemPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        ImageIcon imagemOriginal = new ImageIcon(titulo.getImagem());
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagemPanel.add(imagemLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(30, 30, 30));

        JLabel nome = new JLabel(titulo.getNome());
        nome.setFont(new Font(fonte, Font.BOLD, 26));
        nome.setForeground(Color.WHITE);
        nome.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nome);

        JLabel avaliacao = new JLabel("⭐ Avaliação: " + titulo.getMediaAvaliacoes() + "/10");
        avaliacao.setFont(new Font(fonte, Font.PLAIN, 18));
        avaliacao.setForeground(Color.LIGHT_GRAY);
        avaliacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(avaliacao);

        // Botão de avaliação (Filme ou Documentário)
        if (titulo.getTipo().equalsIgnoreCase("Filme") || titulo.getTipo().equalsIgnoreCase("Documentario")) {
            infoPanel.add(Box.createVerticalStrut(30));
            JButton avaliar = new JButton("Adicionar Avaliação");
            botaoAvaliarTitulo(avaliar, titulo, avaliacao, painelPrincipal);
            avaliar.setAlignmentX(Component.CENTER_ALIGNMENT);
            avaliar.setBackground(new Color(255, 215, 0)); // dourado
            avaliar.setForeground(Color.BLACK);
            avaliar.setFocusPainted(false);
            avaliar.setFont(new Font(fonte, Font.BOLD, 14));
            avaliar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding
            infoPanel.add(avaliar);
        }

        imagemPanel.add(infoPanel);

        // === Painel de Detalhes e Abas ===
        JPanel detalhesPanel = new JPanel();
        detalhesPanel.setLayout(new BoxLayout(detalhesPanel, BoxLayout.Y_AXIS));
        detalhesPanel.setBackground(new Color(30, 30, 30));
        detalhesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

// Label: Ano e Diretor
        JLabel anoDiretor = new JLabel(titulo.getAnoDeLancamento() + " | Dirigido por: " + titulo.getDiretor());
        anoDiretor.setFont(new Font(fonte, Font.PLAIN, 14));
        anoDiretor.setForeground(Color.LIGHT_GRAY);

// Label: Descrição/Sinopse
        JLabel descricao = new JLabel("<html><div style='width: 500px;'>" + titulo.getSinopse() + "</div></html>");
        descricao.setFont(new Font(fonte, Font.PLAIN, 14));
        descricao.setForeground(Color.WHITE);

// Abas
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(new Color(45, 45, 45));
        abas.setForeground(Color.WHITE);
        abas.setFont(new Font(fonte, Font.BOLD, 14));

// Aba: Informações Gerais
        JPanel abaInfoGeral = new JPanel();
        abaInfoGeral.setLayout(new BoxLayout(abaInfoGeral, BoxLayout.Y_AXIS));
        abaInfoGeral.setBackground(new Color(45, 45, 45));
        abaInfoGeral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        abaInfoGeral.add(anoDiretor);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        abaInfoGeral.add(descricao);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        JLabel duracaoLabel = new JLabel("⏱ Duração: " + titulo.getDuracaoEmMinutos() + " min");
        duracaoLabel.setForeground(Color.WHITE);
        duracaoLabel.setFont(new Font(fonte, Font.PLAIN, 14));
        abaInfoGeral.add(duracaoLabel);
        abas.addTab("Informações Gerais", abaInfoGeral);

// Aba: Gêneros
        // Aba: Gêneros (apenas para Filme e Série)
        if (titulo instanceof Filme filme) {
            JPanel abaGeneros = new JPanel();
            abaGeneros.setLayout(new BoxLayout(abaGeneros, BoxLayout.Y_AXIS));
            abaGeneros.setBackground(new Color(45, 45, 45));

            JLabel generoLabel = new JLabel("🎞 Gêneros: " + String.join(", ", filme.getGeneros()));
            generoLabel.setForeground(Color.WHITE);
            generoLabel.setFont(new Font(fonte, Font.PLAIN, 14));

            abaGeneros.add(Box.createVerticalStrut(10));
            abaGeneros.add(generoLabel);
            abas.addTab("Gêneros", abaGeneros);

        } else if (titulo instanceof Serie serie) {
            JPanel abaGeneros = new JPanel();
            abaGeneros.setLayout(new BoxLayout(abaGeneros, BoxLayout.Y_AXIS));
            abaGeneros.setBackground(new Color(45, 45, 45));

            JLabel generoLabel = new JLabel("🎞 Gêneros: " + String.join(", ", serie.getGeneros()));
            generoLabel.setForeground(Color.WHITE);
            generoLabel.setFont(new Font(fonte, Font.PLAIN, 14));

            abaGeneros.add(Box.createVerticalStrut(10));
            abaGeneros.add(generoLabel);
            abas.addTab("Gêneros", abaGeneros);
        }

// Aba: Episódios
        if (titulo.getTipo().equalsIgnoreCase("Serie") && titulo instanceof Serie serie) {
            JPanel abaEpisodios = new JPanel();
            abaEpisodios.setLayout(new BoxLayout(abaEpisodios, BoxLayout.Y_AXIS));
            abaEpisodios.setBackground(new Color(45, 45, 45));
            abaEpisodios.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel infoEps = new JLabel("📺 " + serie.getNumeroDeEpisodios() + " episódios:");
            infoEps.setForeground(Color.WHITE);
            infoEps.setFont(new Font(fonte, Font.BOLD, 14));
            abaEpisodios.add(infoEps);
            abaEpisodios.add(Box.createVerticalStrut(10));

            for (EpisodioSerie ep : serie.getEpisodios()) {
                JPanel epPanel = new JPanel(new BorderLayout());
                epPanel.setBackground(new Color(60, 60, 60));
                epPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

                JLabel tituloEp = new JLabel("S" + ep.getNumeroTemporada() + "E" + ep.getNumeroEpisodio() + " - " + ep.getNome());
                tituloEp.setForeground(Color.WHITE);
                tituloEp.setFont(new Font(fonte, Font.PLAIN, 13));

                JButton avaliarEpBtn = new JButton("Avaliar");
                avaliarEpBtn.setFocusPainted(false);
                avaliarEpBtn.setBackground(new Color(100, 100, 255));
                avaliarEpBtn.setForeground(Color.WHITE);
                avaliarEpBtn.setFont(new Font(fonte, Font.PLAIN, 12));
                botaoAvaliarEpisodio(avaliarEpBtn, ep);

                epPanel.add(tituloEp, BorderLayout.WEST);
                epPanel.add(avaliarEpBtn, BorderLayout.EAST);
                abaEpisodios.add(epPanel);
                abaEpisodios.add(Box.createVerticalStrut(5));
            }

            JScrollPane scrollEps = new JScrollPane(abaEpisodios);
            scrollEps.setPreferredSize(new Dimension(550, 200));
            abas.addTab("Episódios", scrollEps);
        }

// Adiciona componentes principais
        detalhesPanel.add(nome);
        detalhesPanel.add(Box.createVerticalStrut(5));
        detalhesPanel.add(avaliacao);
        detalhesPanel.add(Box.createVerticalStrut(10));
        detalhesPanel.add(abas);

// Botão Voltar
        JButton voltar = new JButton("⬅ Voltar");
        voltar.setFont(new Font(fonte, Font.PLAIN, 14));
        voltar.setFocusPainted(false);
        voltar.setBackground(new Color(80, 80, 80));
        voltar.setForeground(Color.WHITE);
        voltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        voltar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        voltar.addActionListener(e -> {
            painelPrincipal.removeAll();
            painelPrincipal.add(new JScrollPane(painelCards), BorderLayout.CENTER);
            painelPrincipal.revalidate();
            painelPrincipal.repaint();
        });

        detalhesPanel.add(Box.createVerticalStrut(20));
        detalhesPanel.add(voltar);

// Layout final
        container.add(imagemPanel, BorderLayout.WEST);
        container.add(detalhesPanel, BorderLayout.CENTER);
        painelPrincipal.add(container, BorderLayout.CENTER);
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    private static void botaoAvaliarTitulo(JButton botao, ConteudoAudiovisual titulo, JLabel labelAvaliacao, JPanel painelPrincipal) {
        botao.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null, "Digite uma nota de 0 a 10:", "Nova Avaliação", JOptionPane.PLAIN_MESSAGE);
            if (input != null) {
                try {
                    double nota = Double.parseDouble(input);
                    if (nota >= 0 && nota <= 10) {
                        titulo.avaliar(nota);
                        new BancoDeDados("dados.csv").AtualizarTitulo(titulo);
                        labelAvaliacao.setText("⭐ Avaliação: " + titulo.getMediaAvaliacoes() + "/10");
                        painelPrincipal.revalidate();
                        painelPrincipal.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "A nota deve ser entre 0 e 10.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido.");
                }
            }
        });
    }

    private static void botaoAvaliarEpisodio(JButton botao, EpisodioSerie episodio) {
        botao.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null, "Nota para o episódio:", "Avaliação de Episódio", JOptionPane.PLAIN_MESSAGE);
            if (input != null) {
                try {
                    double nota = Double.parseDouble(input);
                    if (nota >= 0 && nota <= 10) {
                        // episodio.adicionarAvaliacao(nota);
                        JOptionPane.showMessageDialog(null, "Nota registrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "A nota deve ser entre 0 e 10.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido.");
                }
            }
        });
    }
}