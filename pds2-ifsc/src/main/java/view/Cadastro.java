package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.PessoaDAO;
import model.Pessoa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Cadastro extends JFrame {

    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtIdade;
    private JLabel lblIdade;
    private JTextField txtId;
    private DefaultTableModel modelo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cadastro frame = new Cadastro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Cadastro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(123, 50, 46, 14);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(179, 47, 86, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(123, 19, 46, 14);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(179, 16, 86, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);

        lblIdade = new JLabel("Idade");
        lblIdade.setBounds(123, 81, 46, 14);
        contentPane.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setColumns(10);
        txtIdade.setBounds(179, 78, 86, 20);
        contentPane.add(txtIdade);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String idade = txtIdade.getText();
                String id = txtId.getText();

                Pessoa p = new Pessoa();
                p.setPrimeiro_nome(nome);
                p.setId(Integer.valueOf(id));
                p.setIdade(Integer.valueOf(idade));

                PessoaDAO dao = new PessoaDAO();
                dao.inserir(p);

                txtNome.setText(null);
                txtId.setText(null);
                txtIdade.setText(null);

                refreshTable();
            }
        });
        btnCadastrar.setBounds(179, 110, 86, 23);
        contentPane.add(btnCadastrar);

        modelo = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Idade" });
        JTable tabela = new JTable(modelo);

        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setBounds(41, 160, 349, 190);
        contentPane.add(barraRolagem);

        setSize(450, 400);
        setVisible(true);
        refreshTable();
    }

    private void refreshTable() {
        PessoaDAO dao = new PessoaDAO();
        ArrayList<Pessoa> pessoas = dao.listar();

        modelo.setRowCount(0);

        for (Pessoa pessoa : pessoas) {
            modelo.addRow(new Object[] { pessoa.getId(), pessoa.getPrimeiro_nome(), pessoa.getIdade() });
        }
    }
}