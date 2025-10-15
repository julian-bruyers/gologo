package com.julianbruyers.gologo;

import com.intellij.ide.FileIconProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.IconUtil;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * File icon provider for Go-related files.
 * This provider replaces the default Gopher icons with the official Go logo.
 */
public class GoLogo implements FileIconProvider, DumbAware {

    private static final Icon GO_FILE_ICON = IconUtil.scale(
        IconLoader.getIcon("/icons/go_file.svg", GoLogo.class.getClassLoader()),
        null,
        1.95f
    );
    private static final Icon GO_MOD_ICON = IconUtil.scale(
        IconLoader.getIcon("/icons/go_mod.svg", GoLogo.class.getClassLoader()),
        null,
        1.95f
    );
    private static final Icon GO_SUM_ICON = IconUtil.scale(
        IconLoader.getIcon("/icons/go_sum.svg", GoLogo.class.getClassLoader()),
        null,
        1.95f
    );

    @Nullable
    @Override
    public Icon getIcon(
        @NotNull VirtualFile file,
        int flags,
        @Nullable Project project
    ) {
        if (file == null) return null;

        String fileName = file.getName().toLowerCase();

        // Handle go.mod files
        if (fileName.equals("go.mod")) {
            return GO_MOD_ICON;
        }
        // Handle go.sum files
        else if (fileName.equals("go.sum")) {
            return GO_SUM_ICON;
        }
        // Handle .go files
        else if (fileName.endsWith(".go")) {
            return GO_FILE_ICON;
        }

        return null;
    }
}
