package com.pedromarininave.gui.pedido;

import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.gui.MenuPrincipal;
import com.pedromarininave.services.PedidoService;
import com.pedromarininave.swing.tables.PedidoTableModel;
import com.pedromarininave.testepratico.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;

public class ListPedidosMenu {

    @Autowired
    private final PedidoService pedidoService;

    private final MenuPrincipal menuPrincipal;
    private final List<Pedido> pedidos;

    private GuiBuilder guiBuilder;

    public ListPedidosMenu(PedidoService pedidoService, MenuPrincipal menuPrincipal) {
        this.pedidoService = pedidoService;
        this.menuPrincipal = menuPrincipal;
        this.pedidos = carregarPedidos();
        createGui();
    }

    private void createGui() {
        guiBuilder = new GuiBuilder(GuiConstants.PEDIDOS_MENU_NAME);

        PedidoTableModel pedidoTableModel = new PedidoTableModel(pedidos);

        JTable table = new JTable(pedidoTableModel);

        guiBuilder.addButton(GuiConstants.ADD_BUTTON_NAME, () -> {
            CadPedidoMenu cadPedidoMenu = new CadPedidoMenu(pedidoService, this);

            cadPedidoMenu.openGui();
        }, true);

        guiBuilder.addTable(table);

        guiBuilder.addButton(GuiConstants.BACK_BUTTON_NAME, menuPrincipal::openGui, true);
    }

    private List<Pedido> carregarPedidos() {
        try {
            return pedidoService.findAll().get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        createGui();
    }

    public void openGui() {
        guiBuilder.build().open();
    }
}