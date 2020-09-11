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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.PacienteDAO;
import dao.ProntuarioDAO;
import model.Paciente;
import model.Prontuario;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaProntuario extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnInicio;
	private JMenuItem mntmVoltarParaMenu;
	private JMenuItem mntmSair;
	private JMenu mnAjuda;
	private JMenuItem mntmSobre;
	private JLabel lblData0;
	private JLabel lblHora0;
	private JLabel lblHora;
	private JLabel lblData;
	private JLabel lblLogoTelaSecretaria;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JLabel lblNewLabel;
	private TextArea txtHistorico;
	private JTextField txtCpfPaciente;
	private JButton btnConsultarPaciente;
	private JLabel lblNome;
	private JLabel lblNomePaciente;
	private JLabel lblIdade;
	private JLabel lblIdadePaciente;
	private JLabel lblSexo;
	private JLabel lblSexoPaciente;
	private JPanel panel_1;
	private JLabel lblTipo;
	private JLabel lblDataParecer;
	private JTextField txtDataParecer;
	private JLabel lblObservaes;
	private TextArea txtObs;
	private JTextField txtHoraParecer;
	private JLabel lblHoraParecer;
	private JButton btnListaParecer;
	private Prontuario prontuario;
	private ProntuarioDAO daoProntuario;
	private PacienteDAO dao;
	private JButton button;
	private JLabel lblAtualizarHistorico;
	private JComboBox cmbTipoParecer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProntuario frame = new TelaProntuario();
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
			//txtHoraParecer.setText(String.format("%1$tH:%1$tM:%1$tS", now));
		}
	}
	//==============================================================

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaProntuario() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				// Data
				Date dataSistema = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				lblData.setText(formato.format(dataSistema));
				//txtDataParecer.setText(formato.format(dataSistema));

				//Hora
				Timer timer = new Timer(1000, new hora());
				timer.start();
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaProntuario.class.getResource("/imgs/iconePage2.png")));
		setTitle("Prontuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 503);
		this.setLocationRelativeTo(null); // Abrir tela no meio

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);

		mntmVoltarParaMenu = new JMenuItem("Voltar para menu");
		mntmVoltarParaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Menu Dentista ============================
				TelaMenuDentista exibir = new TelaMenuDentista();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual ==========
				setVisible(false);
				//==================================================================
			}
		});
		mnInicio.add(mntmVoltarParaMenu);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Login ============================
				TelaLogin exibir = new TelaLogin();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual==========
				setVisible(false);
				//==================================================================
			}
		});
		mnInicio.add(mntmSair);

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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(511, 12, 38, 14);
		contentPane.add(lblData0);

		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(511, 26, 38, 14);
		contentPane.add(lblHora0);

		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(552, 26, 71, 15);
		contentPane.add(lblHora);

		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(552, 12, 71, 15);
		contentPane.add(lblData);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(210, -12, 238, 60);
		contentPane.add(lblLogoTelaSecretaria);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 77, 613, 290);
		contentPane.add(tabbedPane);

		panel_1 = new JPanel();
		tabbedPane.addTab("Parecer ", null, panel_1, null);
		panel_1.setLayout(null);

		lblTipo = new JLabel("Procedimento:");
		lblTipo.setFont(new Font("Arial", Font.BOLD, 12));
		lblTipo.setBounds(10, 10, 86, 14);
		panel_1.add(lblTipo);

		lblDataParecer = new JLabel("Data:");
		lblDataParecer.setFont(new Font("Arial", Font.BOLD, 12));
		lblDataParecer.setBounds(317, 10, 42, 14);
		panel_1.add(lblDataParecer);

		txtDataParecer = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataParecer.setBackground(new Color(255, 255, 204));
		txtDataParecer.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDataParecer.setBounds(354, 7, 86, 21);
		panel_1.add(txtDataParecer);
		txtDataParecer.setColumns(10);

		lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservaes.setFont(new Font("Arial", Font.BOLD, 12));
		lblObservaes.setBounds(10, 40, 86, 14);
		panel_1.add(lblObservaes);

		txtObs = new TextArea();
		txtObs.setBackground(new Color(255, 255, 204));
		txtObs.setFont(new Font("Arial", Font.PLAIN, 15));
		txtObs.setBounds(10, 60, 588, 192);
		panel_1.add(txtObs);

		txtHoraParecer = new JFormattedTextField(new MaskFormatter("##:##:##"));
		txtHoraParecer.setBackground(new Color(255, 255, 204));
		txtHoraParecer.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHoraParecer.setColumns(10);
		txtHoraParecer.setBounds(512, 7, 86, 21);
		panel_1.add(txtHoraParecer);

		lblHoraParecer = new JLabel("Hora:");
		lblHoraParecer.setFont(new Font("Arial", Font.BOLD, 12));
		lblHoraParecer.setBounds(475, 10, 42, 14);
		panel_1.add(lblHoraParecer);
		
		cmbTipoParecer = new JComboBox();
		cmbTipoParecer.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbTipoParecer.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o", "Clareamento", "Cl\u00EDnica geral", "Consulta", "Endodontia", "Implante dent\u00E1rio", "Ortodontia", "Periodontia", "Pr\u00F3tese dent\u00E1ria m\u00F3vel"}));
		cmbTipoParecer.setBounds(102, 8, 193, 20);
		panel_1.add(cmbTipoParecer);

		panel = new JPanel();
		tabbedPane.addTab("Hist\u00F3rico", null, panel, null);
		panel.setLayout(null);

		txtHistorico = new TextArea();
		txtHistorico.setFont(new Font("Arial", Font.PLAIN, 15));
		txtHistorico.setBackground(new Color(255, 255, 204));
		txtHistorico.setEditable(false);
		txtHistorico.setBounds(10, 24, 588, 228);
		panel.add(txtHistorico);

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//========================================
				try {
					txtHistorico.setText(null);

					long cpfTela = Long.parseLong(txtCpfPaciente.getText());

					daoProntuario = new ProntuarioDAO();
					daoProntuario.consultar(cpfTela);
					List<Prontuario> lista2 = new ArrayList<Prontuario>();
					lista2 = daoProntuario.ListarTodos();

					txtHistorico.append("\t\t\t\t-- HISTORICO PESSOAL -- \n");
					for(Prontuario prontuario : lista2) {
						if(cpfTela == prontuario.getCpf()){
							txtHistorico.append("\nData: "+ prontuario.getDataParecer() +" às " + prontuario.getHoraParecer() + "\n");
							txtHistorico.append("Tipo: " + prontuario.getTipoParecer() + "\n\n");
							txtHistorico.append("Observações: \n" + prontuario.getObs() + "\n");
							txtHistorico.append("\n***********************************************************************************************\n");
						}
					}	
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Atualiazar tela de Histórico" + e1);
				}
				//========================================
			}
		});
		button.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/atualizarPg2.png")));
		button.setBounds(570, 0, 28, 22);
		panel.add(button);

		lblAtualizarHistorico = new JLabel("Atualizar Historico:");
		lblAtualizarHistorico.setFont(new Font("Arial", Font.BOLD, 12));
		lblAtualizarHistorico.setBounds(456, 3, 112, 15);
		panel.add(lblAtualizarHistorico);

		btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//================================================
				try{
					prontuario = new Prontuario();
					prontuario.setCpf(Long.parseLong(txtCpfPaciente.getText()));
					prontuario.setTipoParecer((String)cmbTipoParecer.getSelectedItem());
					prontuario.setDataParecer(txtDataParecer.getText());
					prontuario.setHoraParecer(txtHoraParecer.getText());
					prontuario.setObs(txtObs.getText());

					daoProntuario = new ProntuarioDAO();
					daoProntuario.salvar(prontuario);
					JOptionPane.showMessageDialog(null, "Prontuario Cadastrado com Sucesso!");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Salvar Prontuario\nMotivo: " + e1);
				}
				//================================================
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/iconSalvar.png")));
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBounds(10, 378, 71, 67);
		contentPane.add(btnSalvar);

		btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//============================================
				txtCpfPaciente.setText(null);
				lblNomePaciente.setText(null);
				lblIdadePaciente.setText(null);
				lblSexoPaciente.setText(null);
				cmbTipoParecer.setSelectedIndex(0);
				txtDataParecer.setText(null);
				txtHoraParecer.setText(null);
				txtObs.setText(null);
				txtHistorico.setText(null);
				//============================================
			}
		});
		btnLimpar.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/iconLimpar.png")));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setBounds(191, 378, 71, 67);
		contentPane.add(btnLimpar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================
				try {
					JOptionPane.showMessageDialog(null, "ATENÇÃO: \nPara excluir corretamente um prontuario verifique se os campos CPF, DATA e HORA estão preenchidos corretamente.");

					//Exclui Cadastro do Paciente
					prontuario = new Prontuario();
					prontuario.setCpf(Long.parseLong(txtCpfPaciente.getText()));
					prontuario.setDataParecer(txtDataParecer.getText());
					prontuario.setHoraParecer(txtHoraParecer.getText());

					daoProntuario = new ProntuarioDAO();
					daoProntuario.excluir(prontuario);
					JOptionPane.showMessageDialog(null,"Excluido com Sucesso");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Excluir os dados\nMotivo: " + e1);
				}
				//==========================================
			}
		});
		btnExcluir.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/iconExcluir.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(552, 378, 71, 67);
		contentPane.add(btnExcluir);

		lblNewLabel = new JLabel("Informe o CPF do Paciente:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 2, 162, 14);
		contentPane.add(lblNewLabel);

		txtCpfPaciente = new JTextField();
		txtCpfPaciente.setBackground(new Color(255, 255, 204));
		txtCpfPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================================================
				try {
					long cpfTela = Long.parseLong(txtCpfPaciente.getText());

					dao = new PacienteDAO();
					dao.consultar(cpfTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == cpfTela) {
							lblNomePaciente.setText(paciente.getNome());
							lblIdadePaciente.setText(paciente.getIdade());
							lblSexoPaciente.setText(paciente.getSexo());						

							achou = true;
							break;
						}
					}

					daoProntuario = new ProntuarioDAO();
					daoProntuario.consultar(cpfTela);
					List<Prontuario> lista2 = new ArrayList<Prontuario>();
					lista2 = daoProntuario.ListarTodos();

					boolean vazio = true;

					txtHistorico.append("\t\t\t\t-- HISTORICO PESSOAL -- \n");
					for(Prontuario prontuario : lista2) {
						if(cpfTela == prontuario.getCpf()){
							txtHistorico.append("\nData: "+ prontuario.getDataParecer() +" às " + prontuario.getHoraParecer() + "\n");
							txtHistorico.append("Tipo: " + prontuario.getTipoParecer() + "\n\n");
							txtHistorico.append("Observações: \n" + prontuario.getObs() + "\n");
							txtHistorico.append("\n***********************************************************************************************\n");

							vazio = false;
						}
					}

					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}else if(vazio == true) {
						txtHistorico.append("NADA RESGISTRADO !");
					}

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Responsavel \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		txtCpfPaciente.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCpfPaciente.setBounds(10, 25, 128, 14);
		contentPane.add(txtCpfPaciente);
		txtCpfPaciente.setColumns(10);

		btnConsultarPaciente = new JButton("");
		btnConsultarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================================================
				try {
					long cpfTela = Long.parseLong(txtCpfPaciente.getText());

					dao = new PacienteDAO();
					dao.consultar(cpfTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == cpfTela) {
							lblNomePaciente.setText(paciente.getNome());
							lblIdadePaciente.setText(paciente.getIdade());
							lblSexoPaciente.setText(paciente.getSexo());						

							achou = true;
							break;
						}
					}

					daoProntuario = new ProntuarioDAO();
					daoProntuario.consultar(cpfTela);
					List<Prontuario> lista2 = new ArrayList<Prontuario>();
					lista2 = daoProntuario.ListarTodos();

					boolean vazio = true;

					txtHistorico.append("\t\t\t\t-- HISTORICO PESSOAL -- \n");
					for(Prontuario prontuario : lista2) {
						if(cpfTela == prontuario.getCpf()){
							txtHistorico.append("\nData: "+ prontuario.getDataParecer() +" às " + prontuario.getHoraParecer() + "\n");
							txtHistorico.append("Tipo: " + prontuario.getTipoParecer() + "\n\n");
							txtHistorico.append("Observações: \n" + prontuario.getObs() + "\n");
							txtHistorico.append("\n***********************************************************************************************\n");

							vazio = false;
						}
					}

					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}else if(vazio == true) {
						txtHistorico.append("NADA RESGISTRADO !");
					}

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Responsavel \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		btnConsultarPaciente.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnConsultarPaciente.setBounds(143, 23, 19, 17);
		contentPane.add(btnConsultarPaciente);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 12));
		lblNome.setBounds(74, 51, 45, 15);
		contentPane.add(lblNome);

		lblNomePaciente = new JLabel("");
		lblNomePaciente.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNomePaciente.setBounds(115, 51, 182, 14);
		contentPane.add(lblNomePaciente);

		lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Arial", Font.BOLD, 12));
		lblIdade.setBounds(307, 51, 46, 14);
		contentPane.add(lblIdade);

		lblIdadePaciente = new JLabel("");
		lblIdadePaciente.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIdadePaciente.setBounds(346, 51, 46, 14);
		contentPane.add(lblIdadePaciente);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Arial", Font.BOLD, 12));
		lblSexo.setBounds(429, 51, 46, 14);
		contentPane.add(lblSexo);

		lblSexoPaciente = new JLabel("");
		lblSexoPaciente.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSexoPaciente.setBounds(469, 51, 90, 14);
		contentPane.add(lblSexoPaciente);

		btnListaParecer = new JButton("");
		btnListaParecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======== Deixar Visivel Tela Cadastro ============================
				try {
					TelaListarProntuario exibir;
					exibir = new TelaListarProntuario();
					exibir.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela de Cadastro \nMotivo:" + e1);
				}	
				//==================================================================
			}
		});
		btnListaParecer.setToolTipText("Listar Prontuarios");
		btnListaParecer.setIcon(new ImageIcon(TelaProntuario.class.getResource("/imgs/iconLista1.png")));
		btnListaParecer.setBounds(378, 378, 71, 67);
		contentPane.add(btnListaParecer);
	}
}
