package com.example.dots365

import android.graphics.Canvas
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

class DotLiveWallpaperService : WallpaperService() {

    override fun onCreateEngine(): Engine {
        return DotEngine()
    }

    inner class DotEngine : Engine() {

        private val handler = Handler()
        private var visible = false

        private val drawRunnable = object : Runnable {
            override fun run() {
                draw()
            }
        }

        override fun onVisibilityChanged(visible: Boolean) {
            this.visible = visible
            if (visible) {
                draw()
            } else {
                handler.removeCallbacks(drawRunnable)
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            visible = false
            handler.removeCallbacks(drawRunnable)
        }

        private fun draw() {
            if (!visible) return

            val holder = surfaceHolder ?: return
            val canvas = try {
                holder.lockCanvas()
            } catch (e: Exception) {
                return
            } ?: return

            try {
                val bitmap = DotRenderer.render(
                    applicationContext,
                    canvas.width,
                    canvas.height
                )
                canvas.drawBitmap(bitmap, 0f, 0f, null)
            } finally {
                holder.unlockCanvasAndPost(canvas)
            }

            handler.removeCallbacks(drawRunnable)
            handler.postDelayed(drawRunnable, 60 * 60 * 1000)
        }

    }
}
