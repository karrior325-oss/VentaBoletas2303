package com.sistemaboletas.persistencia;

import com.sistemaboletas.modelos.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EventoBD {

    public void guardarEvento(Evento e) {
        String sql = "INSERT INTO evento(nombre, fecha, hora, lugar, patrocinador) VALUES (?,?,?,?,?)";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getFecha());
            ps.setString(3, e.getHora());
            ps.setString(4, e.getLugar());
            ps.setString(5, e.getPatrocinador());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}