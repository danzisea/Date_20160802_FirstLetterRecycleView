package com.zyl.my.date_20160802_firstletterrecycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by My on 2016/8/2.
 */
public class CustomView extends View {
    private String[] arrLetter = new String[]{"#", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"};
    private int textHeight = 0;
    private TextView textView_dialog = null;
    private OnLetterClickListener listener = null;
    private Paint paint = null;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint =  new Paint();
    }

    public CustomView(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        float density = getResources().getDisplayMetrics().density;
        paint.setTextSize(14*density);


        //控件的宽：
        int viewWidth = getWidth();
        //文字的长度：
        //int screenHeight = getResources().getDisplayMetrics().heightPixels;
        //textHeight = screenHeight / arrLetter.length;
        textHeight = getHeight()/arrLetter.length;

        for (int i = 0; i < arrLetter.length; i++) {
            paint.setColor(Color.BLACK);
            float textWidth = paint.measureText(arrLetter[i]);
            canvas.drawText(arrLetter[i],(int)(viewWidth-textWidth)/2,textHeight*(i+1),paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        int position = (int) (y/textHeight);

        if(position>0 && position<arrLetter.length){//这句话一定要加
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                if(textView_dialog!=null && textView_dialog.getVisibility()== View.VISIBLE){
                    textView_dialog.setVisibility(View.GONE);
                }
                invalidate();//擦除当前View,重新绘制
                break;
            default:
                setBackgroundColor(Color.GRAY);
                if (textView_dialog!=null) {
                    textView_dialog.setVisibility(View.VISIBLE);
                    textView_dialog.setText(arrLetter[position]);
                }
                if (listener != null) {
                    listener.LetterClick(arrLetter[position]);
                }
                invalidate();
                break;
        }
        }
        return true;
    }

    //给自定义View自己定义一个监听器，传递被点击的字母到Activity中设置给textview
    public interface OnLetterClickListener{
        void LetterClick(String letter);
    }

    public void setOnLetterclickListener(TextView textView_dialog,OnLetterClickListener listener){
        this.textView_dialog = textView_dialog;
        this.listener = listener;
    }
}
