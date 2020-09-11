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

public class TelaMenuDentista extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogoTelaDentista;
	private JLabel lblInformeATela;
	private JButton btnVoltarLogin;
	private JButton btnProntuario;
	private JButton btnArquivos;
	private JLabel lblDataAtual;
	private JLabel lblHora;
	private JLabel lblData0;
	private JLabel lblData;
	private JLabel lblHora0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuDentista frame = new TelaMenuDentista();
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
	public TelaMenuDentista() {
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
		setTitle("Menu Dentista");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMenuDentista.class.getResource("/imgs/iconePage2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 313);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogoTelaDentista = new JLabel("");
		lblLogoTelaDentista.setIcon(new ImageIcon(TelaMenuDentista.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaDentista.setBounds(127, 5, 238, 80);
		contentPane.add(lblLogoTelaDentista);
		
		lblInformeATela = new JLabel("Informe a Tela que deseja acessar:");
		lblInformeATela.setFont(new Font("Bodoni MT", Font.BOLD, 18));
		lblInformeATela.setBounds(121, 66, 342, 46);
		contentPane.add(lblInformeATela);
		
		btnVoltarLogin = new JButton("");
		btnVoltarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Dentista ============================
				TelaLogin exibir = new TelaLogin();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnVoltarLogin.setIcon(new ImageIcon(TelaMenuDentista.class.getResource("/imgs/iconeSair1.png")));
		btnVoltarLogin.setToolTipText("Voltar para tela de login");
		btnVoltarLogin.setBackground(new Color(255, 102, 102));
		btnVoltarLogin.setBounds(5, 5, 38, 38);
		contentPane.add(btnVoltarLogin);
		
		btnProntuario = new JButton("");
		btnProntuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Menu Dentista ============================
				try {
					TelaProntuario exibir;
					exibir = new TelaProntuario();
					exibir.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnProntuario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnProntuario.setIcon(new ImageIcon(TelaMenuDentista.class.getResource("/imgs/iconeProntuario2.png")));
		btnProntuario.setBackground(new Color(204, 204, 204));
		btnProntuario.setToolTipText("Prontuario");
		btnProntuario.setBounds(83, 123, 102, 103);
		contentPane.add(btnProntuario);
		
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
		btnArquivos.setIcon(new ImageIcon(TelaMenuDentista.class.getResource("/imgs/iconeScanner2.png")));
		btnArquivos.setBackground(new Color(204, 204, 204));
		btnArquivos.setToolTipText("Arquivos");
		btnArquivos.setBounds(303, 123, 102, 103);
		contentPane.add(btnArquivos);
				
		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(375, 1, 38, 14);
		contentPane.add(lblData0);
		
		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(416, 0, 71, 15);
		contentPane.add(lblData);
		
		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(375, 15, 38, 14);
		contentPane.add(lblHora0);
		
		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(416, 14, 71, 15);
		contentPane.add(lblHora);
	}

}
