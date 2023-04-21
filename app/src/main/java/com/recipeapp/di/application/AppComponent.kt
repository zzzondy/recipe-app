package com.recipeapp.di.application

import android.content.Context
import com.recipeapp.di.navigation.NavigationComponent
import com.recipeapp.presentation.di.RecipesComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    val navigationComponentFactory: NavigationComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val recipesComponentFactory: RecipesComponent.Factory
}