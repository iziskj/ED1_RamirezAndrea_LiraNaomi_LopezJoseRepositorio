/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Mascota;
import modelo.MascotaDB;
import modelo.UsuarioDB;
import vista.InicioSesion;
import vista.MenuPrincipal;
import vista.RegistroMascota;
import vista.GestionMascotas;

/**
 *
 * @author panch
 */
public class MascotaController implements ActionListener {

    //Objetos para el modelo y la vista.
    private MenuPrincipal vistaMenu;
    private RegistroMascota vistaRegistro;
    private GestionMascotas vistaGestion;
    private MascotaDB mascotaDB;
    private UsuarioDB usuarioDB;

    //Contructor para inicializar los objetos. Constructor desde MenuPrincipal
    public MascotaController(MenuPrincipal vistaMenu, MascotaDB mascotaDB, UsuarioDB usuarioDB) {
        this.vistaMenu = vistaMenu;
        this.mascotaDB = mascotaDB;
        this.usuarioDB = usuarioDB;

        this.vistaMenu.btnRegistrarMascota.addActionListener(this);
        this.vistaMenu.btnGestionarMascotas.addActionListener(this);
        this.vistaMenu.btnCerrarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Eventos en el MenuPrincipal
        if (vistaMenu != null) {
            if (e.getSource() == vistaMenu.btnRegistrarMascota) {
                abrirRegistro();
            } else if (e.getSource() == vistaMenu.btnGestionarMascotas) {
                abrirGestion();
            } else if (e.getSource() == vistaMenu.btnCerrarSesion) {
                cerrarSesion(vistaMenu);
            }
        }
            
        //Eventos en el RegistroMascota
        if (vistaRegistro != null) {
            if (e.getSource() == vistaRegistro.btnEnviarReporte) { // Botón Guardar
                guardarMascota();
            } else if (e.getSource() == vistaRegistro.btnLimpiar) {
                limpiarRegistro();
            } else if (e.getSource() == vistaRegistro.btnInicio) {
                abrirMenu(vistaRegistro);
            } else if (e.getSource() == vistaRegistro.btnGestion) {
                abrirGestionDesde(vistaRegistro);
            } else if (e.getSource() == vistaRegistro.btnCerrarSesion) {
                cerrarSesion(vistaRegistro);
            }
        }

        //Eventos en el GestionMascotas
        if (vistaGestion != null) {
            if (e.getSource() == vistaGestion.btnActualizar) {
                actualizarMascota();
            } else if (e.getSource() == vistaGestion.btnEliminar) {
                eliminarMascota();
            } else if (e.getSource() == vistaGestion.btnInicio) {
                abrirMenu(vistaGestion);
            } else if (e.getSource() == vistaGestion.btnRegistro) {
                abrirRegistroDesde(vistaGestion);
            } else if (e.getSource() == vistaGestion.btnCerrarSesion) {
                cerrarSesion(vistaGestion);
            }
        }
    }

    //Métodos de navegación
    private void abrirRegistro() {
        vistaRegistro = new RegistroMascota();
        asociarEventosRegistro();
        vistaRegistro.setLocationRelativeTo(null);
        vistaRegistro.setVisible(true);
        vistaMenu.dispose();
        vistaMenu = null;
    }

    private void abrirGestion() {
        vistaGestion = new GestionMascotas();
        asociarEventosGestion();
        mostrarTabla();
        vistaGestion.setLocationRelativeTo(null);
        vistaGestion.setVisible(true);
        vistaMenu.dispose();
        vistaMenu = null;
    }

    private void abrirMenu(javax.swing.JFrame actual) {
        vistaMenu = new MenuPrincipal();
        vistaMenu.btnRegistrarMascota.addActionListener(this);
        vistaMenu.btnGestionarMascotas.addActionListener(this);
        vistaMenu.btnCerrarSesion.addActionListener(this);
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setVisible(true);
        actual.dispose();
        nullificarVistas();
    }

    private void abrirGestionDesde(javax.swing.JFrame actual) {
        vistaGestion = new GestionMascotas();
        asociarEventosGestion();
        mostrarTabla();
        vistaGestion.setLocationRelativeTo(null);
        vistaGestion.setVisible(true);
        actual.dispose();
        vistaRegistro = null;
    }

    private void abrirRegistroDesde(javax.swing.JFrame actual) {
        vistaRegistro = new RegistroMascota();
        asociarEventosRegistro();
        vistaRegistro.setLocationRelativeTo(null);
        vistaRegistro.setVisible(true);
        actual.dispose();
        vistaGestion = null;
    }

