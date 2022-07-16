package com.amk.mylibrary.interactors;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\f\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000eB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\f\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "", "()V", "Empty", "Failure", "Loading", "SortByChangePercent", "SortByChangePercentReverse", "SortByChangePrice", "SortByChangePriceReverse", "SortByName", "SortByNameReverse", "SortByPrice", "SortByPriceReverse", "Success", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Empty;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Failure;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Loading;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePercent;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePercentReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePrice;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePriceReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByName;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByNameReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByPrice;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByPriceReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Success;", "list-companies_debug"})
public abstract class ListCompanyFragmentState {
    
    private ListCompanyFragmentState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Loading;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "()V", "list-companies_debug"})
    public static final class Loading extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        public static final com.amk.mylibrary.interactors.ListCompanyFragmentState.Loading INSTANCE = null;
        
        private Loading() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Failure;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "msg", "", "(Ljava/lang/Throwable;)V", "getMsg", "()Ljava/lang/Throwable;", "list-companies_debug"})
    public static final class Failure extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.Throwable msg = null;
        
        public Failure(@org.jetbrains.annotations.NotNull()
        java.lang.Throwable msg) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Throwable getMsg() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Success;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class Success extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public Success(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByName;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByName extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByName(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByNameReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByNameReverse extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByNameReverse(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByPrice;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByPrice extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByPrice(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByPriceReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByPriceReverse extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByPriceReverse(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePrice;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByChangePrice extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByChangePrice(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePriceReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByChangePriceReverse extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByChangePriceReverse(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePercent;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByChangePercent extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByChangePercent(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$SortByChangePercentReverse;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "data", "", "Lcom/amk/core/entity/Company;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "list-companies_debug"})
    public static final class SortByChangePercentReverse extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.amk.core.entity.Company> data = null;
        
        public SortByChangePercentReverse(@org.jetbrains.annotations.NotNull()
        java.util.List<com.amk.core.entity.Company> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.amk.core.entity.Company> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/amk/mylibrary/interactors/ListCompanyFragmentState$Empty;", "Lcom/amk/mylibrary/interactors/ListCompanyFragmentState;", "()V", "list-companies_debug"})
    public static final class Empty extends com.amk.mylibrary.interactors.ListCompanyFragmentState {
        @org.jetbrains.annotations.NotNull()
        public static final com.amk.mylibrary.interactors.ListCompanyFragmentState.Empty INSTANCE = null;
        
        private Empty() {
            super();
        }
    }
}