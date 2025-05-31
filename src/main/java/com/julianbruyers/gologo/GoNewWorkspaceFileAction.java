package com.julianbruyers.gologo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Custom Go New Workspace File action that replaces the original Go plugin action with Go logo icon.
 */
public class GoNewWorkspaceFileAction extends AnAction implements DumbAware {

    private static final Icon GO_NEW_WORKSPACE_FILE_ICON = IconLoader.getIcon("/icons/go_new_workspace_file.svg", GoNewWorkspaceFileAction.class.getClassLoader());

    public GoNewWorkspaceFileAction() {
        super("Go Workspace File", "Create a new Go workspace file", GO_NEW_WORKSPACE_FILE_ICON);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Delegate to the original Go plugin action
        ActionManager actionManager = ActionManager.getInstance();
        AnAction originalAction = actionManager.getAction("Go.NewWorkspaceFile");
        if (originalAction == null) {
            originalAction = actionManager.getAction("NewWorkspaceFile");
        }
        
        if (originalAction != null) {
            originalAction.actionPerformed(e);
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        // Keep the presentation updated
        e.getPresentation().setIcon(GO_NEW_WORKSPACE_FILE_ICON);
        
        // Delegate update logic to original action if available
        ActionManager actionManager = ActionManager.getInstance();
        AnAction originalAction = actionManager.getAction("Go.NewWorkspaceFile");
        if (originalAction == null) {
            originalAction = actionManager.getAction("NewWorkspaceFile");
        }
        
        if (originalAction != null) {
            originalAction.update(e);
        } else {
            e.getPresentation().setEnabled(e.getProject() != null);
        }
    }
}
