package com.recipeapp.presentation.screens.recipes_list.di

import com.recipeapp.presentation.screens.recipes_list.RecipesListScreenViewModel
import com.recipeapp.presentation.screens.recipes_list.di.modules.NetworkServiceModule
import com.recipeapp.presentation.screens.recipes_list.di.modules.RemoteBindingModule
import com.recipeapp.presentation.screens.recipes_list.di.modules.RepositoryBindingModule
import com.recipeapp.presentation.screens.recipes_list.di.modules.ScreenModule
import com.recipeapp.presentation.screens.recipes_list.di.modules.UseCaseModule
import dagger.Subcomponent

@RecipesListScreenScope
@Subcomponent(
    modules = [
        NetworkServiceModule::class, RemoteBindingModule::class,
        RepositoryBindingModule::class, UseCaseModule::class,
        ScreenModule::class
    ]
)
interface RecipesListScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RecipesListScreenComponent
    }

    val recipesListScreenViewModel: RecipesListScreenViewModel
}