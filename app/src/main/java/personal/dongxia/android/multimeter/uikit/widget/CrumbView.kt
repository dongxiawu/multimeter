package personal.dongxia.android.multimeter.uikit.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

/**
 * 面包屑
 * @date 2020/6/30
 * @author wudongxia
 */
class CrumbView: HorizontalScrollView {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null): super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    private fun initView() {

    }
}