package com.pedromarininave.extensions.javax.swing.JFrame;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import javax.swing.*;

@Extension
public class JFrameExtensions {

    public static void open(@This JFrame frame) {
        frame.setVisible(true);
    }

    public static void close(@This JFrame frame) {
        frame.setVisible(false);
    }

    public static void showAlert(@This JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showAlert(@This JFrame frame, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }
}