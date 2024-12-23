import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin

actual fun getPlatformName(): String = "iOS"

@Suppress("unused")
fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}
