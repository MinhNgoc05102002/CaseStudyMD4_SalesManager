// Product

function closeAddProduct() {
    document.getElementById("productID").value = "";
    document.getElementById("name_product_input").value = "";
    document.getElementById("price_in").value = "";
    document.getElementById("price_out").value = "";
    document.getElementById("product_number").value = "";
    document.getElementById("product_description").value = "";

    document.getElementById("add_product_image").value = "";
    document.getElementById("image_product_input").src = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png";

    let inputTag = document.getElementsByClassName('catechoice')
    for (let i = 0; i < inputTag.length; i++) {
        inputTag[i].checked = false;
    }

    document.getElementById("modalProduct").style.display = "none";

}

function showUpdateProduct(productID, name, pricein, priceout, description, number, image, category) {
    let addButton = document.getElementById('addProductButton')
    let editButton = document.getElementById('editProductButton')


    document.getElementById("productID").value = productID;

    document.getElementById("name_product_input").value = name;
    document.getElementById("price_in").value = pricein;
    document.getElementById("price_out").value = priceout;
    document.getElementById("product_number").value = number;
    document.getElementById("product_description").value = description;

    changeImageSource(image)
    let id = productID.trim()
    if (id == "") {
        editButton.style.display = 'none';
        addButton.style.display = 'inline';
    } else {
        document.getElementById("add_product_image").value = image;
        document.getElementById("image_product_input").value = image;

        alert(category.size);
        // let categoryList = category.split(',');
        // if(categoryList.length > 0) {
        //     for(let i = 0; i < categoryList.length; i++) {
        //         console.log(categoryList[i])
        //         if(document.getElementById(categoryList[i]) != null) document.getElementById(categoryList[i]).checked = true;
        //     }
        // }

        // document.getElementById('content_blog_input').value = content

        addButton.style.display = 'none';
        editButton.style.display = 'inline';
    }
    document.getElementById("modalProduct").style.display = "block";
}

function changeImageSource(source) {
    let productImage = document.getElementById('add_product_image')
    if (source == "") {
        productImage.src = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"
    } else {
        productImage.src = source
    }
}

// Category

function closeAddCate() {
    document.getElementById("categoryID").value = "";
    document.getElementById("name_category_input").value = "";
    document.getElementById("category_description").value = "";

    document.getElementById("modalCategory").style.display = "none";

}

function showUpdateCategory(cateID, name, description) {
    let addButton = document.getElementById('addCategoryButton')
    let editButton = document.getElementById('editCategoryButton')


    document.getElementById("categoryID").value = cateID;
    document.getElementById("name_category_input").value = name;
    document.getElementById("category_description").value = description;

    let id = cateID.trim()

    if (id == "") {
        editButton.style.display = 'none';
        addButton.style.display = 'inline';
    } else {
        document.getElementById("form_category").action = "/categories/edit/" + id;
        addButton.style.display = 'none';
        editButton.style.display = 'inline';
    }
    document.getElementById("modalCategory").style.display = "block";
}