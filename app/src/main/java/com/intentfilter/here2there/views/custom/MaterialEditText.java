package com.intentfilter.here2there.views.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.AbsSavedState;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;

import com.intentfilter.here2there.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

import static android.text.TextUtils.isEmpty;
import static butterknife.OnTextChanged.Callback.BEFORE_TEXT_CHANGED;

public class MaterialEditText extends TextInputLayout {

    @BindView(R.id.editText)
    TextInputEditText editText;

    private String hint;
    private String label;
    private OnFocusChangeListener listener;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setErrorEnabled(true);

        LayoutInflater.from(context).inflate(R.layout.custom_material_edit_text, this, true);
        ButterKnife.bind(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);

        label = typedArray.getString(R.styleable.MaterialEditText_labelText);
        hint = typedArray.getString(R.styleable.MaterialEditText_android_hint);

        boolean enabled = typedArray.getBoolean(R.styleable.MaterialEditText_enabled, true);
        int inputType = typedArray.getInt(R.styleable.MaterialEditText_android_inputType, InputType.TYPE_CLASS_TEXT);

        typedArray.recycle();

        editText.setInputType(inputType);
        editText.setEnabled(enabled);

        setHint(hint); //must be set at the end as some other attributes conflict with this one
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener listener) {
        this.listener = listener;
    }

    @OnTextChanged(value = R.id.editText, callback = BEFORE_TEXT_CHANGED)
    void beforeInput() {
        setError(null);
    }

    @OnFocusChange(R.id.editText)
    void toggleHintLabel(boolean focused) {
        if (isEmpty(editText.getText()) && isEmpty(getError())) {
            setHint(focused ? label : hint);
        }

        if (listener != null) {
            listener.onFocusChange(editText, focused);
        }
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String text) {
        editText.setText(text);
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        setHint(label);
        super.setError(error);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, editText.getText().toString(), getHint());
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());

        editText.setText(savedState.getText());
        setHint(savedState.getHint());
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        // As we save our own instance state, ensure our children don't save and restore their state as well.
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchThawSelfOnly(container);
    }

    private static class SavedState extends AbsSavedState {

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private String text;
        private CharSequence hint;

        SavedState(Parcel in) {
            super(in, TextInputLayout.class.getClassLoader());
            text = in.readString();
            hint = in.readString();
        }

        SavedState(Parcelable superState, String text, CharSequence hint) {
            super(superState);
            this.text = text;
            this.hint = hint;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(text);
            out.writeString(String.valueOf(hint));
        }

        public String getText() {
            return text;
        }

        public CharSequence getHint() {
            return hint;
        }
    }
}