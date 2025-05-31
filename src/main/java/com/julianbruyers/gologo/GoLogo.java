package com.julianbruyers.gologo;

import com.intellij.ide.FileIconProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GoLogo implements FileIconProvider {

    private static final Icon GO_FILE_ICON = IconLoader.getIcon("/icons/go_file.svg", GoLogo.class.getClassLoader());
    private static final Icon GO_MOD_ICON = IconLoader.getIcon("/icons/go_mod.svg", GoLogo.class.getClassLoader());
    private static final Icon GO_SUM_ICON = IconLoader.getIcon("/icons/go_sum.svg", GoLogo.class.getClassLoader());

    @Nullable
    @Override
    public Icon getIcon(@NotNull VirtualFile file, int flags, @Nullable Project project) {
        String fileName = file.getName().toLowerCase();
            
        if (fileName.equals("go.mod")) {
            return GO_MOD_ICON;
        } else if (fileName.equals("go.sum")) {
            return GO_SUM_ICON;
        } else if (fileName.endsWith(".go")) {
            return GO_FILE_ICON;
        }

        return null;
    }
}
