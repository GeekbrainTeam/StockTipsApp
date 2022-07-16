package com.amk.mylibrary.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\u0006\u0010\u001a\u001a\u00020\u0016J\u0006\u0010\u001b\u001a\u00020\u0016J\u0006\u0010\u001c\u001a\u00020\u0016J\u0006\u0010\u001d\u001a\u00020\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/amk/mylibrary/presentation/CompaniesListViewModel;", "Landroidx/lifecycle/ViewModel;", "Lorg/koin/core/component/KoinComponent;", "()V", "_companiesData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "companiesData", "getCompaniesData", "()Landroidx/lifecycle/MutableLiveData;", "companyList", "", "Lcom/amk/core/entity/Company;", "repository", "Lcom/amk/core/repository/RepositoryCompany;", "getRepository", "()Lcom/amk/core/repository/RepositoryCompany;", "repository$delegate", "Lkotlin/Lazy;", "sortingInteractorImpl", "Lcom/amk/core/interactors/SortingInteractorImpl;", "getSortedByChangePercent", "", "getSortedByChangePercentReverse", "getSortedByChangePrice", "getSortedByChangePriceReverse", "getSortedByName", "getSortedByNameReverse", "getSortedByPrice", "getSortedByPriceReverse", "list-companies_debug"})
public final class CompaniesListViewModel extends androidx.lifecycle.ViewModel implements org.koin.core.component.KoinComponent {
    private final kotlin.Lazy repository$delegate = null;
    private final java.util.List<com.amk.core.entity.Company> companyList = null;
    private com.amk.core.interactors.SortingInteractorImpl sortingInteractorImpl;
    private final androidx.lifecycle.MutableLiveData<com.amk.mylibrary.interactors.ListCompanyFragmentState> _companiesData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.amk.mylibrary.interactors.ListCompanyFragmentState> companiesData = null;
    
    public CompaniesListViewModel() {
        super();
    }
    
    private final com.amk.core.repository.RepositoryCompany getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.amk.mylibrary.interactors.ListCompanyFragmentState> getCompaniesData() {
        return null;
    }
    
    public final void getSortedByName() {
    }
    
    public final void getSortedByNameReverse() {
    }
    
    public final void getSortedByPrice() {
    }
    
    public final void getSortedByPriceReverse() {
    }
    
    public final void getSortedByChangePrice() {
    }
    
    public final void getSortedByChangePriceReverse() {
    }
    
    public final void getSortedByChangePercent() {
    }
    
    public final void getSortedByChangePercentReverse() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.koin.core.Koin getKoin() {
        return null;
    }
}