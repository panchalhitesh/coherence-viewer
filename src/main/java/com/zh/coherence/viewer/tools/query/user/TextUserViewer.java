package com.zh.coherence.viewer.tools.query.user;

import javax.swing.JComponent;

import com.zh.coherence.viewer.components.text.SearchTextPanel;
import com.zh.coherence.viewer.tableview.user.BaseUserObjectViewer;

public class TextUserViewer extends BaseUserObjectViewer {

    public JComponent buildPane(final Object value) {
        SearchTextPanel textPanel = new SearchTextPanel();
        textPanel.setText(String.valueOf(value));

        return textPanel;
    }

    @Override
    public boolean isSupport(Object value) {
        return value != null;
    }
}
