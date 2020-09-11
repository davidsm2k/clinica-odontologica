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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import dao.PacienteDAO;
import dao.ProntuarioDAO;
import model.Paciente;
import model.Prontuario;

public class TelaListarProntuario extends JFrame {

	private JPanel contentPane;
	private TextArea txtListaTodoProntuario;
	private JLabel lblLogoTelaSecretaria;
	private JLabel lblData0;
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblHora0;
	private JButton btnNewButton;
	private PacienteDAO dao;
	private ProntuarioDAO daoProntuario;
	private JLabel lblProntuariosRegistrados;
	private JSeparator separator;
	private JSeparator separator_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarProntuario frame = new TelaListarProntuario();
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
	public TelaListarProntuario() {
		setUndecorated(true);
		setTitle("LISTAR PRONTUARIOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaListarProntuario.class.getResource("/imgs/iconePage2.png")));
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
				try {
					//Listar Prontuarios
					dao = new PacienteDAO();
					List<Paciente> lista = new ArrayList<Paciente>();
					lista = dao.ListarTodos();

					daoProntuario = new ProntuarioDAO();
					List<Prontuario> lista2 = new ArrayList<Prontuario>();
					lista2 = daoProntuario.ListarTodos();

					boolean achou = false;

					for(Paciente paciente : lista) {
						for(Prontuario prontuario : lista2) {
							if(paciente.getCpf() == prontuario.getCpf()) {
								txtListaTodoProntuario.append("Nome Paciente: " + paciente.getNome() + "\n");
								txtListaTodoProntuario.append("CPF Paciente:" + paciente.getCpf() + "\n");
								txtListaTodoProntuario.append("\n\t-- Registros --\n");
								txtListaTodoProntuario.append("Data: "+ prontuario.getDataParecer() +" às " + prontuario.getHoraParecer() + "\n");
								txtListaTodoProntuario.append("Tipo: " + prontuario.getTipoParecer() + "\n");
								txtListaTodoProntuario.append("\nObservações: \n" + prontuario.getObs() + "\n");
								txtListaTodoProntuario.append("\n*******************************************************************************************************\n");
							}
						}
					}

				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro ao Consultar Listagem \nMotivo: " + e1);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null); // Abrir tela no meio
		setUndecorated(true);

		txtListaTodoProntuario = new TextArea();
		txtListaTodoProntuario.setBackground(new Color(255, 255, 204));
		txtListaTodoProntuario.setEditable(false);
		txtListaTodoProntuario.setFont(new Font("Arial", Font.PLAIN, 15));
		txtListaTodoProntuario.setBounds(10, 92, 630, 394);
		contentPane.add(txtListaTodoProntuario);

		lblLogoTelaSecretaria = new JLabel("");
		lblLogoTelaSecretaria.setIcon(new ImageIcon(TelaListarProntuario.class.getResource("/imgs/logoClinicaOdonto6.png")));
		lblLogoTelaSecretaria.setBounds(209, 0, 232, 60);
		contentPane.add(lblLogoTelaSecretaria);

		lblData0 = new JLabel("Data:");
		lblData0.setFont(new Font("Arial", Font.BOLD, 12));
		lblData0.setBounds(528, 11, 38, 14);
		contentPane.add(lblData0);

		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(569, 11, 71, 15);
		contentPane.add(lblData);

		lblHora = new JLabel("");
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setBounds(569, 25, 71, 15);
		contentPane.add(lblHora);

		lblHora0 = new JLabel("Hora:");
		lblHora0.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora0.setBounds(528, 25, 38, 14);
		contentPane.add(lblHora0);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=========================================
				setVisible(false);
				//=========================================
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setIcon(new ImageIcon(TelaListarProntuario.class.getResource("/imgs/iconeSair1.png")));
		btnNewButton.setBounds(10, 11, 38, 38);
		contentPane.add(btnNewButton);
		
		lblProntuariosRegistrados = new JLabel("PRONTUARIOS REGISTRADOS");
		lblProntuariosRegistrados.setFont(new Font("Arial", Font.BOLD, 15));
		lblProntuariosRegistrados.setBounds(216, 71, 225, 18);
		contentPane.add(lblProntuariosRegistrados);
		
		separator = new JSeparator();
		separator.setBounds(10, 78, 189, 8);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(451, 78, 189, 8);
		contentPane.add(separator_1);
	}
}
