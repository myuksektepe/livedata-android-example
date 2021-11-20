package com.muratlakodla.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.muratlakodla.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * Binding işlemi için nullable (boş bırakabilir) bir değişken oluşturuyoruz.
     */
    private var _binding: ActivityMainBinding? = null

    /**
     * Bütün atamalarda üsteki boş bırakılabilir değişken kullanılacak.
     * UI elementleri ile iletişime geçtiğimiz, onları yönettiğimiz
     * zamanlarda ise _binding'den ürettiğimiz non-nullable tipindeki binding
     * değişkenini kullanacağız.
     */
    val binding get() = _binding!!

    /**
     * İlgili activity/fragment için oluşturduğumuz ViewModel sınıfımız için
     * bir değişken oluşuruyoruz ve KTX'in sağladığı syntax ile tanımlama yapıyoruz.
     */
    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Binding için oluşturduğumuz değişkenimizi DataBindingUtil sınıfında yer alan
         * inflate methoduna gerekli değişkenleri göndererek dolduruyoruz.
         *
         * - layoutInflater: kısaca 'ekran düzeni' diye tanımlayabiliriz.
         * - layoutId: activity/fragment'imiza bağlı olan ekran tasarım dosyamız
         * - parent: direkt olarak bir layout'a yani ekran tasarım dosyasına ulaştığımız için parent kısmını null bırakıyoruz
         * - attactToParent: bir parent'ımız olmadığı için bu değeri de false olarak işaretliyoruz.
         */
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)

        /**
         * setContentView fonksiyonunu normalde layout'umuzu gönderdiğimiz fonksiyon olarak tanıyoruz
         * binding işleminde ise yukarıda içini doldurduğumuz değişkenimizin non-nullable halinde yer alan
         * root değişkenini gönderiyoruz.
         *
         * Böylece binding işlemi sonrası oluşan layout'umuzu görüntüleyebiliyor olacağız.
         */
        setContentView(binding.root)

        /**
         * Buraya kadar olan işlemlerimiz olmazsa olmaz işlemlerdi.
         *
         * Bu kısım ise bindig ile bağlandığımız layout'umuzun içinden viewModel'imize bağlanmamız
         * gereken durumlar için mecburidir. Layout incelediğiniz zaman şöyle bir değişken tanımlandığını
         * göreceksiniz;
         *
         *     <data>
         *         <variable
         *         name="viewModel"
         *         type="com.muratlakodla.livedata.MainActivityViewModel" />
         *     </data>
         *
         * Bu değişkenin tanımlanmasını şöyle yorumlayabiliriz. Bu layout'un içinde bir element, bir işlemi yapmak
         * ya da tetiklemek için viewModel'imize ulaşacak.
         *
         * Eğer layout'a bulunan butonu incelerseniz;
         *
         *             android:onClick="@{() -> viewModel.changeText()}"
         *
         * onClick olayında viewModel içindeki bir fonksiyonu tetiklediğini görebilirsiniz.
         *
         * İşte bu bağlantıyı yapmak için bindig'e bu değişkeni tanımlamamız gerekir.
         */
        _binding?.viewModel = viewModel


        /**
         * ViewModel'imizin içinde oluşturduğumuz LiveData tipindeki değişkenimizi
         * sürekli olarak gözlemleyecek olayı yaratıyoruz.
         *
         * Bu gözlemci yapısı ilgili değişkende bir değişiklik görürse içindeki kodları tetikletecek
         * yani bizim örneğimiz için ekrandaki TextView'in içindeki yazıyı güncelleyecek.
         */
        viewModel.textContent.observe(this, {
            binding.txtMain.text = it
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}