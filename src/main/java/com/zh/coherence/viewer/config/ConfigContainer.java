package com.zh.coherence.viewer.config;

import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.JXTree;
import org.springframework.beans.factory.InitializingBean;

import com.zh.coherence.viewer.config.action.ApplyAction;
import com.zh.coherence.viewer.config.action.CancelAction;
import com.zh.coherence.viewer.config.action.HelpAction;
import com.zh.coherence.viewer.config.action.OkAction;

import info.clearthought.layout.TableLayout;

public class ConfigContainer extends JFrame implements InitializingBean {
    private List<ConfigPanel> panels;

    private JXTree tree;
    private JSplitPane splitPane;
    private ConfigTreeModel configTreeModel;
    private JXTitledPanel titledPanel;
    private JPanel configPanelContainer;

    private ConfigPanel currentPanel = null;

    private JButton help;

    public ConfigContainer() throws HeadlessException {
        JPanel root = new JPanel(new BorderLayout());
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250);

        configPanelContainer = new JPanel(new BorderLayout());
        // Buttons
        JPanel buttons = new JPanel(new TableLayout(new double[][]{
                {FILL, 80, 5, 80, 5, 80, 5, 80, 5}, {2, PREFERRED, 4}
        }));
        configPanelContainer.add(buttons, BorderLayout.SOUTH);
        buttons.add(new JButton(new OkAction(this)), "1,1");
        buttons.add(new JButton(new CancelAction(this)), "3,1");
        buttons.add(new JButton(new ApplyAction(this)), "5,1");
        help = new JButton(new HelpAction(this));
        buttons.add(help, "7,1");

        buttons.setBorder(BorderFactory.createEtchedBorder());

        titledPanel = new JXTitledPanel("Config Panel doesn't selected", new JScrollPane(configPanelContainer));

        splitPane.setRightComponent(titledPanel);

        root.add(splitPane, BorderLayout.CENTER);

        setContentPane(root);
    }

    public List<ConfigPanel> getPanels() {
        return panels;
    }

    public void setPanels(List<ConfigPanel> panels) {
        this.panels = panels;
    }

    public ConfigPanel getCurrentPanel() {
        return currentPanel;
    }

    private void showPanel(ConfigPanel panel) {
        if (panel.getConfigPanel() != null) {
            configPanelContainer.add(panel.getConfigPanel(), BorderLayout.CENTER);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        configTreeModel = new ConfigTreeModel(panels);
        tree = new JXTree(configTreeModel);
        tree.setRootVisible(false);

        splitPane.setLeftComponent(new JScrollPane(tree));

        tree.getSelectionModel().addTreeSelectionListener(new SelectionListener());
    }

    private boolean disableTreeListener = false;

    private class SelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            if(disableTreeListener){
                disableTreeListener = false;
                return;
            }
            if (currentPanel != null) {
                boolean allow = currentPanel.leaveThePage();
                if (!allow) {
                    //return selection
                    disableTreeListener = true;
                    tree.getSelectionModel().setSelectionPath(e.getOldLeadSelectionPath());
                    return;
                }else{
                    Component component = currentPanel.getConfigPanel();
                    if(component != null){
                        configPanelContainer.remove(component);
                    }
                }
            }
            Object lastSelected = tree.getLastSelectedPathComponent();
            if (lastSelected instanceof ConfigTreeModel.TreeNodeWrapper) {
                currentPanel = ((ConfigTreeModel.TreeNodeWrapper) lastSelected).getPanel();
                titledPanel.setTitle(currentPanel.getConfigName());
                showPanel(currentPanel);
                titledPanel.updateUI();
                help.setEnabled(currentPanel.isHelpAvailable());
            }
        }
    }
}
