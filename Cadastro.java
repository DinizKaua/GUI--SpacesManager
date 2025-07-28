import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cadastro extends JFrame {
    final BorderLayout layout = new BorderLayout();
    final Color azul_claro = new Color(64, 150, 255);
    final Font fonteLabel = new Font("Arial", Font.PLAIN, 14);
    final JPanel formulario = new JPanel(new GridBagLayout());
    final GridBagConstraints posicao = new GridBagConstraints();

    final Image icon = Toolkit.getDefaultToolkit().getImage("icone.png");
    final JLabel nome = new JLabel("Nome");
    final JTextField campo_nome = new JTextField(20);
    final JButton cadastrar = new JButton("CADASTRAR");
    final JLabel cadastro = new JLabel("CADASTRE-SE");
    final JCheckBox isAdm = new JCheckBox("Você é um adiministrador?");
    final JLabel email = new JLabel("E-mail");
    final JTextField campo_email = new JTextField(20);
    final JLabel senha = new JLabel("Senha");
    final JPasswordField campo_senha = new JPasswordField(20);
    final JLabel chaveAcesso = new JLabel("Chave de Acesso");
    final JPasswordField campo_chaveAcesso = new JPasswordField(20);

    public Cadastro() {
        super("Reservador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setIconImage(icon);
        setLayout(layout);
        formulario.setBackground(azul_claro);
        estilizar();

        posicao.anchor = GridBagConstraints.CENTER;
        configurarPos(posicao, 0, 1, 10, 0, 30, 10);
        formulario.add(cadastro, posicao);
        configurarPos(posicao, 0, 11, 10, 0, 30, 10);
        formulario.add(cadastrar, posicao);
        posicao.anchor = GridBagConstraints.WEST;
        configurarPos(posicao, 0, 2, 0, 0, 5, 0);
        formulario.add(nome, posicao);
        configurarPos(posicao, 0, 3, 0, 0, 20, 0);
        formulario.add(campo_nome, posicao);
        configurarPos(posicao, 0, 4, 0, 0, 5, 0);    // Texto E-mail
        formulario.add(email, posicao);
        configurarPos(posicao, 0, 5,0, 0, 20, 0);    // Campo E-mail
        formulario.add(campo_email, posicao);
        configurarPos(posicao, 0, 6, 0, 0,5, 0);     // Senha
        formulario.add(senha, posicao);
        configurarPos(posicao, 0, 7 , 0, 0, 20, 0);   // Campo Senha
        formulario.add(campo_senha, posicao);
        configurarPos(posicao, 0, 8, 0, 0,5, 0);
        formulario.add(isAdm, posicao);

        isAdm.addActionListener(e ->{
            if(isAdm.isSelected()){
                configurarPos(posicao, 0, 9, 0, 0,5, 0);
                formulario.add(chaveAcesso, posicao);
                configurarPos(posicao, 0, 10, 0, 0,5, 0);
                formulario.add(campo_chaveAcesso, posicao);
                formulario.revalidate();
                formulario.repaint();
            }else{
                formulario.remove(chaveAcesso);
                formulario.remove(campo_chaveAcesso);
                formulario.revalidate();
                formulario.repaint();
            }
        });

        add(formulario, BorderLayout.CENTER);
        setVisible(true);

    }
    private void configurarPos(GridBagConstraints gbc, int x, int y, int top, int left, int bottom, int right) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(top, left, bottom, right);
    }

    private void estilizar(){
        nome.setForeground(Color.WHITE);
        nome.setFont(fonteLabel);
        cadastro.setForeground(Color.WHITE);
        email.setForeground(Color.WHITE);
        email.setFont(fonteLabel);
        senha.setForeground(Color.WHITE);
        senha.setFont(fonteLabel);
        isAdm.setForeground(Color.WHITE);
        isAdm.setFont(fonteLabel);
        isAdm.setBackground(azul_claro);
        chaveAcesso.setForeground(Color.WHITE);
        chaveAcesso.setFont(fonteLabel);

        // Estilo dos campos de escrita (funçãozinha separada pra ficar show
        configurarCampoTexto(campo_email);
        configurarCampoTexto(campo_senha);
        configurarCampoTexto(campo_chaveAcesso);
        configurarCampoTexto(campo_nome);

        cadastrar.setBackground(azul_claro);
        cadastrar.setForeground(Color.WHITE);
        cadastrar.setFont(fonteLabel);
        cadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        cadastrar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2, true),
                BorderFactory.createEmptyBorder(12, 40, 12, 40)
        ));
        cadastrar.setFocusPainted(false);
        cadastrar.setOpaque(true);
        // Isso aqui é só pra dar efeito quando botar o mouse por cima
        cadastrar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cadastrar.setBackground(Color.WHITE);
                cadastrar.setForeground(azul_claro);
            }

            public void mouseExited(MouseEvent e) {
                cadastrar.setBackground(azul_claro);
                cadastrar.setForeground(Color.WHITE);
            }
        });

        cadastro.setFont(new Font("Arial", Font.BOLD, 25));
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
        isAdm.setFocusPainted(false);
    }
    public static void main(String[] args) {
        new Cadastro();
    }
}
