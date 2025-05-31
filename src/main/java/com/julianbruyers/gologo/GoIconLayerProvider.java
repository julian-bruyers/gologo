package com.julianbruyers.gologo;

import com.intellij.ide.IconLayerProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Icon layer provider for additional Go icon customization.
 * This provider adds or modifies icon layers for Go-related files.
 */
public class GoIconLayerProvider implements IconLayerProvider, DumbAware {

    @Nullable
    @Override
    public Icon getLayerIcon(@NotNull Iconable element, boolean isLocked) {
        if (element instanceof PsiFile) {
            PsiFile psiFile = (PsiFile) element;
            String fileName = psiFile.getName().toLowerCase();
            
            // Add layer for go files to ensure they override default icons
            if (fileName.endsWith(".go") || fileName.equals("go.mod") || fileName.equals("go.sum")) {
                return null; // Return null to let the main providers handle it
            }
        }
        
        return null;
    }

    @NotNull
    @Override
    public String getLayerDescription() {
        return "Go Logo icon layer";
    }
}
