package com.work.thirukkural.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.work.thirukkural.R

class ShareKuralDialogFragment(val kuralDialogListener: ShareKuralDialogListener): DialogFragment() {
    private var selectedItems: MutableList<Int> = mutableListOf()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).setMultiChoiceItems(R.array.share_options, null
        ) { _, which, isChecked ->
            if (isChecked) selectedItems.add(which) else selectedItems.remove(
                which
            )
        }.setTitle(R.string.share_dlg_title)
            .setPositiveButton(R.string.ok) { _, _ -> kuralDialogListener.handlePositiveAction(selectedItems)}
            .setNegativeButton(R.string.cancel) { _, _ -> dismiss() }.create()
    }
}