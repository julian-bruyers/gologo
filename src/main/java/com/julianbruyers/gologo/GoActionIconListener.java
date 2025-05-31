package com.julianbruyers.gologo;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Application service that listens for action events and dynamically overrides Go action icons.
 * This ensures icons are replaced even for dynamically registered actions.
 */
@Service
public final class GoActionIconListener implements DumbAware {

    private static final Map<String, Icon> ACTION_ICON_MAP = new HashMap<>();
    private MessageBusConnection connection;

    static {
        ACTION_ICON_MAP.put("Go.NewGoFile", IconLoader.getIcon("/icons/go_new_file.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("Go.NewWorkspaceFile", IconLoader.getIcon("/icons/go_new_workspace_file.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("GoToolsGroup", IconLoader.getIcon("/icons/go_tools.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("Go.Tools", IconLoader.getIcon("/icons/go_tools.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("Go.Settings", IconLoader.getIcon("/icons/go_settings.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("NewGoFile", IconLoader.getIcon("/icons/go_new_file.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("NewWorkspaceFile", IconLoader.getIcon("/icons/go_new_workspace_file.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("GoTools", IconLoader.getIcon("/icons/go_tools.svg", GoActionIconListener.class.getClassLoader()));
        ACTION_ICON_MAP.put("GoSettings", IconLoader.getIcon("/icons/go_settings.svg", GoActionIconListener.class.getClassLoader()));
    }

    public GoActionIconListener() {
        initialize();
    }

    private void initialize() {
        connection = ApplicationManager.getApplication().getMessageBus().connect();
        connection.subscribe(AnActionListener.TOPIC, new AnActionListener() {
            @Override
            public void beforeActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event) {
                // Check if this is a Go-related action and override its icon if needed
                String actionId = ActionManager.getInstance().getId(action);
                if (actionId != null && ACTION_ICON_MAP.containsKey(actionId)) {
                    Icon newIcon = ACTION_ICON_MAP.get(actionId);
                    if (newIcon != null) {
                        action.getTemplatePresentation().setIcon(newIcon);
                        event.getPresentation().setIcon(newIcon);
                    }
                }
            }
        });

        // Also override existing actions immediately
        ApplicationManager.getApplication().invokeLater(() -> {
            ActionManager actionManager = ActionManager.getInstance();
            for (Map.Entry<String, Icon> entry : ACTION_ICON_MAP.entrySet()) {
                AnAction action = actionManager.getAction(entry.getKey());
                if (action != null) {
                    action.getTemplatePresentation().setIcon(entry.getValue());
                }
            }
        });
    }

    public void dispose() {
        if (connection != null) {
            connection.disconnect();
        }
    }
}
