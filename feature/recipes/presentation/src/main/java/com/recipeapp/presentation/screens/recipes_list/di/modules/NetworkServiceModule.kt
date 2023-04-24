package com.recipeapp.presentation.screens.recipes_list.di.modules

import com.recipeapp.presentation.screens.recipes_list.di.RecipesListScreenScope
import com.recipes.data.remote.network.RecipesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkServiceModule {

    @RecipesListScreenScope
    @Provides
    fun provideRecipesService(retrofit: Retrofit): RecipesService =
        retrofit.create(RecipesService::class.java)
}