/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controlador.UsuarioController;
import modelo.UsuarioDB;
import vista.InicioSesion;

/**
 *
 * @author panch
 */
public class Main {
    public static void main(String[] args) {
        InicioSesion vistaLogin = new InicioSesion();
        UsuarioDB usuarioDB = new UsuarioDB();
        
        UsuarioController controlador = new UsuarioController(vistaLogin, usuarioDB);
        
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}
