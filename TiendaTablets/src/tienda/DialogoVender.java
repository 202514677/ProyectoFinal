package tienda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class DialogoVender extends JDialog {

    private final JComboBox<String> cboModelos;
    private final JTextField txtPrecio;
    private final JTextField txtCantidad;
    private final JTextArea txtBoleta;
    private final JButton btnGenerar, btnRegistrar, btnCerrar;

    private final DecimalFormat df = new DecimalFormat("0.00");

    public DialogoVender(Frame owner) {
        super(owner, "Vender", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(560, 420);
        setLocationRelativeTo(owner);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Componentes
        JLabel lblModelo = new JLabel("Modelo:");
        JLabel lblPrecio = new JLabel("Precio unitario (S/.):");
        JLabel lblCantidad = new JLabel("Cantidad:");

        cboModelos = new JComboBox<>();
        txtPrecio = new JTextField(10);
        txtPrecio.setEditable(false);
        txtCantidad = new JTextField("1", 8); // por defecto 1

        btnGenerar = new JButton("Generar Boleta");
        btnRegistrar = new JButton("Registrar Venta");
        btnCerrar = new JButton("Cerrar");

        txtBoleta = new JTextArea(12, 40);
        txtBoleta.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtBoleta);

        // Eventos
        btnCerrar.addActionListener(e -> dispose());
        btnGenerar.addActionListener(this::onGenerarBoleta);
        btnRegistrar.addActionListener(this::onRegistrarVenta);

        cboModelos.addActionListener(e -> mostrarPrecio(cboModelos.getSelectedIndex()));

        // Layout horizontal
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblModelo)
                        .addComponent(lblPrecio)
                        .addComponent(lblCantidad))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cboModelos)
                        .addComponent(txtPrecio)
                        .addComponent(txtCantidad)))
                .addComponent(btnGenerar, GroupLayout.Alignment.CENTER)
                .addComponent(scroll)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnRegistrar)
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
                    .addComponent(lblCantidad).addComponent(txtCantidad))
                .addGap(8)
                .addComponent(btnGenerar)
                .addGap(6)
                .addComponent(scroll)
                .addGap(6)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnCerrar))
        );

        // Inicialización datos UI
        llenarCombo();
        if (cboModelos.getItemCount() > 0) {
            cboModelos.setSelectedIndex(0);
            mostrarPrecio(0);
        }
    }
    
    private void llenarCombo() {
        cboModelos.addItem(Datos.modelo0);
        cboModelos.addItem(Datos.modelo1);
        cboModelos.addItem(Datos.modelo2);
        cboModelos.addItem(Datos.modelo3);
        cboModelos.addItem(Datos.modelo4);
    }

    // --- Mostrar precio unitario cuando se selecciona un modelo ---
    private void mostrarPrecio(int idx) {
        double precio = Datos.precio(idx);
        txtPrecio.setText(df.format(precio));
    }

    // --- Acción: Generar la boleta (calculos y mostrar en txtBoleta) ---
    private void onGenerarBoleta(ActionEvent e) {
        if (!validarCantidad()) return;

        int idx = cboModelos.getSelectedIndex();
        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
        double precioUnit = Datos.precio(idx);

        double importeCompra = precioUnit * cantidad;
        double porcentaje = porcentajePorCantidad(cantidad);
        double importeDescuento = importeCompra * (porcentaje / 100.0);
        double importePagar = importeCompra - importeDescuento;
        String obsequio = obsequioPorCantidad(cantidad);

        StringBuilder sb = new StringBuilder();
        sb.append("BOLETA DE VENTA").append(System.lineSeparator());
        sb.append("================").append(System.lineSeparator());
        sb.append(String.format("Modelo: %s%n", Datos.modelo(idx)));
        sb.append(String.format("Precio unitario: S/ %s%n", df.format(precioUnit)));
        sb.append(String.format("Cantidad: %d%n", cantidad));
        sb.append(String.format("Importe compra: S/ %s%n", df.format(importeCompra)));
        sb.append(String.format("Porcentaje descuento aplicado: %s%%%n", df.format(porcentaje)));
        sb.append(String.format("Importe descuento: S/ %s%n", df.format(importeDescuento)));
        sb.append(String.format("Importe a pagar: S/ %s%n", df.format(importePagar)));
        sb.append(String.format("Obsequio: %s%n", obsequio));
        sb.append(System.lineSeparator());
        sb.append("¡Gracias por su compra!");

        txtBoleta.setText(sb.toString());
    }

    // --- Acción: Registrar la venta (actualiza acumuladores y muestra alerta cada 5 ventas) ---
    private void onRegistrarVenta(ActionEvent e) {
        // Primero validamos cantidad; si no hay boleta generada la generamos automáticamente.
        if (!validarCantidad()) return;
        int idx = cboModelos.getSelectedIndex();
        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
        double precioUnit = Datos.precio(idx);

        double importeCompra = precioUnit * cantidad;
        double porcentaje = porcentajePorCantidad(cantidad);
        double importeDescuento = importeCompra * (porcentaje / 100.0);
        double importePagar = importeCompra - importeDescuento;
        String obsequio = obsequioPorCantidad(cantidad);

        // Actualizar acumuladores globales
        Datos.contadorVentas = Datos.contadorVentas + 1;
        Datos.importeTotalAcumulado = Datos.importeTotalAcumulado + importePagar;

        // Mostrar confirmación y la boleta final
        JOptionPane.showMessageDialog(this,
            "Venta registrada correctamente.\n" +
                "N° Venta: " + Datos.contadorVentas +
                "\nImporte a pagar: S/ " + df.format(importePagar),
            "Venta", JOptionPane.INFORMATION_MESSAGE);

        // Actualizar la boleta en pantalla
        StringBuilder sb = new StringBuilder();
        sb.append("BOLETA REGISTRADA").append(System.lineSeparator());
        sb.append("=================").append(System.lineSeparator());
        sb.append(String.format("N° Venta: %d%n", Datos.contadorVentas));
        sb.append(String.format("Modelo: %s%n", Datos.modelo(idx)));
        sb.append(String.format("Precio unitario: S/ %s%n", df.format(precioUnit)));
        sb.append(String.format("Cantidad: %d%n", cantidad));
        sb.append(String.format("Importe compra: S/ %s%n", df.format(importeCompra)));
        sb.append(String.format("Porcentaje descuento: %s%%%n", df.format(porcentaje)));
        sb.append(String.format("Importe descuento: S/ %s%n", df.format(importeDescuento)));
        sb.append(String.format("Importe a pagar: S/ %s%n", df.format(importePagar)));
        sb.append(String.format("Obsequio: %s%n", obsequio));
        txtBoleta.setText(sb.toString());

        // Verificar alerta cada 5 ventas
        mostrarAlertaCada5();
    }

    // --- Valida que la cantidad sea un entero > 0 ---
    private boolean validarCantidad() {
        String txt = txtCantidad.getText().trim();
        if (txt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int cant = Integer.parseInt(txt);
            if (cant <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // --- Determina el porcentaje de descuento según la cantidad 
    private double porcentajePorCantidad(int cantidad) {
        if (cantidad >= 1 && cantidad <= 5) return Datos.porcentaje1;
        if (cantidad >= 6 && cantidad <= 10) return Datos.porcentaje2;
        if (cantidad >= 11 && cantidad <= 15) return Datos.porcentaje3;
        return Datos.porcentaje4; // más de 15
    }

    // --- Determina el obsequio según la cantidad 
    private String obsequioPorCantidad(int cantidad) {
        if (cantidad == 1) return Datos.obsequio1;
        if (cantidad >= 2 && cantidad <= 5) return Datos.obsequio2;
        return Datos.obsequio3; // más de 5
    }

    // --- Muestra la alerta cada 5 ventas  ---
    private void mostrarAlertaCada5() {
        if (Datos.contadorVentas > 0 && Datos.contadorVentas % 5 == 0) {
            double acumulado = Datos.importeTotalAcumulado;
            double cuota = Datos.cuotaDiaria;
            double porcentaje = (cuota == 0.0) ? 0.0 : (acumulado / cuota) * 100.0;
            String msg = "ALERTA - Resumen de ventas" + System.lineSeparator() +
                "Nro. de venta actual: " + Datos.contadorVentas + System.lineSeparator() +
                "Importe total acumulado: S/ " + df.format(acumulado) + System.lineSeparator() +
                "% de la cuota diaria: " + df.format(porcentaje) + "%";
            JOptionPane.showMessageDialog(this, msg, "Alerta de ventas", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
