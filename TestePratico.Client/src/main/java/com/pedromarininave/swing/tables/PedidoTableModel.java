package com.pedromarininave.swing.tables;

import com.pedromarininave.testepratico.models.Pedido;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PedidoTableModel extends AbstractTableModel {
    private final List<Pedido> pedidos;
    private final String[] columnNames = { "Cliente" };

    public PedidoTableModel(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> pedido.getCliente().getNome();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }
}