package com.sistemaboletas.persistencia;

import com.sistemaboletas.modelos.Compra;
import com.sistemaboletas.modelos.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CompraBD {

    public void guardarCompra(Compra c) {
        String sql = "INSERT INTO compra(id, evento_id, metodo_pago, total, pagada) VALUES (?,?,?,?,?)";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getId());
            ps.setInt(2, c.getEvento().getId());
            ps.setString(3, c.getMetodoPago().toString());
            ps.setDouble(4, c.getTotal());
            ps.setBoolean(5, c.isPagada());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}