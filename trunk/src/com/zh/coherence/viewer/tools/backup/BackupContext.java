package com.zh.coherence.viewer.tools.backup;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 19.03.12
 * Time: 20:56
 */
public class BackupContext {
    public enum BackupAction {BACKUP, RESTORE}
    public enum Target {FOLDER, ZIP}

    protected JProgressBar generalProgress, cacheProgress;

    private BackupAction action = BackupAction.BACKUP;
    private Target target = Target.FOLDER;
    private String path;
    private BackupTableModel backupTableModel = new BackupTableModel();

    public void updateGeneralProgress(){
        generalProgress.setString((Math.rint(1000.0 * generalProgress.getPercentComplete()) / 10.0)  + " %");
    }

    public void incrementGeneralProgress(){
        generalProgress.setValue(generalProgress.getValue() + 1);
        updateGeneralProgress();
    }

    public void incrementCacheProgress(String name){
        cacheProgress.setValue(cacheProgress.getValue() + 1);
        updateCacheProgress(name);
    }

    public void updateCacheProgress(String name) {
        cacheProgress.setString("["+name+"] - " + (Math.rint(100.0 * cacheProgress.getPercentComplete()))
                + " %");
    }

    public BackupAction getAction() {
        return action;
    }

    public void setAction(BackupAction action) {
        this.action = action;
        refreshTableData();
    }

    public void refreshTableData(){
        switch (action){
            case BACKUP:
                backupTableModel.updateCachesFromJMX();
                break;
            case RESTORE:
                backupTableModel.updateCacheFromDir(path);
        }
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        refreshTableData();
    }

    public BackupTableModel getBackupTableModel() {
        return backupTableModel;
    }
}
