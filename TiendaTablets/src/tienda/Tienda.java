package tienda;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class Tienda extends JFrame {
    private JMenuItem miSalir;
    private JMenuItem miConsultar;
    private JMenuItem miModificar;
    private JMenuItem miListar;
    private JMenuItem miVender;
    private JMenuItem miConfDesc;
    private JMenuItem miConfObs;
    private JMenuItem miAcerca;

    public Tienda() {
        setTitle("Tienda de Tablets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mArchivo = new JMenu("Archivo");
        menuBar.add(mArchivo);
        miSalir = new JMenuItem("Salir");
        miSalir.addActionListener(e -> System.exit(0));
        mArchivo.add(miSalir);

        JMenu mMant = new JMenu("Mantenimiento");
        menuBar.add(mMant);
        miConsultar = new JMenuItem("Consultar tablet");
        miConsultar.addActionListener(e -> new DialogoConsultar(this).setVisible(true));
        mMant.add(miConsultar);
        miModificar = new JMenuItem("Modificar tablet");
        miModificar.addActionListener(e -> new DialogoModificar(this).setVisible(true));
        mMant.add(miModificar);
        miListar = new JMenuItem("Listar tablets");
        miListar.addActionListener(e -> new DialogoListar(this).setVisible(true));
        mMant.add(miListar);

        JMenu mVentas = new JMenu("Ventas");
        menuBar.add(mVentas);
        miVender = new JMenuItem("Vender");
        miVender.addActionListener(e -> new DialogoVender(this).setVisible(true));
        mVentas.add(miVender);

        JMenu mConf = new JMenu("Configuración");
        menuBar.add(mConf);
        miConfDesc = new JMenuItem("Configurar descuentos");
        miConfDesc.addActionListener(e -> new DialogoConfigurarDescuentos(this).setVisible(true));
        mConf.add(miConfDesc);
        miConfObs = new JMenuItem("Configurar obsequios");
        miConfObs.addActionListener(e -> new DialogoConfigurarObsequios(this).setVisible(true));
        mConf.add(miConfObs);

        JMenu mAyuda = new JMenu("Ayuda");
        menuBar.add(mAyuda);
        miAcerca = new JMenuItem("Acerca de Tienda");
        miAcerca.addActionListener(e -> new DialogoAcercaDe(this).setVisible(true));
        mAyuda.add(miAcerca);

        // Panel central (placeholder)
        JLabel lbl = new JLabel("Bienvenido a la Tienda de Tablets", SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        getContentPane().add(lbl, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {}
            new Tienda().setVisible(true);
        });
    }
}
