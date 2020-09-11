package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogoLogin;
	private JLabel lblLogin;
	private JTextField txtLogin;
	private JLabel lblSenha;
	private JPasswordField pwdSenha;
	private JButton btnEntrar;
	private JLabel lblLoginSecretaria;
	private JLabel lblSenhaSecretaria;
	private JLabel lblLoginDentista;
	private JLabel lblSenhaDentista;
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblData0;
	private JLabel lblHora0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Hora Atual====================================================
	class hora implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Calendar now = Calendar.getInstance();
			lblHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
		}
	}
	//==============================================================
	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// Data
				Date dataSistema = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				lblData.setText(formato.format(dataSistema));
				
				//Hora
				Timer timer = new Timer(1000, new hora());
				timer.start();
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/imgs/iconePage2.png")));
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("OdontoADS - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 331);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogoLogin = new JLabel("");
		lblLogoLogin.setIcon(new ImageIcon(TelaLogin.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoLogin.setBounds(69, 25, 238, 79);
		contentPane.add(lblLogoLogin);

		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 12));
		lblLogin.setBounds(47, 105, 46, 14);
		contentPane.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(47, 120, 272, 26);
		contentPane.add(txtLogin);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 12));
		lblSenha.setBounds(47, 163, 46, 14);
		contentPane.add(lblSenha);

		pwdSenha = new JPasswordField();
		pwdSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//***********************************************************************************************

				if(checkLoginSecretaria(txtLogin.getText(), new String(pwdSenha.getPassword()))) {
					JOptionPane.showMessageDialog(null,"Acesso a área de Secretaria(o)", "Acesso Autorizado", JOptionPane.INFORMATION_MESSAGE);

					//======== Deixar Visivel Tela Secretaria ====================
					TelaMenuSecretaria exibir = new TelaMenuSecretaria();
					exibir.setVisible(true);
					//===========================================================

					//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
					setVisible(false);
					//==================================================================

				}else if(checkLoginDentista(txtLogin.getText(), new String(pwdSenha.getPassword()))){
					JOptionPane.showMessageDialog(null,"Acesso a área do(a) dentista", "Acesso Autorizado", JOptionPane.INFORMATION_MESSAGE);

					//======== Deixar Visivel Tela Dentista ====================
					TelaMenuDentista exibir = new TelaMenuDentista();
					exibir.setVisible(true);
					//===========================================================

					//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
					setVisible(false);
					//==================================================================
				}else {
					JOptionPane.showMessageDialog(null,"Dados Invalidos", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
				}

				//***********************************************************************************************
			}
		});
		pwdSenha.setBounds(47, 177, 272, 26);
		contentPane.add(pwdSenha);

		btnEntrar = new JButton("");
		btnEntrar.setBackground(new Color(255, 153, 102));
		btnEntrar.setToolTipText("Entrar");
		btnEntrar.setIcon(new ImageIcon(TelaLogin.class.getResource("/imgs/entrar.png")));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//***********************************************************************************************

				if(checkLoginSecretaria(txtLogin.getText(), new String(pwdSenha.getPassword()))) {
					JOptionPane.showMessageDialog(null,"Acesso a área de Secretaria(o)", "Acesso Autorizado", JOptionPane.INFORMATION_MESSAGE);

					//======== Deixar Visivel Tela Secretaria ====================
					TelaMenuSecretaria exibir = new TelaMenuSecretaria();
					exibir.setVisible(true);
					//===========================================================

					//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
					setVisible(false);
					//==================================================================

				}else if(checkLoginDentista(txtLogin.getText(), new String(pwdSenha.getPassword()))){
					JOptionPane.showMessageDialog(null,"Acesso a área do(a) dentista", "Acesso Autorizado", JOptionPane.INFORMATION_MESSAGE);

					//======== Deixar Visivel Tela Dentista ====================
					TelaMenuDentista exibir = new TelaMenuDentista();
					exibir.setVisible(true);
					//===========================================================

					//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
					setVisible(false);
					//==================================================================
				}else {
					JOptionPane.showMessageDialog(null,"Dados Invalidos", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
				}

				//***********************************************************************************************
			}
		});
		btnEntrar.setBounds(255, 215, 64, 45);
		contentPane.add(btnEntrar);

		lblLoginSecretaria = new JLabel("login: secretaria");
		lblLoginSecretaria.setBounds(10, 228, 99, 14);
		contentPane.add(lblLoginSecretaria);

		lblSenhaSecretaria = new JLabel("senha: 123");
		lblSenhaSecretaria.setBounds(10, 246, 64, 14);
		contentPane.add(lblSenhaSecretaria);

		lblLoginDentista = new JLabel("login: dentista");
		lblLoginDentista.setBounds(119, 228, 99, 14);
		contentPane.add(lblLoginDentista);

		lblSenhaDentista = new JLabel("senha: 123");
		lblSenhaDentista.setBounds(119, 246, 64, 14);
		contentPane.add(lblSenhaDentista);
		
		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(295, 0, 64, 14);
		contentPane.add(lblData);
		
		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(295, 15, 64, 14);
		contentPane.add(lblHora);
		
		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(263, 0, 38, 14);
		contentPane.add(lblData0);
		
		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(263, 15, 38, 14);
		contentPane.add(lblHora0);
	}
	//Verificar login e senha
	public boolean checkLoginSecretaria(String login, String senha) {
		return login.equals("secretaria") && senha.equals("123");
	}
	public boolean checkLoginDentista(String login, String senha) {
		return login.equals("dentista") && senha.equals("123");
	}
}
