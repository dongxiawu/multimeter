package personal.dongxia.android.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

public class ClearableEditText extends AppCompatEditText {
    private boolean clearable;
    private Drawable drawable;

    public ClearableEditText(Context context) {
        this(context, null);
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText);
        setClearable(a.getBoolean(R.styleable.ClearableEditText_clearable, true));
        init();
        a.recycle();
    }

    public void setClearable(boolean clearable) {
        this.clearable = clearable;
    }

    public boolean isClearable() {
        return clearable;
    }

    protected void setClearIconVisible(boolean visible) {
        if (!this.clearable) {
            visible = false;
        }

        Drawable x = visible ? this.drawable : null;
        this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], x, this.getCompoundDrawables()[3]);
    }

    public void setClearDrawable(Drawable drawable) {
        if (drawable != null) {
            this.drawable = drawable;
            this.drawable.setBounds(0, 0, this.drawable.getIntrinsicWidth() + 4, this.drawable.getIntrinsicHeight() + 4);
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        updateClearIconVisibleState();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        updateClearIconVisibleState();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.getCompoundDrawables()[2] != null) {
            boolean tappedX = event.getX() > (float)(this.getWidth() - this.getPaddingRight() - this.drawable.getIntrinsicWidth() - 20);
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    this.setText("");
                }
                return true;
            }
        }
        return super.onTouchEvent(event);

    }

    private void updateClearIconVisibleState() {
        if (clearable && isFocused() && getText() != null && getText().toString().length() > 0) {
            this.setClearIconVisible(true);
        } else {
            this.setClearIconVisible(false);
        }
    }

    private void init() {
        Drawable drawable = this.getCompoundDrawables()[2];
        if (drawable == null) {
            drawable = getResources().getDrawable(R.drawable.icon_close);
        }
        this.setClearDrawable(drawable);
        updateClearIconVisibleState();
    }
}
