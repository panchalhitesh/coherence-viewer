package com.zh.coherence.viewer.objectexplorer;

import com.zh.coherence.viewer.objectexplorer.config.OEConfigManager;
import com.zh.coherence.viewer.objectexplorer.viewer.DefaultViewer;
import com.zh.coherence.viewer.objectexplorer.viewer.MapEntryViewer;
import com.zh.coherence.viewer.objectexplorer.viewer.Viewer;
import com.zh.coherence.viewer.objectexplorer.viewer.ViewerFactory;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class ObjectExplorerTreeModel extends AbstractTreeTableModel {
    private Object subject = null;
    private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();
    private ViewerFactory viewerFactory = new ViewerFactory();
    private Map<Object, List<Viewer>> cache = new HashMap<Object, List<Viewer>>();
    private OEConfigManager configManager = OEConfigManager.getInstance();

    @Override
    public Object getRoot() {
        if (subject != null) {
            return viewerFactory.getViewer("root", subject);
        } else {
            return "NULL";
        }
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent != null && parent instanceof Viewer) {
            return getCachedViewer(((Viewer) parent).getSubject(), index);
        } else {
            return null;
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent != null && parent instanceof Viewer) {
            Object subject = ((Viewer) parent).getSubject();
            if (subject != null) {
                if (configManager.isStopped(subject)) {
                    return 0;
                } else {
                    if (subject instanceof Map) {
                        return ((Map) subject).size();
                    } else if (subject instanceof Collection) {
                        return ((Collection) subject).size();
                    } else if(subject.getClass().isArray()){
                        int size = Array.getLength(subject);
                        if(size > configManager.getDataKeeper().getArrayLimit()){
                            size = configManager.getDataKeeper().getArrayLimit() + 1;
                        }
                        return size;
                    } else {
                        return getFieldsList(subject).size();
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        if (node == null) return true;
        if (node instanceof Viewer) {
            Object subject = ((Viewer) node).getSubject();
            return subject == null || configManager.isStopped(subject);
        }
        return "NULL".equals(node);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public void fireDataChanged() {
        for (TreeModelListener listener : listeners) {
            listener.treeStructureChanged(new TreeModelEvent(listener, (TreePath) null));
        }
    }

    private List<Viewer> getFields(Object obj) {
        List<Viewer> ret = new ArrayList<Viewer>();
        if (obj != null) {
            if(obj.getClass().isArray()){
                int size = Array.getLength(obj);
                for (int i = 0; i < size && i <= configManager.getDataKeeper().getArrayLimit(); i++){
                    ret.add(new DefaultViewer("["+i+"]", Array.get(obj, i)));
                }
                if(size > configManager.getDataKeeper().getArrayLimit()){
                    ret.add(new DefaultViewer("[...]", null));
                }
            }else if (obj instanceof Map) {
                Set<Map.Entry> set = ((Map) obj).entrySet();
                for (Map.Entry entry : set) {
                    ret.add(new MapEntryViewer(entry));
                }
            } else if (obj instanceof Collection) {
                String name;
                for (Object o : (Collection) obj) {
                    if (o != null) {
                        name = o.toString();
                        if (name.length() > 15) {
                            name = name.substring(0, 15) + "...";
                        }
                    } else {
                        name = "NULL";
                    }

                    ret.add(viewerFactory.getViewer(name, o));
                }
            } else {
                for (Field field : getFieldsList(obj)) {
                    try {
                        field.setAccessible(true);
                        ret.add(viewerFactory.getViewer(field.getName(), field.get(obj)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Collections.sort(ret, new Comparator<Viewer>() {
            @Override
            public int compare(Viewer o1, Viewer o2) {
                return o1.getNodeName().compareTo(o2.getNodeName());
            }
        });
        return ret;
    }

    private List<Field> getFieldsList(Object obj) {
        List<Field> fields = new ArrayList<Field>();
        Class clazz = obj.getClass();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        while (true) {
            clazz = clazz.getSuperclass();
            if (clazz != null) {
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            } else {
                break;
            }
        }
        return fields;
    }

    public Viewer getCachedViewer(Object parent, int index) {
        if (!cache.containsKey(parent)) {
            cache.put(parent, getFields(parent));
        }

        return cache.get(parent).get(index);
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(Object parent, int index) {
        if(index == 1){
            if (parent != null && parent instanceof Viewer) {
                Object subject = ((Viewer) parent).getSubject();
                if(subject != null && subject.getClass().isArray()){
                    return Array.getLength(subject);
                }
            }

            return getChildCount(parent);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0){
            return "Tree";
        }else{
            return "Size";
        }
    }
}
