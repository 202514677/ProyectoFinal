package tienda;

import javax.swing.*;
import java.awt.*;

public class DialogoConfigurarObsequios extends JDialog {

    private JTextField txtObs1, txtObs2, txtObs3;
    private JButton btnAceptar, btnCancelar;

    public DialogoConfigurarObsequios(Frame owner) {
        super(owner, "Configurar Obsequios", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(owner);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lbl1 = new JLabel("Por 1 unidad:");
        JLabel lbl2 = new JLabel("De 2 a 5 unidades:");
        JLabel lbl3 = new JLabel("Más de 5 unidades:");

        txtObs1 = new JTextField(20);
        txtObs2 = new JTextField(20);
        txtObs3 = new JTextField(20);

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
                        .addComponent(lbl3))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtObs1)
                        .addComponent(txtObs2)
                        .addComponent(txtObs3)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
        );

        // Layout vertical
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1).addComponent(txtObs1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2).addComponent(txtObs2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3).addComponent(txtObs3))
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
        txtObs1.setText(Datos.obsequio1);
        txtObs2.setText(Datos.obsequio2);
        txtObs3.setText(Datos.obsequio3);
    }

    private void guardarCambios() {
        String o1 = txtObs1.getText().trim();
        String o2 = txtObs2.getText().trim();
        String o3 = txtObs3.getText().trim();

        if (o1.isEmpty() || o2.isEmpty() || o3.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ningún obsequio puede quedar vacío.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Datos.obsequio1 = o1;
        Datos.obsequio2 = o2;
        Datos.obsequio3 = o3;

        JOptionPane.showMessageDialog(this, "Obsequios actualizados correctamente.");
        dispose();
    }
}
