package com.julianbruyers.gologo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.ui.AppUIUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Action to refresh Go icons throughout the IDE.
 * This forces a UI refresh to ensure all icons are updated.
 */
public class RefreshIconsAction extends AnAction {    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;
        
        ApplicationManager.getApplication().invokeLater(() -> {
            IconLoader.clearCache();
            VirtualFileManager.getInstance().asyncRefresh(null);
            
            AppUIUtil.invokeOnEdt(() -> {
                FileEditorManager.getInstance(project).getSelectedFiles();
                project.getService(com.intellij.ide.projectView.ProjectView.class).refresh();
            });
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(e.getProject() != null);
    }
}
