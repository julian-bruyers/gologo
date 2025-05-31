package com.julianbruyers.gologo;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Enhanced startup activity that discovers and overrides all Go-related action icons.
 * This uses multiple strategies to find and replace Go action icons.
 */
public class GoActionDiscoveryOverride implements StartupActivity, DumbAware {

    private static final Icon GO_NEW_FILE_ICON = IconLoader.getIcon("/icons/go_new_file.svg", GoActionDiscoveryOverride.class.getClassLoader());
    private static final Icon GO_NEW_WORKSPACE_FILE_ICON = IconLoader.getIcon("/icons/go_new_workspace_file.svg", GoActionDiscoveryOverride.class.getClassLoader());
    private static final Icon GO_TOOLS_ICON = IconLoader.getIcon("/icons/go_tools.svg", GoActionDiscoveryOverride.class.getClassLoader());
    private static final Icon GO_SETTINGS_ICON = IconLoader.getIcon("/icons/go_settings.svg", GoActionDiscoveryOverride.class.getClassLoader());

    // Known Go action ID patterns
    private static final List<String> GO_NEW_FILE_IDS = Arrays.asList(
        "Go.NewGoFile", "NewGoFile", "com.goide.actions.NewGoFileAction",
        "GoLang.NewFile", "Go.NewFile"
    );
    
    private static final List<String> GO_NEW_WORKSPACE_IDS = Arrays.asList(
        "Go.NewWorkspaceFile", "NewWorkspaceFile", "com.goide.actions.NewWorkspaceFileAction",
        "GoLang.NewWorkspaceFile", "Go.NewWorkspace"
    );
    
    private static final List<String> GO_TOOLS_IDS = Arrays.asList(
        "GoToolsGroup", "Go.Tools", "GoTools", "com.goide.actions.GoToolsAction",
        "GoLang.Tools", "Go.ToolsMenu"
    );
    
    private static final List<String> GO_SETTINGS_IDS = Arrays.asList(
        "Go.Settings", "GoSettings", "com.goide.settings.GoSettingsAction",
        "GoLang.Settings", "Go.Configure"
    );

    @Override
    public void runActivity(@NotNull Project project) {
        ApplicationManager.getApplication().invokeLater(() -> {
            discoverAndOverrideGoActions();
        });
    }    private void discoverAndOverrideGoActions() {
        ActionManager actionManager = ActionManager.getInstance();
        
        overrideActionsByIds(actionManager, GO_NEW_FILE_IDS, GO_NEW_FILE_ICON);
        overrideActionsByIds(actionManager, GO_NEW_WORKSPACE_IDS, GO_NEW_WORKSPACE_FILE_ICON);
        overrideActionsByIds(actionManager, GO_TOOLS_IDS, GO_TOOLS_ICON);
        overrideActionsByIds(actionManager, GO_SETTINGS_IDS, GO_SETTINGS_ICON);
        
        discoverGoActionsByPattern(actionManager);
    }

    private void overrideActionsByIds(ActionManager actionManager, List<String> actionIds, Icon icon) {
        for (String actionId : actionIds) {
            AnAction action = actionManager.getAction(actionId);
            if (action != null) {
                action.getTemplatePresentation().setIcon(icon);
            }
        }
    }

    private void discoverGoActionsByPattern(ActionManager actionManager) {
        String[] allActionIds = actionManager.getActionIds("");
        
        for (String actionId : allActionIds) {
            if (isGoRelatedAction(actionId)) {
                AnAction action = actionManager.getAction(actionId);
                if (action != null) {
                    Icon appropriateIcon = determineIconForAction(actionId, action);
                    if (appropriateIcon != null) {
                        action.getTemplatePresentation().setIcon(appropriateIcon);
                    }
                }
            }
        }
    }

    private boolean isGoRelatedAction(String actionId) {
        String lowerId = actionId.toLowerCase();
        return (lowerId.contains("go") && 
                (lowerId.contains("new") || lowerId.contains("file") || 
                 lowerId.contains("workspace") || lowerId.contains("tools") || 
                 lowerId.contains("settings"))) ||               actionId.startsWith("com.goide") ||
               actionId.startsWith("GoLang");
    }
    
    private Icon determineIconForAction(String actionId, AnAction action) {
        String lowerId = actionId.toLowerCase();
        
        if (lowerId.contains("newgofile") || (lowerId.contains("new") && lowerId.contains("file"))) {
            return GO_NEW_FILE_ICON;
        } else if (lowerId.contains("workspace")) {
            return GO_NEW_WORKSPACE_FILE_ICON;
        } else if (lowerId.contains("tools")) {
            return GO_TOOLS_ICON;
        } else if (lowerId.contains("settings") || lowerId.contains("configure")) {
            return GO_SETTINGS_ICON;
        }
        
        return null;
    }
}
