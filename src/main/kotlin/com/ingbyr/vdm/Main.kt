package com.ingbyr.vdm

import com.ingbyr.vdm.utils.AppConfigUtils
import com.ingbyr.vdm.utils.AppProperties
import com.ingbyr.vdm.views.MainView
import javafx.application.Application
import javafx.scene.image.Image
import org.slf4j.LoggerFactory
import tornadofx.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

class Main : App(MainView::class) {
    private val logger = LoggerFactory.getLogger(Main::class.java)
    override val configPath: Path = AppConfigUtils.configFilePath
    private val availableLanguages = listOf("zh", "en", "hu")

    init {
         Locale.setDefault(Locale("en", ""))
        val language = Locale.getDefault().language
        if (language !in availableLanguages) {
            Locale.setDefault(Locale("en", "US"))
        }
        val prop = System.getProperties()
        logger.debug("OS: ${prop["os.name"]?.toString()} Arch: ${prop["os.arch"]?.toString()} Version: ${prop["os.version"]?.toString()}")
        logger.debug("JAVA: ${prop["java.version"]?.toString()} Vendor: ${prop["java.vendor"]?.toString()}")
        logger.debug("Default Locale: ${FX.locale} Current Locale:${Locale.getDefault().language}_${Locale.getDefault().country}")
        logger.debug("Save config file to $configPath")

        // create .vdm dir
        Files.createDirectories(AppProperties.USER_DIR)

        addStageIcon(Image("/imgs/logo.jpg"))
    }
}


fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}