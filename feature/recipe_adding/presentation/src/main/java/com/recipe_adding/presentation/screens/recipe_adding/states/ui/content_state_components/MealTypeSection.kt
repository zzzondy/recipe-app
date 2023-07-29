import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.recipe_adding.presentation.R
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.UIText
import com.recipeapp.utils.bounceClick

@Composable
internal fun MealTypeSection(
    selectedMealTypeName: UIText,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isError: Boolean = false,
) {
    val paddings = RecipeAppTheme.paddings

    val watchIconId = "watch_icon"
    val textId = "text"
    val timeTextId = "time"
    val arrowForwardIconId = "arrow_forward_icon"


    ConstraintLayout(
        modifier = modifier
            .bounceClick(onClick = onClick)
            .background(
                color = RecipeAppTheme.colors.neutral10,
                shape = RecipeAppTheme.shapes.medium
            ),
        constraintSet = ConstraintSet {
            val watchIcon = createRefFor(watchIconId)
            val text = createRefFor(textId)
            val arrowForward = createRefFor(arrowForwardIconId)
            val timeRef = createRefFor(timeTextId)

            constrain(watchIcon) {
                start.linkTo(parent.start, paddings.small)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }

            constrain(text) {
                start.linkTo(watchIcon.end, paddings.small)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }

            constrain(timeRef) {
                end.linkTo(arrowForward.start, paddings.small)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }

            constrain(arrowForward) {
                end.linkTo(parent.end, paddings.small)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .layoutId(watchIconId)
                .background(
                    color = RecipeAppTheme.colors.white0,
                    shape = RecipeAppTheme.shapes.default
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.food_beverage),
                contentDescription = stringResource(R.string.watch_icon),
                tint = RecipeAppTheme.colors.primary50,
                modifier = Modifier.padding(RecipeAppTheme.paddings.extraSmall)
            )
        }

        Text(
            text = stringResource(R.string.meal_type),
            style = RecipeAppTheme.typography.boldP,
            color = RecipeAppTheme.colors.neutral100,
            modifier = Modifier.layoutId(textId),
        )

        Text(
            text = selectedMealTypeName.asString(),
            style = RecipeAppTheme.typography.regularLabel,
            color = if (isError) RecipeAppTheme.colors.error100 else RecipeAppTheme.colors.neutral40,
            modifier = Modifier.layoutId(timeTextId),
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = stringResource(R.string.arrow_back_icon),
            modifier = Modifier.layoutId(arrowForwardIconId),
            tint = RecipeAppTheme.colors.neutral100,
        )
    }
}