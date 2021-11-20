#Android Kotlin ViewModel ve Livedata

![Livedata](https://yuklio.com/f/n1sdc-livedata.png)
_Görsel Kaynak: https://www.youtube.com/watch?v=suC0OM5gGAA_  


Temiz kod mantığı ve bu mantık doğrultusunda geliştirilmiş mimari desenler bir çok yazılım diline
uygulanabildiği gibi Android uygulama geliştirmede de sıkça kullanılır.

Bu prensipler bir uygulamada farklı görevler için kullanılan kodların farklı sınıfların içerisinde 
olması gerektiğini savunur.

Android uygulama geliştirirken, Activity ve Fragment gibi sınıflarda sadece kullanıcının etkileşimde
olduğu aksiyonların kodlarının yazılması gerekir. Bu yüzden kullanıcının aksiyonlarının hazırlandığı,
UI'daki elementlerin manipüle edilmesinden önceki işlemleri ViewModel'lar içinde yazarız.

ViewModel'lar ile View'ler arasında anlık veri alış-verişi olması gereken durumlarda soket mantığıyla
çalışan, gözlemlenebilir, değişiklik olduğu zaman bizi haberdar eden bir yapıya ihtiyaç duyarız.

İşte LiveData tam olarak bunun için kullanılmaktadır.


MainActivity
```kotlin
    viewModel.textContent.observe(this, {
        binding.txtMain.text = it
    })
```

MainActivityViewModel
```kotlin
    private var _textContent = MutableLiveData<String>()
    val textContent: LiveData<String> get() = _textContent
```