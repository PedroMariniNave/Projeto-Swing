package com.pedromarininave.swing.tables;

import com.pedromarininave.testepratico.models.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {
    private final List<Cliente> clientes;
    private final String[] columnNames = { "Código", "Nome", "Valor Limite de Compra (R$)", "Dia de Fechamento da Fatura" };

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> cliente.getCodigo();
            case 1 -> cliente.getNome();
            case 2 -> cliente.getValorLimiteCompra();
            case 3 -> cliente.getDiaFechamentoFatura();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}