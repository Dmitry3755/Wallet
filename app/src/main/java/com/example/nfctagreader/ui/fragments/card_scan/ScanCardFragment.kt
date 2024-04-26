package com.example.nfctagreader.ui.fragments.card_scan

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FmtScannerCardScreenBinding
import com.example.nfctagreader.ui.base.BaseFragment
import com.example.nfctagreader.utils.convertBitmapFromImageProxy
import javax.inject.Inject


class ScanCardFragment : BaseFragment() {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<ScanCardViewModel>() {
        daggerViewModelFactory
    }

    override val mIsBottomMenuVisible = true
    private var binding: FmtScannerCardScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FmtScannerCardScreenBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @OptIn(ExperimentalGetImage::class)
    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { bind ->

            bind.scanCardSubtitle.text = getString(R.string.fmt_scan_card_photo_your_front_card)

            if (mViewModel.checkPermissionCamera(bind.root.context)) {
                mViewModel.initializeCamera(
                    bind.root.context,
                    bind.root.rootView,
                    this as LifecycleOwner,
                    bind.cameraView,
                    ProcessCameraProvider.getInstance(bind.root.context)
                )
            } else {
                mViewModel.requestPermissionCamera(this.requireActivity())
                mViewModel.initializeCamera(
                    bind.root.context,
                    bind.root.rootView,
                    this as LifecycleOwner,
                    bind.cameraView,
                    ProcessCameraProvider.getInstance(bind.root.context)
                )
            }

            bind.createPhotoImageButton.setOnClickListener {
                mViewModel.takeImageProxy()
            }

            mViewModel.imageProxyFront.observe(viewLifecycleOwner) {
                bind.frontSideImage.setImageBitmap(
                    convertBitmapFromImageProxy(
                        mViewModel.imageProxyFront.value!!,
                        bind.cameraView,
                        requireActivity().windowManager.defaultDisplay.width,
                        requireActivity().windowManager.defaultDisplay.height
                    )
                )
                bind.scanCardSubtitle.text = getString(R.string.fmt_scan_card_photo_your_back_card)
            }

            mViewModel.imageProxyBack.observe(viewLifecycleOwner) {
                bind.backSideImage.setImageBitmap(mViewModel.imageProxyBack.value!!.toBitmap())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
