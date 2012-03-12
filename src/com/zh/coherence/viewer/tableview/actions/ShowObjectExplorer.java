package com.zh.coherence.viewer.tableview.actions;

import com.zh.coherence.viewer.objectexplorer.ObjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 12.03.12
 * Time: 20:50
 */
public class ShowObjectExplorer extends AbstractAction{
    private Object value;
    
    public ShowObjectExplorer(Object value) {
        this.value = value;
        putValue(Action.NAME, "object explorer");
        putValue(Action.SMALL_ICON, new ImageIcon("icons/explorer.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog((JFrame)null, "Object Explorer");
        dialog.setSize(800, 600);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ObjectExplorer explorer = new ObjectExplorer();
        explorer.showObject(value);
        dialog.getContentPane().add(explorer);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}