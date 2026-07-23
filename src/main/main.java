/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author panch
 */
public class main {
     public static void main(String[] args){
        FrmLoginUsuario vistaUser = new FrmLoginUsuario();
        FrmLoginAdministrador vistaAdmin = new FrmLoginAdministrador();
        UsuarioDB usuarioDB = new UsuarioDB();
        
        UsuarioController controlador = new UsuarioController(vistaUser, vistaAdmin, usuarioDB);
        
        vistaUser.setLocationRelativeTo(null);
        vistaUser.setVisible(true);
    }
}
