package com.zh.coherence.viewer.tools.statistic.config.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.table.TableModel;

import com.zh.coherence.viewer.utils.icons.IconLoader;

public class MoveTableItemDownAction extends AbstractAction {

    private TableModel model;

    public MoveTableItemDownAction(TableModel model) {
        this.model = model;

        putValue(Action.NAME, "move down");
        putValue(Action.SMALL_ICON, new IconLoader("icons/down.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
