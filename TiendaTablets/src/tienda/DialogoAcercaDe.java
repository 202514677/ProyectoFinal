package tienda;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo "Acerca de" con etiquetas centradas.
 */
public class DialogoAcercaDe extends JDialog {

    public DialogoAcercaDe(Frame owner) {
        super(owner, "Acerca de", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(owner);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título
        JLabel lblTitulo = new JLabel("TIENDA DE TABLETS 1.0");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createVerticalStrut(10)); 

        JLabel lblCurso = new JLabel("Curso: Introducción a la Algoritmia ");
        lblCurso.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblCurso.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblCurso);
     
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2)); 

        panel.add(Box.createVerticalStrut(10));
        panel.add(separador);
        panel.add(Box.createVerticalStrut(10));


        JLabel lblTitleAutor = new JLabel("Autores");
        lblTitleAutor.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblTitleAutor.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitleAutor);
        
        JLabel lblAutor1 = new JLabel("Carlos Manuel Salazar Trejo");
        lblAutor1.setFont(new Font("Arial Black", Font.PLAIN, 16));
        lblAutor1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblAutor1);

        JLabel lblAutor2 = new JLabel("Andrews S. Agramonte Matos");
        lblAutor2.setFont(new Font("Arial Black", Font.PLAIN, 16));
        lblAutor2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblAutor2);

        JLabel lblAutor3 = new JLabel("Percy Martin Aguayo Soto");
        lblAutor3.setFont(new Font("Arial Black", Font.PLAIN, 16));
        lblAutor3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblAutor3);

        panel.add(Box.createVerticalStrut(15));

        JLabel lblFecha = new JLabel("Fecha: 2025");
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblFecha);

        panel.add(Box.createVerticalStrut(20));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);

        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
