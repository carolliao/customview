package com.carol.practice.customviews.views;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.carol.practice.customviews.R;
import com.carol.practice.customviews.utils.DensityUtil;


/**
 * Created by Long on 2019/2/13.
 */

public class CustomCombineTimePickerSetting extends RelativeLayout{
    private static final String TAG = "CustomCombineTimePicker";
    private TextView mTitle;
    private TextView mTips;
    private EditText mStartInput;
    private TextView mTo;
    private EditText mEndInput;
    private Button mOkBtn;
    public CustomCombineTimePickerSetting(Context context, AttributeSet attrs){
        super(context, attrs);
        //获取布局文件
        LayoutInflater.from(context).inflate(R.layout.custom_combine_timepicker_setting, this, true);
        mTitle = (TextView)findViewById(R.id.tv_title);
        mTips  = (TextView)findViewById(R.id.tv_tips);
        mStartInput = (EditText)findViewById(R.id.ed_input_start);
        mTo = (TextView)findViewById(R.id.tv_to);
        mEndInput = (EditText)findViewById(R.id.ed_input_end);
        mOkBtn = (Button)findViewById(R.id.btn_ok);
        //获取自定义的属性值
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomCombineTimePickerSetting);
        if(attributes != null){
            //设置赋值好的属性值到控件
            String titleText = attributes.getString(R.styleable.CustomCombineTimePickerSetting_titleText);
            mTitle.setText(titleText);
            //getDimensionPixelSize获取的单位px，文字大小一般要设置成sp，所以需要转换成sp
            float titleTextSize = attributes.getDimensionPixelSize(R.styleable.CustomCombineTimePickerSetting_titleTextSize, 22);
            titleTextSize = DensityUtil.px2sp(context, titleTextSize);
            mTitle.setTextSize(titleTextSize);
            int titleTextColor = attributes.getColor(R.styleable.CustomCombineTimePickerSetting_titleTextColor, Color.BLACK);
            mTitle.setTextColor(titleTextColor);
            String tipsText = attributes.getString(R.styleable.CustomCombineTimePickerSetting_tipsText);
            mTips.setText(tipsText);
            float tipsTextSize = attributes.getDimension(R.styleable.CustomCombineTimePickerSetting_tipsTextSize, 10);
            tipsTextSize = DensityUtil.px2sp(context, tipsTextSize);
            mTips.setTextSize(tipsTextSize);
            int tipsTextColor = attributes.getColor(R.styleable.CustomCombineTimePickerSetting_tipsTextColor, Color.BLACK);
            mTips.setTextColor(tipsTextColor);
            //设置EditText的宽度
            int width = attributes.getLayoutDimension(R.styleable.CustomCombineTimePickerSetting_inputLayoutWidth, -1);
            width = (width > -1 ? width / 2 : width);
            mStartInput.setWidth(width);
            mEndInput.setWidth(width);
            //设置EditText的文本大小
            float editTextSize = attributes.getDimensionPixelSize(R.styleable.CustomCombineTimePickerSetting_inputTextSize, 22);
            editTextSize = DensityUtil.px2sp(context, editTextSize);
            mStartInput.setTextSize(editTextSize);
            mEndInput.setTextSize(editTextSize);
            mTo.setTextSize(editTextSize);

            //设置两个编辑框的属性
            mStartInput.setInputType(InputType.TYPE_NULL);
            mEndInput.setInputType(InputType.TYPE_NULL);
            mStartInput.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.i(TAG, "startTimeSelect setOnFocusChangeListener hasFocus = " + hasFocus);
                    if(hasFocus){
                        //单击首次获得焦点，后面单击时onClick响应，直到失去焦点
                        showTimePickerDialog(mStartInput);
                    }
                }
            });

            mStartInput.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "startTimeSelect onClick...");
                    showTimePickerDialog(mStartInput);
                }
            });
            mEndInput.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.i(TAG, "endTimeSelect setOnFocusChangeListener hasFocus = " + hasFocus);
                    if(hasFocus){
                        //单击首次获得焦点，后面单击时onClick响应，直到失去焦点
                        showTimePickerDialog(mEndInput);
                    }
                }
            });

            mEndInput.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "endTimeSelect onClick...");
                    showTimePickerDialog(mEndInput);
                }
            });
            //释放属性资源
            attributes.recycle();
        }
    }

    /**
     * 获取编辑控件
     * @return
     */
    public EditText getStartInputEditText(){
        return mStartInput;
    }

    public EditText getEndInputEditText(){
        return mEndInput;
    }

    /**
     * 获取确定按钮控件
     * @return
     */
    public Button getOkButton(){
        return mOkBtn;
    }


    private void showTimePickerDialog(final EditText v){
        int hour = 10;
        int minute = 10;
        if(!v.getText().toString().isEmpty()){
            String[] initvalue = v.getText().toString().split(":");
            if(initvalue.length == 2) {
                hour = Integer.valueOf(initvalue[0]);
                minute = Integer.valueOf(initvalue[1]);
            }
        }
        new TimePickerDialog(getContext(), AlertDialog.THEME_TRADITIONAL, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.i(TAG, "onTimeSet hourOfDay=" + hourOfDay + " minute=" + minute);
                String hour = hourOfDay + "";
                if (hourOfDay < 10) {
                    hour = "0" + hourOfDay;
                }
                String minutes = minute + "";
                if (minute < 10) {
                    minutes = "0" + minute;
                }
                v.setText(hour + ":" + minutes);
            }
        }, hour, minute, true).show();

    }
}
