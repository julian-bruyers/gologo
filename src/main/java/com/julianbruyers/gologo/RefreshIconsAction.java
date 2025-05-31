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
            // Clear icon cache
            IconLoader.clearCache();
            
            // Refresh virtual file system
            VirtualFileManager.getInstance().asyncRefresh(null);
            
            // Refresh project view and editor tabs
            AppUIUtil.invokeOnEdt(() -> {
                // Refresh file editor manager to update tab icons
                FileEditorManager.getInstance(project).getSelectedFiles();
                
                // Force repaint of the project view
                project.getService(com.intellij.ide.projectView.ProjectView.class).refresh();
            });
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(e.getProject() != null);
    }
}
