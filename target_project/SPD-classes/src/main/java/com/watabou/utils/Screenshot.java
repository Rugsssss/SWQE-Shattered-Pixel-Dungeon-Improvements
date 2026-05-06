package com.watabou.utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    public static void capture() {
        // 1. Grab the pixels from the current frame
        // ScreenUtils is a handy LibGDX tool for this
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());

        // 2. Create a unique filename using a timestamp
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        final String fileName = "screenshot_" + timeStamp + ".png";

        // 3. Start a new Thread to save the file
        // This ensures the game doesn't freeze!
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a new pixmap to hold the flipped version
                    Pixmap flipped = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat());

                    // Loop through the pixels and flip them vertically
                    for (int y = 0; y < pixmap.getHeight(); y++) {
                        flipped.drawPixmap(pixmap, 0, y, 0, pixmap.getHeight() - y - 1, pixmap.getWidth(), 1);
                    }

                    String path = "./" + fileName;
                    if (Gdx.app.getType() == com.badlogic.gdx.Application.ApplicationType.Desktop) {
                        PixmapIO.writePNG(Gdx.files.absolute(path), flipped);
                    } else {
                        PixmapIO.writePNG(Gdx.files.local(fileName), flipped);
                    }

                    // Clean up BOTH pixmaps
                    pixmap.dispose();
                    flipped.dispose();

                    Gdx.app.log("Screenshot", "Success! Fixed orientation saved to repo root.");
                } catch (Exception e) {
                    Gdx.app.error("Screenshot", "Failed to save!", e);
                }
            }
        }).start();
    }
}