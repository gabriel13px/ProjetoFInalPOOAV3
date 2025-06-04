import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardConteudoAudiovisual {

    private final ConteudoAudiovisual conteudo;
    String fonte = "SansSerif";



    public CardConteudoAudiovisual(ConteudoAudiovisual conteudo) {
        this.conteudo = conteudo;
    }

    public JPanel construir() {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 320));
        card.setBackground(Color.DARK_GRAY);

        ImageIcon imagem = new ImageIcon(conteudo.getImagem());
        Image imagemRedimensionada = imagem.getImage().getScaledInstance(200, 320, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel nomeLabel = new JLabel(conteudo.getNome());
        nomeLabel.setForeground(Color.WHITE);
        nomeLabel.setHorizontalAlignment(JLabel.CENTER);
        nomeLabel.setFont(new Font(fonte, Font.BOLD, 14));

        JLabel infoLabel = new JLabel();
        infoLabel.setForeground(Color.LIGHT_GRAY);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setFont(new Font(fonte, Font.PLAIN, 12));

        if (conteudo instanceof Filme filme) {
            infoLabel.setText("Gênero: " + String.join(", ", filme.getGeneros()));
        } else if (conteudo instanceof Serie serie) {
            infoLabel.setText( serie.getNumeroDeEpisodios() + " episódios");
        } else if (conteudo instanceof Documentario documentario) {

        }

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nomeLabel);
        infoPanel.add(infoLabel);

        card.add(imagemLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento) {
                Container parent = card.getParent();
                while (parent != null && !(parent instanceof JFrame)) {
                    parent = parent.getParent();
                }

                if (parent instanceof JFrame frame) {
                    JPanel painelPrincipal = (JPanel) frame.getContentPane().getComponent(1);
                    JPanel painelCards = (JPanel) card.getParent();
                    PainelDetalhesConteudo.exibir(painelPrincipal, (JPanel) card.getParent(), conteudo);
                }
            }
        });

        return card;
    }
}