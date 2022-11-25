const btnScrollTop = document.querySelector('.back-to-top') 
const Loader = document.querySelector('.loader__wrap')
const app={
    loadPage:function(){
        window.onload = function(){
            setTimeout(()=>{
                Loader.style.opacity = "0"
            },2000)
            Loader.style.display = "none"
        }
    },

    handleEvent:function(){
        btnScrollTop.addEventListener('click',()=>{
            console.log(window)
            window.scrollTo({
                top:0,
                behavior:'smooth'
            })
        })
    },

    start:function(){
        this.loadPage()

        this.handleEvent()
    }
}

app.start()