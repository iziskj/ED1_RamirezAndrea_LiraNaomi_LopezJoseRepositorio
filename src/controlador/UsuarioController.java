/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.UsuarioDB;
import modelo.MascotaDB;
import vista.InicioSesion;
import vista.MenuPrincipal;

/**
 *
 * @author panch
 */
public class UsuarioController implements ActionListener {
    private InicioSesion vistaLogin;
    private UsuarioDB usuarioDB;

    public UsuarioController(InicioSesion vistaLogin, UsuarioDB usuarioDB) {
        this.vistaLogin = vistaLogin;
        this.usuarioDB = usuarioDB;

        this.vistaLogin.btnIniciarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.btnIniciarSesion) {
            login();
        }
    }

    private void login() {
        String username = vistaLogin.txtUsuario.getText().trim();
        String password = new String(vistaLogin.txtContrasena.getPassword()).trim();

        Usuario u = usuarioDB.verificarLogin(username, password);
        if (u != null) {
            JOptionPane.showMessageDialog(vistaLogin, "Bienvenido al Sistema AdoptCare");
            
            MenuPrincipal menu = new MenuPrincipal();
            MascotaDB mascotaDB = new MascotaDB();
            MascotaController mascotaCtrl = new MascotaController(menu, mascotaDB, usuarioDB);
            
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            vistaLogin.dispose();
        } else {
            JOptionPane.showMessageDialog(vistaLogin, "Credenciales incorrectas");
        }
    }
}