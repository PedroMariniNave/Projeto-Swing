package com.pedromarininave.gui.pedido;

import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.services.PedidoService;
import com.pedromarininave.testepratico.models.Pedido;
import com.pedromarininave.utils.ObjectFieldsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.HashMap;

public class CadPedidoMenu {

    @Autowired
    private final PedidoService pedidoService;

    private final ListPedidosMenu listPedidosMenu;
    private final GuiBuilder guiBuilder = new GuiBuilder(GuiConstants.ADICIONAR_PEDIDOS_MENU_NAME);
    private JFrame frame;

    public CadPedidoMenu(PedidoService pedidoService, ListPedidosMenu listPedidosMenu) {
        this.pedidoService = pedidoService;
        this.listPedidosMenu = listPedidosMenu;
        createGui();
    }

    private void createGui() {
        guiBuilder.addTextBox("Cliente:", Pedido.Fields.cliente);
        guiBuilder.addTextBox("Produtos:", Pedido.Fields.produtosVendidos);
        guiBuilder.addButton(GuiConstants.SAVE_BUTTON_NAME, this::salvarProduto, false);
        guiBuilder.addButton(GuiConstants.CANCEL_BUTTON_NAME, listPedidosMenu::openGui, true);

        frame = guiBuilder.build();
        frame.open();
    }

    private void salvarProduto() {
        try {
            HashMap<String, JTextField> fields = guiBuilder.getTextFields();

            Pedido pedido = new Pedido();
            ObjectFieldsUtils.fillFieldsWithValue(pedido, fields);

            Pedido savedObject = pedidoService.save(pedido).get();
            if (savedObject == null) {
                frame.showAlert("Ocorreu um erro ao cadastrar o pedido.", "Erro");
                return;
            }

            frame.showAlert("Cadastrado com sucesso.", "Sucesso");
            frame.close();

            listPedidosMenu.addPedido(savedObject);
            listPedidosMenu.openGui();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void openGui() {
        guiBuilder.build().open();
    }
}