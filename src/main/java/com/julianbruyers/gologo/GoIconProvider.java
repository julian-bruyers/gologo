package com.julianbruyers.gologo;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Icon provider for PSI elements (Project Structure Interface).
 * This provider handles icons for Go-related PSI elements in the IDE.
 */
public class GoIconProvider extends IconProvider implements DumbAware {

    private static final Icon GO_FILE_ICON = IconLoader.getIcon("/icons/go_file.svg", GoIconProvider.class.getClassLoader());
    private static final Icon GO_MOD_ICON = IconLoader.getIcon("/icons/go_mod.svg", GoIconProvider.class.getClassLoader());
    private static final Icon GO_SUM_ICON = IconLoader.getIcon("/icons/go_sum.svg", GoIconProvider.class.getClassLoader());

    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement element, @Iconable.IconFlags int flags) {
        if (element instanceof PsiFile) {
            PsiFile psiFile = (PsiFile) element;
            String fileName = psiFile.getName().toLowerCase();
            
            if (fileName.equals("go.mod")) {
                return GO_MOD_ICON;
            } else if (fileName.equals("go.sum")) {
                return GO_SUM_ICON;
            } else if (fileName.endsWith(".go")) {
                return GO_FILE_ICON;
            }
        }
        
        return null;
    }
}
