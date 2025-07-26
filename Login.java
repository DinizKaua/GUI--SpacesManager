import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TELA DE LOGIN OU CADASTRO
public class Login extends JFrame {

    // LAYOUT
    final BorderLayout layout = new BorderLayout();
    final Color azul_claro = new Color(64, 150, 255);
    final Font fonteLabel = new Font("Arial", Font.PLAIN, 14);
    final JPanel formulario = new JPanel(new GridBagLayout());
    final GridBagConstraints posicao = new GridBagConstraints();

    // COMPONENTES
    final Image icon = Toolkit.getDefaultToolkit().getImage("icone.png");
    final JLabel perfil = new JLabel(new ImageIcon("perfil.png"));
    final JLabel email = new JLabel("E-mail");
    final JTextField campo_email = new JTextField(20);
    final JLabel senha = new JLabel("Senha");
    final JPasswordField campo_senha = new JPasswordField(20);
    final JButton login = new JButton("LOGIN");
    final JButton cadastro = new JButton("Cadastrar");

    public Login() {
        super("Gerenciador de Espaços");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setIconImage(icon);
        setLayout(layout);
        formulario.setBackground(azul_claro);
        estilizar();

        // CONFIGURAÇÃO E ADIÇÃO DE COMPONENTES
        posicao.gridwidth = 2;
        posicao.anchor = GridBagConstraints.WEST;
        configurarPos(posicao, 0, 2, 0, 0, 5, 0);    // Texto E-mail
        formulario.add(email, posicao);
        configurarPos(posicao, 0, 3,0, 0, 20, 0);    // Campo E-mail
        formulario.add(campo_email, posicao);
        configurarPos(posicao, 0, 4, 0, 0,5, 0);     // Senha
        formulario.add(senha, posicao);
        configurarPos(posicao, 0, 5, 0, 0, 20, 0);   // Campo Senha
        formulario.add(campo_senha, posicao);
        posicao.anchor = GridBagConstraints.EAST;
        configurarPos(posicao, 0, 6, 0, 0, 25, 0);   // Cadastro
        formulario.add(cadastro, posicao);
        posicao.anchor = GridBagConstraints.CENTER;
        configurarPos(posicao, 0, 1, 10, 0, 30, 10); // Imagem
        formulario.add(perfil, posicao);
        configurarPos(posicao, 0, 7, 10, 0, 30, 10); // Login
        formulario.add(login, posicao);

        cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Teste();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Teste();
            }
        });


        add(formulario, BorderLayout.CENTER);
        setVisible(true); // mostra a janela
    }

    private ImageIcon iconRedimencionada(String caminho, int a, int b){
        ImageIcon original = new ImageIcon(caminho);
        Image redimensionada = original.getImage().getScaledInstance(a, b, Image.SCALE_SMOOTH);
        return new ImageIcon(redimensionada);
    }

    // Configura diretamente a posicao dos meus elementos
    private void configurarPos(GridBagConstraints gbc, int x, int y, int top, int left, int bottom, int right) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(top, left, bottom, right);
    }

    // Estiliza meus componentes (css da silva)
    private void estilizar(){

        // Arrumando email e senha
        email.setForeground(Color.WHITE);
        email.setFont(fonteLabel);
        senha.setForeground(Color.WHITE);
        senha.setFont(fonteLabel);

        // Estilo dos campos de escrita (funçãozinha separada pra ficar show
        configurarCampoTexto(campo_email);
        configurarCampoTexto(campo_senha);

        // Estilo do login
        login.setBackground(azul_claro);
        login.setForeground(Color.WHITE);
        login.setFont(fonteLabel);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2, true),
                BorderFactory.createEmptyBorder(12, 40, 12, 40)
        ));
        login.setFocusPainted(false);
        login.setOpaque(true);
        // Isso aqui é só pra dar efeito quando botar o mouse por cima
        login.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                login.setBackground(Color.WHITE);
                login.setForeground(azul_claro);
            }

            public void mouseExited(MouseEvent e) {
                login.setBackground(azul_claro);
                login.setForeground(Color.WHITE);
            }
        });

        // Por ultimo o cadastro
        cadastro.setBackground(azul_claro);
        cadastro.setForeground(Color.WHITE);
        cadastro.setFont(fonteLabel);
        cadastro.setBorder(null);
        cadastro.setFocusPainted(false);
        cadastro.setOpaque(false);
        cadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // mesma coisa do login
        cadastro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cadastro.setForeground(new Color(200, 220, 255));
            }

            public void mouseExited(MouseEvent e) {
                cadastro.setForeground(Color.WHITE);
            }
        });
    }

    private void configurarCampoTexto(JTextField campo) {
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
        new Login(); // inicia a interface
    }
}

