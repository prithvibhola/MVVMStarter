package prithvi.io.mvvmstarter.utility.extentions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

private val beforeTextChangedStub = { _: CharSequence?, _: Int, _: Int, _: Int -> Unit }
private val onTextChangedStub = { _: CharSequence?, _: Int, _: Int, _: Int -> Unit }
private val afterTextChanged = { _: Editable? -> Unit }

fun EditText.addTextWatcher(
        beforeTextChange: (var1: CharSequence?, var2: Int, var3: Int, var4: Int) -> Unit = beforeTextChangedStub,
        onTextChange: (var1: CharSequence?, var2: Int, var3: Int, var4: Int) -> Unit = onTextChangedStub,
        afterTextChange: (var1: Editable?) -> Unit = afterTextChanged
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            beforeTextChange(p0, p1, p2, p3)
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChange(p0, p1, p2, p3)
        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChange(p0)
        }

    })
}