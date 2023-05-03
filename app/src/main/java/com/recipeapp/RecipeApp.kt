package com.recipeapp

import android.app.Application
import android.content.Context
import com.recipe_adding.presentation.di.RecipeAddingComponent
import com.recipe_adding.presentation.di.RecipeAddingComponentProvider
import com.recipeapp.di.application.AppComponent
import com.recipeapp.di.application.DaggerAppComponent
import com.recipeapp.presentation.di.RecipesComponent
import com.recipeapp.presentation.di.RecipesComponentProvider

class RecipeApp : Application(), RecipesComponentProvider, RecipeAddingComponentProvider {

    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent didn't initialize"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(this)
    }


    override fun provideRecipeComponent(): RecipesComponent =
        appComponent.recipesComponentFactory.create()

    override fun provideRecipeAddingComponent(): RecipeAddingComponent =
        appComponent.recipeAddingComponentFactory.create()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is RecipeApp -> appComponent
        else -> applicationContext.appComponent
    }