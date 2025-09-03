/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_oodj_group33;
import javax.swing.table.DefaultTableModel;

public abstract class IMTableLoader {
    protected DefaultTableModel model;

    public IMTableLoader(DefaultTableModel model) {
        this.model = model;
    }
}