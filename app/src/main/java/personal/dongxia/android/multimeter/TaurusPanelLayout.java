package personal.dongxia.android.multimeter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

/**
 * 面板布局
 * @author dongxia.wdx
 */
public class TaurusPanelLayout extends FrameLayout {

    private static final String TAG = "PanelLayout";
    private static final String LOG_TAG = TAG;

    private static final int INVALID_POINTER = -1;
    private static final int DEFAULT_ENTER_ANIMATION_DURATION = 500;
    private static final int DEFAULT_EXIT_ANIMATION_DURATION = 500;
    private static final int DEFAULT_ROLLBACK_ANIMATION_DURATION = 300;
    private static final float DEFAULT_TRIGGER_OFFSET = 500f;


    @StringDef({PanelStatus.IDLE, PanelStatus.DRAGGING, PanelStatus.ENTERING, PanelStatus.ROLLING_BACK, PanelStatus.EXITING, PanelStatus.ENTERED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PanelStatus {
        String IDLE = "IDLE";
        String DRAGGING = "DRAGGING";
        String ENTERING = "ENTERING";
        String ENTERED = "ENTERED";
        String EXITING = "EXITING";
        String ROLLING_BACK = "ROLLING_BACK";
    }

    private int enterAnimationDuration;
    private int exitAnimationDuration;
    private int rollbackAnimationDuration;
    private float triggerOffset;
    private View mPanel;
    private float mInitialDownY;
    private @PanelStatus String mPanelStatus = PanelStatus.IDLE;
    private int mActivePointerId = INVALID_POINTER;

    public TaurusPanelLayout(@NonNull Context context) {
        this(context, null);
    }

    public TaurusPanelLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TaurusPanelLayout);
        enterAnimationDuration = a.getInt(R.styleable.TaurusPanelLayout_TaurusPanelLayout_enterAnimationDuration, DEFAULT_ENTER_ANIMATION_DURATION);
        exitAnimationDuration = a.getInt(R.styleable.TaurusPanelLayout_TaurusPanelLayout_exitAnimationDuration, DEFAULT_EXIT_ANIMATION_DURATION);
        rollbackAnimationDuration = a.getInt(R.styleable.TaurusPanelLayout_TaurusPanelLayout_rollbackAnimationDuration, DEFAULT_ROLLBACK_ANIMATION_DURATION);
        // todo 转换成 dp
        triggerOffset = a.getDimension(R.styleable.TaurusPanelLayout_TaurusPanelLayout_triggerOffset, DEFAULT_TRIGGER_OFFSET);
        a.recycle();
    }

    public void setPanel(View panel) {
        this.mPanel = panel;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        int pointerIndex;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(LOG_TAG, "onInterceptTouchEvent:MotionEvent.ACTION_DOWN, PanelStatus = " + mPanelStatus);
                if (!PanelStatus.IDLE.equals(mPanelStatus)) {
                    return false;
                }

                mActivePointerId = ev.getPointerId(0);
                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                // 获取初始点击位置
                mInitialDownY = ev.getY(pointerIndex);
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e(LOG_TAG, "onInterceptTouchEvent:MotionEvent.ACTION_MOVE, PanelStatus = " + mPanelStatus);
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }
                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                mPanelStatus = PanelStatus.DRAGGING;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(ev);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER;
                mPanelStatus = PanelStatus.IDLE;
                break;
            default: break;
        }

        return PanelStatus.DRAGGING.equals(mPanelStatus);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        int pointerIndex = -1;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //mActivePointerId = ev.getPointerId(0);
                //mIsBeingDragged = false;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }

                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                if (!PanelStatus.DRAGGING.equals(mPanelStatus)) {
                    mPanelStatus = PanelStatus.DRAGGING;
                }
                if (PanelStatus.DRAGGING.equals(mPanelStatus)) {
                    final float y = ev.getY(pointerIndex);
                    processDrag(y, mInitialDownY);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN: {
                pointerIndex = ev.getActionIndex();
                if (pointerIndex < 0) {
                    Log.e(LOG_TAG,
                        "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                mActivePointerId = ev.getPointerId(pointerIndex);
                break;
            }

            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(ev);
                break;

            case MotionEvent.ACTION_UP: {
                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (PanelStatus.DRAGGING.equals(mPanelStatus)) {
                    final float y = ev.getY(pointerIndex);
                    processDragEnd(y, mInitialDownY);
                }
                mActivePointerId = INVALID_POINTER;
                return false;
            }
            case MotionEvent.ACTION_CANCEL:
            default: return false;
        }

        return true;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = ev.getActionIndex();
        final int pointerId = ev.getPointerId(pointerIndex);
        if (pointerId == mActivePointerId) {
            // This was our active pointer going up. Choose a new
            // active pointer and adjust accordingly.
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mActivePointerId = ev.getPointerId(newPointerIndex);
        }
    }

    private void processDrag(float endY, float startY) {
        Log.d(TAG, "processDrag: endY=" + endY + " startY=" + startY);
        final float yDiff = endY - mInitialDownY;
        mPanel.setVisibility(VISIBLE);
        mPanel.setX(0);
        mPanel.setY(0 - mPanel.getHeight() + yDiff);
    }

    private void processDragEnd(float endY, float startY) {
        Log.d(TAG, "processDragEnd: endY=" + endY + " startY=" + startY);
        final float yDiff = endY - mInitialDownY;
        if (yDiff > triggerOffset) {
            mPanelStatus = PanelStatus.ENTERING;
            mPanel.setVisibility(VISIBLE);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 0 - mPanel.getY());
            translateAnimation.setDuration(enterAnimationDuration);
            translateAnimation.setAnimationListener(new CommonAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    mPanel.setX(0);
                    mPanel.setY(0);
                    mPanelStatus = PanelStatus.ENTERED;
                }
            });
            mPanel.startAnimation(translateAnimation);
        } else {
            mPanelStatus = PanelStatus.ROLLING_BACK;
            mPanel.setVisibility(VISIBLE);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0,  0, 0 - mPanel.getHeight() - mPanel.getY());
            translateAnimation.setDuration(rollbackAnimationDuration);
            translateAnimation.setAnimationListener(new CommonAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    mPanel.setX(0);
                    mPanel.setY(0 - mPanel.getHeight());
                    mPanel.setVisibility(INVISIBLE);
                    mPanelStatus = PanelStatus.IDLE;
                }
            });
            mPanel.startAnimation(translateAnimation);
        }
    }

    private void processExit() {
        if (!PanelStatus.ENTERED.equals(mPanelStatus)) {
            return;
        }
        mPanelStatus = PanelStatus.EXITING;
        mPanel.setVisibility(VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0,  0, 0 - mPanel.getHeight() - mPanel.getY());
        translateAnimation.setDuration(rollbackAnimationDuration);
        translateAnimation.setAnimationListener(new CommonAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mPanel.setX(0);
                mPanel.setY(0 - mPanel.getHeight());
                mPanel.setVisibility(INVISIBLE);
                mPanelStatus = PanelStatus.IDLE;
            }
        });
        mPanel.startAnimation(translateAnimation);
    }


    private class CommonAnimationListener implements AnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            // empty
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // empty
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // empty
        }
    }
}
