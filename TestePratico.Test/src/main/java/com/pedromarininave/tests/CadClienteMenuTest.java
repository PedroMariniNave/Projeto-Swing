package com.pedromarininave.tests;

import com.pedromarininave.gui.MenuPrincipal;
import com.pedromarininave.gui.cliente.CadClienteMenu;
import com.pedromarininave.gui.cliente.ListClientesMenu;
import com.pedromarininave.services.ClienteService;
import com.pedromarininave.services.ProdutoService;
import com.pedromarininave.services.PedidoService;
import com.pedromarininave.testepratico.models.Cliente;
import org.junit.Test;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CadClienteMenuTest {

    @Test
    public void testSalvarClienteSuccesso() throws InterruptedException {
        Thread.sleep(3000); // necessário esperar a API iniciar

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        PedidoService pedidoService = new PedidoService();
        MenuPrincipal menuPrincipal = new MenuPrincipal(clienteService, produtoService, pedidoService);
        ListClientesMenu listClientesMenu = new ListClientesMenu(clienteService, menuPrincipal);

        CadClienteMenu cadClienteMenu = new CadClienteMenu(clienteService, listClientesMenu);

        loadFieldsMockCliente(cadClienteMenu);
        Cliente savedObject = cadClienteMenu.salvarCliente(false);

        cadClienteMenu.closeGui();

        assertEquals("123", savedObject.getCodigo());
        assertEquals("Nome do Cliente", savedObject.getNome());
        assertEquals(new BigDecimal("1000.0"), savedObject.getValorLimiteCompra());
        assertEquals(10, savedObject.getDiaFechamentoFatura());
    }

    private static void loadFieldsMockCliente(CadClienteMenu cadClienteMenu) {
        HashMap<String, JTextField> fields = cadClienteMenu.getGuiBuilder().getTextFields();
        JTextField codigoField = new JTextField("123");
        JTextField nomeField = new JTextField("Nome do Cliente");
        JTextField valorLimiteField = new JTextField("1000.0");
        JTextField diaFechamentoField = new JTextField("10");

        fields.put(Cliente.Fields.codigo, codigoField);
        fields.put(Cliente.Fields.nome, nomeField);
        fields.put(Cliente.Fields.valorLimiteCompra, valorLimiteField);
        fields.put(Cliente.Fields.diaFechamentoFatura, diaFechamentoField);
    }
}
