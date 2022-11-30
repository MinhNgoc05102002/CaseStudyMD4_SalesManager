function minusNumber(id) {
    let number = document.getElementById(id).innerHTML;
    number = parseInt(number);
    number--;
    document.getElementById(id).innerHTML = number;

}

function plusNumber(id) {
    let number = document.getElementById(id).innerHTML;
    number = parseInt(number);
    number++;
    document.getElementById(id).innerHTML = number;

}
function closeOrder() {
    document.getElementById("order-address").value = "";
    document.getElementById("modalOrder").style.display = "none";
}

function showModalOrder() {

    document.getElementById("modalOrder").style.display = "block";
}