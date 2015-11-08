package com.zh.coherence.viewer.tools.statistic.tabs;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import javax.swing.Icon;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTitledSeparator;

import com.zh.coherence.viewer.jmx.JMXManager;
import com.zh.coherence.viewer.tools.statistic.table.map.MapTableComponent;
import com.zh.coherence.viewer.utils.icons.IconLoader;

import info.clearthought.layout.TableLayout;

public class ClusterInfoPane extends JXPanel implements JmxInfoPane{

    private MapTableComponent clusterTable;

    public ClusterInfoPane() {
        super(new TableLayout(new double[][]{
                {2, TableLayout.FILL, 2},
                {5, PREFERRED,2, 150, 2}
        }));

        Icon icon = new IconLoader("icons/cluster.png");

        add(new JXTitledSeparator("Cluster", SwingConstants.LEFT, icon), "1,1");

        clusterTable = new MapTableComponent();

        add(clusterTable, "1,3");

        refresh();
    }

    public void refresh(){
        clusterTable.setData(JMXManager.getInstance().getClusterInfo());
    }
}
