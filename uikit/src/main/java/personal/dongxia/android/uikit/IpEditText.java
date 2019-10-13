package personal.dongxia.android.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class IpEditText extends AppCompatEditText {
    public IpEditText(Context context) {
        this(context, null);
    }

    public IpEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public IpEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText);
        a.recycle();
    }
}
