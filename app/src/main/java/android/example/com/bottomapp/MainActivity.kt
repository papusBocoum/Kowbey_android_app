package android.example.com.bottomapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar

import android.util.SparseArray
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.layout_my_custom_view.*
import me.toptas.fancyshowcase.FancyShowCaseView
import me.toptas.fancyshowcase.listener.OnViewInflateListener
import android.view.WindowManager
import android.os.Build
import android.util.Log
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import androidx.recyclerview.widget.GridLayoutManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils

import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.AccelerateInterpolator
import android.view.animation.ScaleAnimation
import com.google.firebase.FirebaseApp

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private var mDocRef: DatabaseReference? = null

   var mFancyShowCaseView : FancyShowCaseView? = null

   var mClickListener: View.OnClickListener = View.OnClickListener {
        mFancyShowCaseView?.hide()
        mFancyShowCaseView = null
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home -> {
                val fragment = BlogFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, fragment.javaClass.getSimpleName()).addToBackStack(null)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.kowbey -> {
                val fragment = ChapterFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, fragment.javaClass.getSimpleName()).addToBackStack(null)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.moi-> {
                val fragment = StoreFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, fragment.javaClass.getSimpleName()).addToBackStack(null)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)








        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setSupportActionBar(findViewById(R.id.toolbar))
        //home navigation





        val fragment = BlogFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, fragment.javaClass.getSimpleName()).addToBackStack(null)
            .commit()

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()


        if (id == R.id.search) {


            showcase()

            return true
        }



        return super.onOptionsItemSelected(item)

    }



    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                with(fragment.childFragmentManager) {
                    if (backStackEntryCount > 0) {
                        popBackStack()
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }



fun showcase(){

    FirebaseApp.initializeApp(this);

    val ref = FirebaseDatabase.getInstance().getReference()
    var key = ref.child("posts").push().key
    if (key != null) {
        ref.child("posts").child(key).setValue("second")
            .addOnSuccessListener {
                Log.d("testing", "Finally we saved the user to Firebase Database")
            }
            .addOnFailureListener {
                Log.d("testing", "Failed to set value to database: ${it.message}")
            }
    }

    mFancyShowCaseView = FancyShowCaseView.Builder(this)

        .customView(R.layout.layout_my_custom_view, object : OnViewInflateListener {


            override fun onViewInflated(view: View) {
                findViewById<View>(R.id.btn_action_1).setOnClickListener(mClickListener)
            }
        })
        .enableTouchOnFocusedView(true)
        .backgroundColor(Color.parseColor("#F2F5F5F5"))

        .closeOnTouch(true)
        .build()
    mFancyShowCaseView!!.show()

}



    fun LaunchSecondActivity(view: View) {


        val intent = Intent(getApplicationContext(), DeuxiemeActivity::class.java)

        startActivity(intent)

    }


fun LaunchPublicationActivity(view: View){
    val intent = Intent(getApplicationContext(), Annonces::class.java)

    startActivity(intent)
}
    class BlogFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.home_fragment, container, false)
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
    val adapter = GroupAdapter<ViewHolder>()
            posts_recycler_view.adapter = adapter
            val lLayout = GridLayoutManager(context, 2)
            posts_recycler_view.layoutManager = lLayout;
            posts_recycler_view.setNestedScrollingEnabled(false);
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            adapter.add(PostItem())
            animate(animated_text)



        }

        fun animate(view: View) {
            var mAnimation = ScaleAnimation(
                0.3f,
                0.5f,
                0.3f,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.45f
            )
            mAnimation.setDuration(400)
            mAnimation.setRepeatCount(-1)
            mAnimation.setRepeatMode(Animation.REVERSE)
            mAnimation.setInterpolator(AccelerateInterpolator())
            mAnimation.setAnimationListener(object : Animation.AnimationListener {

                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {}

                override fun onAnimationRepeat(animation: Animation) {}
            })
            view.animation = mAnimation
        }

    }

    class PostItem:Item<ViewHolder>(){

        override fun bind(viewHolder: ViewHolder, position: Int) {

        }

        override fun getLayout(): Int {

            return R.layout.one_post_layout

        }
    }


    class ChapterFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_common, container, false)
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            tvCommon.text = "Chapter Fragment"

        }
    }


    class StoreFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_common, container, false)
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            tvCommon.text = "Blog Fragment"

        }
    }

    data class Post(val categorie: String, val titre: String, val description: String, val prix: Long,
                    val thumbnail: String)


}
