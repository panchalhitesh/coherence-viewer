package com.zh.coherence.viewer.tools.statistic.table.map;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;

public class MapTableComponent extends JXPanel{

    private JXTable table;

    private JToolBar toolBar;

    private MapTableModel tableModel;

    private Map data;

    public MapTableComponent() {
        super(new BorderLayout());

        tableModel = new MapTableModel();
        table = new JXTable(tableModel);

        toolBar = new JToolBar(JToolBar.HORIZONTAL);

        add(toolBar, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
        tableModel.setData(data);
    }
}
