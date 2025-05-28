import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JSlider;

public class WelcomeFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WelcomeFrame frame = new WelcomeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public WelcomeFrame() {
        setTitle("Flicksy");
        setForeground(new Color(70, 130, 180));
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\clari\\OneDrive\\Área de Trabalho\\gitbash.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setResizable(false);
        panel = new JPanel();
        panel.setBackground(new Color(230, 230, 250));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        setContentPane(panel);
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to flicksy!");
        welcomeLabel.setBounds(33, 33, 364, 116);
        welcomeLabel.setForeground(new Color(70, 130, 180));
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 42));
        panel.add(welcomeLabel);

        JButton btnStart = new JButton("Start");
        btnStart.setForeground(new Color(70, 130, 180));
        btnStart.setFont(new Font("Serif", Font.BOLD, 25));
        btnStart.setBackground(new Color(240, 248, 255));
        btnStart.setBounds(159, 159, 124, 36);
        btnStart.setFocusPainted(false);
        panel.add(btnStart);

        btnStart.addActionListener(e -> {
            SelecaoTipoDeTitulo selecao = new SelecaoTipoDeTitulo();
            selecao.setVisible(true);
            dispose();
        });
    }
}

