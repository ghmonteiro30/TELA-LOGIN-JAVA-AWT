import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.sound.sampled.*;

public class TelaLogin extends Frame implements ActionListener {
    
    // Componentes da interface
    private Label lblUsuario;
    private Label lblSenha;
    private TextField txtUsuario;
    private TextField txtSenha;
    private Button btnLogin;
    private Button btnCancelar;
    
    public TelaLogin() {
        // Configurações da janela
        setTitle("Tela de Login");
        setSize(300, 150);
        setLayout(new GridLayout(4, 2));  // Alterado para 4 linhas para acomodar o novo botão
        
        // Inicialização dos componentes
        lblUsuario = new Label("Usuário:");
        lblSenha = new Label("Senha:");
        txtUsuario = new TextField();
        txtSenha = new TextField();
        txtSenha.setEchoChar('*');  // Oculta a senha com asteriscos
        btnLogin = new Button("Login");
        btnCancelar = new Button("Cancelar");
        
        // Adiciona os componentes à janela
        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(txtSenha);
        add(btnLogin);
        add(btnCancelar);
        
        // Configura o evento dos botões
        btnLogin.addActionListener(this);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a aplicação quando o botão Cancelar é clicado
                System.exit(0);
            }
        });
        
        // Configura o fechamento da janela
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica de login (qualquer valor é aceito)
        String usuario = txtUsuario.getText().trim();
        String senha = txtSenha.getText().trim();
        
        if (usuario.isEmpty() || senha.isEmpty()) {
            // Se qualquer campo estiver em branco, exibe uma mensagem de erro
            System.out.println("Usuário e senha não podem estar em branco.");
        } else {
            // Se os campos estiverem preenchidos, considera o login bem-sucedido
            System.out.println("Login bem-sucedido!");
            abrirNovaTela(usuario);
        }
    }

    private void abrirNovaTela(String usuario) {
        // Cria uma nova janela
        Frame novaTela = new Frame("Bem-vindo");
        novaTela.setSize(400, 300);
        novaTela.setLayout(new BorderLayout());
        
        // Cria um painel para a mensagem
        Panel painelMensagem = new Panel();
        Label lblMensagem = new Label("Parabéns " + usuario + ", você entrou no sistema!", Label.CENTER);
        painelMensagem.add(lblMensagem);
        novaTela.add(painelMensagem, BorderLayout.NORTH);
        
        // Adiciona o GIF ao painel
        ImageIcon gifIcon = new ImageIcon("imagem.gif"); // Substitua pelo nome do seu GIF
        JLabel gifLabel = new JLabel(gifIcon);
        novaTela.add(gifLabel, BorderLayout.CENTER);
        
        // Reproduz o MP3
        playMP3("musica.wav"); // Substitua pelo nome do seu arquivo MP3
        
        // Configura o fechamento da nova janela
        novaTela.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Torna a nova janela visível
        novaTela.setVisible(true);
    }

    private void playMP3(String filename) {
        try {
            File mp3File = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3File);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // Cria e exibe a tela de login
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}
