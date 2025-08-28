package tienda;

import javax.swing.*;
import java.awt.*;

public class DialogoConsultar extends JDialog {

    private JComboBox<String> cboModelos;
    private JTextField txtPrecio, txtAncho, txtAlto, txtFondo;
    private JButton btnCerrar;

    public DialogoConsultar(Frame owner) {
        super(owner, "Consultar Tablet", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(owner);

        // --- Panel principal ---
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Labels
        JLabel lblModelo = new JLabel("Modelo:");
        JLabel lblPrecio = new JLabel("Precio (S/.):");
        JLabel lblAncho = new JLabel("Ancho (cm):");
        JLabel lblAlto = new JLabel("Alto (cm):");
        JLabel lblFondo = new JLabel("Fondo (cm):");

        // Controles
        cboModelos = new JComboBox<>();
        txtPrecio = new JTextField(10); txtPrecio.setEditable(false);
        txtAncho  = new JTextField(10); txtAncho.setEditable(false);
        txtAlto   = new JTextField(10); txtAlto.setEditable(false);
        txtFondo  = new JTextField(10); txtFondo.setEditable(false);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        // Layout horizontal
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblModelo)
                    .addComponent(cboModelos))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblAncho)
                    .addComponent(txtAncho))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblAlto)
                    .addComponent(txtAlto))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFondo)
                    .addComponent(txtFondo))
                .addComponent(btnCerrar, GroupLayout.Alignment.CENTER)
        );

        // Layout vertical
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo).addComponent(cboModelos))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio).addComponent(txtPrecio))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAncho).addComponent(txtAncho))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlto).addComponent(txtAlto))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFondo).addComponent(txtFondo))
                .addGap(15)
                .addComponent(btnCerrar)
        );

        // --- Inicialización ---
        llenarCombo();
        cboModelos.setSelectedIndex(0);
        mostrarDatos(0);

        cboModelos.addActionListener(e -> mostrarDatos(cboModelos.getSelectedIndex()));
    }

    // Métodos auxiliares
    private void llenarCombo() {
        cboModelos.addItem(Datos.modelo0);
        cboModelos.addItem(Datos.modelo1);
        cboModelos.addItem(Datos.modelo2);
        cboModelos.addItem(Datos.modelo3);
        cboModelos.addItem(Datos.modelo4);
    }

    private void mostrarDatos(int idx) {
        txtPrecio.setText(String.format("%.2f", Datos.precio(idx)));
        txtAncho.setText(String.format("%.1f", Datos.ancho(idx)));
        txtAlto.setText(String.format("%.1f", Datos.alto(idx)));
        txtFondo.setText(String.format("%.1f", Datos.fondo(idx)));
    }
}
