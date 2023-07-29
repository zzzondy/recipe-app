package com.recipe_adding.domain.use_cases

class ValidateQuantityUseCase {

    operator fun invoke(quantity: String): Boolean = regex.matches(quantity)

    companion object {
        private val regex = Regex("[0-9]{1,4}[a-zA-Zа-яА-Я]{1,2}")
        const val lengthLimit = 6
    }
}