package personal.dongxia.android.multimeter.uikit.widget.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * recyclerview 分割线
 * @author wudongxia
 * @date 2020/3/18
 */
class DividerItemDecoration(context: Context, private var orientation: Int) : DividerItemDecoration(context, orientation) {

    private var mMiddleDivider: Drawable? = null
    private var mHeaderDivider: Drawable? = null
    private var mFooterDivider: Drawable? = null
    private val mBounds = Rect()

    override fun setOrientation(orientation: Int) {
        super.setOrientation(orientation)
        this.orientation = orientation
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || (mHeaderDivider == null && mMiddleDivider == null && mFooterDivider == null)) {
            return
        }
        if (orientation == VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    override fun setDrawable(drawable: Drawable) {
        super.setDrawable(drawable)
        mMiddleDivider = drawable
        mFooterDivider = drawable
    }

    fun setHeaderDrawable(drawable: Drawable?) {
        mHeaderDivider = drawable
    }
    fun setFooterDrawable(drawable: Drawable?) {
        mFooterDivider = drawable
    }
    fun setMiddleDrawable(drawable: Drawable?) {
        mMiddleDivider = drawable
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }
        val childCount: Int = parent.childCount
        for (i in 0 until childCount) {
            val divider = if (mHeaderDivider != null && i == 0) {
                mHeaderDivider
            } else if (mFooterDivider != null && i == childCount - 1) {
                mFooterDivider
            } else if (mMiddleDivider != null && i != childCount - 1 && i != 0) {
                mMiddleDivider
            } else {
                null
            }

            divider?.apply {
                val child: View = parent.getChildAt(i)
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val top = mBounds.top + child.translationY.roundToInt()
                val bottom = mBounds.top + divider.intrinsicHeight
                divider.setBounds(left, top, right, bottom)
                divider.draw(canvas)
            }
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }
        val childCount: Int = parent.childCount
        for (i in 0 until childCount) {
            val divider = if (mHeaderDivider != null && i == 0) {
                mHeaderDivider
            } else if (mFooterDivider != null && i == childCount - 1) {
                mFooterDivider
            } else if (mMiddleDivider != null && i != childCount - 1 && i != 0) {
                mMiddleDivider
            } else {
                null
            }

            divider?.apply {
                val child: View = parent.getChildAt(i)
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val right = mBounds.right + child.translationX.roundToInt()
                val left = mBounds.right - divider.intrinsicWidth
                divider.setBounds(left, top, right, bottom)
                divider.draw(canvas)
            }
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        var itemTopOffset = 0
        var itemBottomOffset = 0
        var itemStartOffset = 0
        var itemEndOffset = 0
        if (position == 0) {
            mHeaderDivider?.let {
                itemTopOffset += max(it.intrinsicHeight, 0)
                itemStartOffset += max(it.intrinsicWidth, 0)
            }
        } else if (position == state.itemCount - 1) {
            mFooterDivider?.let {
                itemBottomOffset += max(it.intrinsicHeight, 0)
                itemEndOffset += max(it.intrinsicWidth, 0)
            }
        } else {
            mMiddleDivider?.let {
                itemBottomOffset += max(it.intrinsicHeight, 0)
                itemEndOffset += max(it.intrinsicWidth, 0)
            }
        }
        if (orientation == VERTICAL) {
            outRect[0, itemTopOffset, 0] = itemBottomOffset
        } else {
            outRect[itemStartOffset, 0, itemEndOffset] = 0
        }
    }

    init {
        setDrawable(context.getDrawable(R.drawable.default_decoration_divider)!!)
    }
}