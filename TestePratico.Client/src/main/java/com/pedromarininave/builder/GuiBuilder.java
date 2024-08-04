package com.pedromarininave.builder;

import com.pedromarininave.constants.GuiConstants;
import com.pedromarininave.swing.filters.NumericDocumentFilter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public final class GuiBuilder {
    private final JFrame frame;
    private final JPanel panel;
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    private final HashMap<String, JTextField> textFields = new HashMap<>();
    private int lastFieldY = 0;

    public GuiBuilder(String title) {
        panel = new JPanel(new GridBagLayout());
        frame = new JFrame(title);

        configureFrame();
        configureGridBagConstraints();
    }

    public GuiBuilder addTextBox(String labelText) {
        return addTextBox(labelText, null, GuiConstants.DEFAULT_COLUMNS, null);
    }

    public GuiBuilder addTextBox(String labelText, String propertyName) {
        return addTextBox(labelText, propertyName, GuiConstants.DEFAULT_COLUMNS, null);
    }

    public GuiBuilder addTextBox(String labelText, String propertyName, int columns, DocumentFilter documentFilter) {
        JTextField textField = new JTextField(columns);
        JLabel label = new JLabel(labelText);

        addComponentInPanel(label);
        addComponentInPanel(textField);

        if (propertyName != null)
            textFields.put(propertyName, textField);

        if (documentFilter != null)
            ((AbstractDocument) textField.getDocument()).setDocumentFilter(documentFilter);

        return this;
    }

    public GuiBuilder addNumericTextBox(String labelText, String propertyName) {
        return addTextBox(labelText, propertyName, GuiConstants.DEFAULT_COLUMNS, new NumericDocumentFilter());
    }

    public GuiBuilder addNumericTextBox(String labelText, String propertyName, int columns) {
        return addTextBox(labelText, propertyName, columns, new DocumentFilter());
    }

    public GuiBuilder addButton(String text, boolean closeOnClick) {
        return addButton(text, () -> {}, closeOnClick);
    }

    public GuiBuilder addButton(String text, Runnable buttonAction) {
        return addButton(text, buttonAction, false);
    }

    public GuiBuilder addButton(String text, Runnable buttonAction, boolean closeOnClick) {
        JButton button = new JButton(text);

        button.addActionListener(e -> {
            buttonAction.run();

            if (closeOnClick)
                frame.close();
        });

        addComponentInPanel(button);

        return this;
    }

    public GuiBuilder addTable(JTable table) {
        return addTable(null, table);
    }

    public GuiBuilder addTable(AbstractTableModel tableModel) {
        return addTable(tableModel, null);
    }

    public GuiBuilder addTable(AbstractTableModel tableModel, JTable table) {
        if (table == null)
            table = new JTable();

        if (tableModel != null)
            table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(frame.getSize());

        addComponentInPanel(scrollPane);

        return this;
    }

    public JFrame build() {
        return frame;
    }

    private void addComponentInPanel(Component component) {
        gridBagConstraints.gridy = lastFieldY++;

        panel.add(component, gridBagConstraints);
        panel.revalidate();
        panel.repaint();
    }

    private void configureFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void configureGridBagConstraints() {
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = 0;
    }

    public HashMap<String, JTextField> getTextFields() {
        return textFields;
    }
}