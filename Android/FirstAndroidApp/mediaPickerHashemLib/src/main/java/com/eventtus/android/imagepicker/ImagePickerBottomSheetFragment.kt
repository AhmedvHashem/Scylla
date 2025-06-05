//package com.eventtus.android.imagepicker
//
//import android.app.Dialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import androidx.fragment.app.setFragmentResult
//import com.eventtus.android.imagepicker.databinding.DialogFragmentImagePickerBinding
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//
//
//class ImagePickerBottomSheetFragment : BottomSheetDialogFragment() {
//
//    private lateinit var binding: DialogFragmentImagePickerBinding
//
//    override fun getTheme() = com.eventtus.android.core.R.style.RoundedBottomSheet30dpTheme
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DialogFragmentImagePickerBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.btnUpload.setOnClickListener {
//            val bundle = Bundle().apply {
//                putString(CHOICE_RESULT_KEY, ImagePicker.ImagePickerItems.UPLOAD.value)
//            }
//            setFragmentResult(CHOICE_REQUEST_KEY, bundle)
//            dismiss()
//        }
//        binding.btnTakeAPhoto.setOnClickListener {
//            val bundle = Bundle().apply {
//                putString(CHOICE_RESULT_KEY, ImagePicker.ImagePickerItems.TAKE_PHOTO.value)
//            }
//            setFragmentResult(CHOICE_REQUEST_KEY, bundle)
//            dismiss()
//        }
//        binding.btnCancel.setOnClickListener { dismiss() }
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
//        dialog.setOnShowListener {
//            setDialogAsExpanded(it as? BottomSheetDialog)
//        }
//        return dialog
//    }
//
//    private fun setDialogAsExpanded(bottomSheetDialog: BottomSheetDialog?) {
//        val bottomSheet =
//            bottomSheetDialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
//        bottomSheet?.let {
//            val behavior = BottomSheetBehavior.from(it)
//            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            behavior.skipCollapsed = true
//        }
//    }
//
//    companion object {
//        const val CHOICE_RESULT_KEY = "choice_result_key"
//        const val CHOICE_REQUEST_KEY = "choice_request_key"
//
//        @JvmStatic
//        fun newInstance(): ImagePickerBottomSheetFragment {
//            return ImagePickerBottomSheetFragment()
//        }
//    }
//}