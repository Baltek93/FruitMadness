package com.example.bartosz.owocoweszalenstwo;

import android.util.Log;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import java.util.Random;
import org.andengine.entity.text.Text;
/**
 * Created by Bartosz on 2015-07-05.
 */
public class GameScene extends BaseScene {
    private Sprite[] sprites;
    private int czas = 1;
    protected int pkt = 0;
    private int[] czasy ;
    private int[] pkts ;
    private Random random ;
    private Text scoreText,timeText;
    private int czasGry=60;
    private int[] tablicapowtorzen;

    @Override

    public void createScene() {
        mResourceManager.pkt=0;
        czas=1;
tablicapowtorzen=new int[10];
       for(int x:tablicapowtorzen)
               x=-2;
        /*

        nadanie tła
         */
        ParallaxBackground background = new ParallaxBackground(0, 0, 0);
        background.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0, new Sprite(0, 0, mResourceManager.gameTextureRegion[7], mVertexBufferObjectManager)));
        setBackground(background);
        scoreText = new Text(1500, 900, mResourceManager.czcionkaWyniku, "Wynik:0"  ,30, mVertexBufferObjectManager );
        timeText = new Text(100, 900, mResourceManager.czcionkaWyniku, "Czas:60"  ,30, mVertexBufferObjectManager );

        attachChild(scoreText);
        attachChild(timeText);

        sprites= new Sprite[10];
        pkts= new int[7];
        random= new Random();
        czasy= new int[7];
        czasy[5]= random.nextInt(5)+25;
        Log.d("polozenie" , String.valueOf(czasy[5]));
        for(Sprite x: sprites)
            x=null;
        TablicaPKT();
        /*
        tworzymy koszyk ktory sie porusza
         */
        final Sprite koszykSprite = new Sprite(850, 800, mResourceManager.gameTextureRegion[8], mVertexBufferObjectManager) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

                this.setX(pSceneTouchEvent.getX() - this.getWidth() / 2);

                return true;
            }
        };
/*
dodajemy mu mozliwosc poruszania sie
 */
        attachChild(koszykSprite);
        registerTouchArea(koszykSprite);
        setTouchAreaBindingOnActionDownEnabled(true);
/*
zmienna ktora tworzy nam obiekty sprite
 */
        TimerHandler myTimer = new TimerHandler(2f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                czas++;
                if(czas>=30)
                    czas=1;
                tworzenieobiektow();


            }
        });

        TimerHandler myTimer2 = new TimerHandler(0.01f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {

                for (int i = 0; i < 10; i++) {
                    if (sprites[i] != null && sprites[i].getY() >= 1080) {
                        detachChild(sprites[i]);
                        sprites[i] = null;


                    }
                    if (sprites[i] != null && sprites[i].getY() >= koszykSprite.getY() - 100 && sprites[i].getY() <= koszykSprite.getY() + 100 && sprites[i].getX() <= koszykSprite.getX() + 450 && sprites[i].getX() >= koszykSprite.getX() - 100) {

                        AddPoint(sprites[i]);
                        scoreText.setText("Wynik:"+mResourceManager.pkt);
                        Log.d("Wynik", String.valueOf(mResourceManager.pkt));


                        detachChild(sprites[i]);
                        sprites[i] = null;
                    }
                }
        for (int i=0;i<10;i++)
        {
            if(sprites[i]!=null)
            {
                sprites[i].setY(sprites[i].getY() + 2*i+3);
                sprites[i].setRotation(sprites[i].getRotation() + 1);

            }
}




            }
        });
        final TimerHandler TimerGry= new TimerHandler(1f,true,new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                czasGry--;

                timeText.setText("Czas:"+czasGry);
                if(czasGry==0){
                    mSceneManager.setScene(SceneManager.SceneType.SCENE_END);

                }
            }
        });
        registerUpdateHandler(TimerGry);
        registerUpdateHandler(myTimer);
        registerUpdateHandler(myTimer2);
    }
/*
tworzenie obiektow ktore "spadaja"
 */
    private void tworzenieobiektow() {


        przedzialczasowy(); // losujemy co ile maja byc pokazywane owoce
        for (int i = 0; i < 7; i++)
            if (czas % czasy[i]  == 0) // np w 5 10 15 20 sekundzie pojawia sie jablko
            {
                for (int j = 0; j < 10; j++) {
                    if (sprites[j] == null) {

                        tablicapowtorzen[j]=random.nextInt(12);
                        for(int k=0;k<10;k++)
                            while(tablicapowtorzen[j]==tablicapowtorzen[k] && j!=k)
                                tablicapowtorzen[j]=random.nextInt(12);

                        sprites[j] = new Sprite(tablicapowtorzen[j] * 128 + 50, -50, mResourceManager.gameTextureRegion[i], mVertexBufferObjectManager);

                        attachChild(sprites[j]);
                        break;

                    }
                }
            }

    }
/*
W jakim przedziale maja spadac
 */
    private void przedzialczasowy() {
        czasy[0] = random.nextInt(7) + 1; //losowanie jablka 1-7
        czasy[1] = random.nextInt(10) + 1; //losowanie sliwki 1-10
        czasy[2] = random.nextInt(10) + 1;//gruszka 1-10
        czasy[3] = random.nextInt(15) + 1;//mrowka 1-15
        czasy[4] = random.nextInt(15) + 1;//robak 1-15

        czasy[6] = random.nextInt(15) + 1;//biedronka 1-15
    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_GAME;

    }



    private void AddPoint(Sprite sprite)
    {
        for (int i = 0; i < 6; i++)
            if (sprite.getTextureRegion().equals(mResourceManager.gameTextureRegion[i]))
                mResourceManager.pkt += pkts[i];
        if(sprite.getTextureRegion().equals(mResourceManager.gameTextureRegion[6]))
        {
            czasGry+=pkts[6];
            timeText.setText("Czas:"+czasGry);
        }
    }
    private void TablicaPKT()
    {
        for(int i=0;i<3;i++)
            pkts[i]=i+1;
        pkts[3]=-1;
        pkts[4]=-2;
        pkts[5]=-3;
        pkts[6]=10;
    }
}
