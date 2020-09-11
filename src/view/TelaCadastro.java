package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.EnderecoDAO;
import dao.PacienteDAO;
import dao.ResponsavelDAO;
import model.Endereco;
import model.Paciente;
import model.Responsavel;


public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private JMenu mnAjuda;
	private JLabel lblLogoTelaSecretaria;
	private JMenuItem mntmMenu;
	private JMenuItem mntmSair;
	private JMenuItem mntmSobre;
	private JSeparator separator;
	private JTabbedPane tabbedPane;
	private JPanel panelCadastrarPaciente;
	private JPanel panelListaCadastro;
	private JTextField txtListaCpf;
	private JLabel lblListaCpf;
	private JButton btnConsultarLista;
	private JButton btnListarTodos;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblCpf;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblDataNasc;
	private JFormattedTextField txtDataNasc;
	private JLabel lblData0;
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblHora0;
	private JLabel lblIdade;
	private JTextField txtIdade;
	private JLabel lblPaciente;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblCelular;
	private JTextField txtCelular;
	private JButton btnAlterarPaciente;
	private JButton btnExcluirPaciente;
	private JLabel lblSexoResponsavel_1;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;
	private JButton btnLimparPaciente;
	private final ButtonGroup sexoPaciente = new ButtonGroup();
	private JButton btnLimparLista;
	private Paciente paciente;
	private PacienteDAO dao;
	private JPanel panelCadastrarResponsavel;
	private JPanel panelCadastrarEndereco;
	private JSeparator separator_3;
	private JLabel lblResponsavel;
	private JSeparator separator_4;
	private JLabel lblNomeResponsavel;
	private JTextField txtNomeResponsavel;
	private JLabel lblCpfResponsavel;
	private JLabel lblDataNascResponsavel;
	private JFormattedTextField txtDataNascResponsavel;
	private JLabel lblIdadeResponsavel;
	private JTextField txtIdadeResponsavel;
	private JLabel lblEmailResponsavel;
	private JTextField txtEmailResponsavel;
	private JLabel lblCelularResponsavel;
	private JFormattedTextField txtCelularResponsavel;
	private JLabel lblSexoResponsavel;
	private JRadioButton rdbtnMasculinoResponsavel;
	private JRadioButton rdbtnFemininoResponsavel;
	private JLabel lblCep;
	private JTextField txtCep;
	private JLabel lblRua;
	private JTextField txtRua;
	private JLabel lblNumeroCasa;
	private JTextField txtNumeroCasa;
	private JComboBox cmbEstado;
	private JTextField txtCidade;
	private JLabel lblCidade;
	private JTextField txtBairro;
	private JLabel lblBairro;
	private JSeparator separator_5;
	private JLabel lblEndereco;
	private JSeparator separator_6;
	private JLabel lblCpfPacienteResponsavel;
	private JTextField txtConsultarResponsavel;
	private JButton btnConsultarResponsavel;
	private JLabel lblEstado;
	private JButton btnSalvarResponsavel;
	private JButton btnAlterarResponsavel;
	private JButton btnLimparResponsavel;
	private JButton btnExcluirResponsavel;
	private JLabel lblCodigoPacienteResponsavel_1;
	private JTextField txtConsultarEndereco;
	private JButton btnConsultarEndereco;
	private JButton btnSalvarEndereco;
	private JButton btnAlterarEndereco;
	private JButton btnLimparEndereco;
	private JButton btnExcluirEndereco;
	private Responsavel responsavel;
	private ResponsavelDAO daoResp;
	private Endereco endereco;
	private EnderecoDAO daoEnd;
	private JLabel lblExibirRespPaciente;
	private JLabel lblExibirRespNome;
	private JLabel lblExibirEndNome;
	private JLabel lblExibirEndPaciente;
	private TextArea txtListar;
	private final ButtonGroup sexoResponsavel = new ButtonGroup();
	private JTextField txtCpf;
	private JButton btnSalvar;
	private JTextField txtCpfResponsavel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	 * @throws ParseException 
	 */
	public TelaCadastro() throws ParseException {
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
		setResizable(false);
		setTitle("Cadastro");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastro.class.getResource("/imgs/iconePage2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 476);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 653, 21);
		contentPane.add(menuBar);

		mnArquivo = new JMenu("Inicio");
		menuBar.add(mnArquivo);

		mntmMenu = new JMenuItem("Ir para Menu");
		mntmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Menu Secretaria ============================
				TelaMenuSecretaria exibir = new TelaMenuSecretaria();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
				setVisible(false);
				//==================================================================
			}
		});
		mnArquivo.add(mntmMenu);

		separator = new JSeparator();
		mnArquivo.add(separator);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Login============================
				TelaLogin exibir = new TelaLogin();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		mnArquivo.add(mntmSair);

		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//========================================
				JOptionPane.showMessageDialog(null, "Entre em contato com a equipe de desenvolvimento");
				//========================================
			}
		});
		mnAjuda.add(mntmSobre);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 68, 569, 372);
		contentPane.add(tabbedPane);

		panelCadastrarPaciente = new JPanel();
		panelCadastrarPaciente.setForeground(Color.LIGHT_GRAY);
		panelCadastrarPaciente.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Cadastrar Paciente", null, panelCadastrarPaciente, null);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		panelCadastrarPaciente.setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));
		lblNome.setBounds(10, 49, 46, 14);
		panelCadastrarPaciente.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNome.setBounds(70, 48, 372, 16);
		panelCadastrarPaciente.add(txtNome);
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpf.setBounds(10, 102, 34, 14);
		panelCadastrarPaciente.add(lblCpf);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setBounds(10, 127, 57, 14);
		panelCadastrarPaciente.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(70, 126, 372, 16);
		panelCadastrarPaciente.add(txtEmail);


		lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setFont(new Font("Arial", Font.BOLD, 15));
		lblDataNasc.setBounds(10, 75, 90, 14);
		panelCadastrarPaciente.add(lblDataNasc);

		txtDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNasc.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDataNasc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//================================================
				try {
					SimpleDateFormat calculo = new SimpleDateFormat("dd/MM/yyyy");
					Date primeiraDt = calculo.parse(txtDataNasc.getText());
					Date segundaDt = calculo.parse(lblData.getText());

					long diffEmMil = Math.abs(segundaDt.getTime() - primeiraDt.getTime());
					long diff = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS);

					long idade = diff / 365;

					txtIdade.setText(Long.toString(idade));

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro no calculo da Idade:" + e1);
				}
				//================================================
			}
		});
		txtDataNasc.setBounds(97, 74, 86, 16);
		panelCadastrarPaciente.add(txtDataNasc);

		lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Arial", Font.BOLD, 15));
		lblIdade.setBounds(193, 75, 52, 15);
		panelCadastrarPaciente.add(lblIdade);

		txtIdade = new JTextField();
		txtIdade.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIdade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(Integer.parseInt(txtIdade.getText()) < 16) {
					txtNomeResponsavel.setEnabled(true);
					txtCpfResponsavel.setEnabled(true);
					txtEmailResponsavel.setEnabled(true);
					txtDataNascResponsavel.setEnabled(true);
					txtIdadeResponsavel.setEnabled(true);
					txtCelularResponsavel.setEnabled(true);
					rdbtnMasculinoResponsavel.setEnabled(true);
					rdbtnFemininoResponsavel.setEnabled(true);
				}else {
					txtNomeResponsavel.setEnabled(false);
					txtCpfResponsavel.setEnabled(false);
					txtEmailResponsavel.setEnabled(false);
					txtDataNascResponsavel.setEnabled(false);
					txtIdadeResponsavel.setEnabled(false);
					txtCelularResponsavel.setEnabled(false);
					rdbtnMasculinoResponsavel.setEnabled(false);
					rdbtnFemininoResponsavel.setEnabled(false);
				}*/
			}
		});
		txtIdade.setColumns(10);
		txtIdade.setBounds(240, 74, 37, 16);
		panelCadastrarPaciente.add(txtIdade);

		lblPaciente = new JLabel("PACIENTE");
		lblPaciente.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaciente.setBounds(230, 11, 90, 15);
		panelCadastrarPaciente.add(lblPaciente);

		separator_1 = new JSeparator();
		separator_1.setBounds(318, 19, 236, 3);
		panelCadastrarPaciente.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBounds(10, 20, 210, 3);
		panelCadastrarPaciente.add(separator_2);

		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Arial", Font.BOLD, 15));
		lblCelular.setBounds(10, 153, 57, 14);
		panelCadastrarPaciente.add(lblCelular);

		txtCelular = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
		txtCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCelular.setColumns(10);
		txtCelular.setBounds(70, 152, 172, 16);
		panelCadastrarPaciente.add(txtCelular);

		btnAlterarPaciente = new JButton("");
		btnAlterarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Cadastro ============================
				try {
					AlterarPaciente exibir;
					exibir = new AlterarPaciente();
					exibir.setVisible(true);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela de Cadastro \nMotivo:" + e1);
				}				

				setVisible(false);
				//==================================================================
			}
		});
		btnAlterarPaciente.setToolTipText("Alterar");
		btnAlterarPaciente.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconLapis.png")));
		btnAlterarPaciente.setBounds(167, 266, 71, 67);
		panelCadastrarPaciente.add(btnAlterarPaciente);

		btnExcluirPaciente = new JButton("");
		btnExcluirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//======== Deixar Visivel Tela Excluir Paciente ============================
				try {
					ExcluirPaciente exibir;
					exibir = new ExcluirPaciente();
					exibir.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela \nMotivo:" + e1);
				}				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnExcluirPaciente.setToolTipText("Excluir");
		btnExcluirPaciente.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconExcluir.png")));
		btnExcluirPaciente.setBounds(483, 266, 71, 67);
		panelCadastrarPaciente.add(btnExcluirPaciente);

		lblSexoResponsavel_1 = new JLabel("Sexo:");
		lblSexoResponsavel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblSexoResponsavel_1.setBounds(10, 184, 46, 15);
		panelCadastrarPaciente.add(lblSexoResponsavel_1);

		rdbtnMasculino = new JRadioButton("MASCULINO");
		rdbtnMasculino.setFont(new Font("Arial", Font.PLAIN, 15));
		sexoPaciente.add(rdbtnMasculino);
		rdbtnMasculino.setBackground(new Color(153, 255, 204));
		rdbtnMasculino.setBounds(10, 206, 128, 14);
		panelCadastrarPaciente.add(rdbtnMasculino);

		rdbtnFeminino = new JRadioButton("FEMININO");
		rdbtnFeminino.setFont(new Font("Arial", Font.PLAIN, 15));
		sexoPaciente.add(rdbtnFeminino);
		rdbtnFeminino.setBackground(new Color(153, 255, 204));
		rdbtnFeminino.setBounds(140, 206, 113, 14);
		panelCadastrarPaciente.add(rdbtnFeminino);

		btnLimparPaciente = new JButton("");
		btnLimparPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//===============================================
				txtCpf.setText(null);
				txtNome.setText(null);
				txtDataNasc.setText(null);
				txtIdade.setText(null);
				txtEmail.setText(null);
				txtCelular.setText(null);
				rdbtnMasculino.setSelected(false);
				rdbtnFeminino.setSelected(false);
				//===============================================
			}
		});
		btnLimparPaciente.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconLimpar.png")));
		btnLimparPaciente.setToolTipText("Limpar");
		btnLimparPaciente.setBounds(335, 266, 71, 67);
		panelCadastrarPaciente.add(btnLimparPaciente);

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpf.setBounds(70, 100, 372, 16);
		panelCadastrarPaciente.add(txtCpf);
		txtCpf.setColumns(10);

		btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconSalvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//====================================================
				try {
					paciente = new Paciente();
					paciente.setNome(txtNome.getText());
					paciente.setDataNasc(txtDataNasc.getText());
					paciente.setIdade(txtIdade.getText());
					paciente.setCpf(Long.parseLong(txtCpf.getText()));
					paciente.setEmail(txtEmail.getText());
					paciente.setCelular(txtCelular.getText());
					if(rdbtnMasculino.isSelected()) {
						paciente.setSexo("M");
					}else if(rdbtnFeminino.isSelected()) {
						paciente.setSexo("F");
					}else {
						paciente.setSexo("X");
					}

					dao = new PacienteDAO();
					dao.salvar(paciente);
					JOptionPane.showMessageDialog(null, "Paciente Cadastrado com Sucesso!");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Salvar Paciente\nMotivo: " + e1);
				}
				//====================================================
			}

		});
		btnSalvar.setBounds(10, 266, 71, 67);
		panelCadastrarPaciente.add(btnSalvar);

		panelCadastrarResponsavel = new JPanel();
		panelCadastrarResponsavel.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Cadastrar Respons\u00E1vel", null, panelCadastrarResponsavel, null);
		panelCadastrarResponsavel.setLayout(null);

		separator_3 = new JSeparator();
		separator_3.setBounds(342, 49, 212, 3);
		panelCadastrarResponsavel.add(separator_3);

		lblResponsavel = new JLabel("RESPONS\u00C1VEL");
		lblResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblResponsavel.setBounds(220, 36, 112, 28);
		panelCadastrarResponsavel.add(lblResponsavel);

		separator_4 = new JSeparator();
		separator_4.setBounds(10, 49, 200, 3);
		panelCadastrarResponsavel.add(separator_4);

		lblNomeResponsavel = new JLabel("Nome:");
		lblNomeResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNomeResponsavel.setBounds(10, 64, 71, 16);
		panelCadastrarResponsavel.add(lblNomeResponsavel);

		txtNomeResponsavel = new JTextField();
		txtNomeResponsavel.setEnabled(false);
		txtNomeResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNomeResponsavel.setColumns(10);
		txtNomeResponsavel.setBounds(71, 64, 348, 16);
		panelCadastrarResponsavel.add(txtNomeResponsavel);

		lblCpfResponsavel = new JLabel("CPF:");
		lblCpfResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpfResponsavel.setBounds(10, 116, 38, 17);
		panelCadastrarResponsavel.add(lblCpfResponsavel);

		lblDataNascResponsavel = new JLabel("Data Nasc.:");
		lblDataNascResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblDataNascResponsavel.setBounds(10, 91, 89, 14);
		panelCadastrarResponsavel.add(lblDataNascResponsavel);

		txtDataNascResponsavel = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNascResponsavel.setText("");
		txtDataNascResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=========================================================
				try {
					SimpleDateFormat calculo = new SimpleDateFormat("dd/MM/yyyy");
					Date primeiraDt = calculo.parse(txtDataNascResponsavel.getText());
					Date segundaDt = calculo.parse(lblData.getText());

					long diffEmMil = Math.abs(segundaDt.getTime() - primeiraDt.getTime());
					long diff = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS);

					long idade = diff / 365;

					txtIdadeResponsavel.setText(Long.toString(idade));

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro no calculo da Idade:" + e1);
				}
				//=========================================================
			}
		});
		txtDataNascResponsavel.setEnabled(false);
		txtDataNascResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDataNascResponsavel.setBounds(91, 91, 86, 16);
		panelCadastrarResponsavel.add(txtDataNascResponsavel);

		lblIdadeResponsavel = new JLabel("Idade:");
		lblIdadeResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblIdadeResponsavel.setBounds(187, 92, 55, 15);
		panelCadastrarResponsavel.add(lblIdadeResponsavel);

		txtIdadeResponsavel = new JTextField();
		txtIdadeResponsavel.setEnabled(false);
		txtIdadeResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIdadeResponsavel.setColumns(10);
		txtIdadeResponsavel.setBounds(232, 92, 37, 16);
		panelCadastrarResponsavel.add(txtIdadeResponsavel);

		lblEmailResponsavel = new JLabel("E-mail:");
		lblEmailResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmailResponsavel.setBounds(10, 145, 60, 14);
		panelCadastrarResponsavel.add(lblEmailResponsavel);

		txtEmailResponsavel = new JTextField();
		txtEmailResponsavel.setEnabled(false);
		txtEmailResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmailResponsavel.setColumns(10);
		txtEmailResponsavel.setBounds(71, 144, 348, 16);
		panelCadastrarResponsavel.add(txtEmailResponsavel);

		lblCelularResponsavel = new JLabel("Celular:");
		lblCelularResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblCelularResponsavel.setBounds(10, 174, 60, 14);
		panelCadastrarResponsavel.add(lblCelularResponsavel);

		txtCelularResponsavel = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
		txtCelularResponsavel.setEnabled(false);
		txtCelularResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCelularResponsavel.setColumns(10);
		txtCelularResponsavel.setBounds(71, 174, 172, 16);
		panelCadastrarResponsavel.add(txtCelularResponsavel);

		lblSexoResponsavel = new JLabel("Sexo:");
		lblSexoResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblSexoResponsavel.setBounds(10, 206, 55, 15);
		panelCadastrarResponsavel.add(lblSexoResponsavel);

		rdbtnMasculinoResponsavel = new JRadioButton("MASCULINO");
		sexoResponsavel.add(rdbtnMasculinoResponsavel);
		rdbtnMasculinoResponsavel.setEnabled(false);
		rdbtnMasculinoResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnMasculinoResponsavel.setBackground(new Color(153, 255, 204));
		rdbtnMasculinoResponsavel.setBounds(10, 228, 117, 14);
		panelCadastrarResponsavel.add(rdbtnMasculinoResponsavel);

		rdbtnFemininoResponsavel = new JRadioButton("FEMININO");
		sexoResponsavel.add(rdbtnFemininoResponsavel);
		rdbtnFemininoResponsavel.setEnabled(false);
		rdbtnFemininoResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnFemininoResponsavel.setBackground(new Color(153, 255, 204));
		rdbtnFemininoResponsavel.setBounds(129, 228, 117, 14);
		panelCadastrarResponsavel.add(rdbtnFemininoResponsavel);

		lblCpfPacienteResponsavel = new JLabel("CPF:");
		lblCpfPacienteResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpfPacienteResponsavel.setBounds(10, 11, 71, 15);
		panelCadastrarResponsavel.add(lblCpfPacienteResponsavel);

		txtConsultarResponsavel = new JTextField();
		txtConsultarResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//==========================================================================
				try {
					long codTela = Long.parseLong(txtConsultarResponsavel.getText());

					dao = new PacienteDAO();
					dao.consultar(codTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == codTela) {
							lblExibirRespNome.setText(paciente.getNome());
							txtNomeResponsavel.setEnabled(true);
							txtDataNascResponsavel.setEnabled(true);
							txtIdadeResponsavel.setEnabled(true);
							txtCpfResponsavel.setEnabled(true);
							txtEmailResponsavel.setEnabled(true);
							txtCelularResponsavel.setEnabled(true);
							rdbtnMasculinoResponsavel.setEnabled(true);
							rdbtnFemininoResponsavel.setEnabled(true);

							achou = true;
							break;
						}
					}
					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}


				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Responsavel \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		txtConsultarResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtConsultarResponsavel.setBounds(44, 11, 112, 15);
		panelCadastrarResponsavel.add(txtConsultarResponsavel);
		txtConsultarResponsavel.setColumns(10);

		btnConsultarResponsavel = new JButton("");
		btnConsultarResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//==========================================================================
				try {
					long codTela = Long.parseLong(txtConsultarResponsavel.getText());

					dao = new PacienteDAO();
					dao.consultar(codTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == codTela) {
							lblExibirRespNome.setText(paciente.getNome());
							txtNomeResponsavel.setEnabled(true);
							txtDataNascResponsavel.setEnabled(true);
							txtIdadeResponsavel.setEnabled(true);
							txtCpfResponsavel.setEnabled(true);
							txtEmailResponsavel.setEnabled(true);
							txtCelularResponsavel.setEnabled(true);
							rdbtnMasculinoResponsavel.setEnabled(true);
							rdbtnFemininoResponsavel.setEnabled(true);

							achou = true;
							break;
						}
					}
					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}


				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Responsavel \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		btnConsultarResponsavel.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarResponsavel.setBounds(162, 11, 20, 16);
		panelCadastrarResponsavel.add(btnConsultarResponsavel);

		btnSalvarResponsavel = new JButton("");
		btnSalvarResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					responsavel = new Responsavel();

					//Responsavel(SALVAR)
					responsavel.setCpf(Long.parseLong(txtConsultarResponsavel.getText()));
					responsavel.setNomeResponsavel(txtNomeResponsavel.getText());
					responsavel.setCpfResponsavel(txtCpfResponsavel.getText());
					responsavel.setDataNascResponsavel(txtDataNascResponsavel.getText());
					responsavel.setIdadeResponsavel(txtIdadeResponsavel.getText());
					responsavel.setEmailResponsavel(txtEmailResponsavel.getText());
					responsavel.setCelularResponsavel(txtCelularResponsavel.getText());
					//sexoPaciente
					if(rdbtnMasculinoResponsavel.isSelected()) {
						responsavel.setSexoResponsavel("M");
					}else if(rdbtnFemininoResponsavel.isSelected()) {
						responsavel.setSexoResponsavel("F");
					}else {
						responsavel.setSexoResponsavel("X");
					}

					daoResp = new ResponsavelDAO();
					daoResp.salvar(responsavel);
					JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Responsavel\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnSalvarResponsavel.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconSalvar.png")));
		btnSalvarResponsavel.setToolTipText("Salvar");
		btnSalvarResponsavel.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSalvarResponsavel.setBounds(10, 266, 71, 67);
		panelCadastrarResponsavel.add(btnSalvarResponsavel);

		btnAlterarResponsavel = new JButton("");
		btnAlterarResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Cadastro ============================
				try {
					AlterarResponsavel exibir;
					exibir = new AlterarResponsavel();
					exibir.setVisible(true);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela \nMotivo:" + e1);
				}				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual(Tela de Alteracao) ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnAlterarResponsavel.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconLapis.png")));
		btnAlterarResponsavel.setToolTipText("Alterar");
		btnAlterarResponsavel.setBounds(167, 266, 71, 67);
		panelCadastrarResponsavel.add(btnAlterarResponsavel);

		btnLimparResponsavel = new JButton("");
		btnLimparResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===============================================
				lblExibirRespNome.setText(null);
				txtConsultarResponsavel.setText(null);
				txtNomeResponsavel.setText(null);
				txtDataNascResponsavel.setText(null);
				txtIdadeResponsavel.setText(null);
				txtCpfResponsavel.setText(null);
				txtEmailResponsavel.setText(null);
				txtCelularResponsavel.setText(null);
				rdbtnMasculino.setSelected(false);
				rdbtnFeminino.setSelected(false);
				//===============================================
			}
		});
		btnLimparResponsavel.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconLimpar.png")));
		btnLimparResponsavel.setToolTipText("Limpar");
		btnLimparResponsavel.setBounds(335, 266, 71, 67);
		panelCadastrarResponsavel.add(btnLimparResponsavel);

		btnExcluirResponsavel = new JButton("");
		btnExcluirResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Excluir Responsavel ============================
				try {
					ExcluirResponsavel exibir;
					exibir = new ExcluirResponsavel();
					exibir.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela \nMotivo:" + e1);
				}				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnExcluirResponsavel.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconExcluir.png")));
		btnExcluirResponsavel.setToolTipText("Excluir");
		btnExcluirResponsavel.setBounds(483, 266, 71, 67);
		panelCadastrarResponsavel.add(btnExcluirResponsavel);

		lblExibirRespPaciente = new JLabel("PACIENTE");
		lblExibirRespPaciente.setFont(new Font("Arial", Font.BOLD, 15));
		lblExibirRespPaciente.setBounds(202, 11, 89, 14);
		panelCadastrarResponsavel.add(lblExibirRespPaciente);

		lblExibirRespNome = new JLabel("");
		lblExibirRespNome.setFont(new Font("Arial", Font.BOLD, 13));
		lblExibirRespNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblExibirRespNome.setBounds(286, 11, 268, 15);
		panelCadastrarResponsavel.add(lblExibirRespNome);
		
		txtCpfResponsavel = new JTextField();
		txtCpfResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpfResponsavel.setEnabled(false);
		txtCpfResponsavel.setBounds(71, 115, 198, 15);
		panelCadastrarResponsavel.add(txtCpfResponsavel);
		txtCpfResponsavel.setColumns(10);

		panelCadastrarEndereco = new JPanel();
		panelCadastrarEndereco.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Cadastrar Endere\u00E7o", null, panelCadastrarEndereco, null);
		panelCadastrarEndereco.setLayout(null);

		lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Arial", Font.BOLD, 15));
		lblCep.setBounds(10, 60, 38, 15);
		panelCadastrarEndereco.add(lblCep);

		txtCep = new JTextField();
		txtCep.setEnabled(false);
		txtCep.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCep.setColumns(10);
		txtCep.setBounds(52, 60, 96, 16);
		panelCadastrarEndereco.add(txtCep);

		lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Arial", Font.BOLD, 15));
		lblRua.setBounds(10, 87, 38, 15);
		panelCadastrarEndereco.add(lblRua);

		txtRua = new JTextField();
		txtRua.setEnabled(false);
		txtRua.setFont(new Font("Arial", Font.PLAIN, 15));
		txtRua.setColumns(10);
		txtRua.setBounds(52, 87, 380, 16);
		panelCadastrarEndereco.add(txtRua);

		lblNumeroCasa = new JLabel("N\u00B0:");
		lblNumeroCasa.setFont(new Font("Arial", Font.BOLD, 15));
		lblNumeroCasa.setBounds(452, 88, 34, 15);
		panelCadastrarEndereco.add(lblNumeroCasa);

		txtNumeroCasa = new JTextField();
		txtNumeroCasa.setEnabled(false);
		txtNumeroCasa.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNumeroCasa.setColumns(10);
		txtNumeroCasa.setBounds(482, 87, 57, 16);
		panelCadastrarEndereco.add(txtNumeroCasa);

		cmbEstado = new JComboBox();
		cmbEstado.setEnabled(false);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"---", "SP", "RJ"}));
		cmbEstado.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbEstado.setBounds(77, 171, 71, 20);
		panelCadastrarEndereco.add(cmbEstado);

		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCidade.setColumns(10);
		txtCidade.setBounds(74, 139, 213, 16);
		panelCadastrarEndereco.add(txtCidade);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Arial", Font.BOLD, 15));
		lblCidade.setBounds(10, 141, 57, 14);
		panelCadastrarEndereco.add(lblCidade);

		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBairro.setColumns(10);
		txtBairro.setBounds(62, 114, 225, 16);
		panelCadastrarEndereco.add(txtBairro);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Arial", Font.BOLD, 15));
		lblBairro.setBounds(10, 115, 57, 15);
		panelCadastrarEndereco.add(lblBairro);

		separator_5 = new JSeparator();
		separator_5.setBounds(10, 42, 213, 3);
		panelCadastrarEndereco.add(separator_5);

		lblEndereco = new JLabel("ENDERE\u00C7O");
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 15));
		lblEndereco.setBounds(248, 34, 92, 15);
		panelCadastrarEndereco.add(lblEndereco);

		separator_6 = new JSeparator();
		separator_6.setBounds(350, 42, 202, 3);
		panelCadastrarEndereco.add(separator_6);

		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Arial", Font.BOLD, 15));
		lblEstado.setBounds(10, 172, 57, 14);
		panelCadastrarEndereco.add(lblEstado);

		lblCodigoPacienteResponsavel_1 = new JLabel("CPF:");
		lblCodigoPacienteResponsavel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblCodigoPacienteResponsavel_1.setBounds(10, 8, 71, 15);
		panelCadastrarEndereco.add(lblCodigoPacienteResponsavel_1);

		txtConsultarEndereco = new JTextField();
		txtConsultarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//==========================================================================
				try {
					long codTela = Long.parseLong(txtConsultarEndereco.getText());

					dao = new PacienteDAO();
					dao.consultar(codTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == codTela) {
							lblExibirEndNome.setText(paciente.getNome());
							txtCep.setEnabled(true);
							txtRua.setEnabled(true);
							txtNumeroCasa.setEnabled(true);
							txtBairro.setEnabled(true);
							txtCidade.setEnabled(true);
							cmbEstado.setEnabled(true);

							achou = true;
							break;
						}
					}
					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}


				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Endereco \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		txtConsultarEndereco.setFont(new Font("Arial", Font.PLAIN, 15));
		txtConsultarEndereco.setColumns(10);
		txtConsultarEndereco.setBounds(45, 7, 109, 15);
		panelCadastrarEndereco.add(txtConsultarEndereco);

		btnConsultarEndereco = new JButton("");
		btnConsultarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================================================
				try {
					long codTela = Long.parseLong(txtConsultarEndereco.getText());

					dao = new PacienteDAO();
					dao.consultar(codTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == codTela) {
							lblExibirEndNome.setText(paciente.getNome());
							txtCep.setEnabled(true);
							txtRua.setEnabled(true);
							txtNumeroCasa.setEnabled(true);
							txtBairro.setEnabled(true);
							txtCidade.setEnabled(true);
							cmbEstado.setEnabled(true);

							achou = true;
							break;
						}
					}
					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}


				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Endereco \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		btnConsultarEndereco.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarEndereco.setBounds(160, 7, 20, 16);
		panelCadastrarEndereco.add(btnConsultarEndereco);

		btnSalvarEndereco = new JButton("");
		btnSalvarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				endereco = new Endereco();

				//Enderco(SALVAR)
				endereco.setCpf(Long.parseLong(txtConsultarEndereco.getText()));
				endereco.setCep(txtCep.getText());
				endereco.setRua(txtRua.getText());
				endereco.setNumeroCasa(txtNumeroCasa.getText());
				endereco.setBairro(txtBairro.getText());
				endereco.setCidade(txtCidade.getText());
				endereco.setEstado((String)cmbEstado.getSelectedItem());
				try {
					daoEnd = new EnderecoDAO();
					daoEnd.salvar(endereco);
					JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Endereco\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnSalvarEndereco.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconSalvar.png")));
		btnSalvarEndereco.setToolTipText("Salvar");
		btnSalvarEndereco.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSalvarEndereco.setBounds(10, 266, 71, 67);
		panelCadastrarEndereco.add(btnSalvarEndereco);

		btnAlterarEndereco = new JButton("");
		btnAlterarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AlterarEndereco frame = new AlterarEndereco();
					frame.setVisible(true);
					setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao mudar de tela " + e1);
				}
			}
		});
		btnAlterarEndereco.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconLapis.png")));
		btnAlterarEndereco.setToolTipText("Alterar");
		btnAlterarEndereco.setBounds(167, 266, 71, 67);
		panelCadastrarEndereco.add(btnAlterarEndereco);

		btnLimparEndereco = new JButton("");
		btnLimparEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				lblExibirEndNome.setText(null);
				txtConsultarEndereco.setText(null);
				txtCep.setText(null);
				txtRua.setText(null);
				txtNumeroCasa.setText(null);
				txtBairro.setText(null);
				txtCidade.setText(null);
				cmbEstado.setSelectedIndex(0);

				//==========================================
			}
		});
		btnLimparEndereco.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconLimpar.png")));
		btnLimparEndereco.setToolTipText("Limpar");
		btnLimparEndereco.setBounds(335, 266, 71, 67);
		panelCadastrarEndereco.add(btnLimparEndereco);

		btnExcluirEndereco = new JButton("");
		btnExcluirEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Excluir Endereco ============================
				try {
					ExcluirEndereco exibir;
					exibir = new ExcluirEndereco();
					exibir.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela \nMotivo:" + e1);
				}				
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnExcluirEndereco.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconExcluir.png")));
		btnExcluirEndereco.setToolTipText("Excluir");
		btnExcluirEndereco.setBounds(483, 266, 71, 67);
		panelCadastrarEndereco.add(btnExcluirEndereco);

		lblExibirEndNome = new JLabel("");
		lblExibirEndNome.setFont(new Font("Arial", Font.BOLD, 13));
		lblExibirEndNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblExibirEndNome.setBounds(286, 8, 268, 15);
		panelCadastrarEndereco.add(lblExibirEndNome);

		lblExibirEndPaciente = new JLabel("PACIENTE");
		lblExibirEndPaciente.setFont(new Font("Arial", Font.BOLD, 15));
		lblExibirEndPaciente.setBounds(202, 8, 89, 14);
		panelCadastrarEndereco.add(lblExibirEndPaciente);

		panelListaCadastro = new JPanel();
		panelListaCadastro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelListaCadastro.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Lista de Cadastros", null, panelListaCadastro, null);
		panelListaCadastro.setLayout(null);

		txtListaCpf = new JTextField();
		txtListaCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					long cpfTela = Long.parseLong(txtListaCpf.getText());

					dao = new PacienteDAO();
					dao.consultar(cpfTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoResp = new ResponsavelDAO();
					daoResp.consultar(cpfTela);
					List<Responsavel> listaResp = new ArrayList<Responsavel>();
					listaResp = daoResp.ListarTodos();

					daoEnd = new EnderecoDAO();
					daoEnd.consultar(cpfTela);
					List<Endereco> listaEnd = new ArrayList<Endereco>();
					listaEnd = daoEnd.ListarTodos();

					int achou = 0;

					for(Paciente paciente : lista) {
						if(cpfTela == paciente.getCpf()){
							txtListar.append("________________________________________________________\n");
							txtListar.append("*** Paciente ***\n");
							txtListar.append("Nome do Paciente: " + paciente.getNome() + "\n");
							txtListar.append("Data Nascimento: "+ paciente.getDataNasc() +"\n");
							txtListar.append("Idade: "+ paciente.getIdade() + "\n");
							txtListar.append("CPF: "+ paciente.getCpf() + "\n");
							txtListar.append("E-mail: "+ paciente.getEmail() + "\n");
							txtListar.append("Celular: "+ paciente.getCelular() +"\n");
							txtListar.append("Sexo: "+ paciente.getSexo() + "\n");

							achou++;
							for(Responsavel responsavel : listaResp) {
								if(responsavel.getCpf() == cpfTela) {
									txtListar.append("\n*** Responsavel ***\n");
									txtListar.append("Nome: " + responsavel.getNomeResponsavel() + "\n");
									txtListar.append("Data Nascimento: "+ responsavel.getDataNascResponsavel() +"\n");
									txtListar.append("Idade: "+ responsavel.getIdadeResponsavel() + "\n");
									txtListar.append("CPF: "+ responsavel.getCpfResponsavel() + "\n");
									txtListar.append("E-mail: "+ responsavel.getEmailResponsavel() + "\n");
									txtListar.append("Celular: "+ responsavel.getCelularResponsavel() +"\n");
									txtListar.append("Sexo: "+ responsavel.getSexoResponsavel() + "\n");
									achou++;
								}
							}
							for(Endereco endereco : listaEnd) {
								if(endereco.getCpf() == cpfTela) {
									txtListar.append("\n*** Endereco ***\n");
									txtListar.append("Rua: "+ endereco.getRua() +"\n");
									txtListar.append("Nº: "+ endereco.getNumeroCasa() + "\n");
									txtListar.append("CEP: "+ endereco.getCep() + "\n");
									txtListar.append("Bairro: "+ endereco.getBairro() +"\n");
									txtListar.append("Cidade: "+ endereco.getCidade()+"\n");
									txtListar.append("Estado: "+ endereco.getEstado() +"\n");
									achou++;
								}
							}
							txtListar.append("________________________________________________________\n");
						}
					}
					if(achou == 0){
						JOptionPane.showMessageDialog(null,"Paciente não cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Listar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		txtListaCpf.setFont(new Font("Arial", Font.BOLD, 15));
		txtListaCpf.setBounds(50, 11, 168, 20);
		panelListaCadastro.add(txtListaCpf);
		txtListaCpf.setColumns(10);

		lblListaCpf = new JLabel("CPF");
		lblListaCpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblListaCpf.setBounds(10, 14, 46, 14);
		panelListaCadastro.add(lblListaCpf);

		btnConsultarLista = new JButton("");
		btnConsultarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					long cpfTela = Long.parseLong(txtListaCpf.getText());

					dao = new PacienteDAO();
					dao.consultar(cpfTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoResp = new ResponsavelDAO();
					daoResp.consultar(cpfTela);
					List<Responsavel> listaResp = new ArrayList<Responsavel>();
					listaResp = daoResp.ListarTodos();

					daoEnd = new EnderecoDAO();
					daoEnd.consultar(cpfTela);
					List<Endereco> listaEnd = new ArrayList<Endereco>();
					listaEnd = daoEnd.ListarTodos();

					int achou = 0;

					for(Paciente paciente : lista) {
						if(cpfTela == paciente.getCpf()){
							txtListar.append("________________________________________________________\n");
							txtListar.append("*** Paciente ***\n");
							txtListar.append("Nome do Paciente: " + paciente.getNome() + "\n");
							txtListar.append("Data Nascimento: "+ paciente.getDataNasc() +"\n");
							txtListar.append("Idade: "+ paciente.getIdade() + "\n");
							txtListar.append("CPF: "+ paciente.getCpf() + "\n");
							txtListar.append("E-mail: "+ paciente.getEmail() + "\n");
							txtListar.append("Celular: "+ paciente.getCelular() +"\n");
							txtListar.append("Sexo: "+ paciente.getSexo() + "\n");

							achou++;
							for(Responsavel responsavel : listaResp) {
								if(responsavel.getCpf() == cpfTela) {
									txtListar.append("\n*** Responsavel ***\n");
									txtListar.append("Nome: " + responsavel.getNomeResponsavel() + "\n");
									txtListar.append("Data Nascimento: "+ responsavel.getDataNascResponsavel() +"\n");
									txtListar.append("Idade: "+ responsavel.getIdadeResponsavel() + "\n");
									txtListar.append("CPF: "+ responsavel.getCpfResponsavel() + "\n");
									txtListar.append("E-mail: "+ responsavel.getEmailResponsavel() + "\n");
									txtListar.append("Celular: "+ responsavel.getCelularResponsavel() +"\n");
									txtListar.append("Sexo: "+ responsavel.getSexoResponsavel() + "\n");
									achou++;
								}
							}
							for(Endereco endereco : listaEnd) {
								if(endereco.getCpf() == cpfTela) {
									txtListar.append("\n*** Endereco ***\n");
									txtListar.append("Rua: "+ endereco.getRua() +"\n");
									txtListar.append("Nº: "+ endereco.getNumeroCasa() + "\n");
									txtListar.append("CEP: "+ endereco.getCep() + "\n");
									txtListar.append("Bairro: "+ endereco.getBairro() +"\n");
									txtListar.append("Cidade: "+ endereco.getCidade()+"\n");
									txtListar.append("Estado: "+ endereco.getEstado() +"\n");
									achou++;
								}
							}
							txtListar.append("________________________________________________________\n");
						}
					}
					if(achou == 0){
						JOptionPane.showMessageDialog(null,"Paciente não cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Listar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnConsultarLista.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarLista.setToolTipText("Consultar");
		btnConsultarLista.setBounds(228, 11, 20, 20);
		panelListaCadastro.add(btnConsultarLista);

		btnListarTodos = new JButton("Listar");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					int totalPaciente = 0;
					int totalResponsavel = 0;
					int totalEndereco = 0;

					dao = new PacienteDAO();
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoResp = new ResponsavelDAO();
					List<Responsavel> listaResp = new ArrayList<Responsavel>();
					listaResp = daoResp.ListarTodos();

					daoEnd = new EnderecoDAO();
					List<Endereco> listaEnd = new ArrayList<Endereco>();
					listaEnd = daoEnd.ListarTodos();

					txtListar.append("\t\t***TODOS OS CADASTRADOS FEITOS:***\n");
					for(Paciente paciente : lista) {
						txtListar.append("________________________________________________________\n");
						txtListar.append("*** Paciente ***\n");
						txtListar.append("Nome do Paciente: " + paciente.getNome() + "\n");
						txtListar.append("Data Nascimento: "+ paciente.getDataNasc() +"\n");
						txtListar.append("Idade: "+ paciente.getIdade() + "\n");
						txtListar.append("CPF: "+ paciente.getCpf() + "\n");
						txtListar.append("E-mail: "+ paciente.getEmail() + "\n");
						txtListar.append("Celular: "+ paciente.getCelular() +"\n");
						txtListar.append("Sexo: "+ paciente.getSexo() + "\n");
						for(Responsavel responsavel : listaResp) {
							if(responsavel.getCpf() == paciente.getCpf()) {
								txtListar.append("\n*** Responsavel ***\n");
								txtListar.append("Nome: " + responsavel.getNomeResponsavel() + "\n");
								txtListar.append("Data Nascimento: "+ responsavel.getDataNascResponsavel() +"\n");
								txtListar.append("Idade: "+ responsavel.getIdadeResponsavel() + "\n");
								txtListar.append("CPF: "+ responsavel.getCpfResponsavel() + "\n");
								txtListar.append("E-mail: "+ responsavel.getEmailResponsavel() + "\n");
								txtListar.append("Celular: "+ responsavel.getCelularResponsavel() +"\n");
								txtListar.append("Sexo: "+ responsavel.getSexoResponsavel() + "\n");
								totalResponsavel++;
							}
						}
						for(Endereco endereco : listaEnd) {
							if(endereco.getCpf() == paciente.getCpf()) {
								txtListar.append("\n*** Endereco ***\n");
								txtListar.append("Rua: "+ endereco.getRua() +"\n");
								txtListar.append("Nº: "+ endereco.getNumeroCasa() + "\n");
								txtListar.append("CEP: "+ endereco.getCep() + "\n");
								txtListar.append("Bairro: "+ endereco.getBairro() +"\n");
								txtListar.append("Cidade: "+ endereco.getCidade()+"\n");
								txtListar.append("Estado: "+ endereco.getEstado() +"\n");
								totalEndereco++;
							}
						}						
						totalPaciente++;
					}
					if(totalPaciente == 0 && totalResponsavel == 0 && totalEndereco == 0 ) {
						JOptionPane.showMessageDialog(null,"Nenhum cadastrado feito.");
					}else {
						txtListar.append("\n************************************************************************\n");
						txtListar.append("Total de Pacientes Cadastrados: " + totalPaciente + "\n");
						txtListar.append("Total de Responsaveis Cadastrados: " + totalResponsavel + "\n");
						txtListar.append("Total de Enderecos Cadastrados: " + totalEndereco + "\n");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Listar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnListarTodos.setFont(new Font("Arial", Font.BOLD, 15));
		btnListarTodos.setToolTipText("Listar Todos");
		btnListarTodos.setBounds(424, 11, 93, 23);
		panelListaCadastro.add(btnListarTodos);

		btnLimparLista = new JButton("");
		btnLimparLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===================
				txtListaCpf.setText(null);
				txtListar.setText(null);
				//===================
			}
		});
		btnLimparLista.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/iconVassoura.png")));
		btnLimparLista.setToolTipText("Limpar");
		btnLimparLista.setBounds(521, 12, 33, 22);
		panelListaCadastro.add(btnLimparLista);

		txtListar = new TextArea();
		txtListar.setBackground(new Color(255, 255, 204));
		txtListar.setEditable(false);
		txtListar.setFont(new Font("Arial", Font.PLAIN, 17));
		txtListar.setBounds(10, 37, 544, 297);
		panelListaCadastro.add(txtListar);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setBounds(170, 15, 238, 55);
		contentPane.add(lblLogoTelaSecretaria);
		lblLogoTelaSecretaria.setIcon(new ImageIcon(TelaCadastro.class.getResource("/imgs/logoClinicaOdonto6.png")));

		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(467, 29, 38, 14);
		contentPane.add(lblData0);

		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(508, 28, 71, 15);
		contentPane.add(lblData);

		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(508, 42, 71, 15);
		contentPane.add(lblHora);

		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(467, 43, 38, 14);
		contentPane.add(lblHora0);
	}
}
