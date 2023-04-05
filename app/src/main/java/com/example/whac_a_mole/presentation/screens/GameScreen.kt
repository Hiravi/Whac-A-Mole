import android.content.Context
import android.os.CountDownTimer
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.data.repository.GameRepositoryImpl
import com.example.whac_a_mole.domain.models.Hole
import com.example.whac_a_mole.domain.models.HoleState
import com.example.whac_a_mole.presentation.components.HoleGridItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen() {
    var isRunning = true

    val holes = remember { mutableStateListOf<Hole>() }
    holes.addAll(
        (1..9).map {

                when (it) {
                    1, 3 -> Hole(height = PaddingValues(top = 64.dp))
                    else -> Hole(height = PaddingValues(0.dp))
                }
            }
    )


    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp, horizontal = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                LazyVerticalStaggeredGrid(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    columns = StaggeredGridCells.Fixed(3),
                    content = {
                        items(holes.size) { index ->
                            HoleGridItem(
                                hole = holes[index],
                                onClick = {
                                    if (holes[index].state == HoleState.Mole) {
                                        val newHole =
                                            holes[index].copy(state = HoleState.KickedMole)
                                        holes[index] = newHole
                                    }
                                }
                            )
                        }
                    },
                )
            }

            val currentTime = remember {
                mutableStateOf(60)
            }

            LaunchedEffect(key1 = currentTime) {
                while (isRunning) {
                    if (currentTime.value > 0) {
                        delay(1000L)
                        currentTime.value -= 1
                    } else isRunning = false
                }
            }

            LaunchedEffect(holes) {
                while (isRunning) {
                    delay(Random.nextLong(200, 1000))
                    val holeNumber = Random.nextInt(0, 8)
                    val oldHole = holes[holeNumber]
                    val holeWithMole = oldHole.copy(state = HoleState.Mole)
                    holes[holeNumber] = holeWithMole
                    delay(Random.nextLong(500, 1000))
                    holes[holeNumber] = oldHole
                }
            }
        }
    }
}

fun moleJump(holes: List<Hole>) {
    (1..Random.nextInt(1, 4)).forEach {

    }
}

