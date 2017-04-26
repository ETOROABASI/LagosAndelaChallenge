package com.example.android.lagosandelachallenge.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.lagosandelachallenge.R;

/**
 * Created by ETORO on 26/04/2017.
 */

public class UserAdapterDivider extends RecyclerView.ItemDecoration {




    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);





        int dividerLeft = parent.getPaddingLeft()+ 96;              //gets the paddingLeft of the recyclerView in the xml
        int dividerRight = parent.getWidth() - parent.getPaddingRight();  //gets the paddingRight of our RecyclerView

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int dividerTop = child.getBottom() + layoutParams.bottomMargin;
            int dividerBottom = dividerTop + 1;
            c.drawLine(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = 1;

    }
}
