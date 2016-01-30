package com.example.bartosz.owocoweszalenstwo;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;

/**
 * Klasa ktora pozwala nam na przejscie miedzy poszczegolnymi scenami
 */
public class SceneManager {
    private static final SceneManager INSTANCE = new SceneManager();

    public enum SceneType {SCENE_SPLASH, SCENE_MENU, SCENE_GAME, SCENE_END}

    private BaseScene mSplashScene;
    private BaseScene mMenuScene;
    private BaseScene mGameScene;
    private BaseScene mEndScene;

    private SceneType mCurrentSceneType;
    private BaseScene mCurrentScene;

    private SceneManager() {}

    public static SceneManager getInstance() {
        return INSTANCE;
    }

    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_MENU:
                setScene(createMenuScene());
                break;
            case SCENE_GAME:
                setScene(createGameScene());
                break;
            case SCENE_SPLASH:
                setScene(createSplashScene());
                break;
            case SCENE_END:
                setScene(createEndScene());
        }
    }

    private void setScene(BaseScene scene) {
        ResourceManager.getInstance().mActivity.getEngine().setScene(scene);
        mCurrentScene = scene;
        mCurrentSceneType = scene.getSceneType();

    }



    public BaseScene getCurrentScene() {
        return mCurrentScene;
    }

    public BaseScene createSplashScene() {
        mSplashScene= new SplashScene();
        //TODO implement
        return mSplashScene;
    }

    private BaseScene createMenuScene() {
        mMenuScene= new MainScene();
        //TODO implement
        return mMenuScene;
    }

    private BaseScene createGameScene() {
        mGameScene= new GameScene();
        //TODO implement
        return mGameScene;
    }
    private BaseScene createEndScene() {
        mEndScene= new EndScene();
        //TODO implement
        return mEndScene;
    }

}

