package com.example.administrator.smallvault.ui.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.administrator.smallvault.R;

/**
 * 半径，数量，颜色，是否需要动画效果
 * Created by xdsjs on 2015/10/26.
 */
public class SiWeiPasswordView extends EditText {

    private float password_radius;//密码的半径
    private int password_num;//密码的位数
    private int password_color;//密码的颜色
    private float border_width;//边线的宽度

    private int textLen;//当前输入文本的长度
    private Paint passwordPaint;

    private OnPasswordInputListener onPasswordInputListener;
    private String truePassword;

    public SiWeiPasswordView(Context context) {
        this(context, null);

    }

    public SiWeiPasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SiWeiPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.PasswordView_password_color:
                    password_color = typedArray.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.PasswordView_password_num:
                    password_num = typedArray.getInt(attr, 4);
                    break;
                case R.styleable.PasswordView_password_radius:
                    password_radius = typedArray.getDimension(attr, 15);
                    break;
                case R.styleable.PasswordView_border_width:
                    border_width = typedArray.getDimension(attr, 5);
                    break;
            }
            typedArray.recycle();
            passwordPaint = new Paint();
            passwordPaint.setAntiAlias(true);
            passwordPaint.setColor(password_color);
            passwordPaint.setStrokeWidth(border_width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float x = 0.0f;
        float y = height / 2.0f;
        float half = width / password_num / 2.0f;
        for (int i = 0; i < password_num; i++) {
            if (i + 1 <= textLen) {
                passwordPaint.setStyle(Paint.Style.STROKE);
            } else {
                passwordPaint.setStyle(Paint.Style.FILL);
            }
            x = width * i / password_num + half;
            canvas.drawCircle(x, y, password_radius, passwordPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.textLen = text.toString().length();
        invalidate();
        if (this.textLen == 4) {
            if (TextUtils.isEmpty(truePassword)) {
                onPasswordInputListener.onInputFinished(text.toString());

            } else {
                if (getTruePassword().equals(text.toString())) {
                    //增加500毫秒的延时
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onPasswordInputListener.onPasswordCheckSuccess();
                        }
                    }, 500);
                } else {
                    //增加500毫秒的延时
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SiWeiPasswordView.this.setText("");
                            onPasswordInputListener.onPasswordCheckFailed();
                        }
                    }, 500);
                }
            }
        }
    }

    public interface OnPasswordInputListener {
        void onInputFinished(String password);

        void onPasswordCheckSuccess();

        void onPasswordCheckFailed();
    }

    public void setOnPasswordInputListener(OnPasswordInputListener onPasswordInputListener) {
        this.onPasswordInputListener = onPasswordInputListener;
    }

    public String getTruePassword() {
        return truePassword;
    }

    public void setTruePassword(String truePassword) {
        this.truePassword = truePassword;
    }
}
