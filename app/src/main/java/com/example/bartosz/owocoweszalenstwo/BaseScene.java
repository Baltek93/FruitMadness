package com.example.bartosz.owocoweszalenstwo;

import org.andengine.engine.camera.Camera;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Bartosz on 2015-07-04.
 */
public abstract class BaseScene extends Scene {

    protected final int SCREEN_WIDTH = GameActivity.CAMERA_WIDTH;
    protected final int SCREEN_HEIGHT = GameActivity.CAMERA_HEIGHT;
    protected int pkt=0;
    protected GameActivity mActivity;
    protected Engine mEngine;
    protected Camera mCamera;
    protected VertexBufferObjectManager mVertexBufferObjectManager;
    protected ResourceManager mResourceManager;
    protected SceneManager mSceneManager;

    public BaseScene() {
        mResourceManager = ResourceManager.getInstance();
        mActivity = mResourceManager.mActivity;
        mVertexBufferObjectManager = mActivity.getVertexBufferObjectManager();
        mEngine = mActivity.getEngine();
        mCamera = mEngine.getCamera();
        mSceneManager = SceneManager.getInstance();
        createScene();
    }

    public abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract SceneManager.SceneType getSceneType();

}
