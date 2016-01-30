package com.example.bartosz.owocoweszalenstwo;

import android.opengl.GLES20;

import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.text.Text;

/**
 * Created by Bartosz on 2015-07-04.
 */
public class MainScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {
    protected static final int MENU_PLAY = 0;
    protected MenuScene mMenuScene;


    @Override
    public void createScene() {
        mMenuScene = createMenuScene();

        ParallaxBackground background = new ParallaxBackground(0, 0, 0);
        background.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0, new Sprite(0, 0, mResourceManager.mMenuTextureRegion, mVertexBufferObjectManager)));
        setBackground(background);


    }

    @Override
    public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
            case MENU_PLAY:

                mMenuScene.closeMenuScene();
                mSceneManager.setScene(SceneManager.SceneType.SCENE_GAME);
                return true;

            default:
                return false;
        }
    }

    protected MenuScene createMenuScene() {
        final MenuScene menuScene = new MenuScene(mCamera);
        menuScene.setPosition(500,300);

        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, mResourceManager.mMenuTextureRegion1, mVertexBufferObjectManager), 1.2f, 1);
        menuScene.addMenuItem(playMenuItem);


        //TODO implement
        menuScene.buildAnimations();

        menuScene.setBackgroundEnabled(false);
        playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 10);

        menuScene.setOnMenuItemClickListener(this);

        setChildScene(menuScene);

        menuScene.setOnMenuItemClickListener(this);
        return menuScene;
    }



    @Override
    public void onBackKeyPressed() {

            mActivity.finish();
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_MENU;
    }



}