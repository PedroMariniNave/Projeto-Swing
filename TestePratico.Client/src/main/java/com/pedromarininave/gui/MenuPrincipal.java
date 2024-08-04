package com.pedromarininave.gui;

import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.gui.pedido.ListPedidosMenu;
import com.pedromarininave.services.ClienteService;
import com.pedromarininave.services.ProdutoService;
import com.pedromarininave.gui.cliente.ListClientesMenu;
import com.pedromarininave.gui.produto.ListProdutosMenu;
import com.pedromarininave.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuPrincipal {
    @Autowired
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;
    private final GuiBuilder guiBuilder = new GuiBuilder(GuiConstants.MAIN_MENU_NAME);

    public MenuPrincipal(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;

        createGui();
        openGui();
    }

    private void createGui() {
        guiBuilder.addButton(GuiConstants.CLIENTES_BUTTON_NAME, () -> {
            ListClientesMenu listClientesMenu = new ListClientesMenu(clienteService, this);

            listClientesMenu.openGui();
        }, true);

        guiBuilder.addButton(GuiConstants.PRODUTOS_BUTTON_NAME, () -> {
            ListProdutosMenu listProdutosMenu = new ListProdutosMenu(produtoService, this);

            listProdutosMenu.openGui();
        }, true);

        guiBuilder.addButton(GuiConstants.PEDIDOS_BUTTON_NAME, () -> {
            ListPedidosMenu listPedidosMenu = new ListPedidosMenu(pedidoService, this);

            listPedidosMenu.openGui();
        }, true);
    }

    public void openGui() {
        guiBuilder.build().open();
    }
}