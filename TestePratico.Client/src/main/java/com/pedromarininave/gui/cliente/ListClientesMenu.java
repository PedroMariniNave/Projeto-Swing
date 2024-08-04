package com.pedromarininave.gui.cliente;

import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.services.ClienteService;
import com.pedromarininave.swing.tables.ClienteTableModel;
import com.pedromarininave.testepratico.models.Cliente;
import com.pedromarininave.gui.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.List;

public class ListClientesMenu {

    @Autowired
    private final ClienteService clienteService;

    private final MenuPrincipal menuPrincipal;
    private final List<Cliente> clientes;

    private GuiBuilder guiBuilder;

    public ListClientesMenu(ClienteService clienteService, MenuPrincipal menuPrincipal) {
        this.clienteService = clienteService;
        this.menuPrincipal = menuPrincipal;
        this.clientes = carregarClientes();
        createGui();
    }

    private void createGui() {
        guiBuilder = new GuiBuilder(GuiConstants.CLIENTES_MENU_NAME);

        ClienteTableModel clienteTableModel = new ClienteTableModel(clientes);

        JTable table = new JTable(clienteTableModel);
        table.setFocusable(false);

        guiBuilder.addButton(GuiConstants.ADD_BUTTON_NAME, () -> {
            CadClienteMenu cadClienteMenu = new CadClienteMenu(clienteService, this);

            cadClienteMenu.openGui();
        }, true);

        guiBuilder.addTable(table);

        guiBuilder.addButton(GuiConstants.BACK_BUTTON_NAME, menuPrincipal::openGui, true);
    }

    private List<Cliente> carregarClientes() {
        try {
            return clienteService.findAll().get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
        createGui();
    }

    public void openGui() {
        guiBuilder.build().open();
    }
}