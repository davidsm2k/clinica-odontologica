package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.AgendamentoDAO;
import dao.PacienteDAO;
import model.Agendamento;
import model.Paciente;

public class TelaListarAgenda extends JFrame {

	private JPanel contentPane;
	private JSeparator separator;
	private JLabel lblListaAgendamentos;
	private JSeparator separator_1;
	private JLabel lblHora0;
	private JLabel lblData0;
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblLogoTelaSecretaria;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable tbAgenda;
	private PacienteDAO dao;
	private Paciente paciente;
	private AgendamentoDAO daoAgendamento;
	private Agendamento agendamento;
	private JLabel lblDataFiltro;
	private JLabel lblFiltro;
	private JLabel lblTurnoFiltro;
	private JTextField txtDataFiltro;
	private JTextField txtTurnoFiltro;
	private JButton btnFiltrar;
	private JButton btnListagemOriginal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarAgenda frame = new TelaListarAgenda();
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

	public TelaListarAgenda() throws ParseException {
		setUndecorated(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				//===================================================
				// Data
				Date dataSistema = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				lblData.setText(formato.format(dataSistema));

				//Hora
				Timer timer = new Timer(1000, new hora());
				timer.start();
				//===================================================

				try {			
					DefaultTableModel model = (DefaultTableModel) tbAgenda.getModel();	
					dao = new PacienteDAO();
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoAgendamento = new AgendamentoDAO();
					List<Agendamento> lista2 = new ArrayList<Agendamento>();
					lista2 = daoAgendamento.ListarTodos();

					for(Paciente paciente : lista) {
						for(Agendamento agendamento : lista2) {
							if(paciente.getCpf() == agendamento.getCpf()) {
								model.addRow(new Object[] { 
										paciente.getNome(), paciente.getIdade(), paciente.getSexo(), paciente.getEmail(),
										agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()
								});
							}
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Listagem \nMotivo: " + e1);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 255, 204));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null); // Abrir tela no meio

		separator = new JSeparator();
		separator.setBounds(10, 64, 303, 8);
		contentPane.add(separator);


		lblListaAgendamentos = new JLabel("LISTA DE AGENDAMENTOS");
		lblListaAgendamentos.setFont(new Font("Arial", Font.BOLD, 15));
		lblListaAgendamentos.setBounds(326, 57, 225, 18);
		contentPane.add(lblListaAgendamentos);

		separator_1 = new JSeparator();
		separator_1.setBounds(534, 64, 303, 8);
		contentPane.add(separator_1);

		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(725, 25, 38, 14);
		contentPane.add(lblHora0);

		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(725, 11, 38, 14);
		contentPane.add(lblData0);

		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(766, 11, 71, 15);
		contentPane.add(lblData);

		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(766, 25, 71, 15);
		contentPane.add(lblHora);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(TelaListarAgenda.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(302, 0, 232, 60);
		contentPane.add(lblLogoTelaSecretaria);

		btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Fechar");
		btnNewButton.setIcon(new ImageIcon(TelaListarAgenda.class.getResource("/imgs/iconeSair1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=========================================
				try {
					TelaAgendamento exibir;
					exibir = new TelaAgendamento();
					exibir.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				//=========================================
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(10, 11, 38, 38);
		contentPane.add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.BOLD, 12));
		scrollPane.setBounds(10, 110, 827, 379);
		contentPane.add(scrollPane);

		tbAgenda = new JTable();
		tbAgenda.setBackground(new Color(255, 255, 204));
		tbAgenda.setFont(new Font("Arial", Font.PLAIN, 12));
		tbAgenda.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"NOME", "IDADE", "SEXO", "E-MAIL", "PROCEDIMENTO", "DATA", "HORA", "TURNO"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});	
		tbAgenda.getColumnModel().getColumn(0).setPreferredWidth(154);
		tbAgenda.getColumnModel().getColumn(1).setPreferredWidth(47);
		tbAgenda.getColumnModel().getColumn(2).setPreferredWidth(44);
		tbAgenda.getColumnModel().getColumn(3).setPreferredWidth(127);
		tbAgenda.getColumnModel().getColumn(4).setPreferredWidth(132);
		tbAgenda.getColumnModel().getColumn(5).setPreferredWidth(51);
		tbAgenda.getColumnModel().getColumn(6).setPreferredWidth(47);
		tbAgenda.getColumnModel().getColumn(7).setPreferredWidth(47);
		scrollPane.setViewportView(tbAgenda);

		lblDataFiltro = new JLabel("DATA");
		lblDataFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataFiltro.setBounds(69, 85, 46, 14);
		contentPane.add(lblDataFiltro);

		lblFiltro = new JLabel("FILTROS:");
		lblFiltro.setFont(new Font("Arial", Font.BOLD, 12));
		lblFiltro.setBounds(10, 85, 64, 14);
		contentPane.add(lblFiltro);

		lblTurnoFiltro = new JLabel("TURNO");
		lblTurnoFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTurnoFiltro.setBounds(199, 85, 46, 14);
		contentPane.add(lblTurnoFiltro);

		txtDataFiltro = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataFiltro.setBackground(new Color(255, 255, 204));
		txtDataFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDataFiltro.setBounds(103, 85, 86, 14);
		contentPane.add(txtDataFiltro);
		txtDataFiltro.setColumns(10);

		txtTurnoFiltro = new JTextField();		
		txtTurnoFiltro.setBackground(new Color(255, 255, 204));
		txtTurnoFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTurnoFiltro.setColumns(10);
		txtTurnoFiltro.setBounds(247, 85, 32, 14);
		contentPane.add(txtTurnoFiltro);

		btnFiltrar = new JButton("");
		btnFiltrar.setIcon(new ImageIcon(TelaListarAgenda.class.getResource("/imgs/iconFiltro.png")));
		btnFiltrar.setToolTipText("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tbAgenda.getModel();
				if(txtDataFiltro.getText().equals("") && txtTurnoFiltro.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe uma Data ou turno para filtrar");
				}
				//Erro:
				else model.setNumRows(0);
				try {								
					dao = new PacienteDAO();
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoAgendamento = new AgendamentoDAO();
					List<Agendamento> lista2 = new ArrayList<Agendamento>();
					lista2 = daoAgendamento.ListarTodos();

					//Tipos de Filtro
					for(Paciente paciente : lista) {
						for(Agendamento agendamento : lista2) {
							if(paciente.getCpf() == agendamento.getCpf()) {
								//===========================================================
								//Filtrar somente a DATA
								if(txtDataFiltro.getText().equals(agendamento.getDataAgendamento()) && txtTurnoFiltro.getText().equals("")) {
									model.addRow(new Object[] { 
											paciente.getNome(), paciente.getIdade(), paciente.getSexo(), paciente.getEmail(),
											agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()
									});
								}

								//Filtrar somente o Turno
								else if(txtTurnoFiltro.getText().equals(agendamento.getTurno()) && txtDataFiltro.getText().equals("  /  /    ")) {
									model.addRow(new Object[] { 
											paciente.getNome(), paciente.getIdade(), paciente.getSexo(), paciente.getEmail(),
											agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()
									});
								}

								//Filtrar com Data e Turno
								else if(txtDataFiltro.getText().equals(agendamento.getDataAgendamento()) && txtTurnoFiltro.getText().equals(agendamento.getTurno())) {
									model.addRow(new Object[] { 
											paciente.getNome(), paciente.getIdade(), paciente.getSexo(), paciente.getEmail(),
											agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()
									});
								}

								//===========================================================
							}
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Listagem \nMotivo: " + e1);
				}
			}
		});
		btnFiltrar.setBounds(281, 83, 21, 18);
		contentPane.add(btnFiltrar);

		btnListagemOriginal = new JButton("Listagem Original");
		btnListagemOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================================
				DefaultTableModel model = (DefaultTableModel) tbAgenda.getModel();	
				model.setNumRows(0);

				try {			
					dao = new PacienteDAO();
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoAgendamento = new AgendamentoDAO();
					List<Agendamento> lista2 = new ArrayList<Agendamento>();
					lista2 = daoAgendamento.ListarTodos();

					for(Paciente paciente : lista) {
						for(Agendamento agendamento : lista2) {
							if(paciente.getCpf() == agendamento.getCpf()) {
								model.addRow(new Object[] { 
										paciente.getNome(), paciente.getIdade(), paciente.getSexo(), paciente.getEmail(),
										agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getTurno()
								});
							}
						}
					}

					txtDataFiltro.setText(null);
					txtTurnoFiltro.setText(null);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Listagem \nMotivo: " + e1);
				}
				//==========================================================
			}
		});
		btnListagemOriginal.setFont(new Font("Arial", Font.BOLD, 12));
		btnListagemOriginal.setBounds(698, 85, 139, 16);
		contentPane.add(btnListagemOriginal);
	}
}
