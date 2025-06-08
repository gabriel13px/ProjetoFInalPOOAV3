package InterfaceGráfica;

import CSV.BancoDeDados;
import Tipos.ConteudoAudiovisual;
import Tipos.EpisodioSerie;
import Tipos.Filme;
import Tipos.Serie;

import javax.swing.*;
import java.awt.*;

public class PainelDetalhesConteudo extends Style {

    public static void exibir(JPanel painelPrincipal, JPanel painelCards, ConteudoAudiovisual titulo) {
        painelPrincipal.removeAll();

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Style.principalCor);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel imagemPanel = new JPanel();
        imagemPanel.setLayout(new BoxLayout(imagemPanel, BoxLayout.Y_AXIS));
        imagemPanel.setBackground(Style.principalCor);
        imagemPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        ImageIcon imagemOriginal = new ImageIcon(titulo.getImagem());
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagemPanel.add(imagemLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Style.principalCor);

        JLabel nome = new JLabel(titulo.getNome());
        nome.setFont(new Font(fonteLetras, Font.BOLD, 26));
        nome.setForeground(Style.corLetras);
        nome.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nome);

        JLabel avaliacao = new JLabel("⭐ Avaliação: " + String.format("%.1f", titulo.getMediaAvaliacoes()) + "/10");
        avaliacao.setFont(new Font(fonteLetras, Font.PLAIN, 18));
        avaliacao.setForeground(corLetras);
        avaliacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (!titulo.getTipo().equalsIgnoreCase("Tipos.Serie")) {
            avaliacao.setVisible(true);
        }else{
            avaliacao.setVisible(false);
        }

        if (titulo.getTipo().equalsIgnoreCase("Tipos.Filme") || titulo.getTipo().equalsIgnoreCase("Tipos.Documentario")) {
            infoPanel.add(Box.createVerticalStrut(30));
            JButton avaliar = new JButton("Adicionar Avaliação");
            botaoAvaliarTitulo(avaliar, titulo, avaliacao, painelPrincipal);
            avaliar.setAlignmentX(Component.CENTER_ALIGNMENT);
            avaliar.setBackground(corBotao2);
            avaliar.setForeground(Color.BLACK);
            avaliar.setFocusPainted(false);
            avaliar.setFont(new Font(fonteLetras, Font.BOLD, 14));
            avaliar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            infoPanel.add(avaliar);
        }

        imagemPanel.add(infoPanel);
        JPanel detalhesPanel = new JPanel();
        detalhesPanel.setLayout(new BoxLayout(detalhesPanel, BoxLayout.Y_AXIS));
        detalhesPanel.setBackground(principalCor);
        detalhesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel anoDiretor = new JLabel(titulo.getAnoDeLancamento() + " | Dirigido por: " + titulo.getDiretor());
        anoDiretor.setFont(new Font(fonteLetras, Font.PLAIN, 14));
        anoDiretor.setForeground(Color.LIGHT_GRAY);

        JLabel descricao = new JLabel("<html><div style='width: 500px;'>" + titulo.getSinopse() + "</div></html>");
        descricao.setFont(new Font(fonteLetras, Font.PLAIN, 14));
        descricao.setForeground(Color.WHITE);

        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(new Color(45, 45, 45));
        abas.setForeground(Color.WHITE);
        abas.setFont(new Font(fonteLetras, Font.BOLD, 14));

        JPanel abaInfoGeral = new JPanel();
        abaInfoGeral.setLayout(new BoxLayout(abaInfoGeral, BoxLayout.Y_AXIS));
        abaInfoGeral.setBackground(Style.detalhesCor);
        abaInfoGeral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        abaInfoGeral.add(anoDiretor);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        abaInfoGeral.add(descricao);
        abaInfoGeral.add(Box.createVerticalStrut(10));
        JLabel duracaoLabel = new JLabel("⏱ Duração: " + titulo.getDuracaoEmMinutos() + " min");
        duracaoLabel.setForeground(Color.WHITE);
        duracaoLabel.setFont(new Font(fonteLetras, Font.PLAIN, 14));
        abaInfoGeral.add(duracaoLabel);
        abas.addTab("Informações Gerais", abaInfoGeral);


        if (titulo instanceof Filme filme) {
            JPanel abaGeneros = new JPanel();
            abaGeneros.setLayout(new BoxLayout(abaGeneros, BoxLayout.Y_AXIS));
            abaGeneros.setBackground(Style.detalhesCor);

            JLabel generoLabel = new JLabel("🎞 Gêneros: " + String.join(", ", filme.getGeneros()));
            generoLabel.setForeground(Color.WHITE);
            generoLabel.setFont(new Font(fonteLetras, Font.PLAIN, 14));

            abaGeneros.add(Box.createVerticalStrut(10));
            abaGeneros.add(generoLabel);
            abas.addTab("Gêneros", abaGeneros);

        } else if (titulo instanceof Serie serie) {
            JPanel abaGeneros = new JPanel();
            abaGeneros.setLayout(new BoxLayout(abaGeneros, BoxLayout.Y_AXIS));
            abaGeneros.setBackground(Style.detalhesCor);

            JLabel generoLabel = new JLabel("🎞 Gêneros: " + String.join(", ", serie.getGeneros()));
            generoLabel.setForeground(Style.corLetras);
            generoLabel.setFont(new Font(fonteLetras, Font.PLAIN, 14));

            abaGeneros.add(Box.createVerticalStrut(10));
            abaGeneros.add(generoLabel);
            abas.addTab("Gêneros", abaGeneros);
        }

        if (titulo.getTipo().equalsIgnoreCase("Tipos.Serie") && titulo instanceof Serie serie) {
            JPanel abaEpisodios = new JPanel();
            abaEpisodios.setLayout(new BoxLayout(abaEpisodios, BoxLayout.Y_AXIS));
            abaEpisodios.setBackground(new Color(45, 45, 45));
            abaEpisodios.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel infoEps = new JLabel("📺 " + serie.getNumeroDeEpisodios() + " episódios:");
            infoEps.setForeground(Style.corLetras);
            infoEps.setFont(new Font(fonteLetras, Font.BOLD, 14));
            abaEpisodios.add(infoEps);
            abaEpisodios.add(Box.createVerticalStrut(10));

            for (EpisodioSerie episodio : serie.getEpisodios()) {
                JPanel epPanel = new JPanel(new BorderLayout());
                epPanel.setBackground(new Color(60, 60, 60));
                epPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
                epPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

                JLabel tituloEpisodio = new JLabel("S" + episodio.getNumeroTemporada() + "E" + episodio.getNumeroEpisodio() + " - " + episodio.getNome() + "  |  Nota: " + String.format("%.1f", episodio.getMediaAvaliacoes()) + "/10");
                tituloEpisodio.setForeground(corLetras);
                tituloEpisodio.setFont(new Font(fonteLetras, Font.PLAIN, 13));

                JButton avaliarEpBotao = new JButton("Avaliar");
                avaliarEpBotao.setFocusPainted(false);
                avaliarEpBotao.setBackground(corBotao3);
                avaliarEpBotao.setForeground(corLetras);
                avaliarEpBotao.setFont(new Font(fonteLetras, Font.PLAIN, 12));
                botaoAvaliarEpisodio(avaliarEpBotao, episodio);

                epPanel.add(tituloEpisodio, BorderLayout.WEST);
                epPanel.add(avaliarEpBotao, BorderLayout.EAST);
                abaEpisodios.add(epPanel);
                abaEpisodios.add(Box.createVerticalStrut(5));
            }

            JScrollPane scrollEpisodios = new JScrollPane(abaEpisodios);
            scrollEpisodios.setPreferredSize(new Dimension(550, 200));
            abas.addTab("Episódios", scrollEpisodios);
        }

        detalhesPanel.add(nome);
        detalhesPanel.add(Box.createVerticalStrut(5));
        detalhesPanel.add(avaliacao);
        detalhesPanel.add(Box.createVerticalStrut(10));
        detalhesPanel.add(abas);


        JButton voltar = new JButton("⬅ Voltar");
        voltar.setFont(new Font(fonteLetras, Font.PLAIN, 14));
        voltar.setFocusPainted(false);
        voltar.setBackground(Style.detalhesCor);
        voltar.setForeground(Style.corLetras);
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
                        labelAvaliacao.setText("⭐ Avaliação: " + String.format("%.1f", titulo.getMediaAvaliacoes()) + "/10");
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
                         episodio.avaliar(nota);
                         //não funciona, vou fingir que sei de nada...
                        //(Atualização)funciona, to muito feliz
                        new CSV.BancoDeDados("dados.csv").AtualizarEpisodio(episodio);
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