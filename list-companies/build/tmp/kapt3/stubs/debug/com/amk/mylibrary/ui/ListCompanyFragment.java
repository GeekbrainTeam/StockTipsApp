package com.amk.mylibrary.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0002H\u0016J\u001a\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/amk/mylibrary/ui/ListCompanyFragment;", "Lcom/amk/core/ui/BaseFragment;", "Lcom/amk/mylibrary/databinding/FragmentListCompanyBinding;", "Lcom/amk/mylibrary/presentation/CompaniesListViewModel;", "()V", "coordinator", "Lcom/amk/core/navigation/AppNavigation;", "getCoordinator", "()Lcom/amk/core/navigation/AppNavigation;", "coordinator$delegate", "Lkotlin/Lazy;", "directionSort", "Lcom/amk/mylibrary/utils/Direction;", "typeSort", "Lcom/amk/mylibrary/utils/TypeSort;", "chooseSort", "", "getVModelClass", "Ljava/lang/Class;", "getViewBinding", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "list-companies_debug"})
public final class ListCompanyFragment extends com.amk.core.ui.BaseFragment<com.amk.mylibrary.databinding.FragmentListCompanyBinding, com.amk.mylibrary.presentation.CompaniesListViewModel> {
    private final kotlin.Lazy coordinator$delegate = null;
    private com.amk.mylibrary.utils.TypeSort typeSort;
    private com.amk.mylibrary.utils.Direction directionSort;
    
    public ListCompanyFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.amk.mylibrary.databinding.FragmentListCompanyBinding getViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.Class<com.amk.mylibrary.presentation.CompaniesListViewModel> getVModelClass() {
        return null;
    }
    
    private final com.amk.core.navigation.AppNavigation getCoordinator() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void chooseSort(com.amk.mylibrary.utils.Direction directionSort, com.amk.mylibrary.utils.TypeSort typeSort) {
    }
}