package com.pedromarininave.gui.produto;

import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.services.ProdutoService;
import com.pedromarininave.testepratico.models.Produto;
import com.pedromarininave.utils.ObjectFieldsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.HashMap;

public class CadProdutoMenu {

    @Autowired
    private final ProdutoService produtoService;

    private final ListProdutosMenu listProdutosMenu;
    private final GuiBuilder guiBuilder = new GuiBuilder(GuiConstants.ADICIONAR_PRODUTO_MENU_NAME);
    private JFrame frame;

    public CadProdutoMenu(ProdutoService produtoService, ListProdutosMenu listProdutosMenu) {
        this.produtoService = produtoService;
        this.listProdutosMenu = listProdutosMenu;
        createGui();
    }

    private void createGui() {
        guiBuilder.addTextBox("Código:", Produto.Fields.codigo);
        guiBuilder.addTextBox("Descrição:", Produto.Fields.descricao);
        guiBuilder.addNumericTextBox("Preço (R$):", Produto.Fields.preco);
        guiBuilder.addButton(GuiConstants.SAVE_BUTTON_NAME, this::salvarProduto, false);
        guiBuilder.addButton(GuiConstants.CANCEL_BUTTON_NAME, listProdutosMenu::openGui, true);

        frame = guiBuilder.build();
        frame.open();
    }

    private void salvarProduto() {
        try {
            HashMap<String, JTextField> fields = guiBuilder.getTextFields();

            Produto produto = new Produto();
            ObjectFieldsUtils.fillFieldsWithValue(produto, fields);

            Produto savedObject = produtoService.save(produto).get();
            if (savedObject == null) {
                frame.showAlert("Ocorreu um erro ao cadastrar o produto.", "Erro");
                return;
            }

            frame.showAlert("Cadastrado com sucesso.", "Sucesso");
            frame.close();

            listProdutosMenu.addProduto(savedObject);
            listProdutosMenu.openGui();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void openGui() {
        guiBuilder.build().open();
    }
}