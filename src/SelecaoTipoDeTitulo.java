import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelecaoTipoDeTitulo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SelecaoTipoDeTitulo frame = new SelecaoTipoDeTitulo();
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
    public SelecaoTipoDeTitulo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\clari\\OneDrive\\Área de Trabalho\\gitbash.png"));
        setTitle("Seleção de título");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JRadioButton rdbtnFilme = new JRadioButton("filme");
        rdbtnFilme.setForeground(new Color(128, 128, 192));
        rdbtnFilme.setBackground(new Color(230, 230, 250));
        rdbtnFilme.setFont(new Font("Serif", Font.BOLD, 25));
        rdbtnFilme.setBounds(25, 76, 78, 28);
        contentPane.add(rdbtnFilme);

        JRadioButton rdbtnSrie = new JRadioButton("série");
        rdbtnSrie.setForeground(new Color(128, 128, 192));
        rdbtnSrie.setBackground(new Color(230, 230, 250));
        rdbtnSrie.setFont(new Font("Serif", Font.BOLD, 25));
        rdbtnSrie.setBounds(25, 106, 78, 28);
        contentPane.add(rdbtnSrie);

        JRadioButton rdbtnDoc = new JRadioButton("documentário");
        rdbtnDoc.setForeground(new Color(128, 128, 192));
        rdbtnDoc.setBackground(new Color(230, 230, 250));
        rdbtnDoc.setFont(new Font("Serif", Font.BOLD, 25));
        rdbtnDoc.setBounds(25, 136, 178, 28);
        contentPane.add(rdbtnDoc);

        JRadioButton rdbtnAnime = new JRadioButton("anime");
        rdbtnAnime.setForeground(new Color(128, 128, 192));
        rdbtnAnime.setBackground(new Color(230, 230, 250));
        rdbtnAnime.setFont(new Font("Serif", Font.BOLD, 25));
        rdbtnAnime.setBounds(25, 166, 137, 28);
        contentPane.add(rdbtnAnime);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbtnAnime);
        grupo.add(rdbtnDoc);
        grupo.add(rdbtnFilme);
        grupo.add(rdbtnSrie);

        JLabel lblNewLabel = new JLabel("Qual item deseja avaliar?");
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 30));
        lblNewLabel.setForeground(new Color(70, 130, 180));
        lblNewLabel.setBackground(new Color(102, 153, 255));
        lblNewLabel.setBounds(49, 10, 352, 54);
        contentPane.add(lblNewLabel);

        JButton btnNext = new JButton("Próximo");
        btnNext.setFont(new Font("Serif", Font.BOLD, 20));
        btnNext.setForeground(new Color(70, 130, 180));
        btnNext.setBounds(145, 217, 126, 36);
        contentPane.add(btnNext);

        btnNext.addActionListener(e -> {
            String tipoSelecionado = null;
            if (rdbtnFilme.isSelected()) tipoSelecionado = "Filme";
            else if (rdbtnSrie.isSelected()) tipoSelecionado = "Série";
            else if (rdbtnDoc.isSelected()) tipoSelecionado = "Documentário";
            else if (rdbtnAnime.isSelected()) tipoSelecionado = "Anime";

            if (tipoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um tipo de título.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //ArrayList<Titulo> listaDeTitulos = new ArrayList<>();
           // JPanelCadastroTitulo painelCadastro = new JPanelCadastroTitulo(listaDeTitulos);
          //  JFrame frameCadastro = new JFrame("Cadastro de " + tipoSelecionado);
          //  frameCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         //   frameCadastro.getContentPane().add(painelCadastro);
         //   frameCadastro.pack();
         //   frameCadastro.setLocationRelativeTo(null);
         //   frameCadastro.setVisible(true);

          //  dispose();
        });
    }
}


