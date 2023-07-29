package com.recipe_adding.presentation.di.modules

import com.recipe_adding.data.remote.repository.RemoteRecipeAddingRepository
import com.recipe_adding.data.repository.RecipeAddingRepositoryImpl
import com.recipe_adding.domain.repository.RecipeAddingRepository
import com.recipe_adding.presentation.di.RecipeAddingComponentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
interface RepositoryBindingModule {

    @RecipeAddingComponentScope
    @Binds
    fun bindRecipeAddingRepositoryImplToRecipeAddingRepository(recipeAddingRepositoryImpl: RecipeAddingRepositoryImpl): RecipeAddingRepository
}

@Module
class RepositoryModule {

    @RecipeAddingComponentScope
    @Provides
    fun provideRecipeAddingRepositoryImpl(remoteRecipeAddingRepository: RemoteRecipeAddingRepository): RecipeAddingRepositoryImpl =
        RecipeAddingRepositoryImpl(remoteRecipeAddingRepository)
}