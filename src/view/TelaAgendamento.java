package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.AgendamentoDAO;
import dao.PacienteDAO;
import dao.ProntuarioDAO;
import model.Agendamento;
import model.Paciente;
import model.Prontuario;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class TelaAgendamento extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnInicio;
	private JMenu mnAjuda;
	private JMenuItem mntmVoltarParaMenu;
	private JMenuItem mntmSair;
	private JMenuItem mntmSobre;
	private JLabel lblLogoTelaSecretaria;
	private JLabel lblHora0;
	private JLabel lblData0;
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblAgendamento;
	private JSeparator separator_1;
	private JSeparator separator;
	private JLabel lblCpf;
	private JTextField txtCpf;
	private JLabel lblTurno;
	private JComboBox cmbTurno;
	private JButton btnBuscar;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblCelular;
	private JTextField txtCelular;
	private JLabel lblData_1;
	private JLabel lblHora_1;
	private JLabel lblHistoricoAgendamento;
	private JLabel lblTipo;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnAgendamentosCadastrados;
	private JComboBox cmbHora;
	private PacienteDAO dao;
	private Paciente paciente;
	private Agendamento agendamento;
	private AgendamentoDAO daoAgendamento;
	private JTextField txtData;
	private JComboBox cmbTipoAgendamento;
	private JScrollPane scrollPane;
	private JTable tbHistorico;
	private JLabel lblId;
	private JTextField txtIdExcluir;
	private JButton btnExcluir;
	private JSeparator separator_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgendamento frame = new TelaAgendamento();
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

	public TelaAgendamento() throws ParseException {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				// Data
				Date dataSistema = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				lblData.setText(formato.format(dataSistema));

				//Hora
				Timer timer = new Timer(1000, new hora());
				timer.start();
			}
		});
		setTitle("Agendamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaAgendamento.class.getResource("/imgs/iconePage2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 459);
		this.setLocationRelativeTo(null); // Abrir tela no meio

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);

		mntmVoltarParaMenu = new JMenuItem("Voltar para menu");
		mntmVoltarParaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//======== Deixar Visivel Tela Menu Secretaria ============================
				TelaMenuSecretaria exibir = new TelaMenuSecretaria();
				exibir.setVisible(true);
				//==================================================================

				//======== Não Deixar Visivel a Tela Atual(Tela de Login) ==========
				setVisible(false);
				//==================================================================
			}
		});
		mnInicio.add(mntmVoltarParaMenu);

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(204, 255, 204));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(TelaAgendamento.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(188, 0, 251, 43);
		contentPane.add(lblLogoTelaSecretaria);

		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(483, 24, 38, 14);
		contentPane.add(lblHora0);

		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(483, 10, 38, 14);
		contentPane.add(lblData0);

		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(524, 9, 71, 15);
		contentPane.add(lblData);

		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(524, 23, 71, 15);
		contentPane.add(lblHora);

		lblAgendamento = new JLabel("AGENDAMENTO");
		lblAgendamento.setFont(new Font("Arial", Font.BOLD, 15));
		lblAgendamento.setBounds(250, 51, 136, 18);
		contentPane.add(lblAgendamento);

		separator_1 = new JSeparator();
		separator_1.setBounds(382, 61, 213, 8);
		contentPane.add(separator_1);

		separator = new JSeparator();
		separator.setBounds(10, 61, 220, 8);
		contentPane.add(separator);

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 12));
		lblCpf.setBounds(10, 78, 46, 14);
		contentPane.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBackground(new Color(255, 255, 204));
		txtCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================================================
				DefaultTableModel model = (DefaultTableModel) tbHistorico.getModel();

				try {
					long cpfTela = Long.parseLong(txtCpf.getText());

					dao = new PacienteDAO();
					dao.consultar(cpfTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == cpfTela) {
							txtNome.setText(paciente.getNome());
							txtEmail.setText(paciente.getEmail());
							txtCelular.setText(paciente.getCelular());						

							achou = true;
							break;
						}
					}
					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}
					//ListaHistorico

					daoAgendamento = new AgendamentoDAO();
					daoAgendamento.consultar(cpfTela);
					List<Agendamento> lista2 = new ArrayList<Agendamento>();
					lista2 = daoAgendamento.ListarTodos();

					//Inserir info na tabela

					for(Agendamento agendamento : lista2) {
						if(agendamento.getCpf() == cpfTela) {
							model.addRow(new Object[] { agendamento.getCodAgendamento(), agendamento.getTipoAgendamento(), 
									agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()});
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Paciente \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCpf.setBounds(42, 77, 167, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Arial", Font.BOLD, 12));
		lblTurno.setBounds(10, 149, 46, 14);
		contentPane.add(lblTurno);

		cmbTurno = new JComboBox();
		cmbTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=========================================
				if(cmbTurno.getSelectedItem().equals("Matutino")) {
					cmbHora.setModel(new DefaultComboBoxModel(new String[] {"---","08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30"}));
				} else if(cmbTurno.getSelectedItem().equals("Vespertino")) {
					cmbHora.setModel(new DefaultComboBoxModel(new String[] {"---","13:00","13:30","14:00","14:30","15:30","16:00","16:30","17:30"}));
				}else {
					cmbHora.setModel(new DefaultComboBoxModel(new String[] {"---"}));
				}
				//=========================================
			}
		});
		cmbTurno.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {"---", "Matutino", "Vespertino"}));
		cmbTurno.setBounds(53, 147, 85, 20);
		contentPane.add(cmbTurno);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//==========================================================================
				DefaultTableModel model = (DefaultTableModel) tbHistorico.getModel();

				try {
					long cpfTela = Long.parseLong(txtCpf.getText());

					dao = new PacienteDAO();
					dao.consultar(cpfTela);
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						if(paciente.getCpf() == cpfTela) {
							txtNome.setText(paciente.getNome());
							txtEmail.setText(paciente.getEmail());
							txtCelular.setText(paciente.getCelular());						

							achou = true;
							break;
						}
					}
					if(achou == false) {
						JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					}
					//ListaHistorico

					daoAgendamento = new AgendamentoDAO();
					daoAgendamento.consultar(cpfTela);
					List<Agendamento> lista2 = new ArrayList<Agendamento>();
					lista2 = daoAgendamento.ListarTodos();

					//Exibir info na tabela

					for(Agendamento agendamento : lista2) {
						if(agendamento.getCpf() == cpfTela) {
							model.addRow(new Object[] { agendamento.getCodAgendamento(), agendamento.getTipoAgendamento(), 
									agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()});
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Paciente \nMotivo: " + e1);
				}
				//==========================================================================
			}
		});
		btnBuscar.setIcon(new ImageIcon(TelaAgendamento.class.getResource("/imgs/icons8-pesquisar-24.png")));
		btnBuscar.setBounds(211, 78, 19, 19);
		contentPane.add(btnBuscar);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 12));
		lblNome.setBounds(302, 78, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setColumns(10);
		txtNome.setBounds(344, 77, 251, 20);
		contentPane.add(txtNome);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		lblEmail.setBounds(10, 111, 46, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(51, 109, 224, 20);
		contentPane.add(txtEmail);

		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Arial", Font.BOLD, 12));
		lblCelular.setBounds(302, 111, 64, 14);
		contentPane.add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setEditable(false);
		txtCelular.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCelular.setColumns(10);
		txtCelular.setBounds(354, 109, 241, 20);
		contentPane.add(txtCelular);

		lblData_1 = new JLabel("Data:");
		lblData_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblData_1.setBounds(148, 149, 46, 14);
		contentPane.add(lblData_1);

		lblHora_1 = new JLabel("Hora:");
		lblHora_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora_1.setBounds(255, 150, 46, 14);
		contentPane.add(lblHora_1);

		lblHistoricoAgendamento = new JLabel("Hist\u00F3rico de Agendamento:");
		lblHistoricoAgendamento.setFont(new Font("Arial", Font.BOLD, 12));
		lblHistoricoAgendamento.setBounds(11, 209, 166, 15);
		contentPane.add(lblHistoricoAgendamento);

		lblTipo = new JLabel("Procedimento:");
		lblTipo.setFont(new Font("Arial", Font.BOLD, 12));
		lblTipo.setBounds(362, 149, 92, 14);
		contentPane.add(lblTipo);

		btnSalvar = new JButton("Finalizar Agendamento");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=================================================
				DefaultTableModel model = (DefaultTableModel) tbHistorico.getModel();
				long cpfTela = Long.parseLong(txtCpf.getText());
				String dataTela = txtData.getText();
				String horaTela = (String)cmbHora.getSelectedItem();

				try {
					
					daoAgendamento.consultar(cpfTela);
					List<Agendamento> lista3 = new ArrayList<Agendamento>();
					lista3 = daoAgendamento.ListarTodos();
					
					boolean liberado = true;
					
					// Verificando se agenda esta liberada
					for(Agendamento agendamento : lista3) {
						if(agendamento.getDataAgendamento().equals(dataTela) && agendamento.getHoraAgendamento().equals(horaTela)) {
							liberado = false;
						}
					}
					
					if(liberado == true) {
						try{
							agendamento = new Agendamento();
							agendamento.setCpf(Long.parseLong(txtCpf.getText()));
							if(cmbTurno.getSelectedItem().equals("Matutino")) {
								agendamento.setTurno("M");
							} else if(cmbTurno.getSelectedItem().equals("Vespertino")) {
								agendamento.setTurno("V");
							}else {
								agendamento.setTurno("X");
							}
							agendamento.setDataAgendamento(txtData.getText());
							agendamento.setHoraAgendamento((String)cmbHora.getSelectedItem());
							if(cmbTipoAgendamento.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Insira um procedimento");
							}else {
								agendamento.setTipoAgendamento((String)cmbTipoAgendamento.getSelectedItem());
							}

							daoAgendamento = new AgendamentoDAO();
							daoAgendamento.salvar(agendamento);
							JOptionPane.showMessageDialog(null, "Agendamento Cadastrado com Sucesso!");

							//limpar tela
							model.setNumRows(0);

						}catch(Exception e1) {
							JOptionPane.showMessageDialog(null, "Erro ao Salvar Agendamento\nMotivo: " + e1);
						}

						//Tabela
						try {
							daoAgendamento.consultar(cpfTela);
							List<Agendamento> lista2 = new ArrayList<Agendamento>();
							lista2 = daoAgendamento.ListarTodos();

							//Exibir info na tabela
							for(Agendamento agendamento : lista2) {
								if(agendamento.getCpf() == cpfTela) {
									model.addRow(new Object[] { agendamento.getCodAgendamento(), agendamento.getTipoAgendamento(), 
											agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()});
								}
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Erro ao Atualizar Historico\nMotivo: " + e1);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Agenda Indisponivel !");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao verificar disponibilidade na agenda\nMotivo: " + e1);
				}

				
			}
			//================================================
		});
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalvar.setBounds(230, 177, 167, 23);
		contentPane.add(btnSalvar);

		btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=========================================
				txtCpf.setText(null);
				txtNome.setText(null);
				txtEmail.setText(null);
				txtCelular.setText(null);
				cmbTurno.setSelectedIndex(0);
				txtData.setText(null);
				cmbHora.setSelectedIndex(0);
				cmbTipoAgendamento.setSelectedIndex(0);
				DefaultTableModel model = (DefaultTableModel) tbHistorico.getModel();
				model.setNumRows(0);
				txtIdExcluir.setText(null);
				//=========================================
			}
		});
		btnLimpar.setIcon(new ImageIcon(TelaAgendamento.class.getResource("/imgs/iconVassoura2.png")));
		btnLimpar.setBounds(235, 78, 22, 20);
		contentPane.add(btnLimpar);

		btnAgendamentosCadastrados = new JButton("");
		btnAgendamentosCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//======== Deixar Visivel Tela Lista Agenda ============================
				try {
					DefaultTableModel model = (DefaultTableModel) tbHistorico.getModel();
					model.setNumRows(0);
					TelaListarAgenda exibir;
					exibir = new TelaListarAgenda();
					exibir.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Exibir tela de Listar Agenda \nMotivo:" + e1);
				}				

				setVisible(false);
				//==================================================================
			}
		});
		btnAgendamentosCadastrados.setIcon(new ImageIcon(TelaAgendamento.class.getResource("/imgs/iconAgenda1.png")));
		btnAgendamentosCadastrados.setToolTipText("Agenda");
		btnAgendamentosCadastrados.setFont(new Font("Arial", Font.BOLD, 12));
		btnAgendamentosCadastrados.setBounds(10, 9, 41, 46);
		contentPane.add(btnAgendamentosCadastrados);

		cmbHora = new JComboBox();
		cmbHora.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbHora.setModel(new DefaultComboBoxModel(new String[] {"---"}));
		cmbHora.setBounds(290, 147, 58, 20);
		contentPane.add(cmbHora);

		txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtData.setBackground(new Color(255, 255, 204));
		txtData.setFont(new Font("Arial", Font.PLAIN, 12));
		txtData.setColumns(10);
		txtData.setBounds(181, 147, 68, 20);
		contentPane.add(txtData);


		cmbTipoAgendamento = new JComboBox();
		cmbTipoAgendamento.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbTipoAgendamento.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o", "Clareamento", "Cl\u00EDnica geral", "Consulta", "Endodontia", "Implante dent\u00E1rio", "Ortodontia", "Periodontia", "Pr\u00F3tese dent\u00E1ria m\u00F3vel"}));
		cmbTipoAgendamento.setBounds(448, 147, 147, 20);
		contentPane.add(cmbTipoAgendamento);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 231, 585, 167);
		contentPane.add(scrollPane);

		tbHistorico = new JTable();
		tbHistorico.setBackground(new Color(255, 255, 204));
		tbHistorico.setEnabled(false);
		tbHistorico.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "PROCEDIMENTO", "DATA", "HORA", "TURNO"
				}
				) {
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbHistorico);

		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Arial", Font.BOLD, 12));
		lblId.setBounds(456, 211, 19, 14);
		contentPane.add(lblId);

		txtIdExcluir = new JTextField();
		txtIdExcluir.setBackground(new Color(255, 255, 204));
		txtIdExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		txtIdExcluir.setBounds(475, 210, 27, 17);
		contentPane.add(txtIdExcluir);
		txtIdExcluir.setColumns(10);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//==============================================
				DefaultTableModel model = (DefaultTableModel) tbHistorico.getModel();
				long cpfTela = Long.parseLong(txtCpf.getText());

				try {

					//Excluir Endereco
					agendamento = new Agendamento();
					agendamento.setCodAgendamento(Integer.parseInt(txtIdExcluir.getText()));

					daoAgendamento = new AgendamentoDAO();
					daoAgendamento.excluir(agendamento);

					JOptionPane.showMessageDialog(null,"Excluido com Sucesso");

					//limpar tela
					model.setNumRows(0);

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Excluir os dados\nMotivo: " + e1);
				}

				//Tabela
				try {
					daoAgendamento.consultar(cpfTela);
					List<Agendamento> lista2 = new ArrayList<Agendamento>();
					lista2 = daoAgendamento.ListarTodos();

					//Exibir info na tabela
					for(Agendamento agendamento : lista2) {
						if(agendamento.getCpf() == cpfTela) {
							model.addRow(new Object[] { agendamento.getCodAgendamento(), agendamento.getTipoAgendamento(), 
									agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()});
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Atualizar Historico\nMotivo: " + e1);
				}
				//==============================================
			}
		});
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 12));
		btnExcluir.setBounds(506, 210, 89, 17);
		contentPane.add(btnExcluir);

		separator_2 = new JSeparator();
		separator_2.setBounds(10, 205, 585, 8);
		contentPane.add(separator_2);
	}
}
