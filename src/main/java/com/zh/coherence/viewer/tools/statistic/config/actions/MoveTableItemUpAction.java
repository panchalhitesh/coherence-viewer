package com.zh.coherence.viewer.tools.statistic.config.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.table.TableModel;

import com.zh.coherence.viewer.utils.icons.IconLoader;

public class MoveTableItemUpAction extends AbstractAction {

    private TableModel model;

    public MoveTableItemUpAction(TableModel model) {
        this.model = model;

        putValue(Action.NAME, "move up");
        putValue(Action.SMALL_ICON, new IconLoader("icons/up.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
