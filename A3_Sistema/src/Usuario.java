public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private Perfil perfil;

    public Usuario(int id, String nome, String cpf, String email, String cargo,
                   String login, String senha, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getCargo() { return cargo; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public Perfil getPerfil() { return perfil; }
}
