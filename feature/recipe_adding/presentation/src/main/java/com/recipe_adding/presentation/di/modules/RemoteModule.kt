package com.recipe_adding.presentation.di.modules

import com.recipe_adding.data.remote.network.RecipeAddingService
import com.recipe_adding.data.remote.repository.RemoteRecipeAddingRepository
import com.recipe_adding.data.remote.repository.RemoteRecipeAddingRepositoryImpl
import com.recipe_adding.presentation.di.RecipeAddingComponentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteModule::class])
interface RemoteBindingModule {

    @RecipeAddingComponentScope
    @Binds
    fun bindRemoteRecipeAddingRepositoryImplToRemoteRecipeAddingRepository(
        remoteRecipeAddingRepositoryImpl: RemoteRecipeAddingRepositoryImpl
    ): RemoteRecipeAddingRepository
}

@Module
class RemoteModule {


    @RecipeAddingComponentScope
    @Provides
    fun provideRemoteRecipeAddingRepositoryImpl(recipeAddingService: RecipeAddingService): RemoteRecipeAddingRepositoryImpl =
        RemoteRecipeAddingRepositoryImpl(recipeAddingService)
}