    private void asociarEventosRegistro() {
        vistaRegistro.btnEnviarReporte.addActionListener(this);
        vistaRegistro.btnLimpiar.addActionListener(this);
        vistaRegistro.btnInicio.addActionListener(this);
        vistaRegistro.btnRegistro.addActionListener(this);
        vistaRegistro.btnGestion.addActionListener(this);
        vistaRegistro.btnCerrarSesion.addActionListener(this);
    }

    private void asociarEventosGestion() {
        vistaGestion.btnActualizar.addActionListener(this);
        vistaGestion.btnEliminar.addActionListener(this);
        vistaGestion.btnInicio.addActionListener(this);
        vistaGestion.btnRegistro.addActionListener(this);
        vistaGestion.btnGestion.addActionListener(this);
        vistaGestion.btnCerrarSesion.addActionListener(this);
    }

    //Método principal de registrar mascotas
    private void guardarMascota() {
        try {
            String nom = vistaRegistro.txtNombreMascota.getText().trim();
            String raza = vistaRegistro.txtRaza.getText().trim();
            String color = vistaRegistro.txtColor.getText().trim();
            int edad = Integer.parseInt(vistaRegistro.txtEdad.getText().trim());
            String estado = vistaRegistro.cmbEstadoAdopcion.getSelectedItem().toString();

            if (nom.isEmpty() || raza.isEmpty() || color.isEmpty()) {
                JOptionPane.showMessageDialog(vistaRegistro, "Llene todos los datos del formulario");
                return;
            }

            Mascota m = new Mascota(nom, raza, color, edad, estado);
            if (mascotaDB.insertar(m)) {
                JOptionPane.showMessageDialog(vistaRegistro, "Mascota registrada de manera exitosa");
                limpiarRegistro();
            } else {
                JOptionPane.showMessageDialog(vistaRegistro, "Error al registrar la mascota");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaRegistro, "Ingrese un número válido en la edad");
        }
    }

    private void mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Raza");
        modelo.addColumn("Color");
        modelo.addColumn("Edad");
        modelo.addColumn("Estado Adopción");

        List<Mascota> lista = mascotaDB.consultarMascotas();
        for (Mascota m : lista) {
            Object[] fila = { m.getIdMascota(), m.getNombre(), m.getRaza(), m.getColor(), m.getEdad(), m.getEstadoAdopcion() };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        vistaGestion.paneTablaMascotas.setViewportView(tabla);
    }

    private void actualizarMascota() {
        JTable tabla = (JTable) vistaGestion.paneTablaMascotas.getViewport().getView();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaGestion, "Seleccione un registro de la tabla para modificar");
            return;
        }

        try {
            int id = (int) tabla.getValueAt(fila, 0);
            String nom = vistaGestion.txtNombreMascota.getText().trim();
            String raza = vistaGestion.txtRaza.getText().trim();
            String color = vistaGestion.txtColor.getText().trim();
            int edad = Integer.parseInt(vistaGestion.txtEdad.getText().trim());
            String estado = vistaGestion.cmbEstadoAdopcion.getSelectedItem().toString();

            Mascota m = new Mascota(id, nom, raza, color, edad, estado);
            if (mascotaDB.actualizar(m)) {
                JOptionPane.showMessageDialog(vistaGestion, "Mascota actualizada correctamente");
                mostrarTabla();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaGestion, "Error en los datos ingresados");
        }
    }

    private void eliminarMascota() {
        JTable tabla = (JTable) vistaGestion.paneTablaMascotas.getViewport().getView();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaGestion, "Seleccione una mascota para eliminar");
            return;
        }
        int id = (int) tabla.getValueAt(fila, 0);

        if (mascotaDB.eliminar(id)) {
            JOptionPane.showMessageDialog(vistaGestion, "Mascota eliminada del sistema");
            mostrarTabla();
        }
    }

    private void limpiarRegistro() {
        vistaRegistro.txtNombreMascota.setText("");
        vistaRegistro.txtRaza.setText("");
        vistaRegistro.txtColor.setText("");
        vistaRegistro.txtEdad.setText("");
        vistaRegistro.cmbEstadoAdopcion.setSelectedIndex(0);
    }

    private void cerrarSesion(javax.swing.JFrame frame) {
        frame.dispose();
        InicioSesion login = new InicioSesion();
        UsuarioController ctrl = new UsuarioController(login, usuarioDB);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    private void nullificarVistas() {
        vistaRegistro = null;
        vistaGestion = null;
    }
}
