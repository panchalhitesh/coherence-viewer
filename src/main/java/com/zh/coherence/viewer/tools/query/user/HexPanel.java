package com.zh.coherence.viewer.tools.query.user;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.fife.ui.hex.swing.HexEditor;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import com.zh.coherence.viewer.pof.ValueContainer;
import com.zh.coherence.viewer.tableview.user.BaseUserObjectViewer;

public class HexPanel extends BaseUserObjectViewer {

    @Override
    public JComponent buildPane(Object value) {
        JPanel panel = new JPanel(new BorderLayout());
        HexEditor editor = new HexEditor();
        editor.setCellEditable(false);
        panel.add(editor, BorderLayout.CENTER);

        if(value instanceof ValueContainer){
            ByteArrayInputStream bis = new ByteArrayInputStream(((ValueContainer) value).getBinary());
            try {
                editor.open(bis);
            } catch (IOException ex) {
                JXErrorPane.showFrame(null, new ErrorInfo("Error", ex.getMessage(), null, null, ex, Level.SEVERE, null));
            }
        }

        return panel;
    }

    @Override
    public boolean isSupport(Object value) {
        return value instanceof ValueContainer;
    }
}
