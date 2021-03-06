package com.zh.coherence.viewer.tableview.user;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 26.04.12
 * Time: 22:44
 */
public interface UserObjectViewer {
    public static enum Target{FRAME, DIALOG, TAB}

    public JComponent buildPane(Object value);

    public Icon getIcon();

    public boolean isSupport(Object value);

    public String getName();

    public Target getTarget();
}
