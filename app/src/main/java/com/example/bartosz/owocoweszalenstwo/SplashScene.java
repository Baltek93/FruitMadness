package com.example.bartosz.owocoweszalenstwo;

import org.andengine.engine.camera.Camera;


import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.util.HorizontalAlign;
import org.w3c.dom.Text;

/**
 * Created by Bartosz on 2015-07-04.
 */
public class SplashScene extends BaseScene {

    @Override
    public void createScene() {

        Sprite splash = new Sprite(0, 0, mResourceManager.mSplashTextureRegion, mVertexBufferObjectManager) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        attachChild(splash);

    }

    @Override
    public void onBackKeyPressed() {
        mActivity.finish();
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_SPLASH;
    }



}