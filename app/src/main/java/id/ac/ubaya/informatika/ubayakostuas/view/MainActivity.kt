package id.ac.ubaya.informatika.ubayakostuas.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import id.ac.ubaya.informatika.ubayakostuas.R
import id.ac.ubaya.informatika.ubayakostuas.model.Kost
import id.ac.ubaya.informatika.ubayakostuas.model.KostDatabase
import id.ac.ubaya.informatika.ubayakostuas.model.User
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.ubayakostuas.viewmodel.UserViewModel
import android.content.Context
import android.widget.Toast
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel:DetailViewModel
    private lateinit var viewModelUser:UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedFile = "id.ac.ubaya.informatika.ubayakostuas"
        sharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var loginStatus = sharedPreferences.getBoolean("login",false)

        if (loginStatus == false) {
            navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
            navController.navigate(R.id.action_itemHome_to_loginFragment)
        }

        drawerLayout = findViewById(R.id.drawerLayout)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        var navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView,navController)

        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setupWithNavController(navController)

//        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//        val kostList = listOf(
//            Kost("Dream House","Jl. Kedung Asem 121",800000,50000,"https://swamediainc.storage.googleapis.com/swa.co.id/wp-content/uploads/2022/07/29174023/kost.jpg","Kasur, lemari, meja","Wanita","Boleh","Kost murah dan nyaman, dengan kawasan yang strategis","dreamhouse@gmail.com","081475165849",20,16),
//            Kost("Cozy Corner","Jl. Rungkut Mejoyo Utara 11",600000,400000,"https://binabangunbangsa.com/wp-content/uploads/2020/03/tips-Manajemen-Rumah-Kost-yang-Baik-dan-Benar-.jpg","Kasur, TV, meja, kamar mandi luar","Pria","Tidak","Kost dekat ubaya","cozycorner@gmail.com","08978451647",12,10),
//            Kost("Artistic Abode","Jl. Tenggilis Kauman 2",650000,400000,"https://cf.bstatic.com/xdata/images/hotel/max1024x768/275364746.jpg?k=bee2233bb1e094152b8b70e6bcbfb92029f252feb89cc80290f86bb748a2cfd9&o=&hp=1","Kasur, wifi, meja, kamar mandi dalam","Wanita","Boleh","Kost harga terjangkau dengan fasilitas lengkap","artisticabode@gmail.com","087842184684",16,11),
//            Kost("The Pastel Palace","Jl. Nginden Barat 31",875000,600000,"https://www.rukita.co/stories/wp-content/uploads/2021/10/kost-rukita-di-jakarta-selatan-dengan-jendela.jpg","Kasur, meja, kursi, kamar mandi dalam","Wanita","Boleh","Kost dengan fasilitas lengkap dan di kawasan elite","pastelpalace@gmail.com","081975426845",14,8),
//            Kost("The Garden Room","Jl. Mejoyo Indah 133",1200000,800000,"https://cdn.rukita.co/rukita/fit/800x500/media/buildings/building/98ffd4c9-a7b.jpg","Kasur, wifi, AC, lemari, meja, kamar mandi dalam","Wanita","Tidak","Kost dekat dengan Ubaya dan Transmart Rungkut","gardenroom@gmail.com","0821478564982",15,13),
//            Kost("The Bohemian House","Jl. Jemursari Regency AA 26",900000,650000,"https://apollo-singapore.akamaized.net/v1/files/hyfy5cjige4n2-ID/image;s=850x0","Kasur, meja, kamar mandi luar","Pria","Tidak","Kost dengan lingkungan yang asri","bohmianhouse@gmail.com","087779825141",10,6),
//            Kost("The Retro Residence","Jl. Panjang Jiwo Permai 25",700000,500000,"https://cdn1-production-images-kly.akamaized.net/bRlJWmcbisPKVhH1vxjr0d5bUQs=/1200x675/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/861628/original/073424800_1429960385-3.JPG","Kasur, wifi, kamar mandi luar","Pria","Tidak","Kost murah dan harga terjangkau","retroresidence@gmail.com","08298871682",8,3),
//            Kost("The Minimalist Loft","Jl. Tenggilis Utara 214",800000,550000,"https://blog-images.reddoorz.com/uploads/image/file/9920/rumah-kost-koolkost-near-university-of-indonesia.jpg","Kasur, meja, kursi","Wanita","Tidak","Kost dengan jaminan fasilitas sangat nyaman","minimalistloft@gmail.com","084974984981",14,9),
//            Kost("he Green House","Jl. Rungkut Industri II",1000000,750000,"https://tempat-kost.com/images1/1499_2.jpg","Kasur, AC, meja, kamar mandi dalam","Pria","Tidak","Kost baru dan eksklusif di kawasan yang strategis","greenhouse@gmail.com","082134851654",6,5),
//            Kost("The Scandinavian Suite","Jl. Rungkut Mejoyo A 21",900000,500000,"https://cdn.rukita.co/rukita/fit/800x500/media/buildings/building/1866e227-8df.jpg","Kasur, wifi, meja, lemari","Wanita","Boleh","Kost dekat ubaya hanya butuh 5 Menit!","scandinaviansuite@gmail.com","089412577564",13,11),
//            Kost("The Sunny Side","Jl. Tenggilis Raya 131",1450000,1000000,"https://apollo-singapore.akamaized.net/v1/files/5ehffab7nx4o3-ID/image;s=850x0","Kasur, wifi, meja, lemari, AC, kamar mandi dalam, TV","Wanita","Boleh","Kost luas dengan fasilitas super lengkap","sunnyside@gmail.com","087461564497",7,5),
//            Kost("The Blue Lagoon","Jl. Mejoyo 94",600000,450000,"https://idkos.com/images/gallery_property/standar-17.jpeg","Kasur, kamar mandi dalam","Pria","Tidak","Kost murah dan kawasan yang strategis","bluelaogon@gmail.com","08124658794",16,10)
//        )
//        viewModel.addKost(kostList)
//
//        viewModelUser = ViewModelProvider(this).get(UserViewModel::class.java)
//        val user = listOf(
//            User("Budi", "budi@gmail.com", "08487854688", "budi", "budi123", 1)
//        )
//        viewModelUser.addUser(user)

        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemLogout -> {
                    navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
                    navController.navigate(R.id.action_itemHome_to_loginFragment)

                    val editor = sharedPreferences.edit()
                    editor.remove("login")
                    editor.apply()
                    true
                }
                else -> false
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerLayout)
                || navController.navigateUp()
    }
}