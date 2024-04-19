package com.example.nfctagreader.ui.fragments.card_scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FmtScannerCardScreenBinding
import com.example.nfctagreader.ui.base.BaseFragment
import com.example.nfctagreader.ui.fragments.sign_in.SignInViewModel
import com.example.nfctagreader.view_model.DaggerViewModelFactory
import javax.inject.Inject


class ScanCardFragment : BaseFragment() {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<ScanCardViewModel>() {
        daggerViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

   /* @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    @ExperimentalGetImage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (cardViewModel.checkPermissionCamera(this.requireContext())) {
            cardViewModel.scannerCameraInitialize(
                this.requireContext(),
                this.view,
                this as LifecycleOwner,
                binding.root.findViewById(R.id.previewView),
                ProcessCameraProvider.getInstance(this.requireContext())
            )

        } else {
            cardViewModel.requestPermissionCamera(this.requireActivity())
            cardViewModel.scannerCameraInitialize(
                this.requireContext(),
                this.view,
                this as LifecycleOwner,
                binding.root.findViewById(R.id.previewView),
                ProcessCameraProvider.getInstance(this.requireContext())
            )
        }
        binding.createPhotoImageButton.setOnClickListener {
            *//*  try {
                  lifecycle.coroutineScope.launchWhenResumed {
                     val imageProxy =
                          ScannerCamera.imageCapture.takePicture(Executors.newSingleThreadExecutor())
                      scanCardViewModel.extractDataUseCaseViewModel(
                          imageProxy,
                          imageProxy.imageInfo.rotationDegrees
                      )
                      var resultScanning =
                          scanCardViewModel.extractDataFormScannerCamera(scanCardViewModel.scanningTextMutableStateFlow.value!!)
                      bindCardDetails(resultScanning)
                      binding.cardDataTextView.text =
                          "Номер карты: ${resultScanning.number}\nСрок действия (месяц/год): ${resultScanning.expirationMonth}/${resultScanning.expirationYear}"
                      imageProxy.close()
                  }
              } catch (e: ActivityNotFoundException) {
                  Toast.makeText(this.requireContext(), e.message, Toast.LENGTH_SHORT).show()
              } *//*
        }
    }

    suspend fun ImageCapture.takePicture(executor: Executor): ImageProxy {
        return suspendCoroutine { continuation ->

            takePicture(executor, object : ImageCapture.OnImageCapturedCallback() {

                override fun onCaptureSuccess(image: ImageProxy) {
                    continuation.resume(image)
                    super.onCaptureSuccess(image)
                }

                override fun onError(exception: ImageCaptureException) {
                    continuation.resumeWithException(exception)
                    super.onError(exception)
                }
            })
        }
    }

    private fun bindCardDetails(card: CardDetails) {
        card.owner = card.owner
        card.number = card.number
        card.expirationMonth = "${card.expirationMonth}"
        card.expirationYear = "${card.expirationYear}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}