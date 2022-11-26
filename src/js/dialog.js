// Get the modal
var modal = document.getElementById("modalProduct");

// Get the button that opens the modal
var btn = document.getElementById("addnewProduct");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
function closeAddBlogDialog(){
    modal.style.display = "none";
}

function changeImageSource(source) {
    let productImage = document.getElementById('add_product_image')
    if (source=="") {
        productImage.src = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"
    }
    else {
        productImage.src = source
    }
}

