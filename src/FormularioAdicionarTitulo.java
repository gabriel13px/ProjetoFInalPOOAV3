import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class FormularioAdicionarTitulo {

    public static void abrir(JFrame parent, ArrayList<ConteudoAudiovisual> titulos) {
        JFrame formulario = new JFrame("Adicionar Novo Título");
        formulario.setSize(500, 600);
        formulario.setLocationRelativeTo(parent);
        formulario.setLayout(new BorderLayout(10, 10));

        JPanel camposBase = new JPanel(new GridLayout(0, 2, 10, 10));
        JPanel camposDinamicos = new JPanel(new GridLayout(0, 2, 10, 10));
        camposDinamicos.setVisible(false);

        String[] tipos = {"Filme", "Série", "Documentário", "Episódio de Série"};
        JComboBox<String> tipoCombo = new JComboBox<>(tipos);
        JTextField nomeField = new JTextField();
        JTextField imagemField = new JTextField();
        JTextField generoField = new JTextField();
        JLabel generoLabel = new JLabel("Gêneros (separados por vírgula):");
        JTextField anoField = new JTextField();
        JTextField sinopseField = new JTextField();
        JTextField duracaoField = new JTextField();
        JTextField diretorField = new JTextField();
        JTextField notaField = new JTextField();

        camposBase.add(new JLabel("Tipo:"));
        camposBase.add(tipoCombo);
        camposBase.add(new JLabel("Nome:"));
        camposBase.add(nomeField);
        camposBase.add(new JLabel("Caminho da Imagem:"));
        camposBase.add(imagemField);
        camposBase.add(generoLabel);
        camposBase.add(generoField);
        camposBase.add(new JLabel("Ano de Lançamento:"));
        camposBase.add(anoField);
        camposBase.add(new JLabel("Sinopse:"));
        camposBase.add(sinopseField);
        camposBase.add(new JLabel("Duração (minutos):"));
        camposBase.add(duracaoField);
        camposBase.add(new JLabel("Diretor:"));
        camposBase.add(diretorField);
        camposBase.add(new JLabel("Nota Inicial (0-10):"));
        camposBase.add(notaField);

        // Campos para Episódio de Série
        JTextField numTemporadaField = new JTextField();
        JTextField numEpisodioField = new JTextField();
        JComboBox<String> seriesCombo = new JComboBox<>();
        camposDinamicos.add(new JLabel("Número da Temporada:"));
        camposDinamicos.add(numTemporadaField);
        camposDinamicos.add(new JLabel("Número do Episódio:"));
        camposDinamicos.add(numEpisodioField);
        camposDinamicos.add(new JLabel("Série Pertencente:"));
        camposDinamicos.add(seriesCombo);

        // Lógica de ativação de campos dinâmicos
        tipoCombo.addActionListener(e -> {
            String tipoSelecionado = (String) tipoCombo.getSelectedItem();
            boolean isEpSerie = tipoSelecionado.equals("Episódio de Série");
            boolean usaGenero = tipoSelecionado.equals("Filme") || tipoSelecionado.equals("Série");

            camposDinamicos.setVisible(isEpSerie);
            generoField.setVisible(usaGenero);
            generoLabel.setVisible(usaGenero);

            // Popular combo com séries disponíveis
            if (isEpSerie) {
                seriesCombo.removeAllItems();
                for (ConteudoAudiovisual t : titulos) {
                    if (t instanceof Serie) {
                        seriesCombo.addItem(t.getNome());
                    }
                }
            }

            formulario.revalidate();
        });

        // Botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton salvar = new JButton("Salvar");
        JButton cancelar = new JButton("Cancelar");

        salvar.addActionListener(e -> {
            try {
                String tipo = (String) tipoCombo.getSelectedItem();
                String nome = nomeField.getText().trim();
                String imagem = imagemField.getText().trim();
                String sinopse = sinopseField.getText().trim();
                int ano = Integer.parseInt(anoField.getText().trim());
                int duracao = Integer.parseInt(duracaoField.getText().trim());
                String diretor = diretorField.getText().trim();
                int nota = (int) Double.parseDouble(notaField.getText().trim());

                Set<String> generos = new HashSet<>();
                if (tipo.equals("Filme") || tipo.equals("Série")) {
                    String[] partes = generoField.getText().split(",");
                    for (String g : partes) {
                        if (!g.trim().isEmpty()) {
                            generos.add(g.trim());
                        }
                    }
                }

                ConteudoAudiovisual novo = null;

                switch (tipo) {
                    case "Filme":
                        novo = new Filme(nome, imagem, generos, ano, sinopse, duracao, diretor, 1, nota);
                        break;
                    case "Série":
                        novo = new Serie(nome, imagem, generos, ano, sinopse, duracao, diretor);
                        break;
                    case "Documentário":
                        novo = new Documentario(nome, imagem, ano, sinopse, duracao, diretor, 1, nota);
                        break;
                    case "Episódio de Série":
                        int temporada = Integer.parseInt(numTemporadaField.getText().trim());
                        int episodio = Integer.parseInt(numEpisodioField.getText().trim());
                        String serieNome = (String) seriesCombo.getSelectedItem();
                        novo = new EpisodioSerie(nome, imagem, ano, sinopse, duracao, diretor, 1, nota, temporada, episodio, serieNome);
                        break;
                }

                if (novo != null) {
                    titulos.add(novo);
                    new BancoDeDados("dados.csv").AtualizarBanco(titulos);
                    JOptionPane.showMessageDialog(formulario, tipo + " adicionado com sucesso!");
                    formulario.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(formulario, "Erro ao salvar. Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> formulario.dispose());

        botoes.add(salvar);
        botoes.add(cancelar);

        // Montagem da tela
        JPanel centro = new JPanel(new BorderLayout());
        centro.add(camposBase, BorderLayout.NORTH);
        centro.add(camposDinamicos, BorderLayout.CENTER);

        formulario.add(centro, BorderLayout.CENTER);
        formulario.add(botoes, BorderLayout.SOUTH);
        formulario.setVisible(true);
    }
}
