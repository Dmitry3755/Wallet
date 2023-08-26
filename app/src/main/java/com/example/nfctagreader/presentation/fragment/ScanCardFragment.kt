package com.example.nfctagreader.presentation.fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FragmentMainScreenBinding
import com.example.nfctagreader.domain.model.ScannerCamera
import com.example.nfctagreader.domain.model.data_class.CardDetails
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class ScanCardFragment : Fragment() {

    private val scanCardViewModel: ScanCardViewModel by viewModels()
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.root.setViewTreeLifecycleOwner(this.viewLifecycleOwner)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalGetImage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (scanCardViewModel.checkPermissionCamera(this.requireContext())) {
            scanCardViewModel.scannerCameraInitialize(
                this.requireContext(),
                this.view,
                this as LifecycleOwner,
                binding.root.findViewById(R.id.previewView),
                ProcessCameraProvider.getInstance(this.requireContext())
            )

        } else {
            scanCardViewModel.requestPermissionCamera(this.requireActivity())
            scanCardViewModel.scannerCameraInitialize(
                this.requireContext(),
                this.view,
                this as LifecycleOwner,
                binding.root.findViewById(R.id.previewView),
                ProcessCameraProvider.getInstance(this.requireContext())
            )
        }
        binding.createPhotoImageButton.setOnClickListener {

            /*    val viewModelObserver = Observer<String> { newName ->
                    binding.cardDataTextView.text = newName
                }
                scanCardViewModel.scanningTextMutableStateFlow.observe(
                    this.viewLifecycleOwner,
                    viewModelObserver
                )*/

            try {
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
            }
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
    }

    companion object {
    }
}