package prithvi.io.mvvmstarter.utility.extentions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import prithvi.io.mvvmstarter.data.models.EditTextFlow

private val beforeTextChangedStub = { _: CharSequence?, _: Int, _: Int, _: Int -> Unit }
private val onTextChangedStub = { _: CharSequence?, _: Int, _: Int, _: Int -> Unit }
private val afterTextChanged = { _: Editable? -> Unit }

/*
 * Extension function to get EditText watcher methods in more convenient way
 */
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

/*
 * Alternative way to make EditText query into stream
 * Return Flowable of EditTextFlow which can be used to get query stream
 */
fun EditText.addTextWatcher(): Flowable<EditTextFlow> {
    return Flowable.create<EditTextFlow>({ emitter ->
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emitter.onNext(EditTextFlow(p0.toString(), EditTextFlow.Type.BEFORE))
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emitter.onNext(EditTextFlow(p0.toString(), EditTextFlow.Type.ON))
            }

            override fun afterTextChanged(p0: Editable?) {
                emitter.onNext(EditTextFlow(p0.toString(), EditTextFlow.Type.AFTER))
            }
        })
    }, BackpressureStrategy.BUFFER)
}
