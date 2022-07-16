package com.amk.mylibrary.interactors;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0013J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\u0016\u0010\u0019\u001a\u00020\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/amk/mylibrary/interactors/StatesCompanyListInteractor;", "", "binding", "Lcom/amk/mylibrary/databinding/FragmentListCompanyBinding;", "state", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "coordinator", "Lcom/amk/core/navigation/AppNavigation;", "(Lcom/amk/mylibrary/databinding/FragmentListCompanyBinding;Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;Lcom/amk/core/navigation/AppNavigation;)V", "fromBottomAnimation", "Landroid/view/animation/Animation;", "getFromBottomAnimation", "()Landroid/view/animation/Animation;", "fromBottomAnimation$delegate", "Lkotlin/Lazy;", "toBottomAnimation", "getToBottomAnimation", "toBottomAnimation$delegate", "error", "", "hide", "fab", "Lcom/google/android/material/floatingactionbutton/ExtendedFloatingActionButton;", "init", "loading", "setRecyclerView", "list", "", "Lcom/amk/core/entity/Company;", "show", "success", "list-companies_debug"})
public final class StatesCompanyListInteractor {
    private final com.amk.mylibrary.databinding.FragmentListCompanyBinding binding = null;
    private final com.amk.mylibrary.interactors.ListCompanyFragmentState state = null;
    private final com.amk.core.navigation.AppNavigation coordinator = null;
    private final kotlin.Lazy fromBottomAnimation$delegate = null;
    private final kotlin.Lazy toBottomAnimation$delegate = null;
    
    public StatesCompanyListInteractor(@org.jetbrains.annotations.NotNull()
    com.amk.mylibrary.databinding.FragmentListCompanyBinding binding, @org.jetbrains.annotations.NotNull()
    com.amk.mylibrary.interactors.ListCompanyFragmentState state, @org.jetbrains.annotations.NotNull()
    com.amk.core.navigation.AppNavigation coordinator) {
        super();
    }
    
    public final void init() {
    }
    
    private final void loading() {
    }
    
    private final void error() {
    }
    
    private final void success() {
    }
    
    private final void setRecyclerView(java.util.List<com.amk.core.entity.Company> list) {
    }
    
    private final void hide(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton fab) {
    }
    
    private final void show(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton fab) {
    }
    
    private final android.view.animation.Animation getFromBottomAnimation() {
        return null;
    }
    
    private final android.view.animation.Animation getToBottomAnimation() {
        return null;
    }
}