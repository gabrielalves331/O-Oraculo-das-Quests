/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import modelo.Modelo;

/**
 *
 * @author gabri
 */
public interface ModeloDAO {
    public int inserir(Modelo modelo);
    public int editar(Modelo modelo);
    public int apagar(int codigo) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    public List<Modelo> listar();
    public Modelo listar(int codigo);
}
