package com.amk.mylibrary.presentation.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0014\u0010\u000e\u001a\u00020\r*\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0014\u0010\u0010\u001a\u00020\r*\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/amk/mylibrary/presentation/adapter/ListCompaniesHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/amk/mylibrary/databinding/ItemCompanyBinding;", "(Lcom/amk/mylibrary/databinding/ItemCompanyBinding;)V", "bind", "", "company", "Lcom/amk/core/entity/Company;", "changePriceAndPercent", "", "formatePrice", "price", "", "calcChangePercent", "openPrice", "calcChangePrice", "list-companies_debug"})
public final class ListCompaniesHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    private final com.amk.mylibrary.databinding.ItemCompanyBinding binding = null;
    
    public ListCompaniesHolder(@org.jetbrains.annotations.NotNull()
    com.amk.mylibrary.databinding.ItemCompanyBinding binding) {
        super(null);
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    public final void bind(@org.jetbrains.annotations.NotNull()
    com.amk.core.entity.Company company) {
    }
    
    private final java.lang.String changePriceAndPercent(com.amk.core.entity.Company company) {
        return null;
    }
    
    private final double calcChangePercent(double $this$calcChangePercent, double openPrice) {
        return 0.0;
    }
    
    private final double calcChangePrice(double $this$calcChangePrice, double openPrice) {
        return 0.0;
    }
    
    private final java.lang.String formatePrice(double price) {
        return null;
    }
}