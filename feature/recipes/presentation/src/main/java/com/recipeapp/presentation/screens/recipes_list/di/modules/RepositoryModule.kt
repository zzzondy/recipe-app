package com.recipeapp.presentation.screens.recipes_list.di.modules

import com.recipeapp.presentation.screens.recipes_list.di.RecipesListScreenScope
import com.recipes.data.remote.repository.RemoteRecipesRepository
import com.recipes.data.repository.RecipesRepositoryImpl
import com.recipes.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
interface RepositoryBindingModule {

    @RecipesListScreenScope
    @Binds
    fun bindRecipesRepositoryImplToRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository
}

@Module
class RepositoryModule {

    @RecipesListScreenScope
    @Provides
    fun provideRecipesRepositoryImpl(remoteRecipesRepository: RemoteRecipesRepository): RecipesRepositoryImpl =
        RecipesRepositoryImpl(remoteRecipesRepository)
}