package com.amk.mylibrary.presentation.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/amk/mylibrary/presentation/adapter/ListCompaniesAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amk/mylibrary/presentation/adapter/ListCompaniesHolder;", "list", "", "Lcom/amk/core/entity/Company;", "onClickListener", "Lcom/amk/mylibrary/presentation/adapter/ListCompaniesAdapter$OnStateClickListener;", "(Ljava/util/List;Lcom/amk/mylibrary/presentation/adapter/ListCompaniesAdapter$OnStateClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "OnStateClickListener", "list-companies_debug"})
public final class ListCompaniesAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.amk.mylibrary.presentation.adapter.ListCompaniesHolder> {
    private final java.util.List<com.amk.core.entity.Company> list = null;
    private final com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter.OnStateClickListener onClickListener = null;
    
    public ListCompaniesAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.amk.core.entity.Company> list, @org.jetbrains.annotations.NotNull()
    com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter.OnStateClickListener onClickListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.amk.mylibrary.presentation.adapter.ListCompaniesHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.amk.mylibrary.presentation.adapter.ListCompaniesHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/presentation/adapter/ListCompaniesAdapter$OnStateClickListener;", "", "onStateClick", "", "company", "Lcom/amk/core/entity/Company;", "position", "", "list-companies_debug"})
    public static abstract interface OnStateClickListener {
        
        public abstract void onStateClick(@org.jetbrains.annotations.NotNull()
        com.amk.core.entity.Company company, int position);
    }
}