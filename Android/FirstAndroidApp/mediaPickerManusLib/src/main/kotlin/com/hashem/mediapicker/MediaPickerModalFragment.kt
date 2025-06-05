package com.hashem.mediapicker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.hashem.mediapicker.databinding.FragmentMediaPickerModalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A modal bottom sheet dialog fragment that provides UI for selecting media picking options.
 * This is the main entry point for the media picker UI component.
 */
class MediaPickerModalFragment : BottomSheetDialogFragment() {
    
    private var _binding: FragmentMediaPickerModalBinding? = null
    private val binding get() = _binding!!
    
    private var listener: MediaPickerListener? = null
    
    /**
     * Interface for callbacks when user selects an option
     */
    interface MediaPickerListener {
        fun onPickSingleImageSelected()
        fun onPickMultipleImagesSelected()
        fun onCaptureImageSelected()
        fun onPickSingleDocumentSelected()
        fun onPickMultipleDocumentsSelected()
        fun onCancelled()
    }
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Try to get listener from parent fragment or activity
        listener = when {
            parentFragment is MediaPickerListener -> parentFragment as MediaPickerListener
            context is MediaPickerListener -> context
            else -> throw RuntimeException("$context must implement MediaPickerListener")
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaPickerModalBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up click listeners for all buttons
        with(binding) {
            buttonPickSingleImage.setOnClickListener {
                listener?.onPickSingleImageSelected()
                dismiss()
            }
            
            buttonPickMultipleImages.setOnClickListener {
                listener?.onPickMultipleImagesSelected()
                dismiss()
            }
            
            buttonCaptureImage.setOnClickListener {
                listener?.onCaptureImageSelected()
                dismiss()
            }
            
            buttonPickSingleDocument.setOnClickListener {
                listener?.onPickSingleDocumentSelected()
                dismiss()
            }
            
            buttonPickMultipleDocuments.setOnClickListener {
                listener?.onPickMultipleDocumentsSelected()
                dismiss()
            }
            
            buttonCancel.setOnClickListener {
                listener?.onCancelled()
                dismiss()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        const val TAG = "MediaPickerModalFragment"
        
        /**
         * Shows the media picker modal dialog
         * @param fragmentManager The fragment manager to use for the transaction
         */
        fun show(fragmentManager: FragmentManager) {
            MediaPickerModalFragment().show(fragmentManager, TAG)
        }
    }
}
