package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import dao.PacienteDAO;
import dao.ResponsavelDAO;
import model.Paciente;
import model.Responsavel;

public class AlterarResponsavel extends JFrame {

	private JPanel contentPane;
	private JLabel lblDataNasc;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JTextField txtCpfPaciente;
	private JButton btnConsultarPaciente;
	private JLabel lblPaciente;
	private JSeparator separator;
	private JSeparator separator_1;
	private JTextField txtNomeResponsavel;
	private JLabel lblIdade;
	private JTextField txtIdadeResponsavel;
	private JTextField txtEmailResponsavel;
	private JLabel lblEmail;
	private JLabel lblCelular;
	private JLabel lblSexoResponsavel;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;
	private JButton btnAlterarPaciente;
	private JButton btnLimparTela;
	private JLabel lblLogoTelaSecretaria;
	private JFormattedTextField txtDataNascResponsavel;
	private JTextField txtCelularResponsavel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ResponsavelDAO daoResp;
	private Responsavel responsavel;
	private JTextField txtCpfResponsavel;
	private JLabel lblCpfResponsavel;
	private JButton btnVoltarLogin;
	private JLabel lblNomePaciente;
	private JLabel lblPaciente_1;
	private PacienteDAO daoPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarResponsavel frame = new AlterarResponsavel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public AlterarResponsavel() throws ParseException {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 463);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 204));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.DARK_GRAY, Color.DARK_GRAY));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane.setLayout(null);

		lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setFont(new Font("Arial", Font.BOLD, 15));
		lblDataNasc.setBounds(10, 195, 90, 14);
		contentPane.add(lblDataNasc);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));
		lblNome.setBounds(10, 162, 46, 14);
		contentPane.add(lblNome);

		lblCpf = new JLabel("Informe o CPF do Paciente:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpf.setBounds(10, 123, 211, 15);
		contentPane.add(lblCpf);

		txtCpfPaciente = new JTextField();
		txtCpfPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					long cpf = Long.parseLong(txtCpfPaciente.getText());
					
					daoPaciente = new PacienteDAO();
					daoPaciente.consultar(cpf);
					List<Paciente> listaPaciente = new ArrayList<Paciente>();
					listaPaciente = daoPaciente.ListarTodos();
					
					for(Paciente paciente : listaPaciente) {
						if(paciente.getCpf() == cpf) {
							lblNomePaciente.setText(paciente.getNome());
						}
					}
					
					daoResp = new ResponsavelDAO();
					responsavel = daoResp.consultar(cpf);

					List<Responsavel> lista = new ArrayList<Responsavel>();
					lista = daoResp.ListarTodos();

					boolean registrado = false;

					for(Responsavel responsavel : lista) {
						if(cpf == responsavel.getCpf()) {
							registrado = true;
							break;
						}
					}					

					if(registrado == true) {
						//Paciente
						txtNomeResponsavel.setText(responsavel.getNomeResponsavel());
						txtDataNascResponsavel.setText(responsavel.getDataNascResponsavel());
						txtIdadeResponsavel.setText(responsavel.getIdadeResponsavel());
						txtCpfResponsavel.setText(responsavel.getCpfResponsavel());
						txtEmailResponsavel.setText(responsavel.getEmailResponsavel());
						txtCelularResponsavel.setText(responsavel.getCelularResponsavel());
						//sexoPaciente
						if(responsavel.getSexoResponsavel().equals("M")) {
							rdbtnMasculino.setSelected(true);
						}else if(responsavel.getSexoResponsavel().equals("F")) {
							rdbtnFeminino.setSelected(true);
						}else {
							responsavel.setSexoResponsavel("X");
						}
					}else {
						JOptionPane.showMessageDialog(null,"Sem Responsável Cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		txtCpfPaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpfPaciente.setColumns(10);
		txtCpfPaciente.setBounds(211, 123, 128, 16);
		contentPane.add(txtCpfPaciente);

		btnConsultarPaciente = new JButton("");
		btnConsultarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					long cpf = Long.parseLong(txtCpfPaciente.getText());
					
					daoPaciente = new PacienteDAO();
					daoPaciente.consultar(cpf);
					List<Paciente> listaPaciente = new ArrayList<Paciente>();
					listaPaciente = daoPaciente.ListarTodos();
					
					for(Paciente paciente : listaPaciente) {
						if(paciente.getCpf() == cpf) {
							lblNomePaciente.setText(paciente.getNome());
						}
					}
					
					daoResp = new ResponsavelDAO();
					responsavel = daoResp.consultar(cpf);

					List<Responsavel> lista = new ArrayList<Responsavel>();
					lista = daoResp.ListarTodos();

					boolean registrado = false;

					for(Responsavel responsavel : lista) {
						if(cpf == responsavel.getCpf()) {
							registrado = true;
							break;
						}
					}					

					if(registrado == true) {
						//Paciente
						txtNomeResponsavel.setText(responsavel.getNomeResponsavel());
						txtDataNascResponsavel.setText(responsavel.getDataNascResponsavel());
						txtIdadeResponsavel.setText(responsavel.getIdadeResponsavel());
						txtCpfResponsavel.setText(responsavel.getCpfResponsavel());
						txtEmailResponsavel.setText(responsavel.getEmailResponsavel());
						txtCelularResponsavel.setText(responsavel.getCelularResponsavel());
						//sexoPaciente
						if(responsavel.getSexoResponsavel().equals("M")) {
							rdbtnMasculino.setSelected(true);
						}else if(responsavel.getSexoResponsavel().equals("F")) {
							rdbtnFeminino.setSelected(true);
						}else {
							responsavel.setSexoResponsavel("X");
						}
					}else {
						JOptionPane.showMessageDialog(null,"Sem Responsável Cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnConsultarPaciente.setIcon(new ImageIcon(AlterarResponsavel.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarPaciente.setToolTipText("Consultar");
		btnConsultarPaciente.setBounds(343, 123, 20, 16);
		contentPane.add(btnConsultarPaciente);

		lblPaciente = new JLabel("ALTERAR INFORMA\u00C7\u00D5ES DO RESONSAVEL");
		lblPaciente.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaciente.setBounds(77, 55, 323, 15);
		contentPane.add(lblPaciente);

		separator = new JSeparator();
		separator.setBounds(10, 64, 57, 3);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(401, 63, 46, 3);
		contentPane.add(separator_1);

		txtNomeResponsavel = new JTextField();
		txtNomeResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNomeResponsavel.setColumns(10);
		txtNomeResponsavel.setBounds(70, 161, 372, 16);
		contentPane.add(txtNomeResponsavel);

		lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Arial", Font.BOLD, 15));
		lblIdade.setBounds(193, 195, 52, 15);
		contentPane.add(lblIdade);

		txtIdadeResponsavel = new JTextField();
		txtIdadeResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIdadeResponsavel.setColumns(10);
		txtIdadeResponsavel.setBounds(240, 194, 37, 16);
		contentPane.add(txtIdadeResponsavel);

		txtEmailResponsavel = new JTextField();
		txtEmailResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmailResponsavel.setColumns(10);
		txtEmailResponsavel.setBounds(70, 261, 372, 16);
		contentPane.add(txtEmailResponsavel);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setBounds(10, 262, 57, 14);
		contentPane.add(lblEmail);

		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Arial", Font.BOLD, 15));
		lblCelular.setBounds(10, 295, 57, 14);
		contentPane.add(lblCelular);

		lblSexoResponsavel = new JLabel("Sexo:");
		lblSexoResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblSexoResponsavel.setBounds(10, 326, 46, 15);
		contentPane.add(lblSexoResponsavel);

		rdbtnMasculino = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnMasculino.setBackground(new Color(153, 255, 204));
		rdbtnMasculino.setBounds(10, 348, 128, 14);
		contentPane.add(rdbtnMasculino);

		rdbtnFeminino = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFeminino);
		rdbtnFeminino.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnFeminino.setBackground(new Color(153, 255, 204));
		rdbtnFeminino.setBounds(140, 348, 113, 14);
		contentPane.add(rdbtnFeminino);

		btnAlterarPaciente = new JButton("");
		btnAlterarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					responsavel = new Responsavel();
					responsavel.setCpf(Long.parseLong(txtCpfPaciente.getText()));
					responsavel.setNomeResponsavel(txtNomeResponsavel.getText());
					responsavel.setCpfResponsavel(txtCpfResponsavel.getText());
					responsavel.setDataNascResponsavel(txtDataNascResponsavel.getText());
					responsavel.setIdadeResponsavel(txtIdadeResponsavel.getText());
					responsavel.setEmailResponsavel(txtEmailResponsavel.getText());
					responsavel.setCelularResponsavel(txtCelularResponsavel.getText());
					//sexoResponsavel
					if(rdbtnMasculino.isSelected()) {
						responsavel.setSexoResponsavel("M");
					}else if(rdbtnFeminino.isSelected()) {
						responsavel.setSexoResponsavel("F");
					}else {
						responsavel.setSexoResponsavel("X");
					}
					daoResp = new ResponsavelDAO();
					daoResp.alterar(responsavel);
					JOptionPane.showMessageDialog(null,"Responsavel Alterado com Sucesso");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Alterar Responsavel\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnAlterarPaciente.setIcon(new ImageIcon(AlterarResponsavel.class.getResource("/imgs/iconLapis.png")));
		btnAlterarPaciente.setBounds(115, 385, 71, 67);
		contentPane.add(btnAlterarPaciente);

		btnLimparTela = new JButton("");
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===============================================
				txtCpfPaciente.setText(null);
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
		btnLimparTela.setIcon(new ImageIcon(AlterarResponsavel.class.getResource("/imgs/iconLimpar.png")));
		btnLimparTela.setBounds(272, 385, 71, 67);
		contentPane.add(btnLimparTela);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(AlterarResponsavel.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(115, 11, 274, 44);
		contentPane.add(lblLogoTelaSecretaria);

		txtDataNascResponsavel = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNascResponsavel.setBounds(93, 193, 86, 20);
		contentPane.add(txtDataNascResponsavel);

		txtCelularResponsavel = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
		txtCelularResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCelularResponsavel.setColumns(10);
		txtCelularResponsavel.setBounds(70, 293, 211, 16);
		contentPane.add(txtCelularResponsavel);

		txtCpfResponsavel = new JTextField();
		txtCpfResponsavel.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpfResponsavel.setColumns(10);
		txtCpfResponsavel.setBounds(47, 227, 160, 16);
		contentPane.add(txtCpfResponsavel);

		lblCpfResponsavel = new JLabel("CPF:");
		lblCpfResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpfResponsavel.setBounds(10, 228, 46, 15);
		contentPane.add(lblCpfResponsavel);
		
		btnVoltarLogin = new JButton("");
		btnVoltarLogin.setIcon(new ImageIcon(AlterarResponsavel.class.getResource("/imgs/iconeSair1.png")));
		btnVoltarLogin.addActionListener(new ActionListener() {
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

				//======== Não Deixar Visivel a Tela Atual(Tela de Alteracao) ==========
				setVisible(false);
				//==================================================================
			}
		});
		btnVoltarLogin.setToolTipText("Voltar");
		btnVoltarLogin.setBackground(new Color(255, 102, 102));
		btnVoltarLogin.setBounds(10, 11, 38, 38);
		contentPane.add(btnVoltarLogin);
		
		lblNomePaciente = new JLabel("");
		lblNomePaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomePaciente.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNomePaciente.setBounds(87, 81, 345, 20);
		contentPane.add(lblNomePaciente);
		
		lblPaciente_1 = new JLabel("Paciente: ");
		lblPaciente_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaciente_1.setBounds(10, 86, 87, 14);
		contentPane.add(lblPaciente_1);
	}
}
