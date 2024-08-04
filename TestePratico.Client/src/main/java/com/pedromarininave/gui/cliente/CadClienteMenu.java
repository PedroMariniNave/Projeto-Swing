package com.pedromarininave.gui.cliente;

import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.builder.GuiBuilder;
import com.pedromarininave.services.ClienteService;
import com.pedromarininave.testepratico.models.Cliente;
import com.pedromarininave.utils.ObjectFieldsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.HashMap;

public class CadClienteMenu {

    @Autowired
    private final ClienteService clienteService;

    private final ListClientesMenu listClientesMenu;
    private final GuiBuilder guiBuilder = new GuiBuilder(GuiConstants.ADICIONAR_CLIENTE_MENU_NAME);
    private JFrame frame;

    public CadClienteMenu(ClienteService clienteService, ListClientesMenu listClientesMenu) {
        this.clienteService = clienteService;
        this.listClientesMenu = listClientesMenu;
        createGui();
    }

    private void createGui() {
        guiBuilder.addTextBox("Código:", Cliente.Fields.codigo);
        guiBuilder.addTextBox("Nome:", Cliente.Fields.nome);
        guiBuilder.addNumericTextBox("Valor Limite de Compra (R$):", Cliente.Fields.valorLimiteCompra);
        guiBuilder.addTextBox("Dia de Fechamento da Fatura:", Cliente.Fields.diaFechamentoFatura);
        guiBuilder.addButton(GuiConstants.SAVE_BUTTON_NAME, this::salvarCliente, false);
        guiBuilder.addButton(GuiConstants.CANCEL_BUTTON_NAME, listClientesMenu::openGui, true);

        frame = guiBuilder.build();
        frame.open();
    }

    private void salvarCliente() {
        salvarCliente(true);
    }

    public Cliente salvarCliente(boolean showAlert) {
        try {
            HashMap<String, JTextField> fields = guiBuilder.getTextFields();

            Cliente cliente = new Cliente();
            ObjectFieldsUtils.fillFieldsWithValue(cliente, fields);

            Cliente savedObject = clienteService.save(cliente).get();
            if (savedObject == null) {
                if (showAlert)
                    frame.showAlert("Ocorreu um erro ao cadastrar o cliente.", "Erro");
                return null;
            }

            if (showAlert)
                frame.showAlert("Cadastrado com sucesso.", "Sucesso");

            frame.close();

            listClientesMenu.addCliente(savedObject);
            listClientesMenu.openGui();

            return savedObject;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void openGui() {
        guiBuilder.build().open();
    }

    public void closeGui() {
        guiBuilder.build().close();
    }

    public GuiBuilder getGuiBuilder() {
        return guiBuilder;
    }
}