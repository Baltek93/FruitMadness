package com.example.bartosz.owocoweszalenstwo;

import android.hardware.Camera;

import org.andengine.engine.Engine;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 *klasa do przechowywania wartosci danych ktore sa umieszczane w grze
 */
public class ResourceManager {
    private static final ResourceManager INSTANCE = new ResourceManager();
    private BitmapTextureAtlas mSplashTextureAtlas;
    public ITextureRegion mSplashTextureRegion;
    public GameActivity mActivity;

    private BitmapTextureAtlas mMenuTextureAtlas;
    public ITextureRegion mMenuTextureRegion;
    public ITextureRegion mMenuTextureRegion1;
    public ITextureRegion[] gameTextureRegion= new ITextureRegion[9];
    private BitmapTextureAtlas[] gameBitmapAtlas= new BitmapTextureAtlas[9];
    private BitmapTextureAtlas mMenuTextureAtlas1;
    public Font czcionkaWyniku,czcionkaCzasu;
    public int pkt=0;
    private BitmapTextureAtlas mButtonTextureAtlas,mButtonTextureAtlas1;
    public ITextureRegion mButtonTextureRegion,mButtonTextureRegion1;


    private ResourceManager() {}

    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    public void prepare(GameActivity activity) {
        INSTANCE.mActivity = activity;

    }
/*
wczytujemy dane ekranu wejsciowego
 */
    public void loadSplashResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("splash/");
        mSplashTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
        mSplashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mSplashTextureAtlas, mActivity, "ekranpowitalny.png", 0, 0);
        mSplashTextureAtlas.load();


    }



    public void unloadSplashResources() {
        mSplashTextureAtlas.unload();
        mSplashTextureRegion = null;

    }

    public void loadGameResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("menu/");
        mMenuTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(),2048, 2048, TextureOptions.BILINEAR);
        mMenuTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, mActivity, "ekran_startowy.png", 0, 0);
        mMenuTextureAtlas.load();
        mMenuTextureAtlas1 = new BitmapTextureAtlas(mActivity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        mMenuTextureRegion1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas1, mActivity, "przycisk_rozpocznij_gre.png", 0, 0);
        mMenuTextureAtlas1.load();
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("ending/");
        mButtonTextureAtlas= new BitmapTextureAtlas(mActivity.getTextureManager(),512,512, TextureOptions.BILINEAR);
        mButtonTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mButtonTextureAtlas,mActivity,"zakoncz.png",0,0);
        mButtonTextureAtlas.load();
        mButtonTextureAtlas1= new BitmapTextureAtlas(mActivity.getTextureManager(),512,512, TextureOptions.BILINEAR);
        mButtonTextureRegion1= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mButtonTextureAtlas1,mActivity,"ponownie.png",0,0);
        mButtonTextureAtlas1.load();

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("game/");

        for(int i=0;i<6;i++)
        {
            gameBitmapAtlas[i] = new BitmapTextureAtlas(mActivity.getTextureManager(),128, 128, TextureOptions.BILINEAR);
            gameTextureRegion[i] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBitmapAtlas[i], mActivity, String.valueOf(i)+".png", 0, 0);

        }
        gameBitmapAtlas[6] = new BitmapTextureAtlas(mActivity.getTextureManager(),256, 256, TextureOptions.BILINEAR);
        gameTextureRegion[6] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBitmapAtlas[6], mActivity, "zycie.png", 0, 0);

        gameBitmapAtlas[7] = new BitmapTextureAtlas(mActivity.getTextureManager(),2048, 2048, TextureOptions.BILINEAR);
        gameTextureRegion[7] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBitmapAtlas[7], mActivity, "ekran_gry.png", 0, 0);
        gameBitmapAtlas[8] = new BitmapTextureAtlas(mActivity.getTextureManager(),512, 256, TextureOptions.BILINEAR);
        gameTextureRegion[8] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBitmapAtlas[8], mActivity, "koszyk.png", 0, 0);
        for(BitmapTextureAtlas x:gameBitmapAtlas)
            x.load();
        final ITexture TexturaCzasu = new BitmapTextureAtlas(mActivity.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        final ITexture TexturaWyniku = new BitmapTextureAtlas(mActivity.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        czcionkaWyniku = FontFactory.createFromAsset(mActivity.getFontManager(), TexturaWyniku, mActivity.getAssets(), "font/arialbd.ttf", 80, true, 0xFFc07611 );
        czcionkaWyniku.load();
        czcionkaCzasu = FontFactory.createFromAsset(mActivity.getFontManager(), TexturaCzasu, mActivity.getAssets(), "font/arialbd.ttf", 80, true, 0xFFc07611 );
        czcionkaCzasu.load();


    }

}
