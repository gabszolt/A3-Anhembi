import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainSwing extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JTextField loginField;
    private JPasswordField senhaField;

    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField cargoField;
    private JTextField loginCadastroField;
    private JPasswordField senhaCadastroField;
    private JComboBox<Perfil> perfilCombo;

    private ArrayList<Usuario> cadastros = new ArrayList<>();
    private Usuario usuarioLogado;

    public MainSwing() {
        // Look and feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Sistema de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(criarTelaLogin(), "LOGIN");
        cardPanel.add(criarTelaCadastro(), "CADASTRO");
        cardPanel.add(criarTelaMenu(), "MENU");

        add(cardPanel);
        setVisible(true);
    }

    private JPanel criarTelaLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy++;

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; panel.add(loginLabel, gbc);
        loginField = new JTextField(); estilizarCampo(loginField);
        gbc.gridx = 1; panel.add(loginField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(Color.WHITE);
        panel.add(senhaLabel, gbc);
        senhaField = new JPasswordField(); estilizarCampo(senhaField);
        gbc.gridx = 1; panel.add(senhaField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JButton entrarButton = criarBotao("Entrar", new Color(0, 120, 215));
        entrarButton.addActionListener(e -> login());
        panel.add(entrarButton, gbc);

        gbc.gridx = 1;
        JButton irCadastroButton = criarBotao("Cadastrar", new Color(40, 167, 69));
        irCadastroButton.addActionListener(e -> cardLayout.show(cardPanel, "CADASTRO"));
        panel.add(irCadastroButton, gbc);

        return panel;
    }

    private JPanel criarTelaCadastro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Cadastro de Usuário");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy++;

        // Campos e labels
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; panel.add(nomeLabel, gbc);
        nomeField = new JTextField(); estilizarCampo(nomeField);
        gbc.gridx = 1; panel.add(nomeField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setForeground(Color.WHITE);
        panel.add(cpfLabel, gbc);
        cpfField = new JTextField(); estilizarCampo(cpfField);
        gbc.gridx = 1; panel.add(cpfField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        panel.add(emailLabel, gbc);
        emailField = new JTextField(); estilizarCampo(emailField);
        gbc.gridx = 1; panel.add(emailField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setForeground(Color.WHITE);
        panel.add(cargoLabel, gbc);
        cargoField = new JTextField(); estilizarCampo(cargoField);
        gbc.gridx = 1; panel.add(cargoField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setForeground(Color.WHITE);
        panel.add(loginLabel, gbc);
        loginCadastroField = new JTextField(); estilizarCampo(loginCadastroField);
        gbc.gridx = 1; panel.add(loginCadastroField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(Color.WHITE);
        panel.add(senhaLabel, gbc);
        senhaCadastroField = new JPasswordField(); estilizarCampo(senhaCadastroField);
        gbc.gridx = 1; panel.add(senhaCadastroField, gbc);

        gbc.gridy++; gbc.gridx = 0;
        JLabel perfilLabel = new JLabel("Perfil:");
        perfilLabel.setForeground(Color.WHITE);
        panel.add(perfilLabel, gbc);
        perfilCombo = new JComboBox<>(Perfil.values());
        gbc.gridx = 1; panel.add(perfilCombo, gbc);

        // Botões
        gbc.gridy++; gbc.gridx = 0;
        JButton cadastrarButton = criarBotao("Cadastrar", new Color(40, 167, 69));
        cadastrarButton.addActionListener(e -> cadastrar());
        panel.add(cadastrarButton, gbc);

        gbc.gridx = 1;
        JButton voltarButton = criarBotao("Voltar", new Color(178, 34, 34));
        voltarButton.addActionListener(e -> cardLayout.show(cardPanel, "LOGIN"));
        panel.add(voltarButton, gbc);

        return panel;
    }

    private JPanel criarTelaMenu() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(45, 45, 45));

        JLabel welcomeLabel = new JLabel("Bem-vindo ao Sistema!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 16));
        infoArea.setBackground(new Color(60, 63, 65));
        infoArea.setForeground(Color.WHITE);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JButton sairButton = criarBotao("Sair", new Color(178, 34, 34));
        sairButton.addActionListener(e -> {
            usuarioLogado = null;
            cardLayout.show(cardPanel, "LOGIN");
        });
        panel.add(sairButton, BorderLayout.SOUTH);

        return panel;
    }

    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorder(BorderFactory.createEmptyBorder(8,16,8,16));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo);
            }
        });
        return botao;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(60, 63, 65));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private void estilizarCampo(JPasswordField campo) {
        campo.setBackground(new Color(60, 63, 65));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private void login() {
        String login = loginField.getText().trim();
        String senha = new String(senhaField.getPassword());

        for (Usuario u : cadastros) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                JOptionPane.showMessageDialog(this,
                        "Bem-vindo, " + u.getNome() + "! Perfil: " + u.getPerfil(),
                        "Login realizado", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cardPanel, "MENU");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Login ou senha inválidos!",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void cadastrar() {
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim();
        String email = emailField.getText().trim();
        String cargo = cargoField.getText().trim();
        String login = loginCadastroField.getText().trim();
        String senha = new String(senhaCadastroField.getPassword());
        Perfil perfil = (Perfil) perfilCombo.getSelectedItem();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || cargo.isEmpty() ||
                login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Usuario u : cadastros) {
            if (u.getLogin().equals(login)) {
                JOptionPane.showMessageDialog(this, "Login já existe!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Usuario novoUsuario = new Usuario(cadastros.size(), nome, cpf, email, cargo,
                login, senha, perfil);
        cadastros.add(novoUsuario);

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        nomeField.setText("");
        cpfField.setText("");
        emailField.setText("");
        cargoField.setText("");
        loginCadastroField.setText("");
        senhaCadastroField.setText("");
        perfilCombo.setSelectedIndex(0);

        cardLayout.show(cardPanel, "LOGIN");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainSwing::new);
    }
}
