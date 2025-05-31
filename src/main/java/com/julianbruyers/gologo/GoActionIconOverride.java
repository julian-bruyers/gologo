package com.julianbruyers.gologo;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Startup activity that overrides Go plugin action icons with Go logo variants.
 * This replaces icons for menu actions like "New Go File", "Go Tools", etc.
 */
public class GoActionIconOverride implements StartupActivity, DumbAware {

    private static final Icon GO_NEW_FILE_ICON = IconLoader.getIcon("/icons/go_new_file.svg", GoActionIconOverride.class.getClassLoader());
    private static final Icon GO_NEW_WORKSPACE_FILE_ICON = IconLoader.getIcon("/icons/go_new_workspace_file.svg", GoActionIconOverride.class.getClassLoader());
    private static final Icon GO_TOOLS_ICON = IconLoader.getIcon("/icons/go_tools.svg", GoActionIconOverride.class.getClassLoader());
    private static final Icon GO_SETTINGS_ICON = IconLoader.getIcon("/icons/go_settings.svg", GoActionIconOverride.class.getClassLoader());

    @Override
    public void runActivity(@NotNull Project project) {
        ApplicationManager.getApplication().invokeLater(() -> {
            overrideActionIcons();
        });
    }

    private void overrideActionIcons() {
        ActionManager actionManager = ActionManager.getInstance();
        
        // Override Go-related actions with new icons
        overrideActionIcon(actionManager, "Go.NewGoFile", GO_NEW_FILE_ICON);
        overrideActionIcon(actionManager, "Go.NewWorkspaceFile", GO_NEW_WORKSPACE_FILE_ICON);
        overrideActionIcon(actionManager, "GoToolsGroup", GO_TOOLS_ICON);
        overrideActionIcon(actionManager, "Go.Tools", GO_TOOLS_ICON);
        overrideActionIcon(actionManager, "Go.Settings", GO_SETTINGS_ICON);
        
        // Also try common variations of action IDs
        overrideActionIcon(actionManager, "NewGoFile", GO_NEW_FILE_ICON);
        overrideActionIcon(actionManager, "NewWorkspaceFile", GO_NEW_WORKSPACE_FILE_ICON);
        overrideActionIcon(actionManager, "GoTools", GO_TOOLS_ICON);
        overrideActionIcon(actionManager, "GoSettings", GO_SETTINGS_ICON);
    }

    private void overrideActionIcon(ActionManager actionManager, String actionId, Icon newIcon) {
        try {
            AnAction action = actionManager.getAction(actionId);
            if (action != null) {
                action.getTemplatePresentation().setIcon(newIcon);
                System.out.println("GoLogo: Successfully overrode icon for action: " + actionId);
            } else {
                System.out.println("GoLogo: Action not found: " + actionId);
            }
        } catch (Exception e) {
            System.err.println("GoLogo: Error overriding icon for action " + actionId + ": " + e.getMessage());
        }
    }
}
