package com.recipeapp.presentation.screens.recipes_list.di.modules

import com.recipeapp.presentation.screens.recipes_list.di.RecipesListScreenScope
import com.recipes.data.remote.network.RecipesService
import com.recipes.data.remote.repository.RemoteRecipesRepository
import com.recipes.data.remote.repository.RemoteRecipesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [RemoteModule::class])
interface RemoteBindingModule {

    @RecipesListScreenScope
    @Binds
    fun bindRemoteRecipesRepositoryToRecipesRepository(remoteRecipesRepositoryImpl: RemoteRecipesRepositoryImpl): RemoteRecipesRepository
}

@Module
class RemoteModule {

    @RecipesListScreenScope
    @Provides
    fun provideRemoteRecipesRepository(recipesService: RecipesService): RemoteRecipesRepositoryImpl =
        RemoteRecipesRepositoryImpl(recipesService)
}