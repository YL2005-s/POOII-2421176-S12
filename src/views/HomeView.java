package views;

import controllers.HomeController;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
    private final HomeController homeController;

    private JPanel navigationPanel;
    private JTextArea logArea;

    private JButton btn_create;
    private JButton btn_accounts;
    private JButton btn_transfer;
    private JButton btn_movements;
    private JButton btn_exit;

    public HomeView(HomeController homeController) {
        this.homeController = homeController;

        make_frame();
        make_navigationPanel();
        make_btns();
        make_logArea();
    }


    private void make_frame() {
        setLayout(new BorderLayout());
    }

    private void make_navigationPanel() {
        navigationPanel = new JPanel(new FlowLayout());
        add(navigationPanel, BorderLayout.NORTH);
    }

    private void make_btns() {
        btn_create = new JButton("1. Crear cuenta");
        btn_accounts = new JButton("2. Ver cuentas");
        btn_transfer = new JButton("3. Realizar transferencia");
        btn_movements = new JButton("4. Ver movimientos");
        btn_exit = new JButton("5. Salir");

        navigationPanel.add(btn_create);
        navigationPanel.add(btn_accounts);
        navigationPanel.add(btn_transfer);
        navigationPanel.add(btn_movements);
        navigationPanel.add(btn_exit);
    }

    private void make_logArea() {
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        logArea.setMargin(new Insets(10, 15, 10, 10));
        logArea.setBackground(new Color(245, 245, 245));
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }


}
