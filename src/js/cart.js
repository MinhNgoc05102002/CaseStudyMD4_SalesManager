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