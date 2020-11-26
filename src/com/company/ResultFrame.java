package com.company;

import com.company.events.EventService;
import com.company.events.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ContainerAdapter;
import java.util.List;
import java.util.Timer;

public class ResultFrame extends JFrame {
    private JPanel rootPanel;
    private JTextField textField_currEvent;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton button1;
    private JButton button_start;
    private static List<Customer> customers;
    private static Warehouse stock;
    private static Warehouse shop;
    private static EventType currentEvent;

    public ResultFrame() throws InterruptedException {
        //setContentPane(rootPane);
        setVisible(true);
        setSize(400, 300);
        this.add(textField_currEvent);
        this.add(button_start);
        this.pack();
        button_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Supermarket supermarket = new Supermarket();
                try {
                    EventService.runSupermarket(supermarket);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void setCustomers(List<Customer> list) {
        customers = list;
    }

    public static void setStock(Warehouse stock) {
        ResultFrame.stock = stock;
    }

    public static void setShop(Warehouse shop) {
        ResultFrame.shop = shop;
    }

    public static void setCurrentEvent(EventType currentEvent) {
        ResultFrame.currentEvent = currentEvent;
    }

}
