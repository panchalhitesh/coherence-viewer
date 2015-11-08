package com.zh.coherence.viewer.tools.query.config.template;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import org.fife.ui.rsyntaxtextarea.templates.StaticCodeTemplate;
import org.jdesktop.swingx.JXList;

import com.zh.coherence.viewer.utils.icons.IconLoader;

public class CreateTemplateAction extends AbstractAction {

    private TemplateListModel listModel;
    private JXList list;

    public CreateTemplateAction(TemplateListModel listModel, JXList list) {
        this.listModel = listModel;
        this.list = list;

        putValue(Action.SMALL_ICON, new IconLoader("icons/plus.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object in = JOptionPane.showInputDialog((Component)e.getSource(), "Input template name", "New Template", JOptionPane.INFORMATION_MESSAGE);
        if(in != null && !in.toString().isEmpty()){
            StaticCodeTemplate template = new StaticCodeTemplate(in.toString(), "", null);
            Object selected = listModel.addTemplate(template);
            list.setSelectedValue(selected, true);
        }
    }
}
