package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import dao.EnderecoDAO;
import dao.PacienteDAO;
import model.Endereco;
import model.Paciente;

public class ExcluirEndereco extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogoTelaSecretaria;
	private JButton btnVoltarLogin;
	private JLabel lblCpf;
	private JTextField txtCpfPaciente;
	private JButton btnConsultarPaciente;
	private JSeparator separator;
	private JLabel lblPaciente;
	private JSeparator separator_1;
	private JButton btnExcluirPaciente;
	private JButton btnLimparTela;
	private JLabel lblCep;
	private JTextField txtCep;
	private JLabel lblRua;
	private JTextField txtRua;
	private JLabel lblNumeroCasa;
	private JTextField txtNumeroCasa;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private EnderecoDAO daoEnd;
	private Endereco endereco;
	private JLabel lblNomePaciente;
	private PacienteDAO daoPaciente;
	private JLabel lblPaciente_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirEndereco frame = new ExcluirEndereco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExcluirEndereco() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.DARK_GRAY, new Color(64, 64, 64)));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		contentPane.setLayout(null);
		
		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(ExcluirEndereco.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(105, 11, 262, 44);
		contentPane.add(lblLogoTelaSecretaria);
		
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
		btnVoltarLogin.setIcon(new ImageIcon(ExcluirEndereco.class.getResource("/imgs/iconeSair1.png")));
		btnVoltarLogin.setToolTipText("Voltar");
		btnVoltarLogin.setBackground(new Color(255, 102, 102));
		btnVoltarLogin.setBounds(10, 11, 38, 38);
		contentPane.add(btnVoltarLogin);
		
		lblCpf = new JLabel("Informe o CPF do Paciente:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblCpf.setBounds(10, 131, 201, 15);
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
					
					daoEnd = new EnderecoDAO();
					endereco = daoEnd.consultar(cpf);

					List<Endereco> lista = new ArrayList<Endereco>();
					lista = daoEnd.ListarTodos();

					boolean registrado = false;

					for(Endereco endereco : lista) {
						if(cpf == endereco.getCpf()) {
							registrado = true;
							break;
						}
					}					

					if(registrado == true) {
						//Paciente
						txtCep.setText(endereco.getCep());
						txtRua.setText(endereco.getRua());
						txtNumeroCasa.setText(endereco.getNumeroCasa());
						txtBairro.setText(endereco.getBairro());
						txtCidade.setText(endereco.getCidade());
						if(endereco.getEstado().equals("---")) cmbEstado.setSelectedItem("---");
						else if(endereco.getEstado().equals("SP")) cmbEstado.setSelectedItem("SP");
						else cmbEstado.setSelectedItem("RJ");
					}else {
						JOptionPane.showMessageDialog(null,"Sem Endereco cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		txtCpfPaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCpfPaciente.setColumns(10);
		txtCpfPaciente.setBounds(209, 130, 120, 16);
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
					
					daoEnd = new EnderecoDAO();
					endereco = daoEnd.consultar(cpf);

					List<Endereco> lista = new ArrayList<Endereco>();
					lista = daoEnd.ListarTodos();

					boolean registrado = false;

					for(Endereco endereco : lista) {
						if(cpf == endereco.getCpf()) {
							registrado = true;
							break;
						}
					}					

					if(registrado == true) {
						//Paciente
						txtCep.setText(endereco.getCep());
						txtRua.setText(endereco.getRua());
						txtNumeroCasa.setText(endereco.getNumeroCasa());
						txtBairro.setText(endereco.getBairro());
						txtCidade.setText(endereco.getCidade());
						if(endereco.getEstado().equals("---")) cmbEstado.setSelectedItem("---");
						else if(endereco.getEstado().equals("SP")) cmbEstado.setSelectedItem("SP");
						else cmbEstado.setSelectedItem("RJ");
					}else {
						JOptionPane.showMessageDialog(null,"Sem Endereco cadastrado");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnConsultarPaciente.setIcon(new ImageIcon(ExcluirEndereco.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarPaciente.setToolTipText("Consultar");
		btnConsultarPaciente.setBounds(339, 130, 20, 16);
		contentPane.add(btnConsultarPaciente);
		
		separator = new JSeparator();
		separator.setBounds(10, 78, 125, 2);
		contentPane.add(separator);
		
		lblPaciente = new JLabel("EXCLUIR ENDERECO");
		lblPaciente.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaciente.setBounds(151, 68, 158, 15);
		contentPane.add(lblPaciente);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(319, 77, 125, 3);
		contentPane.add(separator_1);
		
		btnExcluirPaciente = new JButton("");
		btnExcluirPaciente.setToolTipText("Excluir Endereco");
		btnExcluirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					
					//Excluir Endereco
					endereco = new Endereco();
					endereco.setCpf(Long.parseLong(txtCpfPaciente.getText()));

					daoEnd = new EnderecoDAO();
					daoEnd.excluir(endereco);

					JOptionPane.showMessageDialog(null,"Excluido com Sucesso");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Excluir os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnExcluirPaciente.setIcon(new ImageIcon(ExcluirEndereco.class.getResource("/imgs/iconExcluir.png")));
		btnExcluirPaciente.setBounds(105, 355, 71, 67);
		contentPane.add(btnExcluirPaciente);
		
		btnLimparTela = new JButton("");
		btnLimparTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				txtCpfPaciente.setText(null);
				txtCep.setText(null);
				txtRua.setText(null);
				txtNumeroCasa.setText(null);
				txtBairro.setText(null);
				txtCidade.setText(null);
				cmbEstado.setSelectedIndex(0);
				//==========================================
			}
		});
		btnLimparTela.setIcon(new ImageIcon(ExcluirEndereco.class.getResource("/imgs/iconLimpar.png")));
		btnLimparTela.setBounds(258, 355, 71, 67);
		contentPane.add(btnLimparTela);
		
		lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Arial", Font.BOLD, 15));
		lblCep.setBounds(10, 166, 38, 15);
		contentPane.add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setEnabled(false);
		txtCep.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCep.setColumns(10);
		txtCep.setBounds(52, 166, 96, 16);
		contentPane.add(txtCep);
		
		lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Arial", Font.BOLD, 15));
		lblRua.setBounds(10, 193, 38, 15);
		contentPane.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setEnabled(false);
		txtRua.setFont(new Font("Arial", Font.PLAIN, 15));
		txtRua.setColumns(10);
		txtRua.setBounds(52, 193, 380, 16);
		contentPane.add(txtRua);
		
		lblNumeroCasa = new JLabel("N\u00B0:");
		lblNumeroCasa.setFont(new Font("Arial", Font.BOLD, 15));
		lblNumeroCasa.setBounds(10, 220, 34, 15);
		contentPane.add(lblNumeroCasa);
		
		txtNumeroCasa = new JTextField();
		txtNumeroCasa.setEnabled(false);
		txtNumeroCasa.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNumeroCasa.setColumns(10);
		txtNumeroCasa.setBounds(40, 219, 57, 16);
		contentPane.add(txtNumeroCasa);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Arial", Font.BOLD, 15));
		lblBairro.setBounds(10, 247, 57, 15);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBairro.setColumns(10);
		txtBairro.setBounds(62, 246, 225, 16);
		contentPane.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCidade.setColumns(10);
		txtCidade.setBounds(74, 271, 213, 16);
		contentPane.add(txtCidade);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Arial", Font.BOLD, 15));
		lblCidade.setBounds(10, 273, 57, 14);
		contentPane.add(lblCidade);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Arial", Font.BOLD, 15));
		lblEstado.setBounds(10, 304, 57, 14);
		contentPane.add(lblEstado);
		
		cmbEstado = new JComboBox();
		cmbEstado.setEnabled(false);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"---", "SP", "RJ"}));
		cmbEstado.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbEstado.setBounds(77, 303, 71, 20);
		contentPane.add(cmbEstado);
		
		lblNomePaciente = new JLabel("");
		lblNomePaciente.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNomePaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomePaciente.setBounds(87, 94, 345, 20);
		contentPane.add(lblNomePaciente);
		
		lblPaciente_1 = new JLabel("Paciente: ");
		lblPaciente_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaciente_1.setBounds(10, 99, 87, 14);
		contentPane.add(lblPaciente_1);
	}
}
