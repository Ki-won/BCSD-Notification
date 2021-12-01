package com.example.bcsd_project01


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private lateinit var transaction: FragmentTransaction

    //메인엑티비티 내에서 가변되는 카운팅 변수
    var countNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        fun createNotificationChannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.notification_title)
                val descriptionText = getString(R.string.notification_text_main)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("RandomNotification", name, importance).apply {
                    description = descriptionText
                }

                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transaction= fragmentManager.beginTransaction()
       // transaction.add<CutFrag>(R.id.frame_Layout_Main)
        //transaction.commit()
        //mainActivity의 레이아웃주소들
        val toastBtnId = findViewById<Button>(R.id.button_toast)
        val countBtnId = findViewById<Button>(R.id.button_count)
        val randomBtnId = findViewById<Button>(R.id.button_random)
        val countingText = findViewById<TextView>(R.id.show_counting_Text)

        //각 버튼별 기능
        toastBtnId.setOnClickListener {
            // 다이얼로그 추가
            val getDlg: AlertDialog.Builder = AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth)
            getDlg.setTitle("다이얼로그활성화")
            getDlg.setMessage("하나를 선택해주세요.\n 카운트초기화 \n toast출력 \n 종료")

            getDlg.setPositiveButton("초기화", DialogInterface.OnClickListener {dialog, which ->
                countNumber = 0
            })
            getDlg.setNeutralButton("출력", DialogInterface.OnClickListener {dialog, which ->
                Toast.makeText(this@MainActivity, " 토스트먹고싶다", Toast.LENGTH_SHORT).show()
            })
            getDlg.setNegativeButton("프래그먼트 실행",DialogInterface.OnClickListener {dialog, which ->

            })
            getDlg.show()
        }

        countBtnId.setOnClickListener {
            countNumber++
            countingText.text = countNumber.toString()
        }

        randomBtnId.setOnClickListener {
       var notification: NotificationCompat.Builder? = NotificationCompat.Builder(this, "RandomNotification")
           .setContentTitle(getString(R.string.notification_title))
           .setContentText(getString(R.string.notification_text_main))
           .setSmallIcon(android.R.drawable.ic_dialog_info)
           .setAutoCancel(true)

            NotificationManagerCompat(notify)
            }




        transaction.add(R.id.frame_Layout_Main, CutFrag())
        transaction.commit()

        }




    }
    fun driverNumber(): Int{
        return countNumber
    }
}