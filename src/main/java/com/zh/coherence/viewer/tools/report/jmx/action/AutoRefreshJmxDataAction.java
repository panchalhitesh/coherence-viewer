package com.zh.coherence.viewer.tools.report.jmx.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

import com.zh.coherence.viewer.tools.statistic.report.JMXReport;
import com.zh.coherence.viewer.utils.icons.IconLoader;

public class AutoRefreshJmxDataAction extends AbstractAction{

    private Timer timer = new Timer((int)TimeUnit.MINUTES.toMillis(1), new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMXReport.getInstance().refreshReport();
        }
    });

    public AutoRefreshJmxDataAction() {
        putValue(Action.SMALL_ICON, new IconLoader("icons/clock.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(timer.isRunning()){
            timer.stop();
        }else{
            JMXReport.getInstance().refreshReport();
            timer.start();
        }
    }
}
