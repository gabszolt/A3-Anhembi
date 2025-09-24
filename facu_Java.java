import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

enum Perfil {
    ADMINISTRADOR, GERENTE, COLABORADOR
}

class Usuario {
    private int id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private Perfil perfil;

    public Usuario(int id, String nomeCompleto, String cpf, String email, String cargo, 
                  String login, String senha, Perfil perfil) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", login='" + login + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}

    class MainSwing extends JFrame {
    static ArrayList<Usuario> cadastros = new ArrayList<>();
    static Usuario usuario;
    
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    // Login components
    private JTextField loginField;
    private JPasswordField senhaField;
    
    // Cadastro components
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField cargoField;
    private JTextField loginCadastroField;
    private JPasswordField senhaCadastroField;
    private JComboBox<Perfil> perfilCombo;

    public MainSwing() {
        setTitle("Sistema de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        initComponents();
        setupLayout();
        
        setVisible(true);
    }
    
    private void initComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Login Panel
        JPanel loginPanel = createLoginPanel();
        
        // Cadastro Panel
        JPanel cadastroPanel = createCadastroPanel();
        
        // Main Menu Panel
        JPanel menuPanel = createMenuPanel();
        
        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(cadastroPanel, "CADASTRO");
        cardPanel.add(menuPanel, "MENU");
        
        add(cardPanel);
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        
        JButton loginButton = new JButton("Entrar");
        JButton cadastroButton = new JButton("Cadastrar");
        
        loginButton.addActionListener(e -> tentarLogin());
        cadastroButton.addActionListener(e -> cardLayout.show(cardPanel, "CADASTRO"));
        
        panel.add(titleLabel);
        panel.add(new JLabel());
        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(loginButton);
        panel.add(cadastroButton);
        
        return panel;
    }
    
    private JPanel createCadastroPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Cadastro de Usuário", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel nomeLabel = new JLabel("Nome completo:");
        nomeField = new JTextField();
        
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        
        JLabel emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoField = new JTextField();
        
        JLabel loginLabel = new JLabel("Login:");
        loginCadastroField = new JTextField();
        
        JLabel senhaLabel = new JLabel("Senha:");
        senhaCadastroField = new JPasswordField();
        
        JLabel perfilLabel = new JLabel("Perfil:");
        perfilCombo = new JComboBox<>(Perfil.values());
        
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");
        
        cadastrarButton.addActionListener(e -> cadastrar());
        voltarButton.addActionListener(e -> cardLayout.show(cardPanel, "LOGIN"));
        
        panel.add(titleLabel);
        panel.add(new JLabel());
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(cargoLabel);
        panel.add(cargoField);
        panel.add(loginLabel);
        panel.add(loginCadastroField);
        panel.add(senhaLabel);
        panel.add(senhaCadastroField);
        panel.add(perfilLabel);
        panel.add(perfilCombo);
        panel.add(cadastrarButton);
        panel.add(voltarButton);
        
        return panel;
    }
    
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel welcomeLabel = new JLabel("Bem-vindo ao Sistema!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(e -> sair());
        
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        panel.add(sairButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
    }
    
    public void cadastrar() {
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim();
        String email = emailField.getText().trim();
        String cargo = cargoField.getText().trim();
        String login = loginCadastroField.getText().trim();
        String senha = new String(senhaCadastroField.getPassword());
        Perfil perfil = (Perfil) perfilCombo.getSelectedItem();
        
        // Basic validation
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || cargo.isEmpty() || 
            login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if login already exists
        for (Usuario u : cadastros) {
            if (u.getLogin().equals(login)) {
                JOptionPane.showMessageDialog(this, "Login já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Usuario novoUsuario = new Usuario(
            cadastros.size(), nome, cpf, email, cargo, login, senha, perfil
        );
        
        cadastros.add(novoUsuario);
        
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear fields
        nomeField.setText("");
        cpfField.setText("");
        emailField.setText("");
        cargoField.setText("");
        loginCadastroField.setText("");
        senhaCadastroField.setText("");
        perfilCombo.setSelectedIndex(0);
        
        cardLayout.show(cardPanel, "LOGIN");
    }
    
    public boolean tentarLogin() {
        String login = loginField.getText().trim();
        String senha = new String(senhaField.getPassword());
        
        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Login e senha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Usuario usuarioDoLogin = null;
        for (Usuario x : cadastros) {
            if (x.getLogin().equals(login)) {
                usuarioDoLogin = x;
                break;
            }
        }
        
        if (usuarioDoLogin == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (usuarioDoLogin.getSenha().equals(senha)) {
            entrar(login);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void entrar(String login) {
        for (Usuario x : cadastros) {
            if (x.getLogin().equals(login)) {
                usuario = x;
                break;
            }
        }
        
        // Update menu with user info
        JPanel menuPanel = (JPanel) cardPanel.getComponent(2);
        JTextArea infoArea = (JTextArea) ((JScrollPane) menuPanel.getComponent(1)).getViewport().getView();
        
        String info = "Usuário logado:\n" +
                     "Nome: " + usuario.getNomeCompleto() + "\n" +
                     "CPF: " + usuario.getCpf() + "\n" +
                     "Email: " + usuario.getEmail() + "\n" +
                     "Cargo: " + usuario.getCargo() + "\n" +
                     "Login: " + usuario.getLogin() + "\n" +
                     "Perfil: " + usuario.getPerfil() + "\n\n" +
                     "Total de usuários cadastrados: " + cadastros.size();
        
        infoArea.setText(info);
        
        cardLayout.show(cardPanel, "MENU");
        
        // Clear login fields
        loginField.setText("");
        senhaField.setText("");
    }
    
    public void sair() {
        usuario = null;
        cardLayout.show(cardPanel, "LOGIN");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainSwing();
        });
    }
}