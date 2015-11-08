package com.zh.coherence.viewer.utils.icons;

import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.InitializingBean;

public class IconLoader extends ImageIcon implements InitializingBean{
    private String path;

    public IconLoader() {
    }

    public IconLoader(String path) {
        this.path = path;
        init();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init(){
        if(path != null){
            URL url = this.getClass().getClassLoader().getResource(path);
            setImage(Toolkit.getDefaultToolkit().getImage(url));
        }else{
            URL url = getClass().getResource("broken.png");
            setImage(Toolkit.getDefaultToolkit().getImage(url));
        }
    }
}
