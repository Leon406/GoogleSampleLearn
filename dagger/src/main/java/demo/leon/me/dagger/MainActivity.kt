package demo.leon.me.dagger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import demo.leon.me.dagger.dagger.DaggerStudentComponent
import demo.leon.me.dagger.dagger.Student
import demo.leon.me.dagger.dagger.StudentModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var student: Student
    @Inject
    lateinit var student2: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerStudentComponent.builder()
            .studentModule(StudentModule(this))
            .build()
            .inject(this)
        btnShow.setOnClickListener {
            Toast.makeText(this, student.name, Toast.LENGTH_SHORT).show()
        }
    }
}
