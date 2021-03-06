package work.addiel.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


public class MainActivity extends FragmentActivity {

    private static final  int NUM_PAGES = 5;

    private ViewPager  mPager;


    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);


        //animacion 1
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());


    }


    public void onBackPressed(){
        if (mPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }

        public Fragment getItem(int position){
            return new fragmentScreenSlidePage();
        }

        public int getCount(){
            return NUM_PAGES;
        }
    }


    public class ZoomOutPageTransformer implements ViewPager.PageTransformer{

    //animacion 1


        /*

        private static final float MIN_SCALE = 0.85f;
        private static  final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position){

            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if(position <  -1){
                view.setAlpha(0);
            }else if(position <= 1){
                float scaleFactor = Math.max(MIN_SCALE, 1-Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horMargin = pageWidth * (1 - scaleFactor) / 2;

                if(position < 0){
                    view.setTranslationX(horMargin - vertMargin / 2);
                }else{
                    view.setTranslationX(-horMargin + vertMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA) );


            }else{
                view.setAlpha(0);
            }
        }

    */






        //animacion 2

        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 0) {

                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) {

                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);

                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else {
                view.setAlpha(0);
            }


        }



    }



}

