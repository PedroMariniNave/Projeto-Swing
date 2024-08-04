package com.pedromarininave.gui.produto;

import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.services.ProdutoService;
import com.pedromarininave.swing.tables.ProdutoTableModel;
import com.pedromarininave.testepratico.models.Produto;
import com.pedromarininave.gui.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;

public class ListProdutosMenu {

    @Autowired
    private final ProdutoService produtoService;

    private final MenuPrincipal menuPrincipal;
    private final List<Produto> produtos;

    private GuiBuilder guiBuilder;

    public ListProdutosMenu(ProdutoService produtoService, MenuPrincipal menuPrincipal) {
        this.produtoService = produtoService;
        this.menuPrincipal = menuPrincipal;
        this.produtos = carregarProdutos();
        createGui();
    }

    private void createGui() {
        guiBuilder = new GuiBuilder(GuiConstants.PRODUTOS_MENU_NAME);

        ProdutoTableModel produtoTableModel = new ProdutoTableModel(produtos);

        JTable table = new JTable(produtoTableModel);

        guiBuilder.addButton(GuiConstants.ADD_BUTTON_NAME, () -> {
            CadProdutoMenu cadProdutoMenu = new CadProdutoMenu(produtoService, this);

            cadProdutoMenu.openGui();
        }, true);

        guiBuilder.addTable(table);

        guiBuilder.addButton(GuiConstants.BACK_BUTTON_NAME, menuPrincipal::openGui, true);
    }

    private List<Produto> carregarProdutos() {
        try {
            return produtoService.findAll().get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
        createGui();
    }

    public void openGui() {
        guiBuilder.build().open();
    }
}