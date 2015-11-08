package com.zh.coherence.viewer.tools.statistic.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.zh.coherence.viewer.tools.statistic.report.JMXReport;
import com.zh.coherence.viewer.utils.icons.IconLoader;

public class UpdateDataAction extends AbstractAction{
    private JMXReport report;

    public UpdateDataAction(JMXReport report) {
        this.report = report;
        putValue(Action.SMALL_ICON, new IconLoader("icons/arrow-circle-double.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        report.refresh();
    }
}
