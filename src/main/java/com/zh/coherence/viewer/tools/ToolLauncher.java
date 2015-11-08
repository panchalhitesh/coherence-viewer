package com.zh.coherence.viewer.tools;

import javax.swing.Icon;

import com.zh.coherence.viewer.utils.icons.IconLoader;

public abstract class ToolLauncher {

    private String name;

    private Icon icon;

    public abstract CoherenceViewerTool newTool();

    public boolean isAvailable(){
        return true;
    }

    public Icon getIcon(){
        return icon != null ? icon : new IconLoader("icons/toolbox.png");
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
