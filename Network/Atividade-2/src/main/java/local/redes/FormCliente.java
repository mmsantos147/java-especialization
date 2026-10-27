package local.redes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * @author Matheus Martins dos Santos
 */
public class FormCliente extends JFrame implements ActionListener {

    private static final String HOST = "127.0.0.1";

    private static final int PORTA = 50000;

    private final JTextField txtNome = new JTextField();

    private final JTextField txtIdade = new JTextField();

    private final JTextArea txtRetorno = new JTextArea(6, 40);

    private final JButton btnEnviar = new JButton("Enviar");

    public FormCliente() {
        super("Cliente");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(8, 8, 8, 8));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        painel.add(new JLabel("Nome"), gbc);
        painel.add(txtNome, gbc);

        painel.add(new JLabel("Idade"), gbc);
        painel.add(txtIdade, gbc);

        painel.add(new JLabel("Retorno do Servidor"), gbc);

        txtRetorno.setEditable(false);
        txtRetorno.setLineWrap(true);
        txtRetorno.setWrapStyleWord(true);
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        painel.add(new JScrollPane(txtRetorno), gbc);

        btnEnviar.addActionListener(this);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        painel.add(btnEnviar, gbc);

        setContentPane(painel);
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEnviar) {
            enviarPessoa();
        }
    }

    private void enviarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(txtNome.getText().trim());
        try {
            pessoa.setIdade(Integer.parseInt(txtIdade.getText().trim()));
        } catch (NumberFormatException ex) {
            txtRetorno.setText("Idade inválida: digite um número inteiro.");
            return;
        }

        try (Socket socket = new Socket(HOST, PORTA);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            out.flush();

            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                out.writeObject(pessoa);
                out.flush();

                String resposta = (String) in.readObject();
                txtRetorno.setText("Recebeu do servidor:\n" + resposta);
            }
        } catch (IOException | ClassNotFoundException ex) {
            txtRetorno.setText("Erro na comunicação com o servidor: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormCliente().setVisible(true));
    }
}
