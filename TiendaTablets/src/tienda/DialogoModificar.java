package tienda;

import javax.swing.*;
import java.awt.*;

public class DialogoModificar extends JDialog {

    private JComboBox<String> cboModelos;
    private JTextField txtPrecio, txtAncho, txtAlto, txtFondo;
    private JButton btnGuardar, btnCerrar;

    public DialogoModificar(Frame owner) {
        super(owner, "Modificar Tablet", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(owner);

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
        txtPrecio = new JTextField(10);
        txtAncho  = new JTextField(10);
        txtAlto   = new JTextField(10);
        txtFondo  = new JTextField(10);

        btnGuardar = new JButton("Guardar");
        btnCerrar  = new JButton("Cerrar");

        // Eventos
        btnGuardar.addActionListener(e -> guardarCambios(cboModelos.getSelectedIndex()));
        btnCerrar.addActionListener(e -> dispose());

        cboModelos.addActionListener(e -> mostrarDatos(cboModelos.getSelectedIndex()));

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
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnGuardar)
                    .addComponent(btnCerrar))
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCerrar))
        );

        // --- Inicialización ---
        llenarCombo();
        cboModelos.setSelectedIndex(0);
        mostrarDatos(0);
    }

    // --- Métodos auxiliares ---
    private void llenarCombo() {
        cboModelos.addItem(Datos.modelo0);
        cboModelos.addItem(Datos.modelo1);
        cboModelos.addItem(Datos.modelo2);
        cboModelos.addItem(Datos.modelo3);
        cboModelos.addItem(Datos.modelo4);
    }

    private void mostrarDatos(int idx) {
        txtPrecio.setText(String.valueOf(Datos.precio(idx)));
        txtAncho.setText(String.valueOf(Datos.ancho(idx)));
        txtAlto.setText(String.valueOf(Datos.alto(idx)));
        txtFondo.setText(String.valueOf(Datos.fondo(idx)));
    }

    private void guardarCambios(int idx) {
        try {
            double precio = Double.parseDouble(txtPrecio.getText());
            double ancho  = Double.parseDouble(txtAncho.getText());
            double alto   = Double.parseDouble(txtAlto.getText());
            double fondo  = Double.parseDouble(txtFondo.getText());

            if (precio <= 0 || ancho <= 0 || alto <= 0 || fondo <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Los valores deben ser mayores que 0.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (idx) {
                case 0 -> { Datos.precio0 = precio; Datos.ancho0 = ancho; Datos.alto0 = alto; Datos.fondo0 = fondo; }
                case 1 -> { Datos.precio1 = precio; Datos.ancho1 = ancho; Datos.alto1 = alto; Datos.fondo1 = fondo; }
                case 2 -> { Datos.precio2 = precio; Datos.ancho2 = ancho; Datos.alto2 = alto; Datos.fondo2 = fondo; }
                case 3 -> { Datos.precio3 = precio; Datos.ancho3 = ancho; Datos.alto3 = alto; Datos.fondo3 = fondo; }
                case 4 -> { Datos.precio4 = precio; Datos.ancho4 = ancho; Datos.alto4 = alto; Datos.fondo4 = fondo; }
            }

            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Debe ingresar valores numéricos válidos.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
