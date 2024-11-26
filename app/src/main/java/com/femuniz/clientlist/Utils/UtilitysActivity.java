package com.femuniz.clientlist.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class UtilitysActivity {
    public static void applyMask(final EditText editText, final String mask) {
        editText.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating;
            private String oldText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                String unmasked = s.toString().replaceAll("[^\\d]", "");
                StringBuilder masked = new StringBuilder();
                int unmaskedIndex = 0;

                for (char c : mask.toCharArray()) {
                    if (c == '#') {
                        if (unmaskedIndex < unmasked.length()) {
                            masked.append(unmasked.charAt(unmaskedIndex));
                            unmaskedIndex++;
                        } else {
                            break;
                        }
                    } else {
                        masked.append(c);
                    }
                }

                isUpdating = true;
                oldText = masked.toString();
                editText.setText(masked.toString());
                editText.setSelection(masked.length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
