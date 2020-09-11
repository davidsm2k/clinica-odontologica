package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
import model.Paciente;

public class AlterarPaciente extends JFrame {

	private JPanel contentPane;
	private JLabel lblDataNasc;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JTextField txtCpfPaciente;
	private JButton btnConsultarPaciente;
	private JLabel lblPaciente;
	private JSeparator separator;
	private JSeparator separator_1;
	private JTextField txtNomePaciente;
	private JLabel lblIdade;
	private JTextField txtIdade;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JLabel lblCelular;
	private JLabel lblSexoResponsavel;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;
	private JButton btnAlterarPaciente;
	private JButton btnLimparTela;
	private JLabel lblLogoTelaSecretaria;
	private JFormattedTextField txtDataNasc;
	private JTextField txtCelular;
	private PacienteDAO dao;
	private Paciente paciente;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnVoltarLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarPaciente frame = new AlterarPaciente();
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
	public AlterarPaciente() throws ParseException {
		setUndecorated(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarPaciente.class.getResource("/imgs/iconePage2.png")));
		setTitle("Alterar Informa\u00E7\u00F5es do Paciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 204));
		contentPane.setToolTipText("");
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY, null, null));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane.setLayout(null);

		lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setFont(new Font("Arial", Font.BOLD, 15));
		lblDataNasc.setBounds(10, 164, 90, 14);
		contentPane.add(lblDataNasc);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));
		lblNome.setBounds(10, 125, 46, 14);
		contentPane.add(lblNome);

		lblCpf = new JLabel("Informe o CPF do Paciente:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpf.setBounds(10, 87, 234, 15);
		contentPane.add(lblCpf);

		txtCpfPaciente = new JTextField();
		txtCpfPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					long cpf = Long.parseLong(txtCpfPaciente.getText());

					dao = new PacienteDAO();
					paciente = dao.consultar(cpf);

					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean registrado = false;

					for(Paciente paciente : lista) {
						if(cpf == paciente.getCpf()) {
							registrado = true;
							break;
						}
					}					

					if(registrado == true) {
						//Paciente
						txtNomePaciente.setText(paciente.getNome());
						txtDataNasc.setText(paciente.getDataNasc());
						txtIdade.setText(paciente.getIdade());
						txtEmail.setText(paciente.getEmail());
						txtCelular.setText(paciente.getCelular());
						//sexoPaciente
						if(paciente.getSexo().equals("M")) {
							rdbtnMasculino.setSelected(true);
						}else if(paciente.getSexo().equals("F")) {
							rdbtnFeminino.setSelected(true);
						}else {
							paciente.setSexo("X");
						}
					}else {
						JOptionPane.showMessageDialog(null,"Paciente não cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		txtCpfPaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpfPaciente.setColumns(10);
		txtCpfPaciente.setBounds(212, 86, 136, 16);
		contentPane.add(txtCpfPaciente);

		btnConsultarPaciente = new JButton("");
		btnConsultarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					long cpf = Long.parseLong(txtCpfPaciente.getText());

					dao = new PacienteDAO();
					paciente = dao.consultar(cpf);

					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean registrado = false;

					for(Paciente paciente : lista) {
						if(cpf == paciente.getCpf()) {
							registrado = true;
							break;
						}
					}					

					if(registrado == true) {
						//Paciente
						txtNomePaciente.setText(paciente.getNome());
						txtDataNasc.setText(paciente.getDataNasc());
						txtIdade.setText(paciente.getIdade());
						txtEmail.setText(paciente.getEmail());
						txtCelular.setText(paciente.getCelular());
						//sexoPaciente
						if(paciente.getSexo().equals("M")) {
							rdbtnMasculino.setSelected(true);
						}else if(paciente.getSexo().equals("F")) {
							rdbtnFeminino.setSelected(true);
						}else {
							paciente.setSexo("X");
						}
					}else {
						JOptionPane.showMessageDialog(null,"Paciente não cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnConsultarPaciente.setIcon(new ImageIcon(AlterarPaciente.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarPaciente.setToolTipText("Consultar");
		btnConsultarPaciente.setBounds(352, 86, 20, 16);
		contentPane.add(btnConsultarPaciente);

		lblPaciente = new JLabel("ALTERAR INFORMA\u00C7\u00D5ES DO PACIENTE");
		lblPaciente.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaciente.setBounds(81, 54, 301, 15);
		contentPane.add(lblPaciente);

		separator = new JSeparator();
		separator.setBounds(10, 63, 61, 3);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(381, 62, 57, 3);
		contentPane.add(separator_1);

		txtNomePaciente = new JTextField();
		txtNomePaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNomePaciente.setColumns(10);
		txtNomePaciente.setBounds(70, 124, 372, 16);
		contentPane.add(txtNomePaciente);

		lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Arial", Font.BOLD, 15));
		lblIdade.setBounds(193, 164, 52, 15);
		contentPane.add(lblIdade);

		txtIdade = new JTextField();
		txtIdade.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIdade.setColumns(10);
		txtIdade.setBounds(240, 163, 37, 16);
		contentPane.add(txtIdade);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(70, 198, 372, 16);
		contentPane.add(txtEmail);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setBounds(10, 199, 57, 14);
		contentPane.add(lblEmail);

		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Arial", Font.BOLD, 15));
		lblCelular.setBounds(10, 238, 57, 14);
		contentPane.add(lblCelular);

		lblSexoResponsavel = new JLabel("Sexo:");
		lblSexoResponsavel.setFont(new Font("Arial", Font.BOLD, 15));
		lblSexoResponsavel.setBounds(10, 275, 46, 15);
		contentPane.add(lblSexoResponsavel);

		rdbtnMasculino = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnMasculino.setBackground(new Color(153, 255, 204));
		rdbtnMasculino.setBounds(10, 297, 128, 14);
		contentPane.add(rdbtnMasculino);

		rdbtnFeminino = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFeminino);
		rdbtnFeminino.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnFeminino.setBackground(new Color(153, 255, 204));
		rdbtnFeminino.setBounds(140, 297, 113, 14);
		contentPane.add(rdbtnFeminino);

		btnAlterarPaciente = new JButton("");
		btnAlterarPaciente.setToolTipText("Salvar Alteracao");
		btnAlterarPaciente.setIcon(new ImageIcon(AlterarPaciente.class.getResource("/imgs/iconLapis.png")));
		btnAlterarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==================================================
				try {
					paciente = new Paciente();
					paciente.setCpf(Long.parseLong(txtCpfPaciente.getText()));
					paciente.setNome(txtNomePaciente.getText());
					paciente.setDataNasc(txtDataNasc.getText());
					paciente.setIdade(txtIdade.getText());
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
					dao.alterar(paciente);
					JOptionPane.showMessageDialog(null, "Paciente Alterado com Sucesso!");
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Erro ao Alterar Paciente" + e1);
				}
				//==================================================
			}
		});
		btnAlterarPaciente.setBounds(114, 333, 71, 67);
		contentPane.add(btnAlterarPaciente);

		btnLimparTela = new JButton("");
		btnLimparTela.setToolTipText("Limpar tela");
		btnLimparTela.setIcon(new ImageIcon(AlterarPaciente.class.getResource("/imgs/iconLimpar.png")));
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===============================================
				txtCpfPaciente.setText(null);
				txtNomePaciente.setText(null);
				txtDataNasc.setText(null);
				txtIdade.setText(null);
				txtEmail.setText(null);
				txtCelular.setText(null);
				rdbtnMasculino.setSelected(false);
				rdbtnFeminino.setSelected(false);
				//===============================================
			}
		});
		btnLimparTela.setBounds(271, 333, 71, 67);
		contentPane.add(btnLimparTela);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setToolTipText("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(AlterarPaciente.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(110, 11, 272, 41);
		contentPane.add(lblLogoTelaSecretaria);

		txtDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNasc.setBounds(93, 162, 86, 20);
		contentPane.add(txtDataNasc);

		txtCelular = new JFormattedTextField(new MaskFormatter("(##) # ####-####"));
		txtCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCelular.setColumns(10);
		txtCelular.setBounds(70, 236, 211, 16);
		contentPane.add(txtCelular);
		
		btnVoltarLogin = new JButton("");
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
		btnVoltarLogin.setIcon(new ImageIcon(AlterarPaciente.class.getResource("/imgs/iconeSair1.png")));
		btnVoltarLogin.setToolTipText("Voltar");
		btnVoltarLogin.setBackground(new Color(255, 102, 102));
		btnVoltarLogin.setBounds(10, 11, 38, 38);
		contentPane.add(btnVoltarLogin);
	}

}
