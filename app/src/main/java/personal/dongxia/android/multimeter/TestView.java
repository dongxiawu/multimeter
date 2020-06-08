package personal.dongxia.android.multimeter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/**
 * @author wudongxia
 * @date 2020/6/7
 */
public class TestView extends LinearLayout {
    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setPosition(0, 0  - getHeight());
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }
}
