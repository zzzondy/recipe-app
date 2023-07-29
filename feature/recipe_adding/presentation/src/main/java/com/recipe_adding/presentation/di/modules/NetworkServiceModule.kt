package com.recipe_adding.presentation.di.modules

import com.recipe_adding.data.remote.network.RecipeAddingService
import com.recipe_adding.presentation.di.RecipeAddingComponentScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkServiceModule {

    @RecipeAddingComponentScope
    @Provides
    fun provideRecipeAddingService(retrofit: Retrofit): RecipeAddingService =
        retrofit.create(RecipeAddingService::class.java)
}