package com.julianbruyers.gologo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Custom Go New File action that replaces the original Go plugin action with Go logo icon.
 */
public class GoNewFileAction extends AnAction implements DumbAware {

    private static final Icon GO_NEW_FILE_ICON = IconLoader.getIcon("/icons/go_new_file.svg", GoNewFileAction.class.getClassLoader());

    public GoNewFileAction() {
        super("Go File", "Create a new Go file", GO_NEW_FILE_ICON);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Delegate to the original Go plugin action
        ActionManager actionManager = ActionManager.getInstance();
        AnAction originalAction = actionManager.getAction("Go.NewGoFile");
        if (originalAction == null) {
            originalAction = actionManager.getAction("NewGoFile");
        }
          if (originalAction != null) {
            originalAction.actionPerformed(e);
        } else {
            // Fallback: Enable Go file creation through basic mechanisms
            // This is a placeholder - in practice the original action should be found
            System.out.println("GoLogo: Original Go new file action not found");
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        // Keep the presentation updated
        e.getPresentation().setIcon(GO_NEW_FILE_ICON);
        
        // Delegate update logic to original action if available
        ActionManager actionManager = ActionManager.getInstance();
        AnAction originalAction = actionManager.getAction("Go.NewGoFile");
        if (originalAction == null) {
            originalAction = actionManager.getAction("NewGoFile");
        }
        
        if (originalAction != null) {
            originalAction.update(e);
        } else {
            e.getPresentation().setEnabled(e.getProject() != null);
        }
    }
}
