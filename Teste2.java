import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Teste2 extends JFrame {
    final JLabel username = new JLabel("Username");
    final JTextField campo_username = new JTextField(20);
    final JLabel password = new JLabel("Password");
    final JPasswordField campo_password = new JPasswordField(20);
    final JButton login = new JButton("LOGIN");
    final JButton cadastro = new JButton("Cadastrar");

    public Teste2() {
        super("Reservador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);

        // Configurar fundo azul gradient
        getContentPane().setBackground(new Color(64, 150, 255));
        setLayout(new BorderLayout());

        // Criar painel para o formulário

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(new Color(64, 150, 255));
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar estilos
        configurarEstilos();

        // Ícone da pessoa (topo)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 30, 0);

        // Username label
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        formulario.add(username, gbc);

        // Campo username
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        formulario.add(campo_username, gbc);

        // Password label
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        formulario.add(password, gbc);

        // Campo password
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        formulario.add(campo_password, gbc);

        // Painel para botão cadastrar (alinhado à direita)
        JPanel painelCadastro = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelCadastro.setBackground(new Color(64, 150, 255));
        painelCadastro.add(cadastro);

        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 25, 0);
        formulario.add(painelCadastro, gbc);

        // Botão LOGIN
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 30, 0);
        formulario.add(login, gbc);

        add(formulario, BorderLayout.CENTER);
        setVisible(true);
    }

    private void configurarEstilos() {

        // Labels em branco
        username.setForeground(Color.WHITE);
        username.setFont(new Font("Arial", Font.PLAIN, 14));

        password.setForeground(Color.WHITE);
        password.setFont(new Font("Arial", Font.PLAIN, 14));

        // Campos de texto estilo moderno
        configurarCampoTexto(campo_username);
        configurarCampoTexto(campo_password);

        // Botão LOGIN estilo moderno (contorno branco)
        login.setBackground(new Color(64, 150, 255));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2, true),
                BorderFactory.createEmptyBorder(12, 40, 12, 40)
        ));
        login.setFocusPainted(false);
        login.setOpaque(true);

        // Efeito hover no botão LOGIN
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(Color.WHITE);
                login.setForeground(new Color(64, 150, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(new Color(64, 150, 255));
                login.setForeground(Color.WHITE);
            }
        });

        // Botão CADASTRAR (texto simples, sem fundo)
        cadastro.setBackground(new Color(64, 150, 255));
        cadastro.setForeground(Color.WHITE);
        cadastro.setFont(new Font("Arial", Font.PLAIN, 14));
        cadastro.setBorder(null);
        cadastro.setFocusPainted(false);
        cadastro.setOpaque(false);
        cadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover no botão CADASTRAR
        cadastro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cadastro.setForeground(new Color(200, 220, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cadastro.setForeground(Color.WHITE);
            }
        });
    }

    private void configurarCampoTexto(JTextField campo) {
        // Fundo ligeiramente mais claro que o fundo para visibilidade
        campo.setBackground(new Color(80, 160, 255));
        campo.setForeground(Color.WHITE);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE),
                BorderFactory.createEmptyBorder(10, 8, 10, 8)
        ));
        campo.setOpaque(true);
        campo.setCaretColor(Color.WHITE);
    }

    public static void main(String[] args) {
        new Teste2(); // inicia a interface
    }
}