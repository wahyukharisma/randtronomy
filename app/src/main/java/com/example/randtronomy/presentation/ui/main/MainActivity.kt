package com.example.randtronomy.presentation.ui.main

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.randtronomy.databinding.ActivityMainBinding
import com.example.randtronomy.util.*
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val tag : String = "AppDebug"
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getRandomAstronomy()

        with(binding){
            fab.setOnClickListener {
                shimmerLayout.startShimmer()
                shimmerLayout.show()
                viewModel.getRandomAstronomy()
            }

            viewModel.data.observe(this@MainActivity,{
                    data ->
                when(data) {
                    is ResultOfNetwork.Success -> {
                        val result = data.value[0]

                        tvDate.text = DateFormatLocale.dateWithDay(result.date)
                        tvTitle.text = result.title
                        tvDescription.text = result.explanation

                        Glide.with(this@MainActivity)
                            .load(result.url)
                            .listener(object: RequestListener<Drawable>{
                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    TODO("Not yet implemented")
                                }

                                override fun onResourceReady(
                                    resource: Drawable?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    shimmerLayout.stopShimmer()
                                    shimmerLayout.remove()
                                    return false
                                }

                            })
                            .into(ivContent)

                        Glide.with(this@MainActivity)
                            .load(result.url)
                            .apply(RequestOptions.bitmapTransform(BlurTransformation(10,3)))
                            .into(ivBackground)
                    }
                    is ResultOfNetwork.Failure -> {
                        Log.d( tag,data.message ?: "Unknown Error")
                    }
                }
            })
        }
    }
}