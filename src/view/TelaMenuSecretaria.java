package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class TelaMenuSecretaria extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogoTelaSecretaria;
	private JLabel lblInformeATela;
	private JButton btnCadastro;
	private JButton btnAgendamento;
	private JButton btnArquivos;
	private JButton btnVoltarLogin;
	private JLabel lblData0;
	private JLabel lblHora0;
	private JLabel lblData;
	private JLabel lblHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuSecretaria frame = new TelaMenuSecretaria();
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
	public TelaMenuSecretaria() {
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
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMenuSecretaria.class.getResource("/imgs/iconePage2.png")));
		setTitle("Menu Secretaria");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 319);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(TelaMenuSecretaria.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(133, 5, 231, 91);
		contentPane.add(lblLogoTelaSecretaria);

		lblInformeATela = new JLabel("Informe a tela que deseja acessar:");
		lblInformeATela.setFont(new Font("Bodoni MT", Font.BOLD, 18));
		lblInformeATela.setBounds(121, 66, 342, 46);
		contentPane.add(lblInformeATela);

		btnCadastro = new JButton("");
		btnCadastro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Cadastro ============================
				try {
					TelaCadastro exibir;
					exibir = new TelaCadastro();
					exibir.setVisible(true);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela de Cadastro \nMotivo:" + e1);
				}				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnCadastro.setBackground(new Color(153, 255, 153));
		btnCadastro.setIcon(new ImageIcon(TelaMenuSecretaria.class.getResource("/imgs/iconeCadastro2.png")));
		btnCadastro.setToolTipText("Cadastro Paciente");
		btnCadastro.setFont(new Font("Bodoni MT", Font.BOLD, 12));
		btnCadastro.setBounds(70, 123, 102, 103);
		contentPane.add(btnCadastro);

		btnAgendamento = new JButton("");
		btnAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Agendamento ============================
				try {
					TelaAgendamento exibir;
					exibir = new TelaAgendamento();
					exibir.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela de Agendamento \nMotivo:" + e1);
				}				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnAgendamento.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAgendamento.setIcon(new ImageIcon(TelaMenuSecretaria.class.getResource("/imgs/iconeAgendamento.png")));
		btnAgendamento.setToolTipText("Agendamento");
		btnAgendamento.setBackground(new Color(153, 255, 153));
		btnAgendamento.setBounds(199, 123, 102, 103);
		contentPane.add(btnAgendamento);

		btnArquivos = new JButton("");
		btnArquivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        //=====================================================
				try{
		        	JOptionPane.showMessageDialog(null,"Acessando drive com arquivos da clinica...");
		        	JOptionPane.showMessageDialog(null,"Informações para o acesso\n\nusuario: oydconsult@gmail.com\nsenha: oydconsultAds123");
		            URI link = new URI("https://drive.google.com/drive/my-drive");
		            Desktop.getDesktop().browse(link);
		        }catch(Exception erro){
		            System.out.println(erro);
		        }
				//=====================================================
			}
		});
		btnArquivos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnArquivos.setIcon(new ImageIcon(TelaMenuSecretaria.class.getResource("/imgs/iconeScanner2.png")));
		btnArquivos.setToolTipText("Arquivos");
		btnArquivos.setBackground(new Color(153, 255, 153));
		btnArquivos.setBounds(341, 123, 102, 103);
		contentPane.add(btnArquivos);

		btnVoltarLogin = new JButton("");
		btnVoltarLogin.setIcon(new ImageIcon(TelaMenuSecretaria.class.getResource("/imgs/iconeSair1.png")));
		btnVoltarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//======== Deixar Visivel Tela Dentista ============================
				TelaLogin exibir = new TelaLogin();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnVoltarLogin.setToolTipText("Voltar para tela de login");
		btnVoltarLogin.setBounds(5, 5, 38, 38);
		btnVoltarLogin.setBackground(new Color(255, 102, 102));
		contentPane.add(btnVoltarLogin);

		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(405, 0, 38, 14);
		contentPane.add(lblData0);

		Date dataAtual = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(405, 15, 38, 14);
		contentPane.add(lblHora0);

		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(441, 0, 63, 15);
		contentPane.add(lblData);

		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(441, 14, 63, 15);
		contentPane.add(lblHora);

		Date horaAtual = new Date();
		SimpleDateFormat hr = new SimpleDateFormat("hh:mm");
	}

}
