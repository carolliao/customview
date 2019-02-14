package com.carol.practice.customviews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carol.practice.customviews.R;
import com.carol.practice.customviews.utils.DensityUtil;


/**
 * Created by Long on 2019/2/13.
 */

public class CustomCombineEditTextSetting extends RelativeLayout{
    private TextView mTitle;
    private TextView mTips;
    private EditText mInput;
    private Button   mOkBtn;
    public CustomCombineEditTextSetting(Context context, AttributeSet attrs){
        super(context, attrs);
        //获取布局文件
        LayoutInflater.from(context).inflate(R.layout.custom_combine_edittext_setting, this, true);
        mTitle = (TextView)findViewById(R.id.tv_title);
        mTips  = (TextView)findViewById(R.id.tv_tips);
        mInput = (EditText)findViewById(R.id.ed_input);
        mOkBtn = (Button)findViewById(R.id.btn_ok);
        //获取自定义的属性值
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomCombineEditTextSetting);
        if(attributes != null){
            //设置赋值好的属性值到控件
            String titleText = attributes.getString(R.styleable.CustomCombineEditTextSetting_titleText);
            mTitle.setText(titleText);
            //getDimensionPixelSize获取的单位px，文字大小一般要设置成sp，所以需要转换成sp
            float titleTextSize = attributes.getDimensionPixelSize(R.styleable.CustomCombineEditTextSetting_titleTextSize, 22);
            titleTextSize = DensityUtil.px2sp(context, titleTextSize);
            mTitle.setTextSize(titleTextSize);
            int titleTextColor = attributes.getColor(R.styleable.CustomCombineEditTextSetting_titleTextColor, Color.BLACK);
            mTitle.setTextColor(titleTextColor);
            String tipsText = attributes.getString(R.styleable.CustomCombineEditTextSetting_tipsText);
            mTips.setText(tipsText);
            float tipsTextSize = attributes.getDimension(R.styleable.CustomCombineEditTextSetting_tipsTextSize, 10);
            tipsTextSize = DensityUtil.px2sp(context, tipsTextSize);
            mTips.setTextSize(tipsTextSize);
            int tipsTextColor = attributes.getColor(R.styleable.CustomCombineEditTextSetting_tipsTextColor, Color.BLACK);
            mTips.setTextColor(tipsTextColor);
            //设置EditText的宽度
            int width = attributes.getLayoutDimension(R.styleable.CustomCombineEditTextSetting_inputLayoutWidth, -1);
            mInput.setWidth(width);
            //设置EditText的文本大小
            float editTextSize = attributes.getDimensionPixelSize(R.styleable.CustomCombineEditTextSetting_inputTextSize, 22);
            editTextSize = DensityUtil.px2sp(context, editTextSize);
            mInput.setTextSize(editTextSize);
            //释放属性资源
            attributes.recycle();
        }
    }

    /**
     * 获取编辑控件
     * @return
     */
    public EditText getInputEditText(){
        return mInput;
    }

    /**
     * 获取确定按钮控件
     * @return
     */
    public Button getOkButton(){
        return mOkBtn;
    }

}
