package com.zh.coherence.viewer.tools.statistic.config.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.jdesktop.swingx.JXList;

import com.zh.coherence.viewer.tools.statistic.config.TabConfigListModel;
import com.zh.coherence.viewer.utils.icons.IconLoader;

public class RemoveTabConfigAction extends AbstractAction {

    private TabConfigListModel model;

    private JXList jxList;

    public RemoveTabConfigAction(TabConfigListModel model, JXList jxList) {
        this.model = model;
        this.jxList = jxList;

        putValue(Action.NAME, "Remove");
        putValue(Action.SMALL_ICON, new IconLoader("icons/minus.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.removeTabs(jxList.getSelectedValues());
    }
}
