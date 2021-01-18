package com.example.gb_material

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.gb_material.web.PictureOfTheDayData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.pod_fragment.*

class PictureOfTheDayFragment: Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pod_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_layout_wiki.setStartIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${et_wiki.text.toString()}")
            })
        }
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "URL картинки пустой", Toast.LENGTH_SHORT).show()
                } else {
                    //Отобразите фото
                    //showSuccess()
                    //Coil в работе: достаточно вызвать у нашего ImageView
                    //нужную extension-функцию и передать ссылку и заглушки для placeholder
                    eiv_pod.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_baseline_image_not_supported_24)
                        placeholder(R.drawable.ic_baseline_sync_24)
                    }
                }
                bottom_sheet_description_header.text = serverResponseData.title
                bottom_sheet_description.text = serverResponseData.explanation
            }
            is PictureOfTheDayData.Loading -> {
                //Отобразите загрузку
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {

            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}