import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
            BancoDeDados arquivos = new BancoDeDados("dados.csv");
            new PaginaPrincipal().iniciar(arquivos);
        }
}


