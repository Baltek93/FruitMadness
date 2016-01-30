package com.example.bartosz.owocoweszalenstwo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

/**
 * Created by Bartosz on 2015-07-05.
 */
public class EndScene extends BaseScene implements  MenuScene.IOnMenuItemClickListener {
    protected static final int MENU_REPLAY = 0;
    protected static final int MENU_QUIT = 1;
    private Text scoreText,highscoreText;
    private MenuScene mMenuScene;
    SharedPreferences setting ;
    int highscore;

    @Override
    public void createScene() {

        ParallaxBackground background = new ParallaxBackground(0, 0, 0);
        background.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0, new Sprite(0, 0, mResourceManager.gameTextureRegion[7], mVertexBufferObjectManager)));
        setBackground(background);

        setting= mActivity.getPreferences(mActivity.MODE_PRIVATE);
        highscore=setting.getInt("highscore",0);
        if(highscore<mResourceManager.pkt)
        {
            SharedPreferences.Editor editor= setting.edit();
            editor.putInt("highscore", mResourceManager.pkt);
            editor.commit();
            highscore=setting.getInt("highscore",0);


        }
        highscoreText = new Text(mResourceManager.mActivity.CAMERA_WIDTH/3, mResourceManager.mActivity.CAMERA_HEIGHT/3-100, mResourceManager.czcionkaWyniku, "Najlepszy wynik :"+highscore  ,30, mVertexBufferObjectManager );
        scoreText = new Text(mResourceManager.mActivity.CAMERA_WIDTH/3, mResourceManager.mActivity.CAMERA_HEIGHT/3, mResourceManager.czcionkaWyniku, "Twój wynik :"+mResourceManager.pkt  ,30, mVertexBufferObjectManager );
        attachChild(scoreText);
        attachChild(highscoreText);
        mMenuScene = createMenuScene();
        Log.d("Wynik", String.valueOf(mResourceManager.pkt));

    }


    @Override
    public void onBackKeyPressed() {
        mActivity.finish();
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_END;
    }


    private MenuScene createMenuScene() {
        final MenuScene menuScene = new MenuScene(mCamera);
        menuScene.setPosition(500, 300);
        final IMenuItem replayMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_REPLAY, mResourceManager.mButtonTextureRegion1, mVertexBufferObjectManager), 1.2f, 1);
        menuScene.addMenuItem(replayMenuItem);
        final IMenuItem quitMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_QUIT, mResourceManager.mButtonTextureRegion, mVertexBufferObjectManager), 1.2f, 1);
        menuScene.addMenuItem(quitMenuItem);
        menuScene.buildAnimations();

        menuScene.setBackgroundEnabled(false);
        replayMenuItem.setPosition(-300, replayMenuItem.getY());
        quitMenuItem.setPosition(700, replayMenuItem.getY() );
        menuScene.setOnMenuItemClickListener(this);

        setChildScene(menuScene);

        menuScene.setOnMenuItemClickListener(this);
        return menuScene;
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
            case MENU_REPLAY:

                mMenuScene.closeMenuScene();
                mSceneManager.setScene(SceneManager.SceneType.SCENE_GAME);
                return true;
            case MENU_QUIT:
                mActivity.finish();
            default:
                return false;
        }
    }
}
