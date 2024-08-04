package com.pedromarininave.swing.tables;

import com.pedromarininave.testepratico.models.Produto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProdutoTableModel extends AbstractTableModel {
    private final List<Produto> produtos;
    private final String[] columnNames = { "Código", "Descrição", "Preço (R$)" };

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> produto.getCodigo();
            case 1 -> produto.getDescricao();
            case 2 -> produto.getPreco();
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