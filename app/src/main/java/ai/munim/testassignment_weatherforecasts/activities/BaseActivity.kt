package ai.munim.testassignment_weatherforecasts.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<DB : ViewDataBinding> :
    AppCompatActivity() {
    protected var mBinding: DB? = null
    protected var progressDialog: ProgressDialog? = null
    protected var mAlertDialogBuilder: AlertDialog.Builder? = null
    private var isActivityRunning = false

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutRes)
        mAlertDialogBuilder = AlertDialog.Builder(this)
        isActivityRunning = true
    }

    fun showSnackBar(message: String?) {
        Snackbar.make(
            window.decorView,
            message!!, Snackbar.LENGTH_SHORT
        ).show()
    }

    fun showSnackBar(view: View?, message: String?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT).show()
    }

    protected fun showProgressDialog() {
        showProgressDialog("")
    }

    fun showProgressDialog(message: String?) {
        if (progressDialog == null) {
            initProgressLoader()
        }
        progressDialog!!.setMessage(message)
        if (isActivityRunning) progressDialog!!.show()
    }

    open fun initProgressLoader() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(false)
    }

    fun cancelProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        isActivityRunning = true
    }

    override fun onPause() {
        super.onPause()
        isActivityRunning = false
    }

}
