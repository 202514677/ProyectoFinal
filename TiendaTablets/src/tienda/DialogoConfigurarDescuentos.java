package tienda;

import javax.swing.*;
import java.awt.*;


public class DialogoConfigurarDescuentos extends JDialog {

    private JTextField txtPorc1, txtPorc2, txtPorc3, txtPorc4;
    private JButton btnAceptar, btnCancelar;

    public DialogoConfigurarDescuentos(Frame owner) {
        super(owner, "Configurar Descuentos", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(owner);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lbl1 = new JLabel("1 a 5 unidades (%):");
        JLabel lbl2 = new JLabel("6 a 10 unidades (%):");
        JLabel lbl3 = new JLabel("11 a 15 unidades (%):");
        JLabel lbl4 = new JLabel("Más de 15 unidades (%):");

        txtPorc1 = new JTextField(8);
        txtPorc2 = new JTextField(8);
        txtPorc3 = new JTextField(8);
        txtPorc4 = new JTextField(8);

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Eventos
        btnAceptar.addActionListener(e -> guardarCambios());
        btnCancelar.addActionListener(e -> dispose());

        // Layout horizontal
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl1)
                        .addComponent(lbl2)
                        .addComponent(lbl3)
                        .addComponent(lbl4))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtPorc1)
                        .addComponent(txtPorc2)
                        .addComponent(txtPorc3)
                        .addComponent(txtPorc4)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
        );

        // Layout vertical
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1).addComponent(txtPorc1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2).addComponent(txtPorc2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3).addComponent(txtPorc3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4).addComponent(txtPorc4))
                .addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
        );

        // Cargar valores actuales
        cargarValores();
    }

    // --- Métodos auxiliares ---
    private void cargarValores() {
        txtPorc1.setText(String.valueOf(Datos.porcentaje1));
        txtPorc2.setText(String.valueOf(Datos.porcentaje2));
        txtPorc3.setText(String.valueOf(Datos.porcentaje3));
        txtPorc4.setText(String.valueOf(Datos.porcentaje4));
    }

    private void guardarCambios() {
        try {
            double p1 = Double.parseDouble(txtPorc1.getText().trim());
            double p2 = Double.parseDouble(txtPorc2.getText().trim());
            double p3 = Double.parseDouble(txtPorc3.getText().trim());
            double p4 = Double.parseDouble(txtPorc4.getText().trim());

            if (p1 <= 0 || p2 <= 0 || p3 <= 0 || p4 <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Todos los porcentajes deben ser mayores que 0.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Datos.porcentaje1 = p1;
            Datos.porcentaje2 = p2;
            Datos.porcentaje3 = p3;
            Datos.porcentaje4 = p4;

            JOptionPane.showMessageDialog(this, "Porcentajes actualizados correctamente.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese valores numéricos válidos para los porcentajes.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
