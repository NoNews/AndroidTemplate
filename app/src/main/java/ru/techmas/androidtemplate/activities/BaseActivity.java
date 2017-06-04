package ru.techmas.androidtemplate.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import ru.techmas.androidtemplate.App;
import ru.techmas.androidtemplate.R;
import ru.techmas.androidtemplate.interfaces.utils_view.NavigatorActivityView;
import ru.techmas.androidtemplate.utils.AnimationHelper;
import ru.techmas.androidtemplate.utils.KeyboardHelper;
import ru.techmas.androidtemplate.utils.Navigator;

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements NavigatorActivityView {

    protected final String TAG = getClass().getSimpleName();


    @Inject
    protected AnimationHelper animationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getViewComponent().inject(this);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    protected final void hideKeyboard() {
        KeyboardHelper.hide(this);
    }

    @SuppressWarnings("unchecked")
    protected final <T extends View> T bindView(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected final void hideView(@NonNull View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    protected final void showView(@NonNull View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public final void startActivity(Class<? extends BaseActivity> activityClass) {
        Navigator.startActivity(this, activityClass);
    }

    @Override
    public final void startActivityForResult(Class<? extends BaseActivity> activityClass, int requestCode) {
        Navigator.startActivityForResult(this, activityClass, requestCode);
    }


    @Override
    public final void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
    }

}
