package com.muratlakodla.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    /**
     * _textContent isminde String tipinden baz alınan MutableLiveData (değiştirilebilir canlı veri)
     * tipinde bir değişken oluşturuyoruz.
     *
     * Tıpkı Activity/Fragment içinde yaptığımız gibi bütün tanımlama işlemlerini bu değişken üzerinde
     * yapacağız.
     */
    private var _textContent = MutableLiveData<String>()

    /**
     * Üste oluşturduğumuz değiştieilebilir değişkeni sadece canlı veri tipine çeviriyoruz.
     * Böylece observable yani gözlemlenebilir hale getiriyoruz.
     */
    val textContent: LiveData<String> get() = _textContent

    /**
     * Bu fonksiyon sadece MutableLiveData tipindeki değişkenin içeriğini değiştiriyoruz
     * ve bunu yaparken postValue fonksiyonunu kullanıyoruz.
     */
    fun changeText() {
        val textList = listOf<String>(
            "Selam Dünya!",
            "Hello World!",
            "Ciao Mondo!",
            "مرحبا بالعالم",
            "Salve Mundi!",
            "Sveika Pasaule!",
            "Здраво свету!",
            "Hej Världen!",
            "こんにちは世界!",
        )
        _textContent.postValue(textList[(0..textList.size-1).random()])
    }
}