package com.pedromarininave;

import com.pedromarininave.services.ClienteService;
import com.pedromarininave.services.ProdutoService;
import com.pedromarininave.gui.MenuPrincipal;
import com.pedromarininave.services.PedidoService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class SpringBootSwingApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteService clienteService = new ClienteService();
            ProdutoService produtoService = new ProdutoService();
            PedidoService pedidoService = new PedidoService();

            SwingUtilities.invokeLater(() -> {
                MenuPrincipal menuPrincipal = new MenuPrincipal(clienteService, produtoService, pedidoService);
                menuPrincipal.openGui();
            });
        });
    }
}