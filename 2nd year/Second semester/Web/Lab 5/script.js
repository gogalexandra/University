$("#slideshow > div:gt(0)").hide();
let pause = false

let slideTime = setInterval(function() {
    if (!pause){
        $('#slideshow > div:first')
            .fadeOut(1000)
            .next()
            .fadeIn(1000)
            .end()
            .appendTo('#slideshow');
    }
}, 3000);

$('#slideshow > div > img').click( function () {
    if (pause){
        $('#slideshow > div > img').css( {width: 300, height: 250})
    }
    else {
        $('#slideshow > div > img').css(  { "display": "block", "margin": "auto", width: 600, height: 500})
    }
    pause = !pause
});