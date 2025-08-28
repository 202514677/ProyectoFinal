package tienda;

import javax.swing.*;
import java.awt.*;

public class DialogoListar extends JDialog {

    private JTextArea txtReporte;
    private JButton btnListar, btnCerrar;

    public DialogoListar(Frame owner) {
        super(owner, "Listado de Tablets", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(owner);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Componentes
        JLabel lblTitulo = new JLabel("REPORTE DE TABLETS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtReporte);

        btnListar = new JButton("Listar");
        btnCerrar = new JButton("Cerrar");

        // Eventos
        btnListar.addActionListener(e -> generarListado());
        btnCerrar.addActionListener(e -> dispose());

        // Layout horizontal
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitulo)
                .addComponent(scroll)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnListar)
                    .addComponent(btnCerrar))
        );

        // Layout vertical
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addComponent(scroll)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListar)
                    .addComponent(btnCerrar))
        );
    }

    // --- Método auxiliar para generar reporte ---
    private void generarListado() {
        String nl = System.lineSeparator();
        StringBuilder sb = new StringBuilder();

        sb.append("LISTADO DE TABLETS").append(nl);
        sb.append("===================").append(nl).append(nl);

        sb.append(linea(Datos.modelo0, Datos.precio0, Datos.ancho0, Datos.alto0, Datos.fondo0));
        sb.append(linea(Datos.modelo1, Datos.precio1, Datos.ancho1, Datos.alto1, Datos.fondo1));
        sb.append(linea(Datos.modelo2, Datos.precio2, Datos.ancho2, Datos.alto2, Datos.fondo2));
        sb.append(linea(Datos.modelo3, Datos.precio3, Datos.ancho3, Datos.alto3, Datos.fondo3));
        sb.append(linea(Datos.modelo4, Datos.precio4, Datos.ancho4, Datos.alto4, Datos.fondo4));

        txtReporte.setText(sb.toString());
    }

    // --- Formateo de cada línea ---
    private String linea(String m, double p, double a, double al, double f) {
        return String.format(
            "Modelo: %s%n  Precio: S/ %.2f%n  Dimensiones: %.1f cm (ancho) x %.1f cm (alto) x %.1f cm (fondo)%n%n",
            m, p, a, al, f
        );
    }
}
