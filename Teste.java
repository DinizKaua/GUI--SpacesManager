import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// TELA DE RESERVAS DE SALAS
public class Teste extends JFrame {

    // LAYOUT
    final BorderLayout layout = new BorderLayout();
    final Color azul_claro = new Color(64, 150, 255);
    final Color azul_escuro = new Color(80, 160, 255);
    final Font fonteLabel = new Font("Arial", Font.PLAIN, 14);
    final Font fonteTitulo = new Font("Arial", Font.BOLD, 18);
    final JPanel painelPrincipal = new JPanel(new BorderLayout());
    final JPanel painelSuperior = new JPanel(new GridBagLayout());
    final JPanel painelCentral = new JPanel(new BorderLayout());
    final JPanel painelInferior = new JPanel(new GridBagLayout());
    final GridBagConstraints posicao = new GridBagConstraints();

    // COMPONENTES SUPERIORES
    final Image icon = Toolkit.getDefaultToolkit().getImage("imagens/icone.png");
    final JLabel titulo = new JLabel("Reserva de Salas");
    final JLabel bemVindo = new JLabel("Selecione uma sala disponível");

    // COMPONENTES DA TABELA
    final String[] colunas = {"Sala", "Tipo", "Capacidade", "Horário Disponível", "Status"};
    final DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Torna a tabela não editável
        }
    };
    final JTable tabelaSalas = new JTable(modeloTabela);
    final JScrollPane scrollTabela = new JScrollPane(tabelaSalas);

    // COMPONENTES DE RESERVA
    final JLabel labelSalaSelecionada = new JLabel("Sala selecionada:");
    final JLabel salaSelecionada = new JLabel("Nenhuma sala selecionada");
    final JLabel labelHorario = new JLabel("Horário:");
    final JComboBox<String> comboHorarios = new JComboBox<>();
    final JLabel labelObservacoes = new JLabel("Observações:");
    final JTextArea campoObservacoes = new JTextArea(3, 20);
    final JScrollPane scrollObservacoes = new JScrollPane(campoObservacoes);
    final JButton botaoReservar = new JButton("RESERVAR SALA");
    final JButton botaoVoltar = new JButton("Voltar");

    // DADOS DAS SALAS (simulando dados vindos de objetos Salas)
    private List<Salas> listaSalas;
    private Salas salaAtualSelecionada = null;

    public Teste() {
        // DEFINIÇÕES BASES
        super("Gerenciador de Espaços - Reservas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setIconImage(icon);
        setLayout(layout);

        inicializarDados();
        estilizar();
        configurarComponentes();
        configurarEventos();

        add(painelPrincipal, BorderLayout.CENTER);
        setVisible(true);
    }

    // Inicializa dados fictícios das salas
    private void inicializarDados() {
        listaSalas = new ArrayList<>();
        listaSalas.add(new Salas("Sala A1", "Reunião", 8, "08:00-12:00", "Disponível"));
        listaSalas.add(new Salas("Sala B2", "Treinamento", 20, "14:00-18:00", "Disponível"));
        listaSalas.add(new Salas("Sala C3", "Conferência", 50, "09:00-17:00", "Disponível"));
        listaSalas.add(new Salas("Sala D4", "Reunião", 6, "13:00-17:00", "Ocupada"));
        listaSalas.add(new Salas("Sala E5", "Workshop", 15, "08:00-16:00", "Disponível"));

        carregarDadosTabela();
    }

    // Carrega os dados das salas na tabela
    private void carregarDadosTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        for (Salas sala : listaSalas) {
            Object[] linha = {
                    sala.getNome(),
                    sala.getTipo(),
                    sala.getCapacidade(),
                    sala.getHorario(),
                    sala.getStatus()
            };
            modeloTabela.addRow(linha);
        }
    }

    private void estilizar() {
        // Painel principal
        painelPrincipal.setBackground(azul_claro);
        painelSuperior.setBackground(azul_claro);
        painelCentral.setBackground(azul_claro);
        painelInferior.setBackground(azul_claro);

        // Título
        titulo.setForeground(Color.WHITE);
        titulo.setFont(fonteTitulo);
        bemVindo.setForeground(Color.WHITE);
        bemVindo.setFont(fonteLabel);

        // Labels
        labelSalaSelecionada.setForeground(Color.WHITE);
        labelSalaSelecionada.setFont(fonteLabel);
        salaSelecionada.setForeground(Color.WHITE);
        salaSelecionada.setFont(new Font("Arial", Font.BOLD, 14));

        labelHorario.setForeground(Color.WHITE);
        labelHorario.setFont(fonteLabel);

        labelObservacoes.setForeground(Color.WHITE);
        labelObservacoes.setFont(fonteLabel);

        // Tabela
        estilizarTabela();

        // ComboBox
        comboHorarios.setBackground(azul_escuro);
        comboHorarios.setForeground(Color.WHITE);
        comboHorarios.setFont(fonteLabel);

        // Campo de observações
        campoObservacoes.setBackground(azul_escuro);
        campoObservacoes.setForeground(Color.WHITE);
        campoObservacoes.setFont(fonteLabel);
        campoObservacoes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        campoObservacoes.setCaretColor(Color.WHITE);

        // Botões
        estilizarBotao(botaoReservar, true);
        estilizarBotao(botaoVoltar, false);
    }

    private void estilizarTabela() {
        tabelaSalas.setBackground(Color.WHITE);
        tabelaSalas.setForeground(Color.BLACK);
        tabelaSalas.setFont(fonteLabel);
        tabelaSalas.setSelectionBackground(azul_claro);
        tabelaSalas.setSelectionForeground(Color.WHITE);
        tabelaSalas.setRowHeight(25);
        tabelaSalas.getTableHeader().setBackground(azul_escuro);
        tabelaSalas.getTableHeader().setForeground(Color.WHITE);
        tabelaSalas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Centralizar texto nas células
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabelaSalas.getColumnCount(); i++) {
            tabelaSalas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        scrollTabela.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    private void estilizarBotao(JButton botao, boolean principal) {
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (principal) {
            botao.setBackground(azul_claro);
            botao.setForeground(Color.WHITE);
            botao.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE, 2, true),
                    BorderFactory.createEmptyBorder(12, 30, 12, 30)
            ));
            botao.setOpaque(true);

            botao.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    botao.setBackground(Color.WHITE);
                    botao.setForeground(azul_claro);
                }
                public void mouseExited(MouseEvent e) {
                    botao.setBackground(azul_claro);
                    botao.setForeground(Color.WHITE);
                }
            });
        } else {
            botao.setBackground(azul_claro);
            botao.setForeground(Color.WHITE);
            botao.setBorder(null);
            botao.setOpaque(false);

            botao.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    botao.setForeground(new Color(200, 220, 255));
                }
                public void mouseExited(MouseEvent e) {
                    botao.setForeground(Color.WHITE);
                }
            });
        }
    }

    private void configurarComponentes() {
        // Painel superior
        posicao.anchor = GridBagConstraints.CENTER;
        configurarPos(posicao, 0, 0, 20, 10);
        painelSuperior.add(titulo, posicao);
        configurarPos(posicao, 1, 0, 10, 20);
        painelSuperior.add(bemVindo, posicao);

        // Painel central com tabela
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelCentral.add(scrollTabela, BorderLayout.CENTER);

        // Painel inferior com formulário
        posicao.anchor = GridBagConstraints.WEST;
        configurarPos(posicao, 0, 0, 10, 5);
        painelInferior.add(labelSalaSelecionada, posicao);
        configurarPos(posicao, 1, 0, 5, 15);
        painelInferior.add(salaSelecionada, posicao);

        configurarPos(posicao, 2, 0, 10, 5);
        painelInferior.add(labelHorario, posicao);
        configurarPos(posicao, 3, 0, 5, 15);
        painelInferior.add(comboHorarios, posicao);

        configurarPos(posicao, 4, 0, 10, 5);
        painelInferior.add(labelObservacoes, posicao);
        configurarPos(posicao, 5, 0, 5, 15);
        painelInferior.add(scrollObservacoes, posicao);

        posicao.anchor = GridBagConstraints.CENTER;
        configurarPos(posicao, 6, 0, 20, 10);
        painelInferior.add(botaoReservar, posicao);

        posicao.anchor = GridBagConstraints.EAST;
        configurarPos(posicao, 7, 0, 10, 20);
        painelInferior.add(botaoVoltar, posicao);

        // Adicionar painéis ao painel principal
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        // Seleção na tabela
        tabelaSalas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaSalas.getSelectedRow();
                if (linhaSelecionada != -1) {
                    String nomeSala = (String) tabelaSalas.getValueAt(linhaSelecionada, 0);
                    String status = (String) tabelaSalas.getValueAt(linhaSelecionada, 4);

                    if ("Disponível".equals(status)) {
                        salaAtualSelecionada = encontrarSalaPorNome(nomeSala);
                        salaSelecionada.setText(nomeSala);
                        atualizarHorarios();
                        botaoReservar.setEnabled(true);
                    } else {
                        salaSelecionada.setText("Sala não disponível");
                        comboHorarios.removeAllItems();
                        botaoReservar.setEnabled(false);
                        salaAtualSelecionada = null;
                    }
                }
            }
        });

        // Botão reservar
        botaoReservar.addActionListener(e -> realizarReserva());

        // Botão voltar
        botaoVoltar.addActionListener(e -> {
            setVisible(false);
            new Login();
        });

        // Inicialmente desabilitar botão reservar
        botaoReservar.setEnabled(false);
    }

    private void atualizarHorarios() {
        comboHorarios.removeAllItems();
        if (salaAtualSelecionada != null) {
            // Aqui você pode implementar lógica mais complexa para horários disponíveis
            String[] horarios = {"08:00-10:00", "10:00-12:00", "14:00-16:00", "16:00-18:00"};
            for (String horario : horarios) {
                comboHorarios.addItem(horario);
            }
        }
    }

    private void realizarReserva() {
        if (salaAtualSelecionada != null && comboHorarios.getSelectedItem() != null) {
            String horarioSelecionado = (String) comboHorarios.getSelectedItem();
            String observacoes = campoObservacoes.getText().trim();

            // Aqui você implementaria a lógica de reserva
            String mensagem = String.format(
                    "Reserva realizada com sucesso!\n\n" +
                            "Sala: %s\n" +
                            "Horário: %s\n" +
                            "Observações: %s",
                    salaAtualSelecionada.getNome(),
                    horarioSelecionado,
                    observacoes.isEmpty() ? "Nenhuma" : observacoes
            );

            JOptionPane.showMessageDialog(this, mensagem, "Reserva Confirmada",
                    JOptionPane.INFORMATION_MESSAGE);

            // Limpar formulário
            limparFormulario();
        }
    }

    private void limparFormulario() {
        tabelaSalas.clearSelection();
        salaSelecionada.setText("Nenhuma sala selecionada");
        comboHorarios.removeAllItems();
        campoObservacoes.setText("");
        botaoReservar.setEnabled(false);
        salaAtualSelecionada = null;
    }

    private Salas encontrarSalaPorNome(String nome) {
        return listaSalas.stream()
                .filter(sala -> sala.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    private void configurarPos(GridBagConstraints gbc, int y, int x, int top, int bottom) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(top, 10, bottom, 10);
    }

    // Classe auxiliar para representar uma Sala
    public static class Salas {
        private String nome;
        private String tipo;
        private int capacidade;
        private String horario;
        private String status;

        public Salas(String nome, String tipo, int capacidade, String horario, String status) {
            this.nome = nome;
            this.tipo = tipo;
            this.capacidade = capacidade;
            this.horario = horario;
            this.status = status;
        }

        // Getters
        public String getNome() { return nome; }
        public String getTipo() { return tipo; }
        public int getCapacidade() { return capacidade; }
        public String getHorario() { return horario; }
        public String getStatus() { return status; }
    }

    public static void main(String[] args) {
        new Teste2();
    }
}