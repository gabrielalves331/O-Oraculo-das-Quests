/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Missao;

/**
 *
 * @author gabri
 */
public interface MissaoDAO {

    void inserir(Missao m);
    void atualizar(Missao m);
    void deletar(int id);
    Missao buscarPorId(int id);
    List<Missao> listar();
}
