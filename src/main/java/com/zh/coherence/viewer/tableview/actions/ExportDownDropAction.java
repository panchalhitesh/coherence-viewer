package com.zh.coherence.viewer.tableview.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import com.zh.coherence.viewer.tableview.CoherenceTableView;
import com.zh.coherence.viewer.utils.icons.IconLoader;

public class ExportDownDropAction extends AbstractAction{
    private CoherenceTableView tableView;
    private JPopupMenu menu;

    public ExportDownDropAction(CoherenceTableView tableView) {
        this.tableView = tableView;

        menu = new JPopupMenu();
        menu.add(new ExportToExcelAction(tableView));
        menu.add(new ExportToCsvAction(tableView));

        putValue(Action.SMALL_ICON, new IconLoader("icons/save.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent component = (JComponent) e.getSource();
        menu.show(component, 0, 20);
    }
}
