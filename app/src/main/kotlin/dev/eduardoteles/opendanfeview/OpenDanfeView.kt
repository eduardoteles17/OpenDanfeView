package dev.eduardoteles.opendanfeview

import br.com.swconsultoria.impressao.service.ImpressaoService
import br.com.swconsultoria.impressao.util.ImpressaoUtil
import com.formdev.flatlaf.FlatIntelliJLaf
import com.formdev.flatlaf.util.SystemInfo
import dev.eduardoteles.opendanfeview.pdfviewer.PdfViewerWebServer
import dev.eduardoteles.opendanfeview.ui.Browser
import dev.eduardoteles.opendanfeview.ui.MenuBar
import dev.eduardoteles.opendanfeview.ui.SplashScreen
import dev.eduardoteles.opendanfeview.ui.TabbedPane
import dev.eduardoteles.opendanfeview.utils.AppFolderUtils
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.friwi.jcefmaven.CefAppBuilder
import org.cef.CefApp
import org.cef.CefClient
import org.cef.browser.CefMessageRouter
import java.awt.BorderLayout
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.*
import kotlin.system.exitProcess


class OpenDanfeView(
    val cefApp: CefApp
) : JFrame("OpenDanfeView") {
    private var client: CefClient? = null

    private val tabsPanel = TabbedPane().apply {
        tabLayoutPolicy = JTabbedPane.SCROLL_TAB_LAYOUT
    }

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 600)
        jMenuBar = MenuBar(::openDanfe)

        contentPane = creatContentPanel()
    }

    private fun creatContentPanel(): JPanel {
        client = cefApp.createClient()

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                cefApp.dispose()
                dispose()
            }
        })

        val msgRouter = CefMessageRouter.create()
        client!!.addMessageRouter(msgRouter)

        val contentPanel = JPanel(BorderLayout())
        contentPanel.add(tabsPanel, BorderLayout.CENTER)

        return contentPanel
    }

    private fun openDanfe() {
        val fileChooser = JFileChooser().apply {
            fileSelectionMode = JFileChooser.FILES_ONLY
            fileFilter = javax.swing.filechooser.FileNameExtensionFilter("Arquivos XML", "xml")
        }

        val result = fileChooser.showOpenDialog(this)

        if (result == JFileChooser.APPROVE_OPTION) {
            val file = fileChooser.selectedFile

            val xml = ImpressaoUtil.leArquivo(file.absolutePath)

            val impressao = ImpressaoUtil.impressaoPadraoNFe(xml)

            ImpressaoService.impressaoPdfArquivo(impressao, "/tmp/danfe.pdf")
            tabsPanel.addTab(file.name, client?.let { Browser(it, "file:///tmp/danfe.pdf").browserUI })
//            tabsPanel.addTab(file.name, client?.let { Browser(it,"https://youtube.com").browserUI })
        }
    }

    companion object {
        fun start(app: CefApp): OpenDanfeView {
            val frame = OpenDanfeView(app)
            frame.isVisible = true
            return frame
        }
    }
}

fun main() {
    val splashScreen = SplashScreen()

    val cefDir = AppFolderUtils.getCefDirectory()

    val builder = CefAppBuilder()

    builder.setInstallDir(File(cefDir))
    builder.cefSettings.windowless_rendering_enabled = false
    builder.cefSettings.cache_path = AppFolderUtils.getCefCacheDirectory()

    builder.setAppHandler(object : me.friwi.jcefmaven.MavenCefAppHandlerAdapter() {
        override fun stateHasChanged(state: CefApp.CefAppState) {
            // Shutdown the app if the native CEF part is terminated
            if (state == CefApp.CefAppState.TERMINATED) exitProcess(0)
        }
    })

    val app = builder.build()

    val server = PdfViewerWebServer.instance

    runBlocking {
        val serverJob = launch {
            server.start()
        }
        Runtime.getRuntime().addShutdownHook(Thread {
            server.stop()
            runBlocking {
                serverJob.join()
            }
        })

        SwingUtilities.invokeLater {
            if (SystemInfo.isLinux || SystemInfo.isWindows) {
                JFrame.setDefaultLookAndFeelDecorated(true)
                JDialog.setDefaultLookAndFeelDecorated(true)
            }
            FlatIntelliJLaf.setup()

            splashScreen.close()
            OpenDanfeView.start(app)
        }
    }
}
