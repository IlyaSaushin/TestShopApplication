package com.earl.profile_feature.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.earl.profile_feature.databinding.FragmentProfileBinding
import com.earl.profile_feature.di.ProfileComponentProvider
import com.earl.utils.coreUi.MainBaseFragment
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import javax.inject.Inject

class ProfileFragment : MainBaseFragment<FragmentProfileBinding>() {

    @Inject lateinit var viewModel: ProfileViewModel
    var encodedImage: String? = null

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as ProfileComponentProvider).provideProfileComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener { logOut() }
        binding.changePhotoTv.setOnClickListener { changeUserImage() }
    }

    private fun logOut() {
        viewModel.removeUserFromLocalDb()
        navigator.signIn()
    }

    private fun changeUserImage() {
        val intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        pickImage.launch(intent)
    }

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val imageUri = result.data!!.data
                try {
                    val inputStream =
                        requireContext().contentResolver.openInputStream(
                            imageUri!!
                        )
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.userAvatar.setImageBitmap(bitmap)
                    encodedImage = encodeImage(bitmap)
                } catch (exception: FileNotFoundException) {
                    exception.printStackTrace()
                }
            }
        }
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    companion object {

        fun newInstance() = ProfileFragment()
    }
}