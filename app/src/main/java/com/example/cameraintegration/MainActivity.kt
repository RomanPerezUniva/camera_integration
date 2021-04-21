package com.example.cameraintegration

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue


private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photoFile: File
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTakePicture.setOnClickListener {



            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)

            val fileProvider = FileProvider.getUriForFile(this, "com.example.fileprovider",
                photoFile )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider)
            if (takePictureIntent.resolveActivity(this.packageManager) != null){
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            }else{
                Toast.makeText(this, "Unanble to open camera",Toast.LENGTH_SHORT).show()
            }
        }


    }
    private  fun getPhotoFile(fileName: String): File{
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return  File.createTempFile(fileName,".jpg",storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenImage =  BitmapFactory. decodeFile(photoFile.absolutePath)
            imageView.setImageBitmap(takenImage)


/*
            val user = KPhoto(
                "Roman",


                R.drawable.grogu



            )
            startActivity(
                Intent(this,SecondActivity::class.java).apply {
                    putExtra("user",user,)

                }
            )*/

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}