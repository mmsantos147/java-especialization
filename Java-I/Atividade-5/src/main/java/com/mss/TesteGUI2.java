package com.mss;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class TesteGUI2 {
    private static BDVeiculos bdVeiculos = new BDVeiculos();

    private static JFrame frame;
    private static JTable tablePasseio;
    private static JTable tableCarga;
    private static DefaultTableModel modelPasseio;
    private static DefaultTableModel modelCarga;
    private static JTabbedPane tabbedPane;
    private static JLabel statusLabel;

    private static final Color COR_FUNDO        = new Color(18, 18, 24);
    private static final Color COR_PAINEL        = new Color(28, 28, 38);
    private static final Color COR_CARD          = new Color(38, 38, 52);
    private static final Color COR_DESTAQUE      = new Color(99, 179, 237);
    private static final Color COR_DESTAQUE2     = new Color(72, 149, 239);
    private static final Color COR_TEXTO         = new Color(220, 220, 235);
    private static final Color COR_TEXTO_SUAVE   = new Color(140, 140, 165);
    private static final Color COR_BORDA         = new Color(55, 55, 75);
    private static final Color COR_SUCESSO       = new Color(72, 199, 142);
    private static final Color COR_ERRO          = new Color(252, 100, 100);
    private static final Color COR_AVISO         = new Color(255, 190, 80);
    private static final Color COR_LINHA_PAR     = new Color(33, 33, 46);
    private static final Color COR_LINHA_IMPAR   = new Color(28, 28, 38);
    private static final Color COR_SELECAO       = new Color(50, 80, 120);
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            iniciarGUI();
        });
    }
 
    private static void iniciarGUI() {
        frame = new JFrame("Sistema de Gestão de Veículos");
        frame.setSize(1050, 680);
        frame.setMinimumSize(new Dimension(900, 580));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(COR_FUNDO);
        frame.setLayout(new BorderLayout());
 
        frame.add(criarCabecalho(), BorderLayout.NORTH);
        frame.add(criarCorpo(),     BorderLayout.CENTER);
        frame.add(criarRodape(),    BorderLayout.SOUTH);
 
        frame.setVisible(true);
        setStatus("Sistema iniciado. Bem-vindo!", COR_DESTAQUE);
    }
 
    private static JPanel criarCabecalho() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COR_PAINEL);
        header.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 2, 0, COR_DESTAQUE),
            new EmptyBorder(14, 24, 14, 24)
        ));

        JLabel titulo = new JLabel("Gestão de Veículos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(COR_DESTAQUE);
        header.add(titulo, BorderLayout.WEST);

        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        acoes.setOpaque(false);
        acoes.add(criarBotao(" Passeio",   COR_DESTAQUE,  e -> abrirCadastroPasseio()));
        acoes.add(criarBotao("Carga",     COR_DESTAQUE2, e -> abrirCadastroCarga()));
        acoes.add(criarBotao("Buscar",    COR_AVISO,     e -> abrirBusca()));
        acoes.add(criarBotao("Excluir",   COR_ERRO,      e -> abrirExclusao()));
        acoes.add(criarBotao("Excluir Todos", COR_ERRO, e -> abrirExclusaoTodos()));
        header.add(acoes, BorderLayout.EAST);
        return header;
    }

    private static JPanel criarCorpo() {
        JPanel corpo = new JPanel(new BorderLayout());
        corpo.setBackground(COR_FUNDO);
        corpo.setBorder(new EmptyBorder(16, 20, 0, 20));
 
        tabbedPane = new JTabbedPane();
        estilizarTabbedPane(tabbedPane);

        String[] colsPasseio = {"Placa", "Marca", "Modelo", "Cor", "Vel. Máx", "Rodas", "Pistões", "Potência", "Passageiros"};
        modelPasseio  = new DefaultTableModel(colsPasseio, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablePasseio = criarTabela(modelPasseio);
        tabbedPane.addTab("Veículos de Passeio  ", criarPainelTabela(tablePasseio, "Passeio"));

        String[] colsCarga = {"Placa", "Marca", "Modelo", "Cor", "Vel. Máx", "Rodas", "Pistões", "Potência", "Carga Máx", "Tara"};
        modelCarga = new DefaultTableModel(colsCarga, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableCarga = criarTabela(modelCarga);
        tabbedPane.addTab("Veículos de Carga  ", criarPainelTabela(tableCarga, "Carga"));
 
        corpo.add(tabbedPane, BorderLayout.CENTER);
        return corpo;
    }
 
    private static JPanel criarRodape() {
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(COR_PAINEL);
        rodape.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, COR_BORDA),
            new EmptyBorder(8, 20, 8, 20)
        ));
 
        statusLabel = new JLabel("Pronto.");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(COR_TEXTO_SUAVE);
        rodape.add(statusLabel, BorderLayout.WEST);
 
        JLabel versao = new JLabel("v1.0  •  MSS Veículos");
        versao.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        versao.setForeground(COR_BORDA);
        rodape.add(versao, BorderLayout.EAST);
 
        return rodape;
    }

    private static JButton criarBotao(String texto, Color cor, ActionListener al) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(COR_FUNDO);
        btn.setBackground(cor);
        btn.setBorder(new EmptyBorder(7, 16, 7, 16));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.addActionListener(al);
 
        btn.addMouseListener(new MouseAdapter() {
            final Color original = cor;
            public void mouseEntered(MouseEvent e) { btn.setBackground(original.brighter()); }
            public void mouseExited (MouseEvent e) { btn.setBackground(original); }
        });
        return btn;
    }
 
    private static JTable criarTabela(DefaultTableModel model) {
        JTable tabela = new JTable(model) {
            public Component prepareRenderer(javax.swing.table.TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (isRowSelected(row)) {
                    c.setBackground(COR_SELECAO);
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? COR_LINHA_PAR : COR_LINHA_IMPAR);
                    c.setForeground(COR_TEXTO);
                }
                if (c instanceof JLabel) ((JLabel) c).setBorder(new EmptyBorder(4, 10, 4, 10));
                return c;
            }
        };
 
        tabela.setBackground(COR_LINHA_IMPAR);
        tabela.setForeground(COR_TEXTO);
        tabela.setGridColor(COR_BORDA);
        tabela.setRowHeight(32);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setSelectionBackground(COR_SELECAO);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setFillsViewportHeight(true);
        tabela.setIntercellSpacing(new Dimension(0, 1));
        tabela.setShowGrid(false);
        tabela.setShowHorizontalLines(true);

        tabela.getTableHeader().setBackground(COR_CARD);
        tabela.getTableHeader().setForeground(COR_DESTAQUE);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 36));
        tabela.getTableHeader().setReorderingAllowed(false);
 
        return tabela;
    }
 
    private static JScrollPane criarPainelTabela(JTable tabela, String tipo) {
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBackground(COR_FUNDO);
        scroll.getViewport().setBackground(COR_LINHA_IMPAR);
        scroll.setBorder(new LineBorder(COR_BORDA, 1));
        scroll.getVerticalScrollBar().setBackground(COR_PAINEL);
        scroll.getHorizontalScrollBar().setBackground(COR_PAINEL);
        return scroll;
    }
 
    private static void estilizarTabbedPane(JTabbedPane tp) {
        tp.setBackground(COR_FUNDO);
        tp.setForeground(COR_TEXTO);
        tp.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tp.setBorder(BorderFactory.createEmptyBorder());
        UIManager.put("TabbedPane.selected",         COR_DESTAQUE);
        UIManager.put("TabbedPane.background",       COR_FUNDO);
        UIManager.put("TabbedPane.foreground",       COR_TEXTO);
        UIManager.put("TabbedPane.tabAreaBackground",COR_PAINEL);
    }
 
    private static JTextField criarCampo(int cols) {
        JTextField f = new JTextField(cols);
        f.setBackground(COR_CARD);
        f.setForeground(COR_TEXTO);
        f.setCaretColor(COR_DESTAQUE);
        f.setBorder(new CompoundBorder(
            new LineBorder(COR_BORDA, 1),
            new EmptyBorder(4, 8, 4, 8)
        ));
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return f;
    }
 
    private static JLabel criarLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(COR_TEXTO_SUAVE);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return l;
    }
 
    private static void adicionarLinhaCampo(JPanel p, GridBagConstraints g, int linha, String label, JComponent campo) {
        g.gridx = 0; g.gridy = linha; g.weightx = 0;
        g.anchor = GridBagConstraints.EAST;
        p.add(criarLabel(label), g);
        g.gridx = 1; g.weightx = 1.0;
        g.anchor = GridBagConstraints.WEST;
        p.add(campo, g);
    }
 
    private static void setStatus(String msg, Color cor) {
        if (statusLabel != null) {
            statusLabel.setText(msg);
            statusLabel.setForeground(cor);
        }
    }
 
    private static int mostrarDialog(Component parent, JPanel conteudo, String titulo) {
        UIManager.put("OptionPane.background",       COR_PAINEL);
        UIManager.put("Panel.background",            COR_PAINEL);
        UIManager.put("OptionPane.messageForeground", COR_TEXTO);
        UIManager.put("Button.background",           COR_CARD);
        UIManager.put("Button.foreground",           COR_TEXTO);
 
        return JOptionPane.showConfirmDialog(
            parent, conteudo, titulo,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
    }
 
    private static void mensagem(String msg, Color cor) {
        setStatus(msg, cor);
        UIManager.put("OptionPane.background",       COR_PAINEL);
        UIManager.put("Panel.background",            COR_PAINEL);
        UIManager.put("OptionPane.messageForeground", cor);
        JOptionPane.showMessageDialog(frame, msg, "Aviso", JOptionPane.PLAIN_MESSAGE);
    }
 
    private static void abrirCadastroPasseio() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_PAINEL);
        panel.setBorder(new EmptyBorder(16, 20, 8, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.fill   = GridBagConstraints.HORIZONTAL;
 
        JTextField fPlaca        = criarCampo(15);
        JTextField fMarca        = criarCampo(15);
        JTextField fModelo       = criarCampo(15);
        JTextField fCor          = criarCampo(15);
        JTextField fVelocMax     = criarCampo(8);
        JTextField fQtdRodas     = criarCampo(5);
        JTextField fQtdPist      = criarCampo(5);
        JTextField fPotencia     = criarCampo(5);
        JTextField fQtdPassag    = criarCampo(5);
 
        adicionarLinhaCampo(panel, g, 0,  "Placa:",           fPlaca);
        adicionarLinhaCampo(panel, g, 1,  "Marca:",           fMarca);
        adicionarLinhaCampo(panel, g, 2,  "Modelo:",          fModelo);
        adicionarLinhaCampo(panel, g, 3,  "Cor:",             fCor);
        adicionarLinhaCampo(panel, g, 4,  "Vel. Máx (km/h):", fVelocMax);
        adicionarLinhaCampo(panel, g, 5,  "Qtd. Rodas:",      fQtdRodas);
        adicionarLinhaCampo(panel, g, 6,  "Qtd. Pistões:",    fQtdPist);
        adicionarLinhaCampo(panel, g, 7,  "Potência (cv):",   fPotencia);
        adicionarLinhaCampo(panel, g, 8,  "Passageiros:",     fQtdPassag);
 
        int res = mostrarDialog(frame, panel, "Cadastrar Veículo de Passeio");
        if (res != JOptionPane.OK_OPTION) return;

        String placa = fPlaca.getText().trim();
        if (placa.isEmpty()) { mensagem("Placa não pode ser vazia.", COR_ERRO); return; }
 
        for (Passeio p : bdVeiculos.getListaPasseio()) {
            if (p != null && p.getPlaca().equals(placa)) {
                mensagem("Já existe veículo com essa placa.", COR_ERRO);
                return;
            }
        }
 
        try {
            String marca     = fMarca.getText().trim();
            String modelo    = fModelo.getText().trim();
            String cor       = fCor.getText().trim();
            float  velocMax  = Float.parseFloat(fVelocMax.getText().trim());
            int    qtdRodas  = Integer.parseInt(fQtdRodas.getText().trim());
            int    qtdPist   = Integer.parseInt(fQtdPist.getText().trim());
            int    potencia  = Integer.parseInt(fPotencia.getText().trim());
            int    qtdPassageiros    = Integer.parseInt(fQtdPassag.getText().trim());
 
            Passeio passeio;
            try {
                passeio = new Passeio(placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia, qtdPassageiros);
            } catch (VelocException e) {
                mensagem("Aviso: " + e.getMessage() + "\nVelocidade ajustada para padrão nacional (100 km/h).", COR_AVISO);
                passeio = new Passeio(placa, marca, modelo, cor, 100, qtdRodas, qtdPist, potencia, qtdPassageiros);
            }
 
            bdVeiculos.getListaPasseio().add(passeio);
            atualizarTabelas();
            tabbedPane.setSelectedIndex(0);
            mensagem("Veículo de passeio cadastrado com sucesso!", COR_SUCESSO);
            setStatus("Passeio '" + placa + "' cadastrado.", COR_SUCESSO);
 
        } catch (NumberFormatException e) {
            mensagem("Preencha corretamente todos os campos numéricos.", COR_ERRO);
        } catch (VelocException e) {
                System.out.println("Aviso: " + e.getMessage());
        }
    }

    private static void abrirCadastroCarga() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_PAINEL);
        panel.setBorder(new EmptyBorder(16, 20, 8, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.fill   = GridBagConstraints.HORIZONTAL;
 
        JTextField fPlaca    = criarCampo(15);
        JTextField fMarca    = criarCampo(15);
        JTextField fModelo   = criarCampo(15);
        JTextField fCor      = criarCampo(15);
        JTextField fVelocMax = criarCampo(8);
        JTextField fQtdRodas = criarCampo(5);
        JTextField fQtdPist  = criarCampo(5);
        JTextField fPotencia = criarCampo(5);
        JTextField fCargaMax = criarCampo(8);
        JTextField fTara     = criarCampo(8);
 
        adicionarLinhaCampo(panel, g, 0,  "Placa:",           fPlaca);
        adicionarLinhaCampo(panel, g, 1,  "Marca:",           fMarca);
        adicionarLinhaCampo(panel, g, 2,  "Modelo:",          fModelo);
        adicionarLinhaCampo(panel, g, 3,  "Cor:",             fCor);
        adicionarLinhaCampo(panel, g, 4,  "Vel. Máx (km/h):", fVelocMax);
        adicionarLinhaCampo(panel, g, 5,  "Qtd. Rodas:",      fQtdRodas);
        adicionarLinhaCampo(panel, g, 6,  "Qtd. Pistões:",    fQtdPist);
        adicionarLinhaCampo(panel, g, 7,  "Potência (cv):",   fPotencia);
        adicionarLinhaCampo(panel, g, 8,  "Carga Máx (kg):",  fCargaMax);
        adicionarLinhaCampo(panel, g, 9,  "Tara (kg):",       fTara);
 
        int res = mostrarDialog(frame, panel, "Cadastrar Veículo de Carga");
        if (res != JOptionPane.OK_OPTION) return;

        String placa = fPlaca.getText().trim();
        if (placa.isEmpty()) { mensagem("Placa não pode ser vazia.", COR_ERRO); return; }
 
        for (Carga c : bdVeiculos.getListaCarga()) {
            if (c != null && c.getPlaca().equals(placa)) {
                mensagem("Já existe veículo com essa placa.", COR_ERRO);
                return;
            }
        }
 
        try {
            String marca    = fMarca.getText().trim();
            String modelo   = fModelo.getText().trim();
            String cor      = fCor.getText().trim();
            float  velocMax = Float.parseFloat(fVelocMax.getText().trim());
            int    qtdRodas = Integer.parseInt(fQtdRodas.getText().trim());
            int    qtdPist  = Integer.parseInt(fQtdPist.getText().trim());
            int    potencia = Integer.parseInt(fPotencia.getText().trim());
            int    cargaMax = Integer.parseInt(fCargaMax.getText().trim());
            int    tara     = Integer.parseInt(fTara.getText().trim());
 
            Carga carga;
            try {
                carga = new Carga(placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia, cargaMax, tara);
            } catch (VelocException e) {
                mensagem("Aviso: " + e.getMessage() + "\nVelocidade ajustada para padrão nacional (90 km/h).", COR_AVISO);
                carga = new Carga(placa, marca, modelo, cor, 90, qtdRodas, qtdPist, potencia, cargaMax, tara);
            }
 
            bdVeiculos.getListaCarga().add(carga);
            atualizarTabelas();
            tabbedPane.setSelectedIndex(1);
            mensagem("Veículo de carga cadastrado com sucesso!", COR_SUCESSO);
            setStatus("Carga '" + placa + "' cadastrada.", COR_SUCESSO);
 
        } catch (NumberFormatException e) {
            mensagem("Preencha corretamente todos os campos numéricos.", COR_ERRO);
        } catch (VelocException e) {
                System.out.println("Aviso: " + e.getMessage());
        }
    }
 
    private static void abrirBusca() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_PAINEL);
        panel.setBorder(new EmptyBorder(16, 20, 8, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.fill   = GridBagConstraints.HORIZONTAL;
 
        JTextField fPlaca = criarCampo(16);
        String[]   tipos  = {"Passeio", "Carga"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBackground(COR_CARD);
        cbTipo.setForeground(COR_TEXTO);
        cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
 
        adicionarLinhaCampo(panel, g, 0, "Placa:", fPlaca);
        adicionarLinhaCampo(panel, g, 1, "Tipo:",  cbTipo);
 
        int res = mostrarDialog(frame, panel, "Buscar Veículo por Placa");
        if (res != JOptionPane.OK_OPTION) return;
 
        String placa = fPlaca.getText().trim();
        if (placa.isEmpty()) { mensagem("Informe a placa.", COR_AVISO); return; }
 
        if (cbTipo.getSelectedIndex() == 0) {
            buscarPasseio(placa);
        } else {
            buscarCarga(placa);
        }
    }
 
    private static void buscarPasseio(String placa) {
        for (Passeio p : bdVeiculos.getListaPasseio()) {
            if (p != null && p.getPlaca().equals(placa)) {
                mensagem(p.toString(), COR_DESTAQUE);
                setStatus("Veículo passeio '" + placa + "' encontrado.", COR_SUCESSO);
                return;
            }
        }
        mensagem("Veículo de passeio com placa '" + placa + "' não encontrado.", COR_AVISO);
        setStatus("Busca: placa não encontrada.", COR_AVISO);
    }
 
    private static void buscarCarga(String placa) {
        for (Carga c : bdVeiculos.getListaCarga()) {
            if (c != null && c.getPlaca().equals(placa)) {
                mensagem(c.toString(), COR_DESTAQUE);
                setStatus("Veículo carga '" + placa + "' encontrado.", COR_SUCESSO);
                return;
            }
        }
        mensagem("Veículo de carga com placa '" + placa + "' não encontrado.", COR_AVISO);
        setStatus("Busca: placa não encontrada.", COR_AVISO);
    }
 
    private static void abrirExclusao() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_PAINEL);
        panel.setBorder(new EmptyBorder(16, 20, 8, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.fill   = GridBagConstraints.HORIZONTAL;
 
        JTextField fPlaca = criarCampo(16);
        String[]   tipos  = {"Passeio", "Carga"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBackground(COR_CARD);
        cbTipo.setForeground(COR_TEXTO);
        cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
 
        adicionarLinhaCampo(panel, g, 0, "Placa:", fPlaca);
        adicionarLinhaCampo(panel, g, 1, "Tipo:",  cbTipo);
 
        int res = mostrarDialog(frame, panel, "Excluir Veículo por Placa");
        if (res != JOptionPane.OK_OPTION) return;
 
        String placa = fPlaca.getText().trim();
        if (placa.isEmpty()) { mensagem("Informe a placa.", COR_AVISO); return; }
 
        if (cbTipo.getSelectedIndex() == 0) {
            excluirPasseio(placa);
        } else {
            excluirCarga(placa);
        }
    }
 
    private static void excluirPasseio(String placa) {
        List<Passeio> lista = bdVeiculos.getListaPasseio();
        for (Passeio p : lista) {
            if (p != null && p.getPlaca().equals(placa)) {
                int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Confirmar exclusão do veículo:\n" + p.toString(),
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    lista.remove(p);
                    atualizarTabelas();
                    mensagem("Veículo de passeio '" + "com a placa: " + placa + "' excluído com sucesso.", COR_SUCESSO);
                    setStatus("Passeio '" + placa + "' excluído.", COR_SUCESSO);
                }
                return;
            }
        }
        mensagem("Veículo de passeio com placa '" + placa + "' não encontrado.", COR_AVISO);
        setStatus("Exclusão: placa não encontrada.", COR_AVISO);
    }
 
    private static void excluirCarga(String placa) {
        List<Carga> lista = bdVeiculos.getListaCarga();
        for (Carga c : lista) {
            if (c != null && c.getPlaca().equals(placa)) {
                int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Confirmar exclusão do veículo:\n" + c.toString(),
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    lista.remove(c);
                    atualizarTabelas();
                    mensagem("Veículo de carga '" + placa + "' excluído com sucesso.", COR_SUCESSO);
                    setStatus("Carga '" + placa + "' excluída.", COR_SUCESSO);
                }
                return;
            }
        }
        mensagem("Veículo de carga com placa '" + placa + "' não encontrado.", COR_AVISO);
        setStatus("Exclusão: placa não encontrada.", COR_AVISO);
    }

    private static void abrirExclusaoTodos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_PAINEL);
        panel.setBorder(new EmptyBorder(16, 20, 8, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.fill   = GridBagConstraints.HORIZONTAL;

        String[]          tipos  = {"Passeio", "Carga"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBackground(COR_CARD);
        cbTipo.setForeground(COR_TEXTO);
        cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        adicionarLinhaCampo(panel, g, 0, "Tipo:", cbTipo);

        int res = mostrarDialog(frame, panel, "Excluir Todos os Veículos por Tipo");
        if (res != JOptionPane.OK_OPTION) return;

        if (cbTipo.getSelectedIndex() == 0) {
            excluirTodosPasseio();
        } else {
            excluirTodosCarga();
        }
    }

    private static void excluirTodosPasseio() {
        List<Passeio> lista = bdVeiculos.getListaPasseio();
        if (lista.isEmpty()) {
            mensagem("Não há veículos de passeio cadastrados.", COR_AVISO);
            setStatus("Nenhum veículo de passeio para excluir.", COR_AVISO);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Deseja excluir TODOS os " + lista.size() + " veículo(s) de passeio?\nEssa ação não pode ser desfeita.",
            "Confirmar Exclusão em Massa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int total = lista.size();
            lista.clear();
            atualizarTabelas();
            mensagem(total + " veículo(s) de passeio excluído(s) com sucesso.", COR_SUCESSO);
            setStatus("Todos os veículos de passeio excluídos (" + total + ").", COR_SUCESSO);
        }
    }

    private static void excluirTodosCarga() {
        List<Carga> lista = bdVeiculos.getListaCarga();
        if (lista.isEmpty()) {
            mensagem("Não há veículos de carga cadastrados.", COR_AVISO);
            setStatus("Nenhum veículo de carga para excluir.", COR_AVISO);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Deseja excluir TODOS os " + lista.size() + " veículo(s) de carga?\nEssa ação não pode ser desfeita.",
            "Confirmar Exclusão em Massa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int total = lista.size();
            lista.clear();
            atualizarTabelas();
            mensagem(total + " veículo(s) de carga excluído(s) com sucesso.", COR_SUCESSO);
            setStatus("Todos os veículos de carga excluídos (" + total + ").", COR_SUCESSO);
        }
    }

    private static void atualizarTabelas() {
        modelPasseio.setRowCount(0);
        for (Passeio p : bdVeiculos.getListaPasseio()) {
            if (p != null) {
                modelPasseio.addRow(new Object[]{
                    p.getPlaca(), p.getMarca(), p.getModelo(), p.getCor(),
                    p.getVelocMax(), p.getQtdRodas(), p.getMotor().getQtdPist(),
                    p.getMotor().getPotencia(), p.getQtdPassageiros()
                });
            }
        }
 
        modelCarga.setRowCount(0);
        for (Carga c : bdVeiculos.getListaCarga()) {
            if (c != null) {
                modelCarga.addRow(new Object[]{
                    c.getPlaca(), c.getMarca(), c.getModelo(), c.getCor(),
                    c.getVelocMax(), c.getQtdRodas(), c.getMotor().getQtdPist(),
                    c.getMotor().getPotencia(), c.getCargaMax(), c.getTara()
                });
            }
        }
 
        setStatus("Tabelas atualizadas. Passeio: " + modelPasseio.getRowCount()
                + "  |  Carga: " + modelCarga.getRowCount(), COR_TEXTO_SUAVE);
    }

}